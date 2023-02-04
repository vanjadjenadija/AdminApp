package com.example.adminapp.beans;

import com.example.adminapp.dao.CategoryDAO;
import com.example.adminapp.models.Category;

import java.io.Serializable;
import java.util.List;

public class CategoryBean implements Serializable {

    private Category category;

    public List<Category> getAll() {
        return CategoryDAO.getAll();
    }

    public Category getById(int updateCategoryId) {
        return CategoryDAO.getById(updateCategoryId);
    }

    public boolean update(Category updateCategory) {
        return CategoryDAO.update(updateCategory);
    }

    public boolean addCategory(Category category) {
        return CategoryDAO.insert(category);
    }

    public Category getByName(String name){
        return CategoryDAO.getByName(name);
    }

    public void delete(int categoryId){
        CategoryDAO.delete(categoryId);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}