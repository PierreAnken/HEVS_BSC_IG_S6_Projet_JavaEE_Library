package library.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.naming.InitialContext;

import library.businessobject.Librarian;
import library.libraryservice.LibraryService;
import library.toolbox.Tb;

@ManagedBean(name = "door")
@ViewScoped
public class Door implements Serializable{

	
	private static final long serialVersionUID = 2110805601999714669L;

	private LibraryService libraryService;

	@ManagedProperty(value="#{UserSession}")
    private UserSession userSession;
	private String email;
	private List<Librarian> librarians;
	
	@PostConstruct
	public void initialize() throws Exception{
		
		System.out.println("PA_DEBUG: init Door");
		InitialContext ctx = new InitialContext();
		libraryService = (LibraryService)ctx.lookup("java:global/Library-0.0.1/LibraryBean!library.libraryservice.LibraryService");
		
		setLibrarians(Librarian.convertFromMapList(libraryService.getLibrarians()));
	}
	
	public String getStatus() {
		if(userSession.getCurrentLibrarian() != null)
			return "Free";
		else
			return "Locked";
				
	}
	
	public void onSelectedLibrarian(){
		if(Tb.stringExists(email)) {
			Librarian librarian = Librarian.convertFromMap(libraryService.getLibrarianFromEmail(email));
			userSession.setCurrentLibrarian(librarian);
		}
	}
	
	
	public LibraryService getLibraryService() {
		return libraryService;
	}

	public void setLibraryService(LibraryService libraryService) {
		this.libraryService = libraryService;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public List<Librarian> getLibrarians() {
		return librarians;
	}

	public void setLibrarians(List<Librarian> librarians) {
		this.librarians = librarians;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}