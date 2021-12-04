//import Controller.loginController;
package GUIClasses;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import ControllerClasses.LoginController;

public class LoginGUI extends GUI{
  //private Logout logoutObj;
  private LoginController logoutObj;
  private Font normalFont = new Font("Courier", Font.PLAIN, 15);
  private JPanel loginPanel = new JPanel();
  private JTextField un = new JTextField();
  private JTextField pw = new JTextField();

  public LoginGUI(){

    
    
    JButton login = new JButton("Login");
    JButton guest = new JButton("Use as Guest");
    //GridLayout L = new GridLayout(4, 2, 20, 20);

    JLabel header = new JLabel("Login to Property Tracker!", JLabel.CENTER);
    header.setForeground(Color.BLACK);
    header.setFont(new Font("Courier", Font.PLAIN, 30));
    header.setBounds(10, 10 , 480, 40);
    panel.add(header);
    //frame.setContentPane(loginPanel);
    
    //panel.setLayout(L);


    /*JLabel username = new JLabel("Username:");
    username.setForeground(Color.BLACK);
    username.setFont(normalFont);
    panel.add(username);*/
   //panel.add(un);
    //username.setBounds(30, 50 , 480, 40);
    

    /*JLabel password = new JLabel("Password:");
    password.setForeground(Color.BLACK);
    password.setFont(normalFont);
    panel.add(password);
    password.setBounds(10, 50 , 480, 40);

    
    
    panel.add(login);
    panel.add(guest);*/
  }

  public void confirmLogin(){

  }
  public void logout(){

  }

}
