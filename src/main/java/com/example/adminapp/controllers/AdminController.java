package com.example.adminapp.controllers;

import com.example.adminapp.beans.AdminBean;
import com.example.adminapp.beans.CategoryBean;
import com.example.adminapp.beans.LogBean;
import com.example.adminapp.beans.UserBean;
import com.example.adminapp.models.Category;
import com.example.adminapp.models.User;
import com.example.adminapp.models.enums.UserStatus;
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
    private static final String ERROR = "/WEB-INF/pages/error.jsp";
    private static final String USERS = "/WEB-INF/pages/users.jsp";
    private static final String ADD_USER = "/WEB-INF/pages/add-user.jsp";
    private static final String UPDATE_USER = "/WEB-INF/pages/update-user.jsp";
    private static final String CATEGORIES = "/WEB-INF/pages/categories.jsp";
    private static final String UPDATE_CATEGORY = "/WEB-INF/pages/update-category.jsp";
    private static final String ADD_CATEGORY = "/WEB-INF/pages/add-category.jsp";
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
                UserBean userBean = new UserBean();
                CategoryBean categoryBean = new CategoryBean();
                session.setAttribute("logBean", logBean);
                session.setAttribute("userBean", userBean);
                session.setAttribute("categoryBean", categoryBean);
                address = USERS;
            } else {
                session.setAttribute("notification", "Invalid credentials.");
            }
        } else {
            AdminBean adminBean = (AdminBean) session.getAttribute("adminBean");
            if (adminBean == null || !adminBean.isLoggedIn()) {
                address = SIGN_IN;
            } else {
                UserBean userBean = (UserBean) session.getAttribute("userBean");
                CategoryBean categoryBean = (CategoryBean) session.getAttribute("categoryBean");
                switch (action) {
                    case "users":
                        address = USERS;
                        break;
                    case "add-user":
                        address = ADD_USER;
                        if (request.getParameter("submit") != null) {
                            User user = new User(0, request.getParameter("firstName"), request.getParameter("lastName"),
                                    request.getParameter("username"), request.getParameter("password"),
                                    request.getParameter("email"), request.getParameter("phoneNumber"),
                                    request.getParameter("city"), request.getParameter("avatar"), UserStatus.ACTIVE.name());
                            if (userBean.addUser(user)) {
                                address = USERS;
                            }
                        }
                        break;
                    case "update-user":
                        address = UPDATE_USER;
                        int updateId = Integer.parseInt(request.getParameter("id"));
                        User updateUser = userBean.getById(updateId);
                        userBean.setUser(updateUser);

                        if (request.getParameter("submit") != null) {
                            User user = new User(updateId, request.getParameter("firstName"), request.getParameter("lastName"),
                                    request.getParameter("username"), request.getParameter("password"),
                                    request.getParameter("email"), request.getParameter("phoneNumber"),
                                    request.getParameter("city"), request.getParameter("avatar"), UserStatus.ACTIVE.name());
                            if (userBean.update(user)) {
                                address = USERS;
                            }
                        }
                        break;
                    case "delete-user":
                        int id = Integer.parseInt(request.getParameter("id"));
                        User user = userBean.getById(id);
                        user.setStatus(UserStatus.INACTIVE.name());
                        userBean.changeStatus(user);
                        address = USERS;
                        break;
                    case "categories":
                        address = CATEGORIES;
                        break;
                    case "update-category":
                        address = UPDATE_CATEGORY;
                        int updateCategoryId = Integer.parseInt(request.getParameter("id"));
                        Category updateCategory = categoryBean.getById(updateCategoryId);
                        categoryBean.setCategory(updateCategory);

                        if (request.getParameter("submit") != null) {
                            Category category = new Category(updateCategoryId, request.getParameter("name"), updateCategory.getParentCategoryId());
                            if (categoryBean.update(category)) {
                                address = CATEGORIES;
                            }
                        }
                        break;
                    case "add-category":
                        address = ADD_CATEGORY;
                        if (request.getParameter("submit") != null) {
                            String selectedParent = request.getParameter("parentCategory");
                            Integer parentId = null;
                            if (!selectedParent.equals("No parent category")) {
                                Category parentCategory = categoryBean.getByName(request.getParameter("parentCategory"));
                                parentId = parentCategory.getId();
                            }
                            Category category = new Category(0, request.getParameter("name"), parentId);
                            if (categoryBean.addCategory(category)) {
                                address = CATEGORIES;
                            }
                        }
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