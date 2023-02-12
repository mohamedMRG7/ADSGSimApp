package com.sim.adsg.adsg.filters;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.err.println("inside doFilterInternal");
        // :todo Get Token from Header

        // :todo Get username from token

        // :todo Get all user's data from DB using username

        // :todo Check Token is valid
        filterChain.doFilter(request,response);
    }
}
