import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;
import java.io.*;



public class RegisteredRenterGUI extends UnregisteredRenterGUI{

    private JPanel newProps; // propListPanel instead of view all
    private Integer id;
    private Vector<Property> newPropVec = new Vector<Property>();
    private JCheckBox notifs;
    public RegisteredRenterGUI(){
        
        mainFrame.setTitle("Renter window");
        
        
        newProps = new JPanel();
        addToNewProps();
        
    }

    private void addToNewProps(){
        newProps.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        
        
    }

    public void addObjects(int id){
        
        this.id = id;
        addToNewProperties();


        tp.add("Recently added" , newProps);

        
    }

    private void addToNewProperties(){

        newProps.removeAll();
        newProps.revalidate();
        newProps.repaint();

        
        newProps.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
       
        


        
        JPanel sPanel = new JPanel(new GridLayout(0, 1, 0, 20));

        sPanel.setSize(500, 600);
        sPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 0, 50));
        newPropVec = dbController.getNewProperties(this.id.toString());
        boolean b = dbController.getNotification(id);
        notifs = new JCheckBox("Turn Notifications on" ,true);
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
    
}
