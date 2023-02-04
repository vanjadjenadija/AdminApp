package com.example.adminapp.dao;

import com.example.adminapp.models.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SELECT_ALL = "SELECT * from category";
    private static final String SELECT_BY_ID = "SELECT * from category c WHERE c.id=?";
    private static final String UPDATE = "UPDATE category c SET name=? WHERE c.id=?";

    private CategoryDAO() {
    }

    public static List<Category> getAll() {
        Connection connection = null;
        ResultSet resultSet = null;
        List<Category> categories = new ArrayList<>();

        try {
            connection = connectionPool.checkOut();
            PreparedStatement preparedStatement = DAOUtil.prepareStatement(connection, SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                categories.add(new Category(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getInt("parent_category_id")));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return categories;
    }

    public static Category getById(int updateCategoryId) {
        Connection connection = null;
        ResultSet resultSet = null;
        List<Category> categories = new ArrayList<>();

        try {
            connection = connectionPool.checkOut();
            PreparedStatement preparedStatement = DAOUtil.prepareStatement(connection, SELECT_BY_ID, false);
            preparedStatement.setInt(1, updateCategoryId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                categories.add(new Category(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getInt("parent_category_id")));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return categories.get(0);
    }

    public static boolean update(Category updateCategory) {
        Connection connection = null;
        boolean result = false;
        try {
            connection = connectionPool.checkOut();
            PreparedStatement preparedStatement = DAOUtil.prepareStatement(connection, UPDATE, false);
            preparedStatement.setString(1, updateCategory.getName());
            preparedStatement.setInt(2, updateCategory.getId());
            result = preparedStatement.executeUpdate() == 1;
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return result;
    }
}