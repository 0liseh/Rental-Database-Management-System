package GUIClasses;
import ControllerClasses.UnregisteredRenterController;

public class UnregisteredRenterGUI extends GUI{

  private renterController renter;
  private JButton search;

  public GUI(){

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


  @Override
  public void backButtonPressed(){
    addElements();
  }

  /*public searchProperties(){

  }

  public contactLandlord(){

  }

  public display(){

  }*/

}
