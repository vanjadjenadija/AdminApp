package com.example.adminapp.beans;

import com.example.adminapp.dao.AdminDAO;
import com.example.adminapp.models.Admin;

import java.io.Serializable;

public class AdminBean implements Serializable {
    private boolean isLoggedIn = false;

    public boolean login(String username, String password) {
        Admin admin = AdminDAO.selectAdmin(username, password);
        if (admin != null) {
            isLoggedIn = true;
            return true;
        }
        return false;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}