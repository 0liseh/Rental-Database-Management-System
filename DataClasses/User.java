package DataClasses;
abstract class User{
  private String name;
  private int id;
  private String email;
  private String phoneNumber;
  private String password;
  public User() {
	  
  }
  public User(String name, int id, String email, String phoneNumber, String password) {
	  this.name = name; 
	  this.id = id; 
	  this.email = email; 
	  this.phoneNumber = phoneNumber; 
	  this.password = password;
  }
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
