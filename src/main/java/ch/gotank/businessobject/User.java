package ch.gotank.businessobject;

import javax.persistence.Entity;

@Entity
public class User extends Person {

	private String email;
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// constructors
	public User() {
		super();
	}

	public User(String lastname, String firstname, String email, String password) {
		super(lastname, firstname);
		this.email = email;
		this.password = password;
	}
}