
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;
import java.io.*;


public class RegisterGUI extends  GUI{

    //variables and objects declared
    private JTextField un, pw, ut , n , pn;
    private JButton login , guest;
    private JLabel username , password, userType, name , phoneNumber;
    private DatabaseController dbController;
    JComboBox nu;
    String type = "yolo";
    public int id;
    String loggingOut = "NO";

    //initializes controlPanel and controller
    //will also call functions to initialize the textFields and buttons
    public RegisterGUI(){
        controlPanel.setLayout(new GridLayout(0 , 1 ,0 , 20));
        //controlPanel.setBorder(BorderFactory.createEmptyBorder(15, 40, 10, 40));
        dbController = new DatabaseController();
        setTextFields();
        setButtons();
        //addObjects();
    }

    //will initialize panel and caall functions to initialze comboBoxes then
    //will add all objects to the Panel
    public void addObjects()
    {
        JPanel temp = new JPanel(new GridLayout( 0, 1, 0 ,20));
        temp.setSize(500, 600);
       String t[] = {"renter", "Landlord"};
        nu = new JComboBox(t);
        controlPanel.removeAll();
        controlPanel.revalidate();
        controlPanel.repaint();
        temp.add(headerLabel);
        temp.add(name);
        temp.add(n);
        temp.add(phoneNumber);
        temp.add(pn);
        temp.add(username);
        temp.add(un);
        temp.add(password);
        temp.add(pw);
        temp.add(userType);
        temp.add(nu);
        temp.add(login);
        temp.add(guest);
        JScrollPane tScroll = new JScrollPane(temp);
        tScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        controlPanel.add(tScroll);
        mainFrame.setVisible(true);
    }

    //sets what the Labels and TextFields looks like
    private void setTextFields(){
        un = new JTextField();
        pw = new JTextField();
        ut = new JTextField();
        n= new JTextField();
        pn = new JTextField();


        un.setFont(normalFont);
        pw.setFont(normalFont);
        ut.setFont(normalFont);
        n.setFont(normalFont);
        pn.setFont(normalFont);
        

        headerLabel = new JLabel("Register to Property Tracker!", JLabel.CENTER);
        headerLabel.setForeground(Color.BLACK);
        headerLabel.setFont(new Font("Courier", Font.PLAIN, 25));

        name = new JLabel("Name:");
        name.setForeground(Color.BLACK);
        name.setFont(normalFont);

        phoneNumber =  new JLabel("Phone Number:");
        phoneNumber.setForeground(Color.BLACK);
        phoneNumber.setFont(normalFont);

        username =  new JLabel("Email:");
        username.setForeground(Color.BLACK);
        username.setFont(normalFont);

        password = new JLabel("Password:");
        password.setForeground(Color.BLACK);
        password.setFont(normalFont);


       


        userType = new JLabel("User Type:");
        userType.setForeground(Color.BLACK);
        userType.setFont(normalFont);
    }

    //sets what the buttons look like and adds event listeners to them
    private void setButtons(){

        login = new JButton("Sign Up");
        guest = new JButton("Guest");


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



    }
    //changes type of gui needed if the geust button is pressed 
    private void guestButtonPressed()
    {
        type = "Unregistered Renter";
    }

    //if loginButton is pressed it will take all values inputted and use databaseController to create a user based on this
    //it will tehn send the user back to the login screen
    private void loginButtonPressed(){
        String user = un.getText().toString();
        System.out.println(user);

        String pass = pw.getText().toString();
        System.out.println(pass);

        String nam = n.getText().toString();
        String phone = pn.getText().toString();
        String userType = nu.getSelectedItem().toString();

        //call dbController . create user

        // /registerUser(String name, int phNo, String mail, String pwd, String uType) {

        id = dbController.registerUser(nam , phone, user, pass , userType);

        type = userType;
            loggingOut = "YES";
        

        //send these values to check database and see if it's a user and what type of user it is

    }
    //returns type of GUI needed
    public String gettype(){
        return type;
    }
    //returns user to login screen by telling it has been logged out of register -> registrateion complete
    public String getLoggingOut(){
        return loggingOut;
    }

}
