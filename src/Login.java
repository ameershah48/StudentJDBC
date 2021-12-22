import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

import org.mindrot.jbcrypt.BCrypt;


public class Login extends JFrame
{
    Connection con;
    DB db;
    String sql;
    PreparedStatement ps;
    ResultSet rs;

    public String checkLogin(String username, String password)
    {
        try {
            String getID = null;
            String getUsername = null;
            String getPassword = null;

            ps = con.prepareStatement("select * from students where username=?");
            ps.setString(1, username);  
            rs = ps.executeQuery();

            while (rs.next()) {
                getID = rs.getString("id");
                getUsername = rs.getString("username");
                getPassword = rs.getString("password");
            }

            if (getID != null) {
                if (BCrypt.checkpw(password, getPassword)){
                    return ("Logged in as: " + getUsername);
                } 
                else {
                    return "Password is invalid!";
                }
            } else {
                return "Username is invalid!";
            }

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Login(String str)
    {
        //Set title
        super(str);

        db = new DB();
        con = db.Connector();
        
        //Add username label and text field
        JLabel lblUsername = new JLabel("Username:");  
        lblUsername.setBounds(190, 70, 80, 20);
        JTextField userTF = new JTextField();  
        userTF.setBounds(280, 70, 120, 20);
        
        //Add password label and password field
        JLabel lblPassword = new JLabel("Password:");  
        lblPassword.setBounds(190, 100, 80, 20);
        JPasswordField passPF = new JPasswordField();  
        passPF.setBounds(280, 100, 120, 20);
        
        //Add Login button
        Icon icon1 = new ImageIcon("src/icon/tick.png");
        JButton logBtn = new JButton("Login",icon1);
        logBtn.setBounds(300, 140, 100, 40);
        
        //Add Cancel button
        Icon icon2 = new ImageIcon("src/icon/cross.png");
        JButton cancelBtn = new JButton("Cancel",icon2);
        cancelBtn.setBounds(190, 140, 100, 40);
        
        //Add Image using label
        Icon icon3 = new ImageIcon("src/icon/lock.png");
        JLabel imglock = new JLabel(icon3);
        imglock.setBounds(20, 10, 200, 200);

        logBtn.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                String username = userTF.getText();
                String password = new String(passPF.getPassword());

                String login = checkLogin(username, password);
                System.out.println(login);
            }
        });
        
        //Add components to this frame
        this.add(lblUsername);
        this.add(lblPassword);
        this.add(userTF);
        this.add(passPF);
        this.add(logBtn);
        this.add(cancelBtn);
        this.add(imglock);        

        this.setLayout(new BorderLayout());
        this.setSize(500,280);
        this.setVisible(true);
    }
}