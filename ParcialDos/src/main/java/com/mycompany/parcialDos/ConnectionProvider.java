/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.parcialDos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author benga
 */
public class ConnectionProvider {
    private static final String USER = "root";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/parcialdos";
    private static final String PASSWORD = "";
    public static Connection connection ;
    
    static {
        
        try{
            connection = DriverManager.getConnection(CONNECTION_URL, USER,PASSWORD); 
        }catch(SQLException e){
            System.out.println(e);
            System.exit(1);
        }
    }
    public static void close() throws SQLException {
        if (ConnectionProvider.connection != null && !ConnectionProvider.connection.isClosed()) {
            try {
                ConnectionProvider.connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
