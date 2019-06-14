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

@ManagedBean(name = "loadMoney")
@ViewScoped
public class LoadMoney implements Serializable{

	private static final long serialVersionUID = 7078809928413778000L;

	private LibraryService libraryService;

	private String cardId;
	private int amount;

	@ManagedProperty(value="#{UserSession}")
    private UserSession userSession;
	
	@PostConstruct
	public void initialize() throws Exception{
		
		System.out.println("PA_DEBUG: init LoadMoney");
		InitialContext ctx = new InitialContext();
		libraryService = (LibraryService)ctx.lookup("java:global/Library-0.0.1/LibraryBean!library.libraryservice.LibraryService");

		amount = 0;
	}
	
	public void onSelectedCardId(){
		if(Tb.stringExists(cardId)) {
			Reader reader = Reader.convertFromMap(libraryService.getReaderFromCardId(cardId));
			if(reader != null) {
				userSession.setCurrentReader(Reader.convertToMap(reader));
			}
		}
		amount = 0;
	}
	
	public void onSelectedAmount() {
		if(amount > 0) {
			Reader reader = Reader.convertFromMap(userSession.getCurrentReader());
			reader.setAccountBalance(reader.getAccountBalance()+amount);
			userSession.setCurrentReader(Reader.convertToMap(reader));
			libraryService.updateReader(Reader.convertToMap(reader));
		}
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int a) {
		amount = a;
	}
	public List<Reader> getReaders() {
		return  Reader.convertFromMapList(libraryService.getReaders());
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
	

}