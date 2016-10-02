package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//this is literally just a custom object, with getters and setters
//just like eclipse can make itself
//this model needs to coincide directly to how the DB is set up.
public class Voter {

	private IntegerProperty voterId;
	private final StringProperty firstName;
	private final StringProperty lastName;
	private final StringProperty address;
	private final StringProperty zipcode;
	private final StringProperty county;
	private final StringProperty state;
	private final StringProperty phone;

	public Voter(int voterId, String firstName, String lastName, String address, String county, String state,
			String zipcode, String phone) {
		this.voterId = new SimpleIntegerProperty(voterId);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.address = new SimpleStringProperty(address);
		this.zipcode = new SimpleStringProperty(zipcode);
		this.county = new SimpleStringProperty(county);
		this.state = new SimpleStringProperty(state);
		this.phone = new SimpleStringProperty(phone);
	}
	public int getVoterId() {
		return voterId.get();
	}
	public void setVoterId(int voterId) {
		this.voterId.set(voterId);
	}
	public IntegerProperty vidProperty() {
        return voterId;
    }
	public String getFirstName() {
		return firstName.get();
	}
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	public StringProperty firstNameProperty() {
        return firstName;
    }
	public String getLastName() {
		return lastName.get();
	}
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	public StringProperty lastNameProperty() {
        return lastName;
    }
	public String getAddress() {
		return address.get();
	}
	public void setAddress(String address) {
		this.address.set(address);
	}
	public StringProperty addressProperty() {
        return address;
    }
	public String getZipcode() {
		return zipcode.get();
	}
	public void setZipcode(String zipcode) {
		this.zipcode.set(zipcode);
	}
	public StringProperty zipProperty() {
        return zipcode;
    }
	public String getCounty() {
		return county.get();
	}
	public void setCounty(String county) {
		this.county.set(county);
	}
	public StringProperty countyProperty() {
        return county;
    }
	public String getState() {
		return state.get();
	}
	public void setState(String state) {
		this.state.set(state);
	}
	public StringProperty stateProperty() {
        return state;
    }
	public String getPhone() {
		return phone.get();
	}
	public void setPhone(String phone) {
		this.phone.set(phone);
	}
	public StringProperty phoneProperty() {
        return phone;
    }


}
