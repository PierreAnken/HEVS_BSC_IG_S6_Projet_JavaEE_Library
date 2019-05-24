package library.businessobject;

import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Library {

	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy="library")
	private Set<Book> books;
	
	@ManyToMany
	private Set<Librarian> librarians;
	
	public Set<Librarian> getLibrarians() {
		return librarians;
	}
	
	public void setLibrarians(Set<Librarian> librarians) {
		this.librarians = librarians;
	}
	
	@Embedded
	private Address address;
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Constructors
	public Library() {
	}

	public Library(String name, Address address) {
		this.name = name;
		this.address = address;
	}
	
	public Library(String name) {
		this.name = name;
	}
	
}
