package com.Accio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    static Connection con=null;
    public static Connection getConnect(){
        if(con!=null)
            return con;

        String user="root";
        String pass="Irfan@2001";
        String db="search_engine";
        return getConnect(user,pass,db);
    }

    private static Connection getConnect(String user, String pass, String db){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost/"+db+"?user="+user+"&password="+pass);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}