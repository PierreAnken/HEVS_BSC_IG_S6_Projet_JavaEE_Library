package library.businessobject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

@Entity
public class Reservation {
	
	private DateTime StartDate, EndDate;

	@Id
	@GeneratedValue(strategy = GenerationType. IDENTITY)
	private Long id;
	
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
