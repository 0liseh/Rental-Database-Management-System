public class Property {
  
  private  int propertyid;
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
}
