package GUIClasses;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;
import java.io.*;

public class ManagerGUI extends GUI{
  
    private JButton changeFee, getPeriodicalReport , viewLandlords , viewProperties , viewRenters;
    
    JLabel header;
    JScrollBar scroll = new JScrollBar();
    
  public ManagerGUI(){
    
    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    scroll.setBounds(490, 0, 10 , 500);

    changeFee  = new JButton("Update Fee");
    getPeriodicalReport = new JButton("Get Perodical Report");
    viewLandlords  = new JButton("View Landlords");
    viewProperties = new JButton("View Properties");
    viewRenters  = new JButton("View Renters");

    header = new JLabel("Select Service", JLabel.CENTER);
    header.setForeground(Color.BLACK);
    header.setFont(new Font("Courier", Font.PLAIN, 30));
    header.setBounds(50, 10 , 400, 40);
    

    getPeriodicalReport.setBounds(40 , 50, 420, 30);
    getPeriodicalReport.setBackground(Color.GRAY);
    getPeriodicalReport.setForeground(Color.WHITE);
    getPeriodicalReport.setFont(normalFont);
    
    getPeriodicalReport.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        PeriodicalButtonPressed();
      }
    });

    viewLandlords.setBounds(40 , 90, 420, 30);
    viewLandlords.setBackground(Color.GRAY);
    viewLandlords.setForeground(Color.WHITE);
    viewLandlords.setFont(normalFont);
   
    viewLandlords.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        LandlordButtonPressed();
      }
    });

    viewProperties.setBounds(40 , 130, 420, 30);
    viewProperties.setBackground(Color.GRAY);
    viewProperties.setForeground(Color.WHITE);
    viewProperties.setFont(normalFont);
   
    viewProperties.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        PropertyButtonPressed();
      }
    });

    viewRenters.setBounds(40 , 170, 420, 30);
    viewRenters.setBackground(Color.GRAY);
    viewRenters.setForeground(Color.WHITE);
    viewRenters.setFont(normalFont);
    
    viewRenters.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        RenterButtonPressed();
      }
    });
    
    changeFee.setBounds(40 , 210, 420, 30);
    changeFee.setBackground(Color.GRAY);
    changeFee.setForeground(Color.WHITE);
    changeFee.setFont(normalFont);
   
    changeFee.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        changeFeeButtonPressed();
      }
    });

  }

  public void addElements(){
    panel.removeAll();
    panel.revalidate();
    panel.repaint();

    panel.add(header);
    panel.add(changeFee);
    panel.add(viewRenters);
    panel.add(viewProperties);
    panel.add(viewLandlords);
    panel.add(getPeriodicalReport);
    panel.add(logout);
    frame.setVisible(true);
  }

  private void changeFeeButtonPressed(){
    //throws exception if cancel button is pressed ay need to cathc error
    String feeAmount = JOptionPane.showInputDialog(panel, "What are you changing the fee to?");
    try{
      double feeDouble = Double.parseDouble(feeAmount);
      double twoDec = Math.round(feeDouble * 100.0) / 100.0;
      JOptionPane.showMessageDialog(panel, "You have successfully changed the fee to: $" + twoDec);
      //should call database controller here to update fee using twoDec
    }catch (NumberFormatException e){
      JOptionPane.showMessageDialog(panel, "Incorrect input unable to change fee");
      changeFeeButtonPressed(); 
    }
    
    
  }

  
  
  private void PeriodicalButtonPressed(){
    
    
  }
  private void LandlordButtonPressed(){
    panel.removeAll();
    panel.revalidate();
    panel.repaint();

    panel.add(back);
    panel.add(logout);
    header = new JLabel("Landlords", JLabel.CENTER);
    header.setForeground(Color.BLACK);
    header.setFont(new Font("Courier", Font.PLAIN, 30));
    header.setBounds(50, 10 , 400, 40);
    
    panel.add(header);
    panel.add(scroll);

    
    


  }

  
  private void PropertyButtonPressed(){
    panel.removeAll();
    panel.revalidate();
    panel.repaint();

    panel.add(back);
    panel.add(logout);
    header = new JLabel("Properties", JLabel.CENTER);
    header.setForeground(Color.BLACK);
    header.setFont(new Font("Courier", Font.PLAIN, 30));
    header.setBounds(50, 10 , 400, 40);
    
    panel.add(header);
    panel.add(scroll);
    
  }
  private void RenterButtonPressed(){
    panel.removeAll();
    panel.revalidate();
    panel.repaint();

    panel.add(back);
    panel.add(logout);
    header = new JLabel("Renters", JLabel.CENTER);
    header.setForeground(Color.BLACK);
    header.setFont(new Font("Courier", Font.PLAIN, 30));
    header.setBounds(50, 10 , 400, 40);
    
    panel.add(header);
    panel.add(scroll);
  }

  private void changePropertyStatus(){
    

  }
  @Override
  public void backButtonPressed(){
    addElements();
  }
  

}
