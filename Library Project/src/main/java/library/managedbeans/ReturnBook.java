package library.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.naming.InitialContext;
import library.businessobject.Reader;
import library.businessobject.Reservation;
import library.libraryservice.LibraryService;
import library.toolbox.Tb;

@ManagedBean(name = "ReturnBook")
@ViewScoped
public class ReturnBook implements Serializable{
		
	private static final long serialVersionUID = -4105483418101680594L;
	
	private LibraryService libraryService;
	private String cardId;
	private boolean returned;

	@ManagedProperty(value="#{UserSession}")
    private UserSession userSession;
	
	@PostConstruct
	public void initialize() throws Exception{
		System.out.println("PA_DEBUG: init ReturnBook");
		InitialContext ctx = new InitialContext();
		libraryService = (LibraryService)ctx.lookup("java:global/Library-0.0.1/LibraryBean!library.libraryservice.LibraryService");
	}
	
	public void onSelectedCardId(){
		System.out.println("PA_DEBUG: RentBook > onSelectedCardId");
		if(Tb.stringExists(cardId)) {
			Reader reader = Reader.convertFromMap(libraryService.getReaderFromCardId(cardId));
			if(reader != null) {
				userSession.setCurrentReader(Reader.convertToMap(reader));
			}
		}
	}
	
	public void performReturn() {
		System.out.println("PA_DEBUG: Return books");
		List<Reservation> reservations = getCurrentReservations();
		for(int i=0; i< reservations.size(); i++) {
			reservations.get(i).setBookReturned(true);
			libraryService.updateReservation(Reservation.convertToMap(reservations.get(i)));
		}
		returned = true;	
		
	}
	
	public Reader getCurrentReader() {
		return Reader.convertFromMap(userSession.getCurrentReader());
	}
	
	
	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
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

	public List<Reservation> getCurrentReservations() {
		Reader currentReader = Reader.convertFromMap(userSession.getCurrentReader());
		return Reservation.convertFromMapList(libraryService.getActiveReservationFromReader(currentReader.getEmail()));
	}

	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}

}