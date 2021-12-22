import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.*;
import org.mindrot.jbcrypt.BCrypt;

public class Register extends JFrame {
    Connection con;
    DB db;
    String sql;

    public String storeStudent(String name, String username, String password, String confirmpassword) {
        if (!password.equals(confirmpassword)) {
            return "Password not match!";
        } else {
            sql = "INSERT INTO students (name, username, password) VALUES (?,?,?)";

            String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

            try {
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, name);
                stmt.setString(2, username);
                stmt.setString(3, hashed);
                int n = stmt.executeUpdate();

                if (n == 1) {
                    return "Register Success";
                } else {
                    return "Failed";
                }

            } catch (Exception e) {
                return e.getMessage();
            }
        }
    }

    public Register(String str) {
        // Set title
        super(str);

        db = new DB();
        con = db.Connector();

        // Add username label and text field
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(190, 70, 120, 20);
        JTextField userTF = new JTextField();
        userTF.setBounds(320, 70, 120, 20);

        // Add name label and text field
        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(190, 100, 120, 20);
        JTextField nameTF = new JTextField();
        nameTF.setBounds(320, 100, 120, 20);

        // Add password label and password field
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(190, 130, 120, 20);
        JPasswordField passPF = new JPasswordField();
        passPF.setBounds(320, 130, 120, 20);

        // Add password label and confirm password field
        JLabel lblConfirm = new JLabel("Confirm Password:");
        lblConfirm.setBounds(190, 160, 120, 20);
        JPasswordField confirmPF = new JPasswordField();
        confirmPF.setBounds(320, 160, 120, 20);

        // Add Login button with icon
        Icon icon1 = new ImageIcon("src/icon/tick.png");
        JButton regBtn = new JButton("Register", icon1);
        regBtn.setBounds(340, 190, 100, 40);

        // Add cancel button with icon
        Icon icon2 = new ImageIcon("src/icon/cross.png");
        JButton cancelBtn = new JButton("Cancel", icon2);
        cancelBtn.setBounds(190, 190, 100, 40);

        // Add Image using label
        Icon icon3 = new ImageIcon("src/icon/lock.png");
        JLabel imglock = new JLabel(icon3);
        imglock.setBounds(20, 10, 200, 200);

        regBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameTF.getText();
                String username = userTF.getText();
                String password = new String(passPF.getPassword());
                String confirmpassword = new String(confirmPF.getPassword());

                String register = storeStudent(name, username, password, confirmpassword);
                System.out.println(register);
            }
        });

        // Add components to this frame
        this.add(lblUsername);
        this.add(lblName);
        this.add(lblPassword);
        this.add(lblConfirm);
        this.add(userTF);
        this.add(nameTF);
        this.add(confirmPF);
        this.add(passPF);
        this.add(regBtn);
        this.add(cancelBtn);
        this.add(imglock);

        this.setLayout(new BorderLayout());
        this.setSize(520, 350);
        this.setVisible(true);
    }
}