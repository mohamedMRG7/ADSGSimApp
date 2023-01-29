package com.sim.adsg.adsg.services.rest;

import org.springframework.http.HttpMethod;

import java.util.Map;

public interface RestCaller {

    Object callRestService(String url , HttpMethod httpMethod , Object request , Class<?> responseClass , Map<String,?> params , Map<String, String> requestHeaders);
}
