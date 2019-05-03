package library.businessobject;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Book {
	
	private String Title, Description, Author;
	private State state;
	private Language Language;
	
	@ManyToOne
	private Location location;
	
	@ManyToOne
	private Library library;
	
	@ManyToMany
	private Set<Reservation> Reservations;
	
	public Set<Reservation> getReservations() {
		return Reservations;
	}
	public void setReservations(Set<Reservation> Reservations) {
		this.Reservations = Reservations;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType. IDENTITY)
	private Long id;
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public Language getLanguage() {
		return Language;
	}
	public void setLanguage(Language language) {
		Language = language;
	}
}
