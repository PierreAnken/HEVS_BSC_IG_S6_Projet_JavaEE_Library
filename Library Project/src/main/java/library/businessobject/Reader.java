package library.businessobject;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Reader extends User {

	private int CardId;

	@OneToMany(mappedBy="reader")
	private Set<Reservation> reservations;
	
	public int getCardId() {
		return CardId;
	}

	public void setCardId(int cardId) {
		CardId = cardId;
	}
}
