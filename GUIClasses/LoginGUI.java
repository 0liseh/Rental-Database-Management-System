//import Controller.loginController;
package GUIClasses;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import ControllerClasses.LoginController;

//login GUI inherits window, pane, and buttons of GUI class
public class LoginGUI extends GUI{
  //private Logout logoutObj;
  private LoginController logoutObj;

  private JTextField un = new JTextField(); //username single line textfield
  private JTextField pw = new JTextField(); //password single line textfield
  JButton login = new JButton("Login");
  JButton guest = new JButton("Use as Guest");
  private String type = "yolo";

  public LoginGUI(){
    /*GridLayout L = new GridLayout(0 , 1);
    panel.setLayout(L);*/
    JLabel header = new JLabel("Login to Property Tracker!", JLabel.CENTER); //creates a label(string) on window
    header.setForeground(Color.BLACK);
    header.setFont(new Font("Courier", Font.PLAIN, 30));
    header.setBounds(10, 10 , 480, 40);
    panel.add(header); //header label is added to panel in GUI class

    //Username label
    JLabel username = new JLabel("Username:");
    username.setForeground(Color.BLACK);
    username.setFont(normalFont);
    username.setBounds(40, 50 , 480, 40);
    panel.add(username);

    //username text field attributes
    un.setBounds(40 , 90, 420, 30);
    un.setFont(normalFont);
    panel.add(un);

    //Password label
    JLabel password = new JLabel("Password:");
    password.setForeground(Color.BLACK);
    password.setFont(normalFont);
    panel.add(password);
    password.setBounds(40, 120 , 480, 40);

    //Password textfield attributes
    pw.setBounds(40, 160, 420, 30);
    pw.setFont(normalFont);
    panel.add(pw);

    //login button attributes
    login.setBounds(40 , 210, 420, 30);
    login.setBackground(Color.GRAY);
    login.setForeground(Color.WHITE);
    login.setFont(normalFont);
    panel.add(login);

    //action listener for login button, calls function
    login.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        loginButtonPressed();
      }
    });

    //guest button attributes
    guest.setBounds(40 , 250, 420, 30);
    guest.setBackground(Color.GRAY);
    guest.setForeground(Color.WHITE);
    guest.setFont(normalFont);

    //action listener for guest button
    guest.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        guestButtonPressed();
      }
    });

    //guest button added to panel
    panel.add(guest);
    //window is opened
    frame.setVisible(true);
  }

  //confirms log in of user
  public void confirmLogin(){

  }

  private void guestButtonPressed(){
    type = "Unregistered Renter";
  }

  private void loginButtonPressed(){
    //search database for the type
    type ="yolo";
    //if type is not found display error
    if(true){
      JOptionPane.showMessageDialog(panel, "Username or Password is Incorrect");
    }else{
      //set type to the return value of the database search
      //type = "";
    }

  }

  //returns what user type they are
  public String gettype(){
    return type;
  }


}
