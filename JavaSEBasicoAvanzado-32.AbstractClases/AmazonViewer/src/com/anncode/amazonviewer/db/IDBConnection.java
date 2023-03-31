package com.anncode.amazonviewer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import static com.anncode.amazonviewer.db.DataBase.*;

public interface IDBConnection{
    default Connection connectToDB(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if(connection != null){
                System.out.println("Se establecio la conexión :)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}