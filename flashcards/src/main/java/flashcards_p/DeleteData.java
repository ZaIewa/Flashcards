package flashcards_p;

import java.sql.*;

public class DeleteData {
    Connection con = null;
    Statement stmt = null;

    DeleteData() {}

    public void delete(String tablename, String pl, String ang) throws SQLException {
        try {
            // Driver required for connection to database
            Class.forName("com.mysql.jdbc.Driver");
            // Setting up the connection with url, user, password
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");

            // Creating an object that allows to insert SQL commands
            stmt = con.createStatement();

            // Deletes a row from given table with matching words
            String sql = "DELETE FROM " + tablename + " WHERE English_word = '" + ang + "' AND Polish_word = '" + pl + "'";
            stmt.executeUpdate(sql);


        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    con.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }

            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
