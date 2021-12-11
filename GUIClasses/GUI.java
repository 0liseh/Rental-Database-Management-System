
//Other GUIs inherit this class
import javax.swing.*;                           //package for GUIs
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;
import java.io.*;


abstract class GUI extends JFrame {
  //list all varaibles and objects to be used by multiple GUI's
   protected JFrame mainFrame;
   protected JPanel controlPanel , propListPanel;
   protected JScrollPane scrollPane;
   protected JLabel headerLabel;
   protected JButton logout  = new JButton("Logout");
   protected JButton back = new JButton("Back");
   protected JButton confirm = new JButton("Confirm");
   protected JButton changeStatus = new JButton("Change Property Status");
   
   
   String loggingOut = "NO";
   protected static Font normalFont = new Font("Courier", Font.PLAIN, 19);
   protected static Font bigFont = new Font("Courier", Font.PLAIN, 25);
   protected JComboBox numOfBedsBox, numOfBathsBox, furnishedBox, locationBox, typeOfPropBox, propertiesBox, statusBox;
   
 
  
   public GUI(){
       mainFrame = new JFrame("Property Application");
       controlPanel = new JPanel(); 
       mainFrame.setSize(500,500);
       mainFrame.add(controlPanel);
       controlPanel.setLayout(null);
       propListPanel = new JPanel(); 

   
       //Below functions initialize what properties the JButtons and JComboBoxes have
       setupButtons();
       comboBoxSetup();
       addListeners();
       
      
   }   

   //set what the buttons for logout and back look like
   private void setupButtons(){
    logout.setOpaque(false);
    logout.setBorderPainted(false);
    logout.setBounds(380 , 10, 120, 30);
    logout.setBackground(Color.WHITE);
    logout.setForeground(Color.BLUE);
    logout.setFont(new Font("Courier", Font.PLAIN, 15)); 


    back.setOpaque(false);
    back.setBorderPainted(false);
    back.setBounds(5 , 10, 80, 30);
    back.setBackground(Color.WHITE);
    back.setForeground(Color.BLUE);
    back.setFont(new Font("Courier", Font.PLAIN, 15)); 
   }


   //add listener for logout button
   private void addListeners(){

      logout.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          logout();
        }
      });
   }

   //initialize options in the combo boxes for registering and searching properties
   private void comboBoxSetup(){
        String numOfBedsAndBathsArr[] = { "-------", "1", "2", "3" , "4" ,"5", "6" , "7" , "8"};
        String funishedArr[] = {"-------", "Furnished", "Unfurnished"};
        String locationArr[] = {"-------", "se" , "nw" , "se", "ne"};
        String typeOfPropArr[] = {"-------", "Apartment", "Condo", "Attached House" , "House", "Townhouse"};
        String statusesArr[] = {"-------", "active" , "rented", "cancelled", "suspended"};
        numOfBedsBox = new JComboBox(numOfBedsAndBathsArr);
        numOfBathsBox = new JComboBox(numOfBedsAndBathsArr);
        furnishedBox = new JComboBox(funishedArr);
        locationBox = new JComboBox(locationArr);
        typeOfPropBox = new JComboBox(typeOfPropArr);
        statusBox = new JComboBox(statusesArr);
   }

   //change the logging out status to yes
  public void logout(){
    loggingOut = "YES";

    
  }
  //return logging out status to main loop
  public String getLoggingOut(){
    return loggingOut;
  }

  //close the window of the GUI you are logging out of or clicking x
  public void closeWindow(){
    mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
  }

}
