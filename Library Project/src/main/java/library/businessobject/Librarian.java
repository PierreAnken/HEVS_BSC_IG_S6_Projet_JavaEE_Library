package library.businessobject;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Librarian extends User {
	
	
	private int EmployeeId;

	@ManyToMany
	private Set<Library> Libraries;
	
	public Set<Library> getLibraries() {
		return Libraries;
	}
	public void setLibrarys(Set<Library> Libraries) {
		this.Libraries = Libraries;
	}
	
	public int getEmployeeId() {
		return EmployeeId;
	}

	public void setEmployeeId(int employeeId) {
		EmployeeId = employeeId;
	}
	
}
