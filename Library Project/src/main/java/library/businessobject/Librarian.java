package library.businessobject;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Librarian extends User {
	
	private int employeeId;

	/*
	 * @ManyToMany private Set<Library> libraries;
	 
	
	public Set<Library> getLibraries() {
		return libraries;
	}

	public void setLibrarys(Set<Library> libraries) {
		this.libraries = libraries;
	}*/
	
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	// Constructors
	public Librarian() {
	}

	public Librarian(String email, String password, String firstname, String lastname, int empId) {
		super(email, password, firstname, lastname);
		this.employeeId = empId;
	}

	public Librarian(String email, String password, String firstname, String lastname, int empId, Address address) {
		super(email, password, firstname, lastname, address);
		this.employeeId = empId;
	}
}
