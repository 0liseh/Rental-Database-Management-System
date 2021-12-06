package GUIClasses;
import ControllerClasses.LandlordController;
import javax.swing.*;                           //package for GUIs
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;
import java.io.*;

public class LandlordGUI extends GUI{

  private LandlordController landlordController;

  private JButton registerProperty, postProperty, changeStateOfListing, confirm;
  private JComboBox sBox , pBox;
  JLabel header;
  private Vector<String> properties = new Vector<String>(0);

  public LandlordGUI(){
    //changeFee  = new JButton("Update Fee");
    confirm = new JButton("Confirm");
    registerProperty  = new JButton("Register Property");
    postProperty = new JButton("Post Property");
    changeStateOfListing  = new JButton("Change State of Listing");

    header = new JLabel("Select Service", JLabel.CENTER);
    header.setForeground(Color.BLACK);
    header.setFont(new Font("Courier", Font.PLAIN, 30));
    header.setBounds(50, 10 , 400, 40);
    

    
    registerProperty.setBackground(Color.GRAY);
    registerProperty.setForeground(Color.WHITE);
    registerProperty.setFont(normalFont);
   
    registerProperty.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        registerPropertyButtonPressed();
      }
    });

    
    postProperty.setBackground(Color.GRAY);
    postProperty.setForeground(Color.WHITE);
    postProperty.setFont(normalFont);
   
    postProperty.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        postPropertyButtonPressed();
      }
    });

    
    changeStateOfListing.setBackground(Color.GRAY);
    changeStateOfListing.setForeground(Color.WHITE);
    changeStateOfListing.setFont(normalFont);
    
    changeStateOfListing.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        changeStateOfListingButtonPressed();
      }
    });

    confirm.setBackground(Color.GRAY);
    confirm.setForeground(Color.WHITE);
    confirm.setFont(normalFont);

    confirm.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        confirmButtonPressed();
      }
    });
    
    
    
  }

  private void initializeBounds(){
    
    postProperty.setBounds(40 , 90, 420, 30);
    registerProperty.setBounds(40 , 50, 420, 30);
    changeStateOfListing.setBounds(40 , 130, 420, 30);
  }


  public void addElements(){
    panel.removeAll();
    panel.revalidate();
    panel.repaint();
    initializeBounds();
    panel.add(header);
    panel.add(postProperty);
    
    panel.add(registerProperty);
    panel.add(changeStateOfListing);
    
    panel.add(logout);
    frame.setVisible(true);
  }

  private void addPropertiesBox(int a, int b){
    pBox = new JComboBox(properties);
    pBox.setSelectedIndex(0);
    pBox.setBounds(40, a, 300, b);
    confirm.setBounds(350 , a, 110 , b);
    panel.add(confirm);
    panel.add(pBox);
  }

  private void changeStateOfListingButtonPressed(){
    initializeBounds();
    properties = new Vector<String>(0);
    Vector<String> statuses = new Vector<String>(4);
    statuses.add("active");
    statuses.add("rented");
    statuses.add("cancelled");
    statuses.add("suspended");

    

    addElements();
    properties.add("Select property To change state of");
    // need to access database and add all properties to the properties vector
    addPropertiesBox(170, 30);

    sBox = new JComboBox(statuses);
    sBox.setSelectedIndex(0);
    sBox.setBounds(40, 210, 300, 30);
    panel.add(sBox);

  }

  private void postPropertyButtonPressed(){
    properties = new Vector<String>(0);
    
    
    addElements();
    changeStateOfListing.setBounds(40 , 170, 420, 30);
    properties.add("Select property to post");
    // need to access database and add all properties to the properties vector
    addPropertiesBox(130, 30);

  }

  private void registerPropertyButtonPressed(){
    panel.removeAll();
    panel.revalidate();
    panel.repaint();

    panel.add(back);
    panel.add(logout);
    header = new JLabel("Register Property", JLabel.CENTER);
    header.setForeground(Color.BLACK);
    header.setFont(new Font("Courier", Font.PLAIN, 30));
    header.setBounds(50, 10 , 400, 40);
    panel.add(header);

    JLabel typeOfProperty = new JLabel("Type of property:");
    typeOfProperty.setForeground(Color.BLACK);
    typeOfProperty.setFont(normalFont);
    panel.add(typeOfProperty);
    typeOfProperty.setBounds(40, 50 , 480, 40);

    tOP.setBounds(40 , 90, 420, 30);
    panel.add(tOP);
    

    JLabel bed= new JLabel("Number of Bedrooms:");
    bed.setForeground(Color.BLACK);
    bed.setFont(normalFont);
    panel.add(bed);
    bed.setBounds(40, 120 , 480, 40);

    
    b.setBounds(40 , 160, 420, 30);
    panel.add(b);
    
    JLabel bath = new JLabel("Number of Bathrooms:");
    bath.setForeground(Color.BLACK);
    bath.setFont(normalFont);
    panel.add(bath);
    bath.setBounds(40, 190 , 480, 40);

   
    b2.setBounds(40 , 230, 420, 30);
    panel.add(b2);

    JLabel f = new JLabel("Furnished or Unfurnished");
    f.setForeground(Color.BLACK);
    f.setFont(normalFont);
    panel.add(f);
    f.setBounds(40, 270 , 480, 40);


    fBox.setSelectedIndex(0);
    fBox.setBounds(40, 310, 420, 30);
    panel.add(fBox);

    JLabel a = new JLabel("Area of City");
    a.setForeground(Color.BLACK);
    a.setFont(normalFont);
    panel.add(a);
    a.setBounds(40, 340 , 480, 40);
    

    spotBox.setSelectedIndex(0);
    spotBox.setBounds(40, 380, 420, 30);
    panel.add(spotBox);

    confirm.setBounds(40, 420, 100, 30);
    panel.add(confirm);

  }

  @Override
  public void backButtonPressed(){
    addElements();
  }

  private void confirmButtonPressed(){
    
    //use controller to send data to database
    
    if(properties.size() > 0){
      //make changes to property here
      String forProperty = pBox.getSelectedItem().toString();
      if(properties.get(0).equals("Select property to post")){
        int fee = 5;
        JOptionPane.showConfirmDialog(panel, "Do you agree to pay the fee of $" + fee);
        backButtonPressed();

      }else if(properties.get(0).equals("Select property To change state of")){
        //need to read from box and switch status, and what property was switched
        String newStatus = sBox.getSelectedItem().toString();
        

        if(forProperty.equals(properties.get(0))){
          JOptionPane.showMessageDialog(panel, "Must select a property to change");
          changeStateOfListingButtonPressed();
        }else{
          JOptionPane.showMessageDialog(panel, "The status of " + forProperty + " has been changed to " + newStatus);
          backButtonPressed();
        }
        

      }
    }else{
      //need to read from box and switch status, and what property was switched
     
      JOptionPane.showMessageDialog(panel, "The property has be registered! to post it select it under post and confirm");
      backButtonPressed();
    }

    
    
  }

  /*public payFee(){

  }

  public updateProperty(){

  }

  public changePropertyStatus(){

  }

  public registerProperty(){

  }

  public listProperty(){

  }

  public display(){

  }

  public checkMail(){
    
  }*/
}
