


public class Property {

  private int propertyid;

  

  private String propertyType;
  private int noOfBed;
  private int noOfBath;
  private boolean furnished;
  private String area;
  private String status;
  private int landlordID;


 
  public Property(){

  }

  public Property(int pid, String pType, int nBed, int nBath, boolean f, String area, String stat, int llID ){
    this.propertyid = pid;
    this.propertyType = pType;
    this.noOfBed = nBed;
    this.noOfBath = nBath;
    this.furnished = f;
    this.area = area;
    this.status = stat;
    this.landlordID = llID;

  }

  public int getPropertyid() {
    return this.propertyid;
  }

  public void setPropertyid(int propertyid) {
    this.propertyid = propertyid;
  }

  public String getPropertyType() {
    return this.propertyType;
  }

  public void setPropertyType(String propertyType) {
    this.propertyType = propertyType;
  }

  public int getNoOfBed() {
    return this.noOfBed;
  }

  public void setNoOfBed(int noOfBed) {
    this.noOfBed = noOfBed;
  }

  public int getNoOfBath() {
    return this.noOfBath;
  }

  public void setNoOfBath(int noOfBath) {
    this.noOfBath = noOfBath;
  }

  public boolean isFurnished() {
    return this.furnished;
  }

  public void setFurnished(boolean furnished) {
    this.furnished = furnished;
  }

  public String getArea() {
    return this.area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getLandlordID() {
    return this.landlordID;
  }

  public void setLandlordID(int landlordID) {
    this.landlordID = landlordID;
  }


}
