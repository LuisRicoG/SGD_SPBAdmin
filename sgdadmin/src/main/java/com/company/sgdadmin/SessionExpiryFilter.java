/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.sgdadmin;

import org.springframework.security.authentication.CredentialsExpiredException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author the_d
 */
public class SessionExpiryFilter implements Filter {

    private final int maxSessionLifeInMinutes;

    private static final String SESSION_EXPIRED = "SESSION_EXPIRED";

    public SessionExpiryFilter(int maxSessionLifeInMinutes) {
        this.maxSessionLifeInMinutes = maxSessionLifeInMinutes;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession(false);

        String sessionId = ((HttpServletRequest) request).getRequestedSessionId();

        if (session == null && sessionId != null) {
            throw new CredentialsExpiredException(SESSION_EXPIRED);
        }
        if (session != null && hasExceededMaximumLife(session)) {
            session.invalidate();
            throw new CredentialsExpiredException(SESSION_EXPIRED);
        }
        chain.doFilter(request, response);
    }

    private boolean hasExceededMaximumLife(HttpSession session) {
        long sessionStartTimestamp = session.getCreationTime();
        long currentTimeStamp = System.currentTimeMillis();
        return (currentTimeStamp - sessionStartTimestamp) > minutesToMillis(maxSessionLifeInMinutes);
    }

    private int minutesToMillis(int timeInMinutes) {
        return timeInMinutes * 60 * 1000;
    }

    @Override
    public void destroy() {
    }
}
