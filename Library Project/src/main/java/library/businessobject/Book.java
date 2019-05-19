package library.businessobject;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Book {
	
	private String title, description, author;
	private int currentOwner;
	private Language Language;
		
    @ManyToOne private Library library;
  
    @OneToMany(mappedBy="book", cascade={CascadeType.REMOVE}) private
    Set<Reservation> reservations;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public Language getLanguage() {
		return Language;
	}
	
	public void setLanguage(Language language) {
		Language = language;
	}
	
	public int getCurrentOwner() {
		return currentOwner;
	}
	
	public void setCurrentOwner(int currentOwner) {
		this.currentOwner = currentOwner;
	}


	// constructors
	public Book() {
	}

	public Book(String title, String description, String author, int currentOwner, Language language) {
		this.title = title;
		this.description = description;
		this.author = author;
		this.currentOwner = currentOwner;
		this.Language = language;
	}

	
}
