package library.businessobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class Reader extends User implements Serializable{

	private static final long serialVersionUID = -7085265629390248126L;
	private int cardId;
	private double accountBalance;
	
	@OneToMany(mappedBy="reader", cascade={CascadeType.REMOVE})
	private Set<Reservation> reservations;

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public int getCardId() {
		return cardId;
	}
	
	// Constructors
	public Reader() {
	}

	public Reader(String email, String firstname, String lastname, int cardId, Address address) {
		super(email,  firstname, lastname, address);
		this.cardId = cardId;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	public static Map<String, Object> convertToMap(Reader r){
		ObjectMapper oMapper = new ObjectMapper();
		Map<String, Object> readerMap = oMapper.convertValue(r, Map.class);
		return readerMap;
	}
	
	public static Reader convertFromMap(Map<String, Object> readerMap){
		ObjectMapper oMapper = new ObjectMapper();
		Reader reader = oMapper.convertValue(readerMap, Reader.class);
		return reader;
	}
	
	public static List<Map<String, Object>> convertToMapList(List<Reader> readers) {
		ArrayList<Map<String, Object>> readersMap = new ArrayList<Map<String,Object>>();
		for(Reader r :  readers) {
			readersMap.add(Reader.convertToMap(r));
		}
		return readersMap;
	}
	
	public static List<Reader> convertFromMapList(List<Map<String, Object>> readersMap) {
		ArrayList<Reader> readers = new ArrayList<Reader>();
		for(Map<String, Object> r :  readersMap) {
			readers.add(Reader.convertFromMap(r));
		}
		return readers;
	}
}
