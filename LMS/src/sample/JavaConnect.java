import java.sql.*;

public class JavaConnect {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // Here, 'JavaTest' is database name, and 'root' is username and password
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javatest","root","root");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");

            while (rs.next()) {
                System.out.println(rs.getInt(1) + ", " + rs.getString(2) + ", " + rs.getString(3));
            }


            conn.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

}
