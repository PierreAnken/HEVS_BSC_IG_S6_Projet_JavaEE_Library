package library.businessobject;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Reader extends User {

	private int cardId;
	private String email;
	private String password, firstname, lastname;

	@OneToMany(mappedBy="reader")
	private Set<Reservation> reservations;
	
	public int getCardId() {
		return cardId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	// Constructors
	public Reader() {
	}

	public Reader(String email, String password, String firstname, String lastname, int cardId) {
		super(email, password, firstname, lastname);
		this.cardId = cardId;
	}

	public Reader(String email, String password, String firstname, String lastname, int cardId, Address address) {
		super(email, password, firstname, lastname, address);
		this.cardId = cardId;
	}
}
