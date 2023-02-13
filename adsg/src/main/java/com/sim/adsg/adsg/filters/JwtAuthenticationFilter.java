package com.sim.adsg.adsg.filters;

import com.sim.adsg.adsg.services.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.err.println("inside doFilterInternal");
        // :todo Get Token from Header

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(7);

        // :todo Get username from token

        String username = jwtService.extractUsername(jwt);

        // :todo Get all user's data from DB using username

        // :todo Check Token is valid

        if (jwtService.isTokenValid(jwt)) {
            System.err.println("Token is valid");
        }
        filterChain.doFilter(request,response);
    }
}
