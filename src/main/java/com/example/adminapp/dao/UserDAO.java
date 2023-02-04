package com.example.adminapp.dao;

import com.example.adminapp.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SELECT_ALL = "SELECT * from user";
    private static final String SELECT_BY_ID = "SELECT * FROM user u WHERE u.id=?";
    private static final String UPDATE_STATUS = "UPDATE user u SET status=? WHERE u.id=?";
    private static final String UPDATE_USER = "UPDATE user u SET first_name=?, last_name=?, username=?, password=?, email=?, phone_number=?, city=?, avatar_url=?, status=? WHERE u.id=?";
    private static final String INSERT = "INSERT INTO user (first_name, last_name, username, password, email, phone_number, city, avatar_url, status) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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

    public static User getById(int id) {
        Connection connection = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();

        try {
            connection = connectionPool.checkOut();
            PreparedStatement preparedStatement = DAOUtil.prepareStatement(connection, SELECT_BY_ID, false);
            preparedStatement.setInt(1, id);
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
        return users.get(0);
    }

    public static void updateUserStatus(Integer id, String status) {
        Connection connection = null;

        try {
            connection = connectionPool.checkOut();
            PreparedStatement preparedStatement = DAOUtil.prepareStatement(connection, UPDATE_STATUS, false);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
    }

    public static boolean insert(User user) {
        Connection connection = null;
        boolean result = false;
        try {
            connection = connectionPool.checkOut();
            PreparedStatement preparedStatement = DAOUtil.prepareStatement(connection, INSERT, false);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, passwordEncoder.encode(user.getPassword()));
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPhoneNumber());
            preparedStatement.setString(7, user.getCity());
            preparedStatement.setString(8, user.getAvatarUrl());
            preparedStatement.setString(9, user.getStatus());
            result = preparedStatement.executeUpdate() == 1;
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return result;
    }

    public static boolean updateUser(User user) {
        Connection connection = null;
        boolean result = false;
        try {
            connection = connectionPool.checkOut();
            PreparedStatement preparedStatement = DAOUtil.prepareStatement(connection, UPDATE_USER, false);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, passwordEncoder.encode(user.getPassword()));
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPhoneNumber());
            preparedStatement.setString(7, user.getCity());
            preparedStatement.setString(8, user.getAvatarUrl());
            preparedStatement.setString(9, user.getStatus());
            preparedStatement.setInt(10, user.getId());
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