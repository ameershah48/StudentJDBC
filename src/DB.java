import java.sql.Connection;
import java.sql.DriverManager;

public class DB{
    Connection con = null;
    String userid = "root";
    String password = "";
    String url = "jdbc:mysql://localhost/studentDB";

    public Connection Connector()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, userid, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return con;
    }

}
