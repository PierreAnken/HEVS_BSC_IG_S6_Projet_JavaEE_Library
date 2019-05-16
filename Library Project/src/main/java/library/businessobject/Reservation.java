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
	
	private DateTime startDate, endDate;

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
	
}