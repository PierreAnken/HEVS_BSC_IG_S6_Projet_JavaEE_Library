package library.businessobject;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Library")
public class Library {

	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@OneToMany(mappedBy="library", cascade={CascadeType.REMOVE})
	private Set<Book> books = new HashSet<Book>();
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name = "library_manager")
	private Set<Librarian> librarians = new HashSet<Librarian>();
	
	public Set<Librarian> getLibrarians() {
		return librarians = new HashSet<Librarian>();
	}
	
	public void setLibrarians(Set<Librarian> librarians) {
		this.librarians = librarians;
	}
	
	public void addLibrarian(Librarian l) {
		librarians.add(l);
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
