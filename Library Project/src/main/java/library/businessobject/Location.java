package library.businessobject;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Location {
	private int floor, row, shelf;

	@Id
	@GeneratedValue(strategy = GenerationType. IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy="location")
	private Set<Book> books;
	
	@ManyToOne
	private Library library;
	
	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getShelf() {
		return shelf;
	}

	public void setShelf(int shelf) {
		this.shelf = shelf;
	}
	
	// constructors
	public Location() {
	}

	public Location(int floor, int row, int shelf) {
		this.floor = floor;
		this.row = row;
		this.shelf = shelf;
	}
}
