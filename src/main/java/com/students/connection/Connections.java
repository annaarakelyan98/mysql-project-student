package com.students.connection;

import com.students.config.Configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {
    private static Connection connection;

    private Connections() {
    }

    public static Connection getObj() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(Configs.getConfig("url"),
                        Configs.getConfig("user"), Configs.getConfig("password"));
            } catch (SQLException sqlException) {
                System.out.println("Connection failure");
            }
        }
        return connection;
    }
}
