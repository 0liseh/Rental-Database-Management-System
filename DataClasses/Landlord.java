import java.util.Vector;

public class Landlord extends User{
	private Vector<Property> properties;
	  
	public Landlord(String name, int id, String email, String phoneNumber, String password, Vector<Property> properties){
		super(name,id,email,phoneNumber,password);
		this.properties = new Vector(properties);
	}
	public Vector<Property> getProperties() {
		return properties;
	}
	public void setProperties(Vector<Property> properties) {
		this.properties = new Vector(properties);
	}
}
