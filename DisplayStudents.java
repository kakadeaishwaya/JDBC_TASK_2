import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DisplayStudents {

    public static void main(String[] args) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        System.out.println("Name : AISHWARYA KAKADE Batch-210");

        try {
            // Step 1: Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Step 2: Establish Connection
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "system",
                    "PawarAR12"
            );

            // Step 3: Create Statement
            stmt = con.createStatement();

            // Step 4: Execute Query
            rs = stmt.executeQuery("SELECT * FROM students");

            // Step 5: Print in Tabular Format
            System.out.println("---------------------------------");
            System.out.println("ID\tName\t\tAge");
            System.out.println("---------------------------------");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");

                System.out.println(id + "\t" + name + "\t\t" + age);
            }

            System.out.println("---------------------------------");

        } catch (ClassNotFoundException e) {
            System.out.println("Oracle Driver not found");
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        } finally {
            // Step 6: Close Resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
