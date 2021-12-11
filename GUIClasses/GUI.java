
//Other GUIs inherit this class
import javax.swing.*;                           //package for GUIs
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;
import java.io.*;


abstract class GUI extends JFrame {
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
       mainFrame.setSize(500, 500);
       mainFrame.add(controlPanel);
       controlPanel.setLayout(null);
       propListPanel = new JPanel();
       

   
       
       setupButtons();
       comboBoxSetup();
       addListeners();
       
      
   }   

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

   private void addListeners(){

      logout.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          logout();
        }
      });
   }

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

  public void logout(){
    loggingOut = "YES";

    
  }
  public String getLoggingOut(){
    return loggingOut;
  }

  public void closeWindow(){
    mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
  }

  private void addToPropListPanel(){// pass in property and add it as a Landlords name house id and address

  }
}
