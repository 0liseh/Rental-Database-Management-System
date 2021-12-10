

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;
import java.io.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class UnregisteredRenterGUI extends GUI {
    
    private JPanel search, searchResults, sendMessage, Login; // propListPanel instead of view all
    private JButton confirmSearch, confirmSendMessage;
    protected JTabbedPane tp;
    private String prop[] = {"-------"}; // use for properties box populate with properties.name
    private JLabel typeOfProperty, bed , bath , f , a;
    private Vector<Property> properties = new Vector<Property>();
    private Vector<Property> searchedProperties = new Vector<Property>();
    protected DatabaseController dbController;
    protected JTextField iD;
    protected JTextArea message;
    protected Integer id = -1;


    public UnregisteredRenterGUI(){

        mainFrame = new JFrame("guest window");
        mainFrame.setSize(500, 600);
        mainFrame.add(controlPanel);
        controlPanel.setLayout(null);
        //needs to use controller to get a list of properties from the database and populat properties[]
        dbController = new DatabaseController();
        properties = dbController.getProperties();
        displayProp();
        //controlPanel.setBorder(BorderFactory.createEmptyBorder(15, 40, 10, 40));
        tp = new JTabbedPane();
        
        search = new JPanel(new GridLayout(0, 1, 0, 20));
        searchResults = new JPanel(new GridLayout(0, 1, 0, 20));
        sendMessage = new JPanel();
        //addToSearchResults();
        setButtons();
        //addObjects();
        
        //dbController.getUsers();
    }   
    private void displayProp(){
    	propListPanel.setLayout(new GridLayout(0, 1, 0, 20));
        JPanel sPanel = new JPanel(new GridLayout(0, 1, 0, 20));

        sPanel.setSize(500, 600);
        sPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 0, 50));

        for(int i = 0; i < properties.size(); i++){
            String tempStr = "\nProperty ID: " + properties.get(i).getPropertyid() + "\n";
            
            tempStr = tempStr + "Type of property: " + properties.get(i).getPropertyType() + "\n";
            tempStr = tempStr + "Number of Bed(s): " + properties.get(i).getNoOfBed() + "\n";
            tempStr = tempStr + "Number of Bath(s): " + properties.get(i).getNoOfBath() + "\n";
            if(properties.get(i).isFurnished()){
                tempStr = tempStr + "Furnished\n";
            }else{
                tempStr = tempStr + "Unfurnished\n";
            }
            tempStr = tempStr + "Location: " + properties.get(i).getArea() + "\n";
            tempStr = tempStr + "Landlord ID: " + properties.get(i).getLandlordID() + "\n";



            JTextArea temp = new JTextArea(tempStr);
            temp.setFont(normalFont);
            sPanel.add(temp);

        }

        JScrollPane scrollPane = new JScrollPane(sPanel);    
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        propListPanel.add(scrollPane);
    }

    public void addObjects(){
        

        addActionListeners();
        addToSearch();
        addToSendMessage();
        tp.add("View all Properties", propListPanel);
        tp.add("Search properties" , search);
        tp.add("send message" , sendMessage);
        tp.add("Login" , Login);
        
        mainFrame.add(tp);
        mainFrame.setVisible(true);
    }

    private void setButtons(){
        confirmSearch = new JButton("Search");
       

        confirmSearch.setBackground(Color.WHITE);
        confirmSearch.setForeground(Color.GRAY);
        confirmSearch.setFont(normalFont);

        confirmSendMessage = new JButton("Send");
       

        confirmSendMessage.setBackground(Color.WHITE);
        confirmSendMessage.setForeground(Color.GRAY);
        confirmSendMessage.setFont(normalFont);
    }

    private void addToSearch(){
        search.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        headerLabel = new JLabel("Select search criteria", JLabel.CENTER);
        headerLabel.setForeground(Color.BLACK);
        headerLabel.setFont(normalFont);
        search.add(headerLabel);

        typeOfProperty = new JLabel("Type of property:");
        typeOfProperty.setForeground(Color.BLACK);
        typeOfProperty.setFont(normalFont);
        search.add(typeOfProperty);
        search.add(typeOfPropBox);

        bed= new JLabel("Number of Bedrooms:");
        bed.setForeground(Color.BLACK);
        bed.setFont(normalFont);
        search.add(bed);
        search.add(numOfBedsBox);
        


        bath = new JLabel("Number of Bathrooms:");
        bath.setForeground(Color.BLACK);
        bath.setFont(normalFont);
        search.add(bath);
        search.add(numOfBathsBox);

        f = new JLabel("Furnished or Unfurnished");
        f.setForeground(Color.BLACK);
        f.setFont(normalFont);
        search.add(f);
        search.add(furnishedBox);

        a = new JLabel("Area of City");
        a.setForeground(Color.BLACK);
        a.setFont(normalFont);
        search.add(a);
        search.add(locationBox);

        search.add(confirmSearch);

    }

    private void addActionListeners(){
        confirmSearch.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
              searchButtonPressed();
            }
        });

        confirmSendMessage.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
              sendMessageButtonPressed();
            }
        });


        
        ChangeListener cl = new ChangeListener(){
            public void stateChange(ChangeEvent et){
                JTabbedPane src = (JTabbedPane) et.getSource();
                int index = src.getSelectedIndex();
                System.out.println("Tab Changed to " + src.getTitleAt(index));
        }

            @Override
            public void stateChanged(ChangeEvent e) {
                JTabbedPane src = (JTabbedPane) e.getSource();
                int index = src.getSelectedIndex();
               
                if(src.getTitleAt(index).equals("Login")){
                   
                    logout();
                }else if(src.getTitleAt(index).equals("Logout")){
                   dbController.logout(id.toString());
                    logout();
                }
                
            }
        };
        tp.addChangeListener(cl);
    }

    private void searchButtonPressed(){
        String tOfProp = typeOfPropBox.getSelectedItem().toString();
        String numB = numOfBedsBox.getSelectedItem().toString();
        String numB2 = numOfBathsBox.getSelectedItem().toString();
        String isFurn = furnishedBox.getSelectedItem().toString();
        String area  = locationBox.getSelectedItem().toString();

        System.out.println(tOfProp);
        System.out.println(numB);
        System.out.println(numB2);
        System.out.println(isFurn);
        System.out.println(area);

        searchedProperties = dbController.getSearchedProperties(tOfProp, numB, numB2, isFurn, area);
        //contact database a grab all properties that match the description
        addToSearchResults();
    }

    private void addToSearchResults(){
        searchResults.removeAll();
        searchResults.revalidate();
        searchResults.repaint();

        
        searchResults.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
       
        

        //searchedProperties = dbController.getSearchedProperties();
        //need to call above function to get results that fit search criteria

        
        JPanel sPanel = new JPanel(new GridLayout(0, 1, 0, 20));

        sPanel.setSize(500, 600);
        sPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 0, 50));

        for(int i = 0; i < searchedProperties.size(); i++){
            String tempStr = "\nProperty ID: " + searchedProperties.get(i).getPropertyid() + "\n";
            tempStr = tempStr + "Type of property: " + searchedProperties.get(i).getPropertyType() + "\n";
            tempStr = tempStr + "Number of Bed(s): " + searchedProperties.get(i).getNoOfBed() + "\n";
            tempStr = tempStr + "Number of Bath(s): " + searchedProperties.get(i).getNoOfBath() + "\n";
            if(searchedProperties.get(i).isFurnished()){
                tempStr = tempStr + "Furnished\n";
            }else{
                tempStr = tempStr + "Unfurnished\n";
            }
            tempStr = tempStr + "Location: " + searchedProperties.get(i).getArea() + "\n";
            tempStr = tempStr + "Landlord ID: " + searchedProperties.get(i).getLandlordID() + "\n";



            JTextArea temp = new JTextArea(tempStr);
            temp.setFont(normalFont);
            temp.setEditable(false);
            sPanel.add(temp);

        }

        JScrollPane scrollPane = new JScrollPane(sPanel);    
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        searchResults.add(scrollPane);
        tp.add("Search Results" , searchResults);
    }

    private void addToSendMessage(){
        sendMessage.setLayout(new GridLayout(0 , 1 ,0 , 20));
        sendMessage.setBorder(BorderFactory.createEmptyBorder(15, 40, 10, 40));

        JTextArea LLID = new JTextArea("Enter ID of landlord you wish to send the message to e.g 5");
        LLID.setFont(normalFont);
        LLID.setOpaque(false);
        LLID.setEditable(false);
        LLID.setLineWrap(true);
        LLID.getWrapStyleWord();
        sendMessage.add(LLID);

        iD = new JTextField();
        iD.setFont(normalFont);
        sendMessage.add(iD);

        JTextArea typed = new JTextArea("Enter Message Below");
        typed.setFont(normalFont);
        typed.setOpaque(false);
        typed.setEditable(false);
        sendMessage.add(typed);

        message = new JTextArea();
        message.setFont(normalFont);
        message.setLineWrap(true);
        message.getWrapStyleWord();
        sendMessage.add(message);

        sendMessage.add(confirmSendMessage);


     
    }

    private void sendMessageButtonPressed(){
        JOptionPane.showMessageDialog(mainFrame, "Message has been sent");
        dbController.sendMail(Integer.parseInt(iD.getText().toString()) , message.getText().toString());
    }
}
