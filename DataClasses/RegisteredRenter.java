
class RegisteredRenter extends User{

  private boolean notifications;
  private String observerState;
  private DatabaseController reference;

  public void update(){

  }

  public RegisteredRenter(String name, int id, String email, String phoneNumber, String password, int notifications){
	  super(name,id,email,phoneNumber,password);
	  if(notifications == 1) {
		  this.notifications = true; 
	  }else {
		  this.notifications = false;
	  }
  }
}
