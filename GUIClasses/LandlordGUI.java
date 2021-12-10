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
    private JPanel reg , post , changeOfStatus; // can add prop list panel so landlords can view all their properties
    private JTabbedPane tp;
    private DatabaseController dbController;
    private int Lid;
    private Integer pId;
    //private Property properties[];
    private String prop[] = {"-------"}; // use for properties box populate with properties.name
    private String prop2[] = {"-------"};
    private JComboBox fees;
    private JComboBox fees2 , pB;
    Vector<String> feeBx = new Vector<String>();
    Vector<String> feeBx2 = new Vector<String>();

    public LandlordGUI(){

        mainFrame = new JFrame("Landlord window");
        mainFrame.setSize(500, 600);
        mainFrame.add(controlPanel);
        controlPanel.setLayout(null);
        //needs to use controller to get a list of properties from the database and populat properties[]
        dbController = new DatabaseController();
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
        //addObjects();

    }

    public void addObjects(int id){
        controlPanel.removeAll();
        controlPanel.revalidate();
        controlPanel.repaint();
        Lid = id;
        addPropertiesBox();
        addToReg();
        addToPost();
        addToChangeOfStatus();
        addListeners();
       

        tp.add("Register new Property!", reg);
        tp.add("Post Property!", post);
        tp.add("Change Status of Property", changeOfStatus);
        tp.add("Logout", new JPanel());
        mainFrame.add(tp);
        
        mainFrame.setVisible(true);
        

    }

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
                    logout();
                }
                
            }
        };
        tp.addChangeListener(cl);
    }


    private void addPropertiesBox(){


        Vector<Integer> properties = dbController.getMyProperties(Lid);
        
        for(int i = 0; i < properties.size(); i++){
        
        prop = Arrays.copyOf(prop, prop.length + 1);
        prop[prop.length -1 ] = String.valueOf("propertyID: " + properties.get(i));

        

        }

        Vector<Integer> propertiesNR = dbController.getNRProperties(Lid);

        for(int i = 0; i < propertiesNR.size(); i++){
            prop2 = Arrays.copyOf(prop2, prop2.length + 1);
            prop2[prop2.length -1 ] = String.valueOf("propertyID: " + properties.get(i));
        }
        propertiesBox = new JComboBox(prop);

        pB = new JComboBox(prop2);

    }

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

    

    private void confirmPostButtonPressed(){
        String propSelected = pB.getSelectedItem().toString();
        System.out.println(propSelected);
        //boolean b = dbController.changeStatus(propSelected.substring(11, propSelected.length()), "rented");

        post.removeAll();
        post.repaint();
        dbController.changeStatus(propSelected.substring(11, propSelected.length()), "active");
        addPropertiesBox();

    }

    private void confirmStatusChangeButtonPressed(){
        String status = (String)statusBox.getSelectedItem();
        System.out.println(status);

        String propSelected = propertiesBox.getSelectedItem().toString();
        System.out.println(propSelected);


        boolean b = dbController.changeStatus(propSelected.substring(11, propSelected.length()), status);

        if(b){
            JOptionPane.showMessageDialog(mainFrame, "Status Changes Successfully!");
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Was Unable to change status");
        }

        addPropertiesBox();
        addToPost();
        //IF CHANGING IT TO ACTIVE STATUS THEY NEED TO PAY FEE
        //should send status and property to the controller so it can update the database
    }

    private void confirmRegisterButtonPressed(){
        String tOfProp = typeOfPropBox.getSelectedItem().toString();
        String numB = numOfBedsBox.getSelectedItem().toString();
        String numB2 = numOfBathsBox.getSelectedItem().toString();
        String isFurn = furnishedBox.getSelectedItem().toString();
        String area  = locationBox.getSelectedItem().toString();
        //sedn these values to database to register them
        System.out.println(tOfProp);
        System.out.println(numB);
        System.out.println(numB2);
        System.out.println(isFurn);
        System.out.println(area);
        //send Lid as well

        pId = dbController.registerProperty(tOfProp, Integer.parseInt(numB), Integer.parseInt(numB2), Boolean.parseBoolean(isFurn), area, Lid);
        addPropertiesBox();
    }

    private void confirmRegAndPostButtonPressed(){
        confirmRegisterButtonPressed();

        JPanel p = new JPanel(new GridLayout(0 , 1, 0, 20));
        p.add(new JLabel("Select Subscription Model for Property"));
        p.add(fees);

        JOptionPane.showMessageDialog(mainFrame, p);
        //change 32 to the prop id;
        dbController.changeStatus(pId.toString() , "active");
        //String tOfProp = typeOfPropBox.getSelectedItem().toString();
       // String numB = numOfBedsBox.getSelectedItem().toString();
        //String numB2 = numOfBathsBox.getSelectedItem().toString();
        //String isFurn = furnishedBox.getSelectedItem().toString();
        //String area  = locationBox.getSelectedItem().toString();
        addPropertiesBox();
        //once sent to database find name of property and change status to active
        //Also must show option to pay for posting if they want to cancel go back to jsut register
    }

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

    private void addToPost(){
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
}
