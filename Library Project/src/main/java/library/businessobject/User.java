package library.businessobject;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public abstract class User {
	
	
	@Id
	private String email;
	private String firstname, lastname;
	
	@Embedded
	protected Address address;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	// Constructors
	public User() {
	}
	
	public User(Address address) {
		this.address = address;
	}

	public User(String email, String firstname, String lastname) {
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public User(String email, String firstname, String lastname, Address address) {
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
	}
}
