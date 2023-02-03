package com.example.adminapp.beans;

import com.example.adminapp.dao.LogDAO;
import com.example.adminapp.dao.UserDAO;
import com.example.adminapp.models.Log;
import com.example.adminapp.models.User;

import java.io.Serializable;
import java.util.List;

public class UserBean implements Serializable {

    public List<User> getAll() {
        return UserDAO.getAll();
    }
}