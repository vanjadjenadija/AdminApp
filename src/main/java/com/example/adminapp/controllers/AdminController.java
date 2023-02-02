package com.example.adminapp.controllers;

import com.example.adminapp.beans.AdminBean;
import com.example.adminapp.beans.LogBean;
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
    private static final String HOME = "/WEB-INF/pages/home.jsp";
    private static final String ERROR = "/WEB-INF/pages/error.jsp";
    private static final String USERS = "/WEB-INF/pages/users.jsp";
    private static final String CATEGORIES = "/WEB-INF/pages/categories.jsp";
    private static final String LOGS = "/WEB-INF/pages/logs.jsp";

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
        } else if (action.equals("sign-out")) {
            session.invalidate();
        } else if (action.equals("sign-in")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            AdminBean adminBean = new AdminBean();
            if (adminBean.login(username, password)) {
                session.setAttribute("adminBean", adminBean);
                LogBean logBean = new LogBean();
                session.setAttribute("logBean", logBean);
                address = LOGS;
            } else {
                session.setAttribute("notification", "Invalid credentials.");
            }
        } else {
            AdminBean adminBean = (AdminBean) session.getAttribute("adminBean");
            if (adminBean == null || !adminBean.isLoggedIn()) {
                address = SIGN_IN;
            } else {
                switch (action) {
                    case "users":
                        address = USERS;
                        break;
                    case "categories":
                        address = CATEGORIES;
                        break;
                    case "logs":
                        address = LOGS;
                        break;
                    default:
                        address = ERROR;
                        break;
                }
            }
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