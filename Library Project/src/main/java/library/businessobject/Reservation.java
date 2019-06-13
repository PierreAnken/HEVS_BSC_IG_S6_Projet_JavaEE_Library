package library.businessobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name="Reservation")
public class Reservation implements Serializable{
	
	private static final long serialVersionUID = 792822687380340794L;
	
	@Temporal(TemporalType.DATE)
	private Date startDate, endDate;
	private boolean bookReturned;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne
	private Reader reader;
	


	@ManyToOne
	private Book book;

	
	public Reservation() {
		
	};
	
	public Reservation(Book b, Map<String, Object> r) {
		
		book = b;
		reader = Reader.convertFromMap(r);
		startDate = new Date();
		
		Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.MONTH, 1);
		endDate = cal.getTime();
	};
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isBookReturned() {
		return bookReturned;
	}

	public void setBookReturned(boolean bookReturned) {
		this.bookReturned = bookReturned;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}
	
	public static Map<String, Object> convertToMap(Reservation r){
		ObjectMapper oMapper = new ObjectMapper();
		Map<String, Object> reservationMap = oMapper.convertValue(r, Map.class);
		return reservationMap;
	}
	
	public static Reservation convertFromMap(Map<String, Object> reservationMap){
		ObjectMapper oMapper = new ObjectMapper();
		Reservation reservation = oMapper.convertValue(reservationMap, Reservation.class);
		return reservation;
	}
	
	public static List<Map<String, Object>> convertToMapList(List<Reservation> reservations) {
		ArrayList<Map<String, Object>> reservationMap = new ArrayList<Map<String,Object>>();
		for(Reservation r :  reservations) {
			reservationMap.add(Reservation.convertToMap(r));
		}
		return reservationMap;
	}
	
	public static List<Reservation> convertFromMapList(List<Map<String, Object>> reservationMap) {
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		for(Map<String, Object> r :  reservationMap) {
			reservations.add(Reservation.convertFromMap(r));
		}
		return reservations;
	}
}