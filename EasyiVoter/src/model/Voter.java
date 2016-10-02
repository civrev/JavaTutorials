package model;

//this is literally just a custom object, with getters and setters
//just like eclipse can make itself
//this model needs to coincide directly to how the DB is set up.
public class Voter {

	private int voterId;
	private String firstName;
	private String lastName;
	private String address;
	private String zipcode;
	private String county;
	private String state;
	private String phone;

	// public getter methods
	public int getvoterId(){ return this.voterId; }
	public String getFirstName(){ return this.firstName; }
	public String getLastName(){ return this.lastName; }
	public String getAddress(){ return this.address; }
	public String getZipCode(){ return this.zipcode; }
	public String getCounty(){ return this.county; }
	public String getState(){ return this.state; }
	public String getPhoneNumber(){ return this.phone; }
	//public setter methods
	public void setvoterId(int id){ this.voterId = id; }
	public void setFirstName(String firstName){ this.firstName = firstName; }
	public void setLastName(String lastName){ this.lastName = lastName; }
	public void setAddress(String address){ this.address = address; }
	public void setZipCode(String zipCode){ this.zipcode = zipCode; }
	public void setCounty(String county){ this.county = county; }
	public void setState(String state){ this.state = state; }
	public void setPhoneNumber(String phone){ this.phone = phone; }
}
