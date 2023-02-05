package com.sim.adsg.adsg.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUUID = UUID.randomUUID().toString();
        request.setAttribute("requestUUID", requestUUID);
        if (log.isInfoEnabled()) {
            log.info("Request with id {} : {} {}",requestUUID,request.getMethod(), request.getRequestURI());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String requestUUID = (String) request.getAttribute("requestUUID");
        if (log.isInfoEnabled()) {
            log.info("Response status for request {} is : {}",requestUUID, response.getStatus());
        }
    }
}
