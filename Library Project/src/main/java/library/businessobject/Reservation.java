package library.businessobject;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

@Entity
public class Reservation {
	
	private DateTime startDate, endDate;
	private boolean bookReturned;

	@Id
	@GeneratedValue(strategy = GenerationType. IDENTITY)
	private Long id;
	
	@ManyToOne
	private Reader reader;
	
	@ManyToOne
	private Book book;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	public boolean isBookReturned() {
		return bookReturned;
	}

	public void setBookReturned(boolean bookReturned) {
		this.bookReturned = bookReturned;
	}
	
}