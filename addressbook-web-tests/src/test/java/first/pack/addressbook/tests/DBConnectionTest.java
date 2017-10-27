package first.pack.addressbook.tests;

import first.pack.addressbook.model.GroupData;
import first.pack.addressbook.model.Groups;
import org.testng.annotations.Test;

import java.sql.*;

public class DBConnectionTest {

    Connection conn = null;

    @Test
    public void testDBConnection() throws SQLException {
        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?" + "user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
            Groups groups = new Groups();
            while (rs.next()) {
                groups.add(new GroupData()
                        .withValue(rs.getInt("group_id"))
                        .withName(rs.getString("group_name"))
                        .withHeader(rs.getString("group_header"))
                        .withFooter(rs.getString("group_footer")));
            }
            System.out.println(groups);
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
