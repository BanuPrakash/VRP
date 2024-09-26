package com.visa.prj.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String user = req.getParameter("email");
       String pwd = req.getParameter("pwd");
       // validate user with pwd
       // if valid
        HttpSession session = req.getSession();
//        session.setMaxInactiveInterval(60*30);
        session.setAttribute("user", user);
        resp.sendRedirect("index.jsp");
    }
}
