package com.example.adminapp.dao;

import com.example.adminapp.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SELECT_ALL = "SELECT * from user";

    private UserDAO() {
    }

    public static List<User> getAll() {
        Connection connection = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();

        try {
            connection = connectionPool.checkOut();
            PreparedStatement preparedStatement = DAOUtil.prepareStatement(connection, SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("username"),
                        resultSet.getString("password"), resultSet.getString("email"),
                        resultSet.getString("phone_number"), resultSet.getString("city"),
                        resultSet.getString("avatar_url"), resultSet.getString("status")));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return users;
    }
}