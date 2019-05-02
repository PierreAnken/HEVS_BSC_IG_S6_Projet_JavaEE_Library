package ch.gotank.businessobject;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	private String street;
	private String zipCode;
	private String city;

	public String getzipCode() {
		return zipCode;
	}
	public void setzipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
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