package com.controller;

import com.entities.User;
import com.services.UserFacadeImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean isDataCorrect;

        User user = new UserFacadeImpl(User.class).login(username, password);
        isDataCorrect = !(user == null);
        if (isDataCorrect) {
            System.out.println(" ------------- We " + username + " sei loggato -------------------");
            request.getSession().setAttribute("user", user.getUsername());
            response.sendRedirect("ShowFilmServlet");
        } else {
            response.sendRedirect("LoginServlet");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("LoginFormPage.jsp");
    }
}
