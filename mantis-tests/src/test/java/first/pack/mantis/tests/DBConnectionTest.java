package first.pack.mantis.tests;

import first.pack.mantis.model.UserData;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnectionTest {

    Connection conn = null;

    @Test
    public void testDBConnection() throws SQLException {
        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select id, username, email from mantis_user_table");
            List<UserData> users = new ArrayList<>();
            while (rs.next()) {
                users.add(new UserData()
                        .withId(rs.getInt("id"))
                        .withUsername(rs.getString("username"))
                        .withEmail(rs.getString("email")));
            }
            System.out.println(users);
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
