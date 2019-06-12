package library.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.naming.InitialContext;

import library.businessobject.Reader;
import library.libraryservice.LibraryService;
import library.toolbox.Tb;

@ManagedBean(name = "RentBook")
@ViewScoped
public class RentBook implements Serializable{

	private static final long serialVersionUID = 7078809928413778000L;

	private LibraryService libraryService;

	private String cardId;
	private boolean paid;

	@ManagedProperty(value="#{UserSession}")
    private UserSession userSession;
	
	@PostConstruct
	public void initialize() throws Exception{
		
		System.out.println("PA_DEBUG: init LoadMoney");
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
	
	public void performPayment() {
		System.out.println("PA_DEBUG: RentBook > performPayment");
	}
	
	public double getAccountBalance() {
		if(userSession.getCurrentReader() == null)
			return 0;
		else
			return Reader.convertFromMap(userSession.getCurrentReader()).getAccountBalance();
	}
	
	public List<Reader> getReaders() {
		return Reader.convertFromMapList(libraryService.getReaders());
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

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	

}