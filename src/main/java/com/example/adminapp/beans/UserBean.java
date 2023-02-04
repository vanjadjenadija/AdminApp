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

    public User getById(int id){
        return UserDAO.getById(id);
    }

    public void changeStatus(User user) {
        UserDAO.changeMessageStatus(user.getId(), user.getStatus());
    }
}