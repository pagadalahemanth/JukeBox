package com.niit.jukebox.methods;

import com.mysql.cj.jdbc.ConnectionImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;

public class JukeBoxConnection {
    public static Connection getJukeBoxConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox","root","Hemanth@07");
        return connection;
    }
}
