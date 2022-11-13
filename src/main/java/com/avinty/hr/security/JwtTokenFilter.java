package com.avinty.hr.security;

import liquibase.repackaged.org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.avinty.hr.controller.AuthController.LOGIN_URI;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenUtil jwtUtil;
 
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getServletPath().equalsIgnoreCase(LOGIN_URI)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (!hasAuthorizationBearer(request)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No JWT token provided (sadly)");
            return;
        }
 
        String token = getAccessToken(request);
 
        if (jwtUtil.validateAccessToken(token)) {
            filterChain.doFilter(request, response);
            return;
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token invalid (unfortunately)");
        }
 
        setAuthenticationContext(token, request);
        filterChain.doFilter(request, response);
    }
 
    private boolean hasAuthorizationBearer(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
            return false;
        }
 
        return true;
    }
 
    private String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.split(" ")[1].trim();
        return token;
    }
 
    private void setAuthenticationContext(String token, HttpServletRequest request) {
        UserDetails userDetails = getUserDetails(token);
 
        UsernamePasswordAuthenticationToken
            authentication = new UsernamePasswordAuthenticationToken(userDetails, null, null);
 
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
 
    private UserDetails getUserDetails(String token) {
        MyUserPrincipal userDetails = new MyUserPrincipal();
        Long jwtSubject = Long.valueOf(jwtUtil.getSubject(token));
 
        userDetails.setId(jwtSubject);

        return userDetails;
    }
}