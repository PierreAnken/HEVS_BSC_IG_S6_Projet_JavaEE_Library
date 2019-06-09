package library.businessobject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	
	public static Map<String, Object> convertToMap(Librarian l){
		ObjectMapper oMapper = new ObjectMapper();
		Map<String, Object> librarianMap = oMapper.convertValue(l, Map.class);
		return librarianMap;
	}
	
	public static Librarian convertFromMap(Map<String, Object> librarianMap){
		ObjectMapper oMapper = new ObjectMapper();
		Librarian librarian = oMapper.convertValue(librarianMap, Librarian.class);
		return librarian;
	}
	
	public static List<Map<String, Object>> convertToMapList(List<Librarian> librarians) {
		ArrayList<Map<String, Object>> librariansMap = new ArrayList<Map<String,Object>>();
		for(Librarian l :  librarians) {
			librariansMap.add(Librarian.convertToMap(l));
		}
		return librariansMap;
	}
	
	public static List<Librarian> convertFromMapList(List<Map<String, Object>> librariansMap) {
		ArrayList<Librarian> librarians = new ArrayList<Librarian>();
		for(Map<String, Object> l :  librariansMap) {
			librarians.add(Librarian.convertFromMap(l));
		}
		return librarians;
	}
}
