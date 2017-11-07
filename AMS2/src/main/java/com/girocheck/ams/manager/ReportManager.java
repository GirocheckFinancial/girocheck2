package com.girocheck.ams.manager;
 
import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author @Roberto Rodriguez :: <RobertoSoftwareEngineer@gmail.com>
 */
@Service
public class ReportManager {

    @Autowired
    RestTemplate restTemplate;

    public List getReport(String url, String entity, String report, String params) { 
//        url += "/Front/AMS/pageID/" + entity +"/list?limit=0";
        String port = url.substring(url.lastIndexOf(":"));
        url = "http://localhost" + port + "/FrontAMS2/" + entity +"/" + entity +"/report";
        
        System.out.println("ReportManager:: params = " + params);
        System.out.println("ReportManager:: report = " + report);
        
        if(report != null){
            url += "?report=" + report;
        }
        if(params != null){
            url += "&params=" + params;
        }
           
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        URI uri =  UriComponentsBuilder.fromHttpUrl(url).build().encode().toUri();
        System.out.println("uri = " + uri.toString());
        
        ParameterizedTypeReference<HashMap<String, Object>> responseType = 
               new ParameterizedTypeReference<HashMap<String, Object>>(){};
           
        ResponseEntity<HashMap<String, Object>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType);
        
        System.out.println("ReportManager -> After restTemplate.exchange");
        
        HashMap<String, Object> map = response.getBody();
        
        System.out.println("map.size() = " + map.size());

        List list = (List)map.get("List");
        System.out.println("list.size() = " + list.size());
        
         
        return list;
    }
    
    public static void main(String[] args) {
        System.out.println( "http://localhost:8080".substring("http://localhost:8080".lastIndexOf(":")));
    }

}
