/*
  How 409 project worked
  Client class is made and is constructed, it calls a function
  fucntion makes another class which starts a GUI 
*/
package GUIClasses;
//Other GUIs inherit this class
import javax.swing.*;                           //package for GUIs
import java.awt.*;

public abstract class GUI extends JFrame{
  JFrame frame; 
  JPanel panel;

  GUI(){
    frame = new JFrame("Property Manager");
    panel = new JPanel();
    frame.setContentPane(panel);
    frame.setSize(1000, 1000);
    frame.setVisible(true);
  }
}
