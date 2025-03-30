package flashcards_p;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteSet {
    Connection con = null;
    Statement stmt = null;

    DeleteSet() {}

    public void delete(String name){
        try{
            // Driver required for connection to database
            Class.forName("com.mysql.jdbc.Driver");
            // Setting up the connection with url, user, password
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");

            // Creating an object that allows to insert SQL commands
            stmt = con.createStatement();

            // Drops table & deletes entry from table containing names of all sets
            String sql = "DROP TABLE IF EXISTS `" + name + "`";
            stmt.executeUpdate(sql);
            sql = "DELETE FROM names WHERE Name = '" + name + "'";
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
