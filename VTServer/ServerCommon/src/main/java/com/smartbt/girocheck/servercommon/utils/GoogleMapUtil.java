package com.smartbt.girocheck.servercommon.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.smartbt.girocheck.servercommon.dao.StateDAO;
import com.smartbt.girocheck.servercommon.model.Address;
import com.smartbt.girocheck.servercommon.model.Merchant;
//import org.apache.cxf.jaxrs.client.WebClient;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet; 
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author rrodriguez
 */
public class GoogleMapUtil {
 
    private static final HttpClient httpClient = HttpClientBuilder.create().build();
    private static final String GOOGLE_MAP_BASE_URL = "http://maps.google.com/maps/api/geocode/json?address=";

    public static void main(String[] args) {
        String addressStr =  "5901 Peachtree Dunwoody Rd, Atlanta,GA";
        calculateMerchantLocation(null);
    }

    public static String calculateMerchantLocation(Merchant merchant) {

        String responseStr = "";
        String lat = "", lng = "";

        String addressStr = getAddressString(merchant);
         

        System.out.println("GoogleMapUtil:: " + merchant.getLegalName() + " :: " + addressStr);

        try {
            HttpGet getRequest = new HttpGet(GOOGLE_MAP_BASE_URL + addressStr.replaceAll(" ", "+"));

            HttpResponse httpResponse = httpClient.execute(getRequest);
            HttpEntity responseEntity = httpResponse.getEntity();

            String response = EntityUtils.toString(responseEntity);
            System.out.println("********* Request SEND SUCCESSFULLY*******************");// mapper.readValue(response.getEntity().getContent(), Response.class);
            System.out.println(response);

            JsonParser parser = new JsonParser();
            JsonObject json = (JsonObject) parser.parse(response);

            JsonArray results = json.getAsJsonArray("results");

            if (results != null && !results.isJsonNull() && results.size() > 0) {
                JsonElement result = results.get(0);

                JsonObject address = result.getAsJsonObject();

                JsonObject geometry = address.getAsJsonObject("geometry");

                if (geometry != null) {
                    JsonObject location = geometry.getAsJsonObject("location");

                    if (location != null) {
                        lat = location.get("lat").getAsString();
                        lng = location.get("lng").getAsString();
 
                        merchant.setLatitude(lat);
                        merchant.setLongitude(lng);
                    }
                }
            } else {
                System.out.println("null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (lat.isEmpty() || lng.isEmpty()) {
            responseStr = merchant.getLegalName() + ":" + addressStr + ",";
        }

        return responseStr;
    }

    private static String getAddressString(Merchant merchant) {

        Address address = merchant.getAddress();

        StringBuilder sb = new StringBuilder();
        if (address != null && address.getAddress() != null) {

            String state = address.getState().getAbbreviation();

            if (state == null) {
                state = StateDAO.get().getAbbreviationById(address.getState().getId());
            }

            sb.append(address.getAddress().replaceAll(" ", "+") + ",+" + address.getCity().replaceAll(" ", "+") + ",+" + state);
        }
        return sb.toString();
    }

}
