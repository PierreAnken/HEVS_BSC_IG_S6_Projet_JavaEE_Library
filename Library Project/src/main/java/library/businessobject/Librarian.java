package library.businessobject;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class Librarian extends User {
	
	@ManyToMany(mappedBy ="librarians")
	private Set<Library> libraries = new HashSet<Library>();
	
	// Constructors
	public Librarian() {
	}

	public Librarian(String email, String firstname, String lastname) {
		super(email, firstname, lastname);
	}

	public Librarian(String email, String firstname, String lastname, Address address) {
		super(email, firstname, lastname, address);
	}
	
	public Map<String, Object> convertToMap(){
		ObjectMapper oMapper = new ObjectMapper();
		Map<String, Object> librarianMap = oMapper.convertValue(this, Map.class);
		return librarianMap;
	}
	
	public static Librarian convertFromMap(Map<String, Object> librarianMap){
		ObjectMapper oMapper = new ObjectMapper();
		Librarian librarian = oMapper.convertValue(librarianMap, Librarian.class);
		return librarian;
	}
}
