package ch.gotank.businessobject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private String category;
	
	// Start constructors -->
	public Category() {
	}

	public Category(String category) {
		this.category = category;
	}
	// <-- End constructors

	// Start Getters & Setters -->	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	// <-- End Getters & Setters
	
	@Override
	public String toString() {
		String cat = id + " - " + category;
		return cat;
	}
}
