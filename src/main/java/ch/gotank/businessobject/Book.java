package ch.gotank.businessobject;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import javax.persistence.Column;

@Entity
@Table(name="Book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="bookID")
	private Long id;
	private String title;
	@Temporal(TemporalType.DATE)
	private Date releaseDate;
	private String isbnNumber;
	private float price;
	private int numberOfPages;
	private String description;
	private int place;
	
	// Start constructors -->
	public Book() {
	}
	
	public Book(String title, Date releaseDate, String isbnNumber, 
			float price, int numberOfPages, String description, int place) {
		this.title = title;
		this.releaseDate = releaseDate;
		this.isbnNumber = isbnNumber;
		this.price = price;
		this.numberOfPages = numberOfPages; 
		this.description = description;
		this.place = place;
	}
	// <-- End constructors
	
	// Start Getters & Setters -->
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getIsbnNumber() {
		return isbnNumber;
	}

	public void setIsbnNumber(String isbnNumber) {
		this.isbnNumber = isbnNumber;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}
	// <-- End Getters & Setters
	
}