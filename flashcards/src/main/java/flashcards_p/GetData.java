package flashcards_p;

import java.sql.*;
import java.sql.DriverManager;

public class GetData {
    Connection con = null;
    Statement stmt = null;

    GetData() {}

    public String[][] get() throws SQLException {
        String[][] data = null;
        try {
            // Driver required for connection to database
            Class.forName("com.mysql.jdbc.Driver");
            //System.out.println("Connecting to database...");
            // Setting up the connection with url, user, password
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
            //System.out.println("Connected");

            // Creating an object that allows to insert SQL commands
            stmt = con.createStatement();
            String sql = "Select pl, ang from fiszki";

            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println("querying SELECT pl, ang FROM fiszki");

            // Used to find out dimensions of array
            int columnsNumber = rsmd.getColumnCount();
            int rowsNumber = 0;
            while (rs.next()) {
                rowsNumber++;
            }

            data = new String[columnsNumber][rowsNumber];
            rowsNumber = 0;
            // Resets the cursor before first entry
            rs.beforeFirst();


            while (rs.next()) {
                data[0][rowsNumber] = rs.getString("pl");
                System.out.println(rs.getString("pl"));
                data[1][rowsNumber] = rs.getString("ang");
                System.out.println(rs.getString("ang"));
                rowsNumber++;
            }


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
        return data;
    }
}
