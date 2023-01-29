package com.sim.adsg.adsg.services.rest;

import com.sim.adsg.adsg.exceptions.BaseException;
import com.sim.adsg.adsg.pojo.common.response.ApiBaseResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Slf4j
@Primary
@Service
public class RestTemplateCallerUtils implements RestCaller{

    @Override
    public Object callRestService(String url , HttpMethod httpMethod , Object request , Class<?> responseClass , Map<String,?> params , Map<String, String> requestHeaders) {
        Object response;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        if (requestHeaders!=null && requestHeaders.size()>0){
            for (Map.Entry<String, String> entry : requestHeaders.entrySet()) {
                headers.add(entry.getKey(),entry.getValue());
            }
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> entity = new HttpEntity<>(request, headers);
        ResponseEntity<?> exchange = restTemplate.exchange(url, httpMethod, entity, responseClass, params == null ? new HashMap<>() : params);
        if(exchange.getStatusCode() != HttpStatus.OK){
            throw new BaseException("Error While Calling Business Service",exchange.getStatusCode());
        }
        response = exchange.getBody();
        System.out.println("response = " + response);
        return response;
    }
}
