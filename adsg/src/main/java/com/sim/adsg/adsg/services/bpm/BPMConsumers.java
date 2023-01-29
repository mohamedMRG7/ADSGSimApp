package com.sim.adsg.adsg.services.bpm;

import com.sim.adsg.adsg.pojo.bpm.common.BpmResponse;
import com.sim.adsg.adsg.pojo.bpm.common.TokenInfo;
import com.sim.adsg.adsg.pojo.bpm.common.invoke.BPMTask;
import com.sim.adsg.adsg.services.rest.RestCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class BPMConsumers {

    @Autowired
    private RestCaller restCaller;

    private final static String BASE_URL = "http://10.3.1.250:7004/bpmintegration/api/integration/";


    public String authenticate(String username , String password){
        Map<String,String> authData = new HashMap<>();
        authData.put("username", username);
        authData.put("password", password);
        String serviceUrl = "authenticate?username={username}&password={password}";
        BpmResponse service = null;
        try {
            service = (BpmResponse) restCaller.callRestService(BASE_URL + serviceUrl, HttpMethod.GET, "", BpmResponse.class, authData,null);
            TokenInfo token = service.getToken();
            System.err.println("inside service");
            return token.getToken();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public BpmResponse queryTasks(TokenInfo tokenInfo){
        String serviceUrl = "queryTasks";
        BpmResponse service = null;
        try {
            service = (BpmResponse) restCaller.callRestService(BASE_URL + serviceUrl, HttpMethod.POST, tokenInfo, BpmResponse.class, null,null);
            return service;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public boolean invokeAction(BPMTask bpmTask){
        String serviceUrl = "invoke";
        BpmResponse service = null;
        try {
            service = (BpmResponse) restCaller.callRestService(BASE_URL + serviceUrl, HttpMethod.POST, bpmTask, BpmResponse.class, null,null);
            return service.isStatus();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
