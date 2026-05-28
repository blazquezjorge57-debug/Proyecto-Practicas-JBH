package com.levelup.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.levelup.config.ConfigLoader;


public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                ConfigLoader.getDbUrl(),
                ConfigLoader.getDbUser(),
                ConfigLoader.getDbPassword()
        );
    }
}