package GUIClasses;
import javax.swing.*;                           //package for GUIs
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;
import java.io.*;

//import ControllerClasses.UnregisteredRenterController;

public class UnregisteredRenterGUI extends GUI{

  //private renterController renter;
  private JButton search, backToLogin;
  private JLabel header;
  private JLabel typeOfProperty, bed , bath , f , a;


  public UnregisteredRenterGUI(){
    header = new JLabel("Select search criteria", JLabel.CENTER);
    header.setForeground(Color.BLACK);
    header.setFont(new Font("Courier", Font.PLAIN, 20));
    header.setBounds(50, 10 , 400, 40);



    search  = new JButton("Search");
    search.setBounds(50, 420, 400, 40);
    search.setBackground(Color.GRAY);
    search.setForeground(Color.WHITE);
    search.setFont(normalFont);

    search.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        searchButtonPressed();
      }
    });


    typeOfProperty = new JLabel("Type of property:");
    typeOfProperty.setForeground(Color.BLACK);
    typeOfProperty.setFont(normalFont);

    typeOfProperty.setBounds(40, 50 , 480, 40);

    tOP.setBounds(40 , 90, 420, 30);



    bed= new JLabel("Number of Bedrooms:");
    bed.setForeground(Color.BLACK);
    bed.setFont(normalFont);

    bed.setBounds(40, 120 , 480, 40);


    b.setBounds(40 , 160, 420, 30);


     bath = new JLabel("Number of Bathrooms:");
    bath.setForeground(Color.BLACK);
    bath.setFont(normalFont);

    bath.setBounds(40, 190 , 480, 40);


    b2.setBounds(40 , 230, 420, 30);


     f = new JLabel("Furnished or Unfurnished");
    f.setForeground(Color.BLACK);
    f.setFont(normalFont);

    f.setBounds(40, 270 , 480, 40);


    furnBox.setSelectedIndex(0);
    furnBox.setBounds(40, 310, 420, 30);


     a = new JLabel("Area of City");
    a.setForeground(Color.BLACK);
    a.setFont(normalFont);

    a.setBounds(40, 340 , 480, 40);


    locationBox.setSelectedIndex(0);
    locationBox.setBounds(40, 380, 420, 30);

    backToLogin = new JButton("Login");
    backToLogin.setOpaque(false);
    backToLogin.setBorderPainted(false);
    backToLogin.setBounds(380 , 10, 120, 30);
    backToLogin.setBackground(Color.WHITE);
    backToLogin.setForeground(Color.BLUE);
    backToLogin.setFont(new Font("Courier", Font.PLAIN, 15));

    backToLogin.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        logout();
      }
    });

  }

  //Is called in Property app, and removes all the previous buttons and text fields while adding the new ones for an unregistered guest
  public void addElements(){
    panel.removeAll();
    panel.revalidate();
    panel.repaint();

    //adding labels
    panel.add(a);
    panel.add(f);
    panel.add(bath);
    panel.add(bed);
    panel.add(typeOfProperty);

    panel.add(header);
    panel.add(search);
    panel.add(backToLogin);
    panel.add(b);
    panel.add(b2);
    panel.add(furnBox);
    panel.add(locationBox);
    panel.add(tOP);
    frame.setVisible(true);
  }


  @Override
  public void backButtonPressed(){
    loggingOut = "YES";
  }

  private void searchButtonPressed(){
    panel.removeAll();
    panel.revalidate();
    panel.repaint();

    //need to pass these values to the controller to connect to the database
    // if they are "--" then it can be any
    String Selectedlocation = locationBox.getSelectedItem().toString();
    String bedNum= b.getSelectedItem().toString();
    String bathNum= b2.getSelectedItem().toString();
    String furnished = furnBox.getSelectedItem().toString();
    String typeOfPlace = tOP.getSelectedItem().toString();

  }

}
