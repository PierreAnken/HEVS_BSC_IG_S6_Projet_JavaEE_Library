package library.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import library.businessobject.Reader;
import library.libraryservice.BagService;
import library.libraryservice.LibraryService;

@ManagedBean(name = "loadMoney", eager = true)
@ViewScoped
public class LoadMoney implements Serializable{

	private static final long serialVersionUID = 7078809928413778000L;

	@EJB
	private LibraryService libraryService;
	
	@EJB
	private BagService bagService;

	
	private List<Reader> readers;
	private String cardId;
	private double Amount;
	
	
	@PostConstruct
	public void initialize() throws Exception{

		readers = libraryService.getReaders();
	}
	
	public String sayHello() {
		System.out.println("PA_DEBUG: sayHello reader:"+cardId);
		
		if(cardId == null)
			return "";
		System.out.println("PA_DEBUG: bagService:"+bagService);
		return "Thank you "+bagService.getCurrentReader().getFirstname();
	}


	public String chooseAmount() {

		if(bagService.getCurrentReader() != null) {
			System.out.println("PA_DEBUG: "+bagService.getCurrentReader().getCardId());
			
			//Reader reader = libraryService.getReaderFromCardId(bagService.getCurrentReader().getCardId());

			//bagService.setCurrentReader(reader);
			return "loadAmount?faces-redirect=true";
		}

		return "loadMoney?faces-redirect=true";
	}
	
	public void onSelectedCardId(){
		System.out.println("PA_DEBUG: Listener > CardId: "+cardId);
		Reader reader = Reader.convertFromMap(libraryService.getReaderFromCardId(cardId));
		if(reader != null) {
			bagService.setCurrentReader(reader);
		}
	}

	public double getAmount() {
		return Amount;
	}

	public void setAmount(double amount) {
		Amount = amount;
	}
	public List<Reader> getReaders() {
		return readers;
	}


	public void setReaders(List<Reader> readers) {
		this.readers = readers;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

}