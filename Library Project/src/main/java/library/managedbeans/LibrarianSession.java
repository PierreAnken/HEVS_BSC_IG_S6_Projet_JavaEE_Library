package library.managedbeans;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.faces.bean.SessionScoped;
import library.businessobject.Librarian;

@SessionScoped
@Stateful
public class LibrarianSession{

	private Librarian currentLibrarian;
	
	@PostConstruct
	public void initialize() {
		System.out.println("PA_DEBUG: Init LibrarianSession");
	}

	public Librarian getCurrentLibrarian() {
		return currentLibrarian;
	}

	public void setCurrentLibrarian(Librarian currentLibrarian) {
		this.currentLibrarian = currentLibrarian;
	}
	
}