package GUIClasses;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ManagerGUI extends GUI{
  
    private JButton changeFee, getPeriodicalReport , viewLandlords , viewProperties , viewRenters, back , logout;
    
  public ManagerGUI(){
    
    changeFee  = new JButton("Update Fee");
    getPeriodicalReport = new JButton("Get Perodical Report");
    viewLandlords  = new JButton("View Landlords");
    viewProperties = new JButton("View Properties");
    viewRenters  = new JButton("View Renters Information");

    JLabel header = new JLabel("Select Service", JLabel.CENTER);
    header.setForeground(Color.BLACK);
    header.setFont(new Font("Courier", Font.PLAIN, 30));
    header.setBounds(10, 10 , 480, 40);
    panel.add(header);

    getPeriodicalReport.setBounds(40 , 50, 420, 30);
    getPeriodicalReport.setBackground(Color.GRAY);
    getPeriodicalReport.setForeground(Color.WHITE);
    getPeriodicalReport.setFont(normalFont);
    panel.add(getPeriodicalReport);
    getPeriodicalReport.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        PeriodicalButtonPressed();
      }
    });

    viewLandlords.setBounds(40 , 90, 420, 30);
    viewLandlords.setBackground(Color.GRAY);
    viewLandlords.setForeground(Color.WHITE);
    viewLandlords.setFont(normalFont);
    panel.add(viewLandlords);
    viewLandlords.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        LandlordButtonPressed();
      }
    });

    viewProperties.setBounds(40 , 130, 420, 30);
    viewProperties.setBackground(Color.GRAY);
    viewProperties.setForeground(Color.WHITE);
    viewProperties.setFont(normalFont);
    panel.add(viewProperties);
    viewProperties.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        PropertyButtonPressed();
      }
    });

    viewRenters.setBounds(40 , 170, 420, 30);
    viewRenters.setBackground(Color.GRAY);
    viewRenters.setForeground(Color.WHITE);
    viewRenters.setFont(normalFont);
    panel.add(viewRenters);
    viewRenters.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        RenterButtonPressed();
      }
    });
    
    changeFee.setBounds(40 , 210, 420, 30);
    changeFee.setBackground(Color.GRAY);
    changeFee.setForeground(Color.WHITE);
    changeFee.setFont(normalFont);
    panel.add(changeFee);
    changeFee.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        changeFeeButtonPressed();
      }
    });
    


  }

  private void changeFeeButtonPressed(){
    String feeAmount = JOptionPane.showInputDialog(panel, "What are you changing the fee to?");
    JOptionPane.showMessageDialog(panel, "You have successfully changed the fee to: $" + feeAmount);
  }
  
  private void PeriodicalButtonPressed(){
    
  }
  private void LandlordButtonPressed(){
    
  }
  private void PropertyButtonPressed(){
    
  }
  private void RenterButtonPressed(){
    
  }

    
    

    

  public void changePropertyStatus(){

  }
  

}
