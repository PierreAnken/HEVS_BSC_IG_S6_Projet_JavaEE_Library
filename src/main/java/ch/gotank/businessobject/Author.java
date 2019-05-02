package ch.gotank.businessobject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Author")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private String lastname;
	private String firstname;
	
	// Start constructors -->
	public Author() {
	}

	public Author(String lastname, String firstname) {
		this.lastname = lastname;
		this.firstname = firstname;
	}
	// <-- End constructors

	// Start Getters & Setters -->	
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	// <-- End Getters & Setters
	
	@Override
	public String toString() {
		String auth = id + " - " + lastname + " " + firstname;
		return auth;
	}

}
