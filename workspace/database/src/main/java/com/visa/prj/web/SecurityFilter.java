package com.visa.prj.web;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(value = "*.jsp", dispatcherTypes = {
        DispatcherType.FORWARD,
        DispatcherType.REQUEST})
public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        System.out.println(req.getRequestURI() + " called !!!");
        // start time
        // get existing session
        HttpSession session = req.getSession(false);
        if(session != null && session.getAttribute("user") != null) {
            chain.doFilter(req, res);
        } else {
            res.sendRedirect("login.html");
        }
        // end time
    }
}
