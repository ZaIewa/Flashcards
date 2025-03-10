package com.example.flashcards;

import java.sql.*;
import java.sql.DriverManager;

public class InsertData {
    Connection con = null;
    Statement stmt = null;

    InsertData() {}

    public void insert(String pl, String ang){
        try{
            // Driver required for connection to database
            Class.forName("com.mysql.jdbc.Driver");
            // Setting up the connection with url, user, password
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");

            // Creating an object that allows to insert SQL commands
            stmt = con.createStatement();
            String sql = "INSERT INTO fiszki(pl,ang)" + "VALUES('" + pl + "','" + ang + "')";
            stmt.executeUpdate(sql);
            
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null){
                    con.close();
                }
            }catch(SQLException se){
                se.printStackTrace();
            }

            try{
                if(con!=null){
                    con.close();
                }
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}
