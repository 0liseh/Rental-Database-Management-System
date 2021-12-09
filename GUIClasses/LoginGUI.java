import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;
import java.io.*;


public class LoginGUI extends GUI{

    private JTextField un, pw;
    private JButton login , guest, register;
    private JLabel username , password;
    private DatabaseController dbController;

    public LoginGUI(){
        controlPanel.setLayout(new GridLayout(0 , 1 ,0 , 20));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(15, 40, 10, 40));
        dbController = new DatabaseController();
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
        controlPanel.add(login);
        controlPanel.add(register);
        controlPanel.add(guest);
        mainFrame.setVisible(true);
    }

    private void setTextFields(){
        un = new JTextField();
        pw = new JTextField();


        un.setFont(normalFont);
        pw.setFont(normalFont);

        headerLabel = new JLabel("Login to Property Tracker!", JLabel.CENTER);
        headerLabel.setForeground(Color.BLACK);
        headerLabel.setFont(new Font("Courier", Font.PLAIN, 25));

        username =  new JLabel("Username:");
        username.setForeground(Color.BLACK);
        username.setFont(normalFont);

        password = new JLabel("Password:");
        password.setForeground(Color.BLACK);
        password.setFont(normalFont);
    }

    private void setButtons(){

        login = new JButton("Login");
        guest = new JButton("Guest");
        register = new JButton("Register");


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

        register.setBackground(Color.GRAY);
        register.setForeground(Color.WHITE);
        register.setFont(normalFont);

        register.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                registerButtonPressed();
            }
        });

    }
    private void guestButtonPressed()
    {
        // make it login as unregistered user
    }

    private void registerButtonPressed()
    {
        // make it go to the register page
    }

    private void loginButtonPressed(){
        String user = un.getText().toString();
        System.out.println(user);

        String pass = pw.getText().toString();
        System.out.println(pass);

        if(dbController.checkUser(user, pass, "Manager")){
            System.out.println("Mananger");
        }else if(dbController.checkUser(user, pass, "Landlord")){
            System.out.println("landlord");
        }else if(dbController.checkUser(user, pass, "Renter")){
            System.out.println("Renter");
        }else{
            System.out.println("Didn't work");
        }
        //send these values to check database and see if it's a user and what type of user it is

    }
}
