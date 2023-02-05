package com.example.clinica.repository;

import java.sql.Connection;
import java.sql.ResultSet;

public class DBUtils {

    public static void closeConnection(Connection connection){
        try{
            connection.close();
        }
        catch(Exception e){
            System.out.println("Connection could not be closed!");
        }
    }

    public static void closeResultSet(ResultSet resultSet){
        try{
            resultSet.close();
        }
        catch(Exception e){
            System.out.println("Result set could not be closed!");
        }
    }

}
