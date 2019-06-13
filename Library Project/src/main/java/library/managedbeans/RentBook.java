package library.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import library.businessobject.Book;
import library.businessobject.Reader;
import library.businessobject.Reservation;
import library.libraryservice.LibraryService;
import library.toolbox.Tb;

@ManagedBean(name = "RentBook")
@ViewScoped
public class RentBook implements Serializable{
	
	@Resource
	UserTransaction utx;
	
	private static final long serialVersionUID = 7078809928413778000L;

	private LibraryService libraryService;

	private String cardId;
	private boolean paid;
	private int overLimit;
	private boolean error;

	@ManagedProperty(value="#{UserSession}")
    private UserSession userSession;
	
	@PostConstruct
	public void initialize() throws Exception{
		System.out.println("PA_DEBUG: init RentBook");
		InitialContext ctx = new InitialContext();
		libraryService = (LibraryService)ctx.lookup("java:global/Library-0.0.1/LibraryBean!library.libraryservice.LibraryService");
		checkBookLimit();
	}
	
	public void onSelectedCardId(){
		System.out.println("PA_DEBUG: RentBook > onSelectedCardId");
		if(Tb.stringExists(cardId)) {
			Reader reader = Reader.convertFromMap(libraryService.getReaderFromCardId(cardId));
			if(reader != null) {
				userSession.setCurrentReader(Reader.convertToMap(reader));
				checkBookLimit();
			}
		}
	}
	
	public boolean hasEnoughMoney() {
		if( getCurrentReader() != null){
			if(userSession.getBagSize()*0.5 <=  getCurrentReader().getAccountBalance())
				return true;
		}
		return false;
	}
	
	public void checkBookLimit() {
		//max books 5
		if( getCurrentReader() != null){
			int activeReservations = libraryService.getActiveReservationFromReader(getCurrentReader().getEmail()).size();
			
			setOverLimit(activeReservations+userSession.getBagSize()-5);
			if(getOverLimit() < 0)
				setOverLimit(0);
		}
	}
	

	public void performPayment() {
		
		System.out.println("PA_DEBUG: RentBook > performPayment");
		
		try {
			
			InitialContext ctx = new InitialContext();
			UserTransaction utx = (UserTransaction)ctx.lookup("java:comp/UserTransaction");
			utx.begin();
			
			Reader currentReader =  getCurrentReader();
			System.out.println("PA_DEBUG: RentBook > performPayment "+currentReader.getEmail());
			List<Book> booksToRent = new ArrayList<Book>(userSession.getBooksInBag());
			System.out.println("PA_DEBUG: RentBook > performPayment > "+booksToRent.size()+" books in bag");
			
			//1 - remove money from user account
			double toPay = booksToRent.size() * 0.5;
			if(currentReader.getAccountBalance() < toPay )
				throw new Exception("Negative account balance after transaction");
			
			currentReader.setAccountBalance(currentReader.getAccountBalance() - toPay);
			
			if(booksToRent.size() == 0)
				throw new Exception("Bag is empty");
			
			//2 create reservation and remove from bag
			for(int i = 0; i<booksToRent.size(); i++) {
				System.out.println("PA_DEBUG: RentBook > performPayment > adding reservation");
				Reservation reservation = new Reservation(booksToRent.get(i),Reader.convertToMap(currentReader));
				libraryService.addReservation(Reservation.convertToMap(reservation));
				userSession.removeBookFromBag(booksToRent.get(i).getId().intValue());
			}
			
			libraryService.updateReader(Reader.convertToMap(currentReader));	
			
			utx.commit();
			
			paid = true;
		} 
		catch (Exception e) {
				System.out.println("PA_DEBUG: RentBook > performPayment "+ e.getMessage());
				setError(true);
			try {
				utx.rollback();
			} catch (IllegalStateException e1) {
				System.err.println(e1.getMessage());
				setError(true);
			} catch (SecurityException e1) {
				System.err.println(e1.getMessage());
				setError(true);
			} catch (SystemException e1) {
				System.err.println(e1.getMessage());
				setError(true);
			} 
		}
		
	}
	
	public double getAccountBalance() {
		if(userSession.getCurrentReader() == null)
			return 0;
		else {
			userSession.reloadCurrentReader();
			return Reader.convertFromMap(userSession.getCurrentReader()).getAccountBalance();
		}
	}
	
	public Reader getCurrentReader() {
		return Reader.convertFromMap(userSession.getCurrentReader());
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

	public int getOverLimit() {
		return overLimit;
	}

	public void setOverLimit(int overLimit) {
		this.overLimit = overLimit;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}


}