

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;
import java.io.*;


public class RegisterGUI extends GUI{

    private JTextField un, pw, ut;
    private JButton login , guest;
    private JLabel username , password, userType;

    public RegisterGUI(){
        controlPanel.setLayout(new GridLayout(0 , 1 ,0 , 20));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(15, 40, 10, 40));
        setTextFields();
        setButtons();
        addObjects();
    }

    public void addObjects()
    {
        controlPanel.removeAll();
        controlPanel.revalidate();
        controlPanel.repaint();
        controlPanel.add(headerLabel);
        controlPanel.add(username);
        controlPanel.add(un);
        controlPanel.add(password);
        controlPanel.add(pw);
        controlPanel.add(userType);
        controlPanel.add(ut);
        controlPanel.add(login);
        controlPanel.add(guest);
        mainFrame.setVisible(true);
    }

    private void setTextFields(){
        un = new JTextField();
        pw = new JTextField();
        ut = new JTextField();


        un.setFont(normalFont);
        pw.setFont(normalFont);
        ut.setFont(normalFont);

        headerLabel = new JLabel("Register to Property Tracker!", JLabel.CENTER);
        headerLabel.setForeground(Color.BLACK);
        headerLabel.setFont(new Font("Courier", Font.PLAIN, 25));

        username =  new JLabel("Username:");
        username.setForeground(Color.BLACK);
        username.setFont(normalFont);

        password = new JLabel("Password:");
        password.setForeground(Color.BLACK);
        password.setFont(normalFont);

        userType = new JLabel("User Type:");
        userType.setForeground(Color.BLACK);
        userType.setFont(normalFont);
    }

    private void setButtons(){

        login = new JButton("Sign Up");
        guest = new JButton("Guest");


        login.setBackground(Color.GRAY);
        login.setForeground(Color.WHITE);
        login.setFont(normalFont);

        login.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                loginButtonPressed();
            }
        });

        guest.setBackground(Color.GRAY);
        guest.setForeground(Color.WHITE);
        guest.setFont(normalFont);

        guest.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                guestButtonPressed();
            }
        });



    }
    private void guestButtonPressed()
    {
        // make it login as unregistered user
    }

    private void loginButtonPressed(){
        String user = un.getText().toString();
        System.out.println(user);

        String pass = pw.getText().toString();
        System.out.println(pass);

        String userT = ut.getText().toString();
        System.out.println(userT);

        //send these values to check database and see if it's a user and what type of user it is

    }
}
