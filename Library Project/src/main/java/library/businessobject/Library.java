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

	private String Name;

	@Id
	@GeneratedValue(strategy = GenerationType. IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy="library")
	private Set<Location> locations;
	
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
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
}
