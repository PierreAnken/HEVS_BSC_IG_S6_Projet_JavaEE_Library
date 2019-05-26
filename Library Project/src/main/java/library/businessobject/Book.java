package library.businessobject;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Book")
public class Book {
	
	private String title, description, author, language;

    @ManyToOne private Library library;
  
    @OneToMany(mappedBy="book", cascade={CascadeType.REMOVE})
    Set<Reservation> reservations;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
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
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	

	// constructors
	public Book() {
	}

	public Book(String title, String description, String author, String language) {
		this.title = title;
		this.description = description;
		this.author = author;
		this.language = language;
	}


}
