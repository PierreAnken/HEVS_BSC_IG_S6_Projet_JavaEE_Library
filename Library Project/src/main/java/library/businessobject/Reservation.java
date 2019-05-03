package library.businessobject;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

@Entity
public class Reservation {
	
	private DateTime StartDate, EndDate;

	@Id
	@GeneratedValue(strategy = GenerationType. IDENTITY)
	private Long id;
	
	@ManyToOne
	private Reader reader;
	
	@ManyToMany
	private Set<Book> Books;
	
	public Set<Book> getBooks() {
		return Books;
	}
	public void setBooks(Set<Book> Books) {
		this.Books = Books;
	}
	
	public DateTime getStartDate() {
		return StartDate;
	}

	public void setStartDate(DateTime startDate) {
		StartDate = startDate;
	}

	public DateTime getEndDate() {
		return EndDate;
	}

	public void setEndDate(DateTime endDate) {
		EndDate = endDate;
	}
	
}
