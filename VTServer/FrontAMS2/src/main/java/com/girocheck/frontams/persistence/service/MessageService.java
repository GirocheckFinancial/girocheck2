/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.girocheck.frontams.persistence.service;

import com.girocheck.frontams.persistence.dao.ClientDAO;
import com.girocheck.frontams.persistence.dao.MobileClientDAO;
import com.girocheck.frontams.persistence.dao.MobileNotificationDAO;
import com.girocheck.frontams.persistence.dto.ClientDTO;
import com.girocheck.frontams.persistence.dto.MessageDTO;
import com.girocheck.frontams.persistence.dto.MobileClientDTO;
import com.smartbt.girocheck.servercommon.utils.SMSUtils;
import com.smartbt.girocheck.servercommon.utils.pushNotification.PushNotificationManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Criterion;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rrodriguez
 */
@Service
@Transactional
public class MessageService {

    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private MobileClientDAO mobileClientDAO;

    @Autowired
    private MobileNotificationDAO mobileNotificationDAO;

    public MessageDTO sendMessage(MessageDTO messageDTO, boolean isFromClientPage) {
        List<Integer> unsendClientIds = sendMessageToMobileClients(messageDTO, isFromClientPage);
        System.out.println("unsentToPushNotifications = " + unsendClientIds.size());

        List<ClientDTO> pureClients = new ArrayList<>();

        //Sending SMS to clients that didn't received Push Notifications
        if (unsendClientIds.size() > 0) {
            System.out.println("Calling getPureClients(messageDTO, unsendClientIds) by IDs " + Arrays.toString(unsendClientIds.toArray()));
            pureClients = getPureClients(messageDTO, unsendClientIds);
            System.out.println("Found " + pureClients + " using these IDs");
        }

        if (isFromClientPage) {
            System.out.println("Calling getPureClients(messageDTO, null)");
            pureClients.addAll(getPureClients(messageDTO, null));
        }

        Integer unsentToPureClients = 0;

        if (pureClients.size() > 0) {
            unsentToPureClients = sendMessageToPureClients(messageDTO, pureClients);
        }

        System.out.println("unsentToPureClients = " + unsentToPureClients);
        messageDTO.setMessagesNotSent(unsentToPureClients);
        messageDTO.setMessagesSent(pureClients.size() - unsentToPureClients);

        return messageDTO;
    }

    public List<ClientDTO> getPureClients(MessageDTO messageDTO, List<Integer> unsendClientIds) {

        Criteria criteria = clientDAO.buildCriteria();

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("telephone").as("telephone"))
                .add(Projections.property("firstName").as("firstName"));

        criteria.setProjection(projectionList)
                .setResultTransformer(Transformers.aliasToBean(ClientDTO.class));

       List<Criterion> expressions = new ArrayList<>();
        if (unsendClientIds == null) {
            expressions = messageDTO.getExpressions();
            //This is to avoid repeated messages
            criteria.add(Restrictions.eq("isMobileClient", false));
        } else {
            criteria.add(Restrictions.in("id", unsendClientIds));
        }
 
        expressions.add(Restrictions.eq("active", true));

        return clientDAO.list(criteria, expressions);
    }

    //Pure clients are the ones that are not Mobile Clients
    private Integer sendMessageToPureClients(MessageDTO messageDTO, List<ClientDTO> clients) {
        Integer unsentClients = 0;

        for (ClientDTO client : clients) {
            if (client.getTelephone() != null) {
                try { 
                    if (!SMSUtils.sendSMS("1" + client.getTelephone(), messageDTO.getText("en"))) {
                        unsentClients++;
                        messageDTO.addUnsentClientName(client.getFirstName());
                    }
                } catch (Exception e) {
                    unsentClients++;
                    messageDTO.addUnsentClientName(client.getFirstName());
                }
            } else {
                unsentClients++;
                messageDTO.addUnsentClientName(client.getFirstName());
            }
        }
        return unsentClients;
    }

    private List<Integer> sendMessageToMobileClients(MessageDTO messageDTO, boolean isFromClientPage) {
        List<Integer> unsentClients = new ArrayList<Integer>();

        Criteria criteria = mobileClientDAO.buildCriteria().add(Restrictions.eq("client.active", true));

        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("client.firstName").as("firstName"))
                .add(Projections.property("client.id").as("clientId"))
                .add(Projections.property("version").as("version"))
                .add(Projections.property("lang").as("lang"))
                .add(Projections.property("pushToken").as("pushToken"))
                .add(Projections.property("deviceType").as("deviceType"));

        String prefix = "client."; // isFromClientPage ? "" : 
       // List<Criterion> formattedExpressions = new ArrayList<>();
        if (isFromClientPage) { 
            //TODO Handle this in the view, in the Message page
             
//            Iterator<Map.Entry<String, Object>> it = messageDTO.getParamsMap().entrySet().iterator();
//            while (it.hasNext()) {
//                Map.Entry<String, Object> entry = it.next();
//                formatedParams.put(prefix + entry.getKey(), entry.getValue());
//            }
        } else {
          //  formattedExpressions = messageDTO.getParamsMap();
        }

        criteria.setProjection(projectionList)
                .setResultTransformer(Transformers.aliasToBean(MobileClientDTO.class));

        List<MobileClientDTO> result = mobileClientDAO.list(criteria, messageDTO.getExpressions());

        for (MobileClientDTO mobileClient : result) {
            if (mobileClient.getVersion() >= 3 && mobileClient.getPushToken() != null) {
                try {
                    mobileNotificationDAO.addNotification(mobileClient.getId(), messageDTO.getTitle(mobileClient.getLang()), messageDTO.getText(mobileClient.getLang()));
                    if (!sendPushNotification(mobileClient, messageDTO.getTitle(mobileClient.getLang()))) { 
                        unsentClients.add(mobileClient.getClientId());
                    }
                } catch (Exception e) {
                    unsentClients.add(mobileClient.getClientId());
                    e.printStackTrace();
                }
            } else {
                unsentClients.add(mobileClient.getClientId());
            }
        }

        messageDTO.setPushNotificationsNotSent(unsentClients.size());
        messageDTO.setPushNotificationsSent(result.size() - messageDTO.getPushNotificationsNotSent());

        return unsentClients;
    }

    private boolean sendPushNotification(MobileClientDTO mobileClientDTO, String text) {
        //text = text.replaceAll("[name]", mobileClientDTO.getFirstName());
        return PushNotificationManager.sendMessage(mobileClientDTO.getDeviceType(), mobileClientDTO.getPushToken(), mobileClientDTO.getLang(), text);
    }
}
