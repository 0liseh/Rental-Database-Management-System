import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;
import java.io.*;



public class RegisteredRenterGUI extends UnregisteredRenterGUI{


    //declare variables and objects
    private JPanel newProps; 
    private JPanel logout;
    private Vector<Property> newPropVec = new Vector<Property>();
    private JCheckBox notifs;
    //intializes Jpanel and title of the window
    public RegisteredRenterGUI(){
        
        mainFrame.setTitle("Renter window");
        
        
        newProps = new JPanel();
        addToNewProps();
        
    }

    //sets border of the newProps panel
    private void addToNewProps(){
        newProps.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        
        
    }

    //adds objects to the mainFrame that aren't added in the UnregisteredRenterGUI
    //these would be notification page and logout panel
    public void addObjects(int id){
        
        this.id = id;
        addToNewProperties();
        tp.remove(tp.getTabCount() - 1);
        logout = new JPanel(null);
        tp.add("Logout", logout);
        addActionListeners();
        boolean b = dbController.getNotifications(this.id.toString());
        if(b && dbController.getNewProperties(this.id.toString()).size() > 0){
            JOptionPane.showMessageDialog(mainFrame, "There are new Properties to view under Recently Added");
        }
        tp.add("Recently added" , newProps);

        
    }

    
    //Uses databse controller to get new properties from database and puts them on the screen
    //also adds notification buttio so user can turn them on or off-> database controller will handle this with the database
    private void addToNewProperties(){

        newProps.removeAll();
        newProps.revalidate();
        newProps.repaint();

        newProps.setLayout(new GridLayout(0 , 1, 0, 20));
        newProps.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
       
        


        
        JPanel sPanel = new JPanel(new GridLayout(0, 1, 0, 20));

        sPanel.setSize(500, 600);
        sPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 0, 50));
        newPropVec = dbController.getNewProperties(this.id.toString());
        boolean b = dbController.getNotifications(id.toString());
        System.out.print(b);
        if(b){
        	
            notifs = new JCheckBox("Turn Notifications on" ,true);
        }else{
            notifs = new JCheckBox("Turn Notifications on" ,false);
        }
        notifs.addItemListener(new ItemListener() {    
            public void itemStateChanged(ItemEvent e) {                 
               changeNotif();   
            }    
         }); 
        
        sPanel.add(notifs);
        for(int i = 0; i < newPropVec.size(); i++){
            String tempStr = "\nProperty ID: " + newPropVec.get(i).getPropertyid() + "\n";
            tempStr = tempStr + "Type of property: " + newPropVec.get(i).getPropertyType() + "\n";
            tempStr = tempStr + "Number of Bed(s): " + newPropVec.get(i).getNoOfBed() + "\n";
            tempStr = tempStr + "Number of Bath(s): " + newPropVec.get(i).getNoOfBath() + "\n";
            if(newPropVec.get(i).isFurnished()){
                tempStr = tempStr + "Furnished\n";
            }else{
                tempStr = tempStr + "Unfurnished\n";
            }
            tempStr = tempStr + "Location: " + newPropVec.get(i).getArea() + "\n";
            tempStr = tempStr + "Landlord ID: " + newPropVec.get(i).getLandlordID() + "\n";



            JTextArea temp = new JTextArea(tempStr);
            temp.setFont(normalFont);
            temp.setEditable(false);
            sPanel.add(temp);

        }
        JScrollPane scrollPane = new JScrollPane(sPanel);    
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        newProps.add(scrollPane);
    }


    //when check box is clicked it will go here to use handler (database controller) to update database on who wants notifications on
    private void changeNotif(){
        if(dbController.getNotifications(this.id.toString())){
            dbController.setNotifications(id.toString(), false);
        }else{
            dbController.setNotifications(id.toString(), true);
        }
    }
    
}
