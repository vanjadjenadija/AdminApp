package com.example.adminapp.beans;

import com.example.adminapp.dao.CategoryDAO;
import com.example.adminapp.dao.LogDAO;
import com.example.adminapp.models.Category;
import com.example.adminapp.models.Log;

import java.io.Serializable;
import java.util.List;

public class CategoryBean implements Serializable {
    public List<Category> getAll() {
        return CategoryDAO.getAll();
    }
}