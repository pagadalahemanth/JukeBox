package com.niit.jukebox.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JukeBoxConnection {
    public static Connection getJukeBoxConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox","root","Hemanth@07");
        return connection;
    }
}
