
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;
import java.io.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JComboBox;
//import ControllerClasses.LandlordController;

public class LandlordGUI extends GUI {
    
    private JButton confirmStatusChange, confirmRegister , confirmRegAndPost, confirmPost;
    private JPanel reg , post , changeOfStatus, messages; // can add prop list panel so landlords can view all their properties
    private JTabbedPane tp;
    private DatabaseController dbController;
    private Integer Lid;
    private Integer pId;
    private JCheckBox notifs;
    //private Property properties[];
    private String prop[] = {"-------"}; // use for properties box populate with properties.name
    private String prop2[] = {"-------"};
    private JComboBox fees;
    private JComboBox fees2 , pB;
    Vector<String> feeBx = new Vector<String>();
    Vector<String> feeBx2 = new Vector<String>();

    public LandlordGUI(){

    }

    //adds tabbed pane to the frame then adds JPanels onto the tabbedPane and calls functions to
    //add objects t the JPanels
    //also calls functions to initialize the correct combo boxes and textareas
    public void addObjects(int id){
    	 dbController = new DatabaseController();
    	Lid = id;
        boolean b = dbController.getNotifications(this.Lid.toString());
        
    	feeBx = new Vector<String>();
    	feeBx = new Vector<String>();
    	prop = new String[1];
    	prop2 = new String[1];
    	prop[0] = "-------"; // use for properties box populate with properties.name
        prop2[0] = "-------";
    	mainFrame = new JFrame("Landlord window");
        mainFrame.setSize(500, 600);
        mainFrame.add(controlPanel);
        controlPanel.setLayout(null);
        //needs to use controller to get a list of properties from the database and populat properties[]
        //controlPanel.setBorder(BorderFactory.createEmptyBorder(15, 40, 10, 40));
        tp = new JTabbedPane();
        
        reg = new JPanel(new GridLayout(0, 1, 0, 20));
        post = new JPanel(new GridLayout(0, 1, 0, 20));
        changeOfStatus = new JPanel(new GridLayout(0, 1, 0, 20));

        Vector<String> f = dbController.getFee();
       

        for(int i = 0; i < f.size(); i++){
            feeBx.add(f.get(i));
            feeBx2.add(f.get(i));
        }

        fees = new JComboBox(feeBx);
        fees2 = new JComboBox(feeBx2);
        propertiesBox = new JComboBox(prop);
        
        setButtons();
        controlPanel.removeAll();
        controlPanel.revalidate();
        controlPanel.repaint();
        addPropertiesBox();
        addToReg();
        addToPost();
        addToChangeOfStatus();
        addToMessages();
        addListeners();
       

        tp.add("Register new Property!", reg);
        tp.add("Post Property!", post);
        tp.add("Change Status of Property", changeOfStatus);
        tp.add("Inbox" , messages);
        tp.add("Logout", new JPanel());
        mainFrame.add(tp);
        
        mainFrame.setVisible(true);
        if(b && dbController.newEmails(Lid.toString())){
            JOptionPane.showMessageDialog(mainFrame, "You Have new Messages");
        }

    }


    //Adds evenet listeners for each button that the landlord will use on every JPanel
    //also adds eent listner for the logout tab which will log use out when it is clicked
    private void addListeners(){

        confirmPost.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                confirmPostButtonPressed();
            }
        });

        confirmRegister.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                confirmRegisterButtonPressed();
            }
        });

        confirmStatusChange.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                confirmStatusChangeButtonPressed();
            }
        });

        confirmRegAndPost.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                confirmRegAndPostButtonPressed();
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
                System.out.println("Tab Changed to " + src.getTitleAt(index));
                if(src.getTitleAt(index).equals("Logout")){
                    dbController.logout(Lid.toString());
                    logout();
                }
                
            }
        };
        tp.addChangeListener(cl);
    }

    //adds properties to the property box used for change status page
    //adds properties box for the properties available to post
    private void addPropertiesBox(){
        Vector<Integer> properties = dbController.getMyProperties(Lid);
        
        for(int i = 0; i < properties.size(); i++){
        
        prop = Arrays.copyOf(prop, prop.length + 1);
        prop[prop.length -1 ] = String.valueOf("propertyID: " + properties.get(i));

        

        }

        Vector<Integer> propertiesNR = dbController.getNRProperties(Lid);
        System.out.println(propertiesNR.size());

        for(int i = 0; i < propertiesNR.size(); i++){
            prop2 = Arrays.copyOf(prop2, prop2.length + 1);
            prop2[prop2.length -1 ] = String.valueOf("propertyID: " + properties.get(i));
        }
        propertiesBox = new JComboBox(prop);

        pB = new JComboBox(prop2);

    }


    //sets what each button will look like 
    private void setButtons(){
        confirmStatusChange = new JButton("Confirm Status Change");
        confirmRegister = new JButton("Register");
        confirmPost = new JButton("Post");
        confirmRegAndPost = new JButton("Register and Post");

        confirmStatusChange.setBackground(Color.WHITE);
        confirmStatusChange.setForeground(Color.GRAY);
        confirmStatusChange.setFont(normalFont);

        confirmRegister.setBackground(Color.WHITE);
        confirmRegister.setForeground(Color.GRAY);
        confirmRegister.setFont(normalFont);
        
        confirmPost.setBackground(Color.WHITE);
        confirmPost.setForeground(Color.GRAY);
        confirmPost.setFont(normalFont);

        confirmRegAndPost.setBackground(Color.WHITE);
        confirmRegAndPost.setForeground(Color.GRAY);
        confirmRegAndPost.setFont(normalFont);


    }

    
    //This method gets called when the action 'confirm post button is pressed' then sends property id to databaseController
    //so that it's status can be updated
    //then calls addOPbjects to update comboBoxes and reset Frame 
    private void confirmPostButtonPressed(){
        String propSelected = pB.getSelectedItem().toString();
       // System.out.println(propSelected);
        //boolean b = dbController.changeStatus(propSelected.substring(11, propSelected.length()), "rented");
        
        if(propSelected.length() != 7) {
        post.removeAll();
        post.repaint();
        post.revalidate();
        dbController.changeStatus(propSelected.substring(11, propSelected.length()), "active");
        closeWindow();
        addObjects(Lid); 
        }
        
    }


    //method called when action 'confrim status change button is pressed' 
    //sends property id and status we are changing it to into the databaseController
    //if trying to change status to active users must pay fee,
    //when changed it will recall addObjects to get new lsit of properties that aren't active that they can post
    private void confirmStatusChangeButtonPressed(){
        String status = (String)statusBox.getSelectedItem();
       // System.out.println(status);

        String propSelected = propertiesBox.getSelectedItem().toString();
        //System.out.println(propSelected);
        if(status.equals("active")){
            JPanel p = new JPanel(new GridLayout(0 , 1, 0, 20));
            p.add(new JLabel("Select Subscription Model for Property"));
            p.add(fees);
            JOptionPane.showMessageDialog(mainFrame, p);
        }
        if(propSelected.length() != 7) {
	        boolean b = dbController.changeStatus(propSelected.substring(11, propSelected.length()), status);
	
	        if(b){
	            JOptionPane.showMessageDialog(mainFrame, "Status Changes Successfully!");
	        }else{
	            JOptionPane.showMessageDialog(mainFrame, "Was Unable to change status");
	        }
	        closeWindow();
	        addObjects(Lid); 
//	        addPropertiesBox();
//	        addToPost();
        }
        //IF CHANGING IT TO ACTIVE STATUS THEY NEED TO PAY FEE
        //should send status and property to the controller so it can update the database
        
    }
    
    //This decorates the Messages Jpanel with each message in the landlord has recieved with newest at the top
    // If none are called then it says you have no new messages
    //it also puts a notifaction checkbox at the top for landlords to pick if they want notifications on when they login
    private void addToMessages(){
    	
    	messages = new JPanel(new GridLayout(0, 1,0, 20));
        messages.setSize(500, 600);
        messages.setLayout(new GridLayout(0, 1, 0, 20));
       
        Vector<String> mems = dbController.getEmail(Lid);
        System.out.println("Mems .size is: " + mems.size());
        JPanel mPanel;
        if(mems.size() > 0){
            //JOptionPane.showMessageDialog(mainFrame, "You Have new Messages check inbox tab to view them");
            mPanel = new JPanel(new GridLayout(0, 1, 0, 20));
            mPanel.setSize(500, 600);
            mPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 300/mems.size(), 50));
            System.out.println("it is not empty");
        }else{
            mPanel = new JPanel(null);
            mPanel.setSize(500, 600);
            mPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 0, 50));
            JLabel noM = new JLabel("You Have No New Messages");
            noM.setFont(normalFont);
            noM.setForeground(Color.black);
            
            mPanel.add(noM);
            System.out.println("it is empty should add");
        }

        boolean b = dbController.getNotifications(Lid.toString());
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
        
        mPanel.add(notifs);
        
  

       for(int i = mems.size() - 1; i >= 0; i--){
        String tempStr = mems.get(i);

        JTextArea temp = new JTextArea(tempStr);
        temp.setFont(normalFont);
        temp.setEditable(false);
        temp.setLineWrap(true);
        temp.setWrapStyleWord(true);
        mPanel.add(temp);

       }
        
       


       JScrollPane scrollPane = new JScrollPane(mPanel);    
       scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
       messages.add(scrollPane);
       
    }


    //Method called when action 'confirm register button is pressed' 
    //will read in all the values in the boxes then send those values to be handled by the database Controller
    //Then  calls addObjects to update comboboxes used for changing status and posting properties 
    private void confirmRegisterButtonPressed(){
        String tOfProp = typeOfPropBox.getSelectedItem().toString();
        String numB = numOfBedsBox.getSelectedItem().toString();
        String numB2 = numOfBathsBox.getSelectedItem().toString();
        String isFurn = furnishedBox.getSelectedItem().toString();
        String area  = locationBox.getSelectedItem().toString();

        pId = dbController.registerProperty(tOfProp, Integer.parseInt(numB), Integer.parseInt(numB2), Boolean.parseBoolean(isFurn), area, Lid);
        closeWindow();
        addObjects(Lid);     
        }

    //Method called when action 'confirm register and post button is pressed' 
    //will read in all the values in the boxes then send those values to be handled by the database Controller
    //then also gives user fees to select from and calls databse controller to update status to active since it has been posted
    //Then  calls addObjects to update comboboxes used for changing status and posting properties 
    private void confirmRegAndPostButtonPressed(){
        confirmRegisterButtonPressed();

        JPanel p = new JPanel(new GridLayout(0 , 1, 0, 20));
        p.add(new JLabel("Select Subscription Model for Property"));
        p.add(fees);

        JOptionPane.showMessageDialog(mainFrame, p);
        //change 32 to the prop id;
        dbController.changeStatus(pId.toString() , "active");
     
        closeWindow();
        addObjects(Lid); 
       
    }

    //this function will decorate the JPanel for registration form
    //setting what all labels and text boxes will look like and setting layout
    private void addToReg(){

        reg.setBorder(BorderFactory.createEmptyBorder(15, 40, 10, 40));

        JLabel typeOfProperty = new JLabel("Type of Property");
        typeOfProperty.setForeground(Color.BLACK);
        typeOfProperty.setFont(normalFont);
        reg.add(typeOfProperty);
        reg.add(typeOfPropBox);

        JLabel bed= new JLabel("Number of Bedrooms:");
        bed.setForeground(Color.BLACK);
        bed.setFont(normalFont);
        reg.add(bed);
        reg.add(numOfBedsBox);

        JLabel bath = new JLabel("Number of Bathrooms:");
        bath.setForeground(Color.BLACK);
        bath.setFont(normalFont);
        reg.add(bath);
        reg.add(numOfBathsBox);

        JLabel f = new JLabel("Furnished or Unfurnished?");
        f.setForeground(Color.BLACK);
        f.setFont(normalFont);
        reg.add(f);
        reg.add(furnishedBox);

        JLabel a = new JLabel("Area of City");
        a.setForeground(Color.BLACK);
        a.setFont(normalFont);
        reg.add(a);
        reg.add(locationBox);

        reg.add(confirmRegister);
        reg.add(confirmRegAndPost);

        

    }


    //addToPost decorates the post JPanel with the proper comboBoxes and Labels 
    private void addToPost(){
        post= new JPanel(new GridLayout(0, 1, 0, 20));
        post.setBorder(BorderFactory.createEmptyBorder(100, 40, 200, 40));
        JLabel whatProp = new JLabel("Select Property to Post");

        whatProp.setForeground(Color.BLACK);
        whatProp.setFont(normalFont);
        post.add(whatProp);


        post.add(pB); 

        JLabel feeL = new JLabel("Select subscription Option");
        feeL.setForeground(Color.BLACK);
        feeL.setFont(normalFont);
        post.add(feeL);

        fees.setForeground(Color.BLACK);
        fees.setFont(normalFont);
        post.add(fees2);
        
        

        post.add(confirmPost);
        

    }

    // decorates the changeStatus panel with proper combo Boxes labels and buttons

    private void addToChangeOfStatus(){
        changeOfStatus.setBorder(BorderFactory.createEmptyBorder(115, 40, 200, 40));

        JLabel whatProp = new JLabel("Select Property to Change Status of");
        whatProp.setForeground(Color.BLACK);
        whatProp.setFont(normalFont);
        changeOfStatus.add(whatProp);

        changeOfStatus.add(propertiesBox);

        JLabel whatState = new JLabel("Select Status to Change to");
        whatState.setForeground(Color.BLACK);
        whatState.setFont(normalFont);
        changeOfStatus.add(whatState);
        changeOfStatus.add(statusBox);

        changeOfStatus.add(confirmStatusChange);
    }

    //This is the actionlsitener for the notification toggle button and will send status of checkmark to the dbcontroller
    private void changeNotif(){
        if(dbController.getNotifications(this.Lid.toString())){
            dbController.setNotifications(Lid.toString(), false);
        }else{
            dbController.setNotifications(Lid.toString(), true);
        }
    }
    
}
