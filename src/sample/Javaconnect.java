package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.io.IOException;

public class Javaconnect {
    public static Connection Connector() {
        Connection DB_Connection = null;
        // create a connection to the database
        try {
            Class.forName("org.sqlite.JDBC");
            DB_Connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            if (DB_Connection == null) System.exit(1);
            System.out.println("Connection to SQLite has been established.");

            return DB_Connection;
        } catch (Exception e) {
            System.out.println("Something went wrong with the connection to the SQL database.");
            return null;
        }
    }
}