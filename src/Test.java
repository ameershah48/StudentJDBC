import java.sql.*;

public class Test {
    Connection con;
    String userid = "root";
    String password = "";
    String url = "jdbc:mysql://localhost/studentDB";

    Test() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, userid, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(int id, String name) {
        try {
            PreparedStatement stmt = con.prepareStatement("update students set name=? where id=?");
            stmt.setString(1, name);
            stmt.setInt(2, id);
    
            int i = stmt.executeUpdate();
            System.out.println(i + " records updated");
        } catch (Exception e) {
            System.out.println(e);
        }
       
    }

    public void delete(int id) {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from students where id=?");
            stmt.setInt(1, id);
    
            int i = stmt.executeUpdate();
            System.out.println(i + " records deleted");
        } catch (Exception e) {
            System.out.println(e);
        }
       
    }

    public void fetch() {
        try {
            PreparedStatement stmt = con.prepareStatement("select * from students");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
       
    }

    public void insert(String name, String username, String password) {
        try {
            PreparedStatement ps=con.prepareStatement("insert into students (`name`, `username`, `password`) values(?,?,?)");  
            ps.setString(1, name);  
            ps.setString(2, username);  
            ps.setString(3, password);  
            int i=ps.executeUpdate();  
            System.out.println(i+" records affected");  
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Test app = new Test();
        
        app.fetch();

        // app.insert("abu", "abu123", "password");
        // app.update(3, "abu123");
        app.delete(3);


        app.fetch();
    }
}