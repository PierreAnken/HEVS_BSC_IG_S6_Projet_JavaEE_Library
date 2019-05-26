package library.businessobject;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Librarian extends User {
	

	@ManyToMany 
	private Set<Library> libraries;
	
	public Set<Library> getLibraries() {
		return libraries;
	}

	public void setLibrarys(Set<Library> libraries) {
		this.libraries = libraries;
	}
	

	// Constructors
	public Librarian() {
	}

	public Librarian(String email, String password, String firstname, String lastname, int empId) {
		super(email, password, firstname, lastname);
	}

	public Librarian(String email, String password, String firstname, String lastname, int empId, Address address) {
		super(email, password, firstname, lastname, address);
	}
}
