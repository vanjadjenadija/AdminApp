package com.example.adminapp.dao;

import com.example.adminapp.models.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
    private static final String SELECT_ALL = "SELECT * from log";

    private LogDAO() {
    }

    public static List<Log> getAll() {
        Connection connection = null;
        ResultSet resultSet = null;
        List<Log> logs = new ArrayList<>();

        try {
            connection = connectionPool.checkOut();
            PreparedStatement preparedStatement = DAOUtil.prepareStatement(connection, SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                logs.add(new Log(resultSet.getInt("id"), resultSet.getString("message"),
                        resultSet.getString("level"), resultSet.getDate("date_time"),
                        resultSet.getString("logger")));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionPool.checkIn(connection);
        }
        return logs;
    }
}