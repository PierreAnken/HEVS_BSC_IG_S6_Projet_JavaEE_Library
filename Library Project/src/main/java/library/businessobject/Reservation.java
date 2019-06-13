package library.businessobject;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	@ManyToOne(cascade = CascadeType.ALL)
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
	
	
}