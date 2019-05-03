package library.businessobject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Library {

	private String Name;

	@Id
	@GeneratedValue(strategy = GenerationType. IDENTITY)
	private Long id;
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
}
