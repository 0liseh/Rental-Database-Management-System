package GUIClasses;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;
import java.io.*;



public class RegisteredRenterGUI extends UnregisteredRenterGUI{

    private JPanel newProps; // propListPanel instead of view all
    
    public RegisteredRenterGUI(){
        
        mainFrame.setTitle("Renter window");
        
        
        newProps = new JPanel();
        addToNewProps();
        
    }

    private void addToNewProps(){
        newProps.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        JLabel header = new JLabel("Recently Added", JLabel.CENTER);
        header.setForeground(Color.BLACK);
        header.setFont(normalFont);
        newProps.add(header);
        tp.add("Recently added" , newProps);
    }

    
}
