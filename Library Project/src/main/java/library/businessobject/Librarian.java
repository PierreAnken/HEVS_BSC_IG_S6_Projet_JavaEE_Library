package library.businessobject;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Librarian extends User {
	
	private int employeeId;
	private String email;
	private String password, firstname, lastname;

	@ManyToMany
	private Set<Library> libraries;
	
	public Set<Library> getLibraries() {
		return libraries;
	}

	public void setLibrarys(Set<Library> libraries) {
		this.libraries = libraries;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
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

	public void setLibraries(Set<Library> libraries) {
		this.libraries = libraries;
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
