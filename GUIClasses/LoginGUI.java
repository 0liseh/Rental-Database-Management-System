//import Controller.loginController;
package GUIClasses;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import ControllerClasses.LoginController;

public class LoginGUI extends GUI{
  //private Logout logoutObj;
  private LoginController logoutObj;
  
  private JTextField un = new JTextField();
  private JTextField pw = new JTextField();
  JButton login = new JButton("Login");
  JButton guest = new JButton("Use as Guest");
  private String type = "yolo";

  public LoginGUI(){
    
    
    /*GridLayout L = new GridLayout(0 , 1);
    panel.setLayout(L);*/
   
    

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

    login.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        loginButtonPressed();
      }
    });


    guest.setBounds(40 , 250, 420, 30);
    guest.setBackground(Color.GRAY);
    guest.setForeground(Color.WHITE);
    guest.setFont(normalFont);

    guest.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        geustButtonPressed();
      }
    });

    panel.add(guest);
    frame.setVisible(true);
  }

  public void confirmLogin(){

  }

  private void geustButtonPressed(){
    type = "Unregistered Renter";
  }

  private void loginButtonPressed(){
    //search database for the type
    type ="yolo";
    //if type is un found display error
    if(true){
      JOptionPane.showMessageDialog(panel, "Username or Password is Incorrect");
    }else{
      //set type to the return value of the database search
      //type = "";
    }
    
  }

  

  public String gettype(){
    return type;
  }
  

}
