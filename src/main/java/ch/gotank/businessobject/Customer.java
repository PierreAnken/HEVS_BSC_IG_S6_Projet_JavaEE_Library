package ch.gotank.businessobject;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class Customer extends Person {

	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	// constructors
	public Customer() {
		super();
	}

	public Customer(String lastname, String firstname, int status) {
		super(lastname, firstname);
		this.status = status;
	}
}