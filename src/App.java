import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class App extends JFrame{
    App(String title)
    {
        //Set title
        super(title);

        //Initilizaed button login and register
        JButton btnLogin = new JButton("Login");
        JButton btnRegister = new JButton("Register");

        //Handle event on click for button login
        btnLogin.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                new Login("Login");
            }
        });

        //Handle event on click for button register
        btnRegister.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                new Register("Register");
            }
        });

        //Add components to this frame
        this.add(btnLogin);
        this.add(btnRegister);

        this.setLayout(new FlowLayout());
        this.setVisible(true);
        this.setSize(300, 100);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new App("App");
    }
}
