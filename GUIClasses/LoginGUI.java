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
  private JTextField un = new JTextField();
  private JTextField pw = new JTextField();

  public LoginGUI(){
    JButton login = new JButton("Login");
    JButton guest = new JButton("Use as Guest");
    
   
    

    JLabel header = new JLabel("Login to Property Tracker!", JLabel.CENTER);
    header.setForeground(Color.BLACK);
    header.setFont(new Font("Courier", Font.PLAIN, 30));
    header.setBounds(10, 10 , 480, 40);
    panel.add(header);
    

    JLabel username = new JLabel("Username:");
    username.setForeground(Color.BLACK);
    username.setFont(normalFont);
    username.setBounds(40, 50 , 480, 40);
    panel.add(username);

    un.setBounds(40 , 90, 420, 30);
    un.setFont(normalFont);
    panel.add(un);

    
    JLabel password = new JLabel("Password:");
    password.setForeground(Color.BLACK);
    password.setFont(normalFont);
    panel.add(password);
    password.setBounds(40, 120 , 480, 40);

    pw.setBounds(40, 160, 420, 30);
    pw.setFont(normalFont);
    panel.add(pw);
    
    login.setBounds(40 , 210, 420, 30);
    login.setBackground(Color.GRAY);
    login.setForeground(Color.WHITE);
    login.setFont(normalFont);
    panel.add(login);


    guest.setBounds(40 , 250, 420, 30);
    guest.setBackground(Color.GRAY);
    guest.setForeground(Color.WHITE);
    guest.setFont(normalFont);
    panel.add(guest);
  }

  public void confirmLogin(){

  }
  public void logout(){

  }

}
