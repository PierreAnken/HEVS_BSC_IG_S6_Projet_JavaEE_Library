package library.businessobject;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Librarian extends User {
	
	@ManyToMany(mappedBy ="librarians")
	private Set<Library> libraries = new HashSet<Library>();
	
	// Constructors
	public Librarian() {
	}

	public Librarian(String email, String password, String firstname, String lastname) {
		super(email, password, firstname, lastname);
	}

	public Librarian(String email, String password, String firstname, String lastname, Address address) {
		super(email, password, firstname, lastname, address);
	}
}
