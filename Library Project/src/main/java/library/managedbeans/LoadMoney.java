package library.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.InitialContext;

import library.businessobject.Reader;
import library.libraryservice.BagService;
import library.libraryservice.LibraryService;

@ManagedBean(name = "loadMoney")
@SessionScoped
public class LoadMoney implements Serializable{

	private static final long serialVersionUID = 7078809928413778000L;

	private LibraryService libraryService;
	private BagService bagService;

	private List<Reader> readers;
	private String cardId;
	private int amount;
	
	
	@PostConstruct
	public void initialize() throws Exception{
		
		System.out.println("PA_DEBUG: init LoadMoney");
		InitialContext ctx = new InitialContext();
		libraryService = (LibraryService)ctx.lookup("java:global/Library-0.0.1/LibraryBean!library.libraryservice.LibraryService");
		bagService = (BagService)ctx.lookup("java:global/Library-0.0.1/BagBean!library.libraryservice.BagService");
		
		readers = Reader.convertFromMapList(libraryService.getReaders());
	}
	
	public void onSelectedCardId(){
		if(cardId != null && !cardId.isEmpty()) {
			Reader reader = Reader.convertFromMap(libraryService.getReaderFromCardId(cardId));
			if(reader != null) {
				bagService.setCurrentReader(Reader.convertToMap(reader));
			}
		}
	}
	
	public void onSelectedAmount() {
		if(amount > 0) {
			Reader reader = getCurrentReader();
			reader.setAccountBalance(reader.getAccountBalance()+amount);
			libraryService.updateReader(Reader.convertToMap(reader));
		}
	}
	
	public Reader getCurrentReader() {
		return Reader.convertFromMap(bagService.getCurrentReader());
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int a) {
		amount = a;
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