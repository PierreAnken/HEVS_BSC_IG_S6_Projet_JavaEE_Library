package ch.gotank.businessobject;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class Librarian extends Person {

	private float salary;

	public float getsalary() {
		return salary;
	}

	public void setsalary(float salary) {
		this.salary = salary;
	}
	
	public Librarian() {
		super();
	}

	public Librarian(String lastname, String firstname, float salary) {
		super(lastname, firstname);
		this.salary = salary;
	}
}