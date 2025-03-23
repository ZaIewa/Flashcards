package flashcards_p;

import java.sql.*;

public class GetSets {

    Connection con = null;
    Statement stmt = null;

    GetSets() {}

    public String[] get() throws SQLException {
        String[] sets = null;
        try {
            // Driver required for connection to database
            Class.forName("com.mysql.jdbc.Driver");
            // Setting up the connection with url, user, password
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");

            // Creating an object that allows to insert SQL commands
            stmt = con.createStatement();
            String sql = "Select Name from names";

            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();

            int setNumber = 0;
            while (rs.next()) {
                setNumber++;
            }

            sets = new String[setNumber];
            setNumber = 0;
            // Resets the cursor before first entry
            rs.beforeFirst();


            while (rs.next()) {
                sets[setNumber] = rs.getString("Name");
                System.out.println(rs.getString("Name"));
                setNumber++;
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
        return sets;
    }
}

