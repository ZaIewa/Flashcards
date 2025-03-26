package flashcards_p;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateSet {
    Connection con = null;
    Statement stmt = null;

    CreateSet() {}

    public void create(String name){
        try{
            // Driver required for connection to database
            Class.forName("com.mysql.jdbc.Driver");
            // Setting up the connection with url, user, password
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");

            // Creating an object that allows to insert SQL commands
            stmt = con.createStatement();

            // Creates new table and adds it's name to table "names" so it could be found easier.
            String sql = "CREATE TABLE `" + name +"` (`Id` INT NOT NULL AUTO_INCREMENT , `Polish_word` CHAR(50) NOT NULL , `English_word` CHAR(50) NOT NULL , PRIMARY KEY (`Id`))";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO names(Name)" + "VALUES('" + name + "')";
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
