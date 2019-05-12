package library.businessobject;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="Client")
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Column(name="nom")
	private String lastname;
	@Column(name="prenom")
	private String firstname;
	@Embedded
	private Address address;
	
	// relations
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)//@JoinColumn(name = "FK_CLIENT")
	private List<Account> accounts;
	
	// id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	// firstname
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	// lastname
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	// accounts (From Account)
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	// address
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	// constructors
	public Customer() {
	}
	
	public Customer(String firstname, String lastname) {
		this.lastname = lastname;
		this.firstname = firstname;
	}
	
	public Customer(String firstname, String lastname, Address address) {
		this.lastname = lastname;
		this.firstname = firstname;
		this.address = address;
	}
	
	@Override
	public String toString() {
		String result = id + "-" + lastname + "-" + firstname;
		return result;
	}
}
