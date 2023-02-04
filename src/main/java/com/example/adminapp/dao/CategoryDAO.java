package com.example.adminapp.dao;

import com.example.adminapp.models.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SELECT_ALL = "SELECT * from category";
    private static final String SELECT_BY_ID = "SELECT * from category c WHERE c.id=?";
    private static final String SELECT_BY_NAME = "SELECT * from category c WHERE c.name=?";
    private static final String UPDATE = "UPDATE category c SET name=? WHERE c.id=?";
    private static final String INSERT = "INSERT INTO category (name, parent_category_id) VALUES(?, ?)";
    private static final String SELECT_PRODUCTS_BY_CATEGORY = "SELECT * FROM product p INNER JOIN product_has_category phc WHERE phc.category_id=? AND p.id=phc.product_id";
    private static final String DELETE_PRODUCT = "DELETE FROM product p WHERE p.id=?";
    private static final String DELETE_CATEGORY = "DELETE FROM category c WHERE c.id=?";

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

    public static boolean insert(Category category) {
        Connection connection = null;
        boolean result = false;
        try {
            connection = connectionPool.checkOut();
            PreparedStatement preparedStatement = DAOUtil.prepareStatement(connection, INSERT, false);
            preparedStatement.setString(1, category.getName());
            if (category.getParentCategoryId() == null)
                preparedStatement.setNull(2, Types.INTEGER);
            else
                preparedStatement.setInt(2, category.getParentCategoryId());
            result = preparedStatement.executeUpdate() == 1;
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return result;
    }

    public static Category getByName(String name) {
        Connection connection = null;
        ResultSet resultSet = null;
        List<Category> categories = new ArrayList<>();

        try {
            connection = connectionPool.checkOut();
            PreparedStatement preparedStatement = DAOUtil.prepareStatement(connection, SELECT_BY_NAME, false);
            preparedStatement.setString(1, name);
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

    public static void delete(int categoryId) {
        Connection connection = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.checkOut();
            PreparedStatement preparedStatement = DAOUtil.prepareStatement(connection, SELECT_PRODUCTS_BY_CATEGORY, false);
            preparedStatement.setInt(1, categoryId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int productId = resultSet.getInt("id");
                preparedStatement = DAOUtil.prepareStatement(connection, DELETE_PRODUCT, false);
                preparedStatement.setInt(1, productId);
                preparedStatement.executeUpdate();
            }
            preparedStatement = DAOUtil.prepareStatement(connection, DELETE_CATEGORY, false);
            preparedStatement.setInt(1, categoryId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
    }
}