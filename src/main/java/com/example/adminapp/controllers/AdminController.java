package com.example.adminapp.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "adminController", value = "/admin-controller")
public class AdminController extends HttpServlet {
    private static final String SIGN_IN = "/WEB-INF/pages/sign-in.jsp";

    public AdminController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String address = SIGN_IN;
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        session.setAttribute("notification", "");

        if (action == null || action.equals("")) {
            address = SIGN_IN;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}