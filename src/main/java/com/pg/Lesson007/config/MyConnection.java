package com.pg.Lesson007.config;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnection {

    private String dbms = "mysql";
    private String userName = "root";
    private String serverName = "localhost";
    private String password = "2agraboso3";
    private int portNumber = 3306;

    public java.sql.Connection getConnection() throws SQLException {

        java.sql.Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);
        connectionProps.put("serverTimezone", "UTC");

        conn = DriverManager.getConnection(
                "jdbc:" + this.dbms + "://" +
                        this.serverName +
                        ":" + this.portNumber + "/javatest",
                connectionProps);

        System.out.println("Connected to database");
        return conn;
    }
}
