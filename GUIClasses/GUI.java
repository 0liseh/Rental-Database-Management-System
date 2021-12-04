/*
  How 409 project worked
  Client class is made and is constructed, it calls a function
  fucntion makes another class which starts a GUI 
*/
package GUIClasses;
//Other GUIs inherit this class
import javax.swing.*;                           //package for GUIs
import java.awt.*;

abstract class GUI extends JFrame{
  JFrame frame; 
  JPanel panel;
  private Font normalFont = new Font("Courier", Font.PLAIN, 15);
  

  GUI(){
    frame = new JFrame("Property Application");
    panel = new JPanel();
    panel.setLayout(null);
    frame.setContentPane(panel);
    
    frame.setSize(500, 500);
    frame.setLocationRelativeTo(null); // Makes window open in middle of screen
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // If window is closed, stop the program
    // /frame.setResizable(false);
    
    
    
  

    frame.setVisible(true);
  }
  public void logout(){

  }
}
