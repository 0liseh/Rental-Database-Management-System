package DataClasses;

import java.util.Vector;

public class Landlord extends User{
	private Vector<Property> properties = new Vector<Property>();
	  
	public Landlord(String name, int id, String email, String phoneNumber, String password, Vector<Property> properties){
		super(name,id,email,phoneNumber,password);
		this.properties = properties;
	}
	public Vector<Property> getProperties() {
		return properties;
	}
	public void setProperties(Vector<Property> properties) {
		this.properties = new Vector(properties);
	}
}
