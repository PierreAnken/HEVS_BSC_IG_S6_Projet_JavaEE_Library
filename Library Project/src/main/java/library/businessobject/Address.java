package library.businessobject;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable {
	
	
	private static final long serialVersionUID = -2549929609831598551L;
	
	private String zipCode, street, city;

	public String getzipCode() {
		return zipCode;
	}

	public void setzipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getstreet() {
		return street;
	}

	public void setstreet(String street) {
		this.street = street;
	}

	public String getcity() {
		return city;
	}

	public void setcity(String city) {
		this.city = city;
	}

	// constructors
	public Address() {
	}

	public Address(String zipCode, String street, String city) {
		this.zipCode = zipCode;
		this.street = street;
		this.city = city;
	}
}