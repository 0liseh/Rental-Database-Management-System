/*The first UI the user comes in contact with. Will call whatever GUI the user needs*/
//Property_app.java

public class Property_app{

  public static void main(String[] args){
    ManagerGUI mGUI = new ManagerGUI();
    LandlordGUI lGUI = new LandlordGUI();
    RegisteredRenterGUI rrGUI = new RegisteredRenterGUI();
    UnregisteredRenterGUI urrGUI = new UnregisteredRenterGUI();
    LoginGUI x = new LoginGUI();
    RegisterGUI rGUI = new RegisterGUI();

    
   while(true){
    String type = x.gettype();
    String loop = "";
    while(type == "yolo"){
      type = x.gettype();
      if(type == "Manager"){
        loop = "m";
        mGUI.addObjects();
        x.closeWindow();
        break;
      }else if(type == "Landlord"){
        loop = "l";
        lGUI.addObjects(x.id);
        x.closeWindow();
        break;
      }else if(type == "Registered Renter"){
        loop = "rr";
        rrGUI.addObjects();
        rrGUI.addObjects(x.id);
        x.closeWindow();
        break;
      }else if (type == "Unregistered Renter"){
        loop = "urr";
        urrGUI.addObjects();
        x.closeWindow();
        break;
      }else if (type == "Register"){
        loop ="r";
        rGUI.addObjects();
        x.closeWindow();
        break;
      }
      System.out.print("");
    }
    if(loop == "m"){
      String status = mGUI.getLoggingOut();
      while(status == "NO"){
        status = mGUI.getLoggingOut();
        if(status == "YES"){
          mGUI.closeWindow();
          x = new LoginGUI();
          break;
        }

         System.out.print("");
      }

      mGUI = new ManagerGUI();
    }else if(loop == "l"){
      String status = lGUI.getLoggingOut();
      while(status == "NO"){
        status = lGUI.getLoggingOut();
        if(status == "YES"){
          lGUI.closeWindow();
          x = new LoginGUI();
          break;
        }

         System.out.print("");
      }

      lGUI = new LandlordGUI();

    }else if(loop == "rr"){
      String status = rrGUI.getLoggingOut();
      while(status == "NO"){
        status = rrGUI.getLoggingOut();
        if(status == "YES"){
          rrGUI.closeWindow();
          x = new LoginGUI();
          break;
        }

         System.out.print("");
      }

      rrGUI = new RegisteredRenterGUI();

    }else if(loop == "urr"){
      String status = urrGUI.getLoggingOut();
      while(status == "NO"){
        status = urrGUI.getLoggingOut();
        if(status == "YES"){
          urrGUI.closeWindow();
          x = new LoginGUI();
          break;
        }

         System.out.print("");
      }

      urrGUI = new UnregisteredRenterGUI();

    }else if(loop =="r"){
      String status = rGUI.getLoggingOut();
      while(status == "NO"){
        status = rGUI.getLoggingOut();
        if(status == "YES"){
          rGUI.closeWindow();
          x = new LoginGUI();
          break;
        }

         System.out.print("");
      }

      rGUI = new RegisterGUI();
    }

  }





  }
}
