package ch.gotank.bookservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateful;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ch.gotank.businessobject.Address;
import ch.gotank.businessobject.Author;
import ch.gotank.businessobject.Book;
import ch.gotank.businessobject.Category;
import ch.gotank.businessobject.Customer;
import ch.gotank.businessobject.Librarian;
import ch.gotank.businessobject.Library;
import ch.gotank.businessobject.User;

@Stateful
@ManagedBean
@SessionScoped
public class BookBean implements BookInterface {

	@PersistenceContext(name = "GotankLibrary")
	private EntityManager em;

	@Override
	public Book getBookById(int idBook) {
		return (Book) em.createQuery("FROM Book b where b.id =:id").setParameter("id", idBook).getSingleResult();
	}

	@Override
	public Book getBookByISBN(String isbn) {
		return (Book) em.createQuery("FROM Book b where b.isbn =:isbn").setParameter("id", isbn).getSingleResult();
	}

	@Override
	public void lendBook(Book b, int customerID) {
		b.setPlace(customerID);
		em.persist(b);
	}

	@Override
	public void bringBackBook(Book b, int customerID) {
		b.setPlace(0);
		em.persist(b);
	}

	@Override
	public void addBook(Book b) {
		em.persist(b);
	}

	@Override
	public void updateBook(Book b) {
		em.persist(b);
	}

	@Override
	public void deleteBook(Book b) {
		em.remove(b);
	}

	@Override
	public List<Book> getAllBooks(){
		return (List<Book>) em.createQuery("FROM BOOK b").getResultList();
	}

	@Override
	public List<Librarian> getAllLibrarians(){
		return (List<Librarian>) em.createQuery("FROM Librarian l").getResultList();
	}

	@Override
	public List<Customer> getAllCustomers(){
		return (List<Customer>) em.createQuery("FROM Customer c").getResultList();
	}

	@Override
	public List<User> getAllUsers(){
		return (List<User>) em.createQuery("FROM User u").getResultList();
	}

	@Override
	public List<Category> getAllCategories(){
		return (List<Category>) em.createQuery("FROM Category c").getResultList();
	}

	@Override
	public List<Book> getBooksByTextString(String text) {
		return (List<Book>) em.createQuery("FROM BOOK b WHERE b.TITLE=:title").setParameter("title", text).getResultList();
	}

	@Override
	public List<Book> getBooksByRelaseYear(int year) {
		return (List<Book>) em.createQuery("FROM BOOK b WHERE b.Releasedate LIKE :year").setParameter("year", year).getResultList();
	}

	@Override
	public List<Book> getBooksByAuthorID(int idAuthor) {
		return (List<Book>) em.createQuery("FROM BOOK b WHERE b.idAuthor=:idAuthor").setParameter("idAuthor", idAuthor).getResultList();
	}

	@Override
	public List<Book> getBooksByAuthorName(String lastname, String firstname) {
		List<Book> result = (List<Book>) em.createQuery("FROM BOOK b, IN(b.author) a WHERE a.Lastname=:lastname and a.Firstname=:firstname")
				.setParameter("lastname", lastname)
				.setParameter("firstname", firstname)
				.getResultList();
		return result;
	}

	@Override
	public void addAuthor(Author a) {
		em.persist(a);
	}

	@Override
	public void updateAuthor(Author a) {
		em.persist(a);
	}

	@Override
	public void deleteAuthor(Author a) {
		em.remove(a);
	}

	@Override
	public List<Author> getAllAuthors() {
		return (List<Author>) em.createQuery("FROM AUTHOR a").getResultList();
	}

	@Override
	public void addAddress(Address ad) {
		em.persist(ad);
	}

	@Override
	public void updateAddress(Address ad) {
		em.persist(ad);
	}

	@Override
	public void deleteAddress(Address ad) {
		em.remove(ad);
	}

	@Override
	public List<Address> getAllAddresses() {
		return (List<Address>) em.createQuery("FROM Address a").getResultList();
	}

	@Override
	public void saveOrUpdate(Book b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void populateLibraryDB(){
		
		// Creating the addresses - Libraries
		Address libAddr1 = new Address("8000", "Paradeplatz", "Zürich");
		Address libAddr2 = new Address("1200", "Gare Cornavin", "Genève");
		Address libAddr3 = new Address("3000", "Bärenplatz", "Bern");

		// Creating the addresses - Users
		Address usrAddr1 = new Address("1234", "Dummystreet", "Testcity");
		Address usrAddr2 = new Address("1234", "Dummystreet", "Testcity");

		// Creating the addresses - Librarians
		Address libUsrAddr1 = new Address("1234", "Dummystreet", "Testcity");
		Address libUsrAddr2 = new Address("1234", "Dummystreet", "Testcity");

		// Creating the addresses - Customers
		Address custAddr1 = new Address("1234", "Dummystreet", "Testcity");
		Address custAddr2 = new Address("1234", "Dummystreet", "Testcity");
		Address custAddr3 = new Address("1234", "Dummystreet", "Testcity");
		Address custAddr4 = new Address("1234", "Dummystreet", "Testcity");
		Address custAddr5 = new Address("1234", "Dummystreet", "Testcity");
		
		// Creating the authors
		Author auth1 = new Author("Erich", "Kästner");
		Author auth2 = new Author("Heinrich", "Heine");
		Author auth3 = new Author("Johann Wolfgang", "von Göthe");
		Author auth4 = new Author("Bertolt", "Brecht");
		Author auth5 = new Author("Kafka", "Frank");
		Author auth6 = new Author("Schiller", "Friedrich");
		Author auth7 = new Author("William", "Shakespeare");
		Author auth8 = new Author("Herrmann", "Hesse");
		
		// Creating the books
		Date date = new Date();
	    SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy");
	    Book b1 = new Book("Testbook", convertToDate("01.01.2005"), "978-3-86430-192-9", 19.95f, 78, "Bla", 1);
	    Book b2 = new Book("Testbook 2", convertToDate("01.01.2006"), "543-5-53480-643-1", 14.95f, 54, "Bla 2", 2);
	    Book b3 = new Book("Testbook 3", convertToDate("01.01.2007"), "645-7-64380-634-4", 18.95f, 67, "Bla 3", 2);
	    Book b4 = new Book("Testbook 4", convertToDate("01.01.2008"), "756-8-43953-675-5", 119.95f, 1432, "Bla 4", 2);
	    Book b5 = new Book("Testbook 5", convertToDate("01.01.2009"), "576-9-83214-312-8", 13.95f, 51, "Bla 5", 1);
	    Book b6 = new Book("Testbook 6", convertToDate("01.01.2010"), "867-1-52843-123-9", 19.99f, 81, "Bla 6", 2);
	    Book b7 = new Book("Testbook 7", convertToDate("01.01.2011"), "545-2-85348-765-1", 29.95f, 143, "Bla 7", 1);
	    Book b8 = new Book("Testbook 8", convertToDate("01.01.2012"), "543-4-94238-978-9", 59.95f, 853, "Bla 8", 2);
	    Book b9 = new Book("Testbook 9", convertToDate("01.01.2013"), "756-8-45831-127-4", 9.95f, 34, "Bla 9", 1);
	    Book b10 = new Book("Testbook 10", convertToDate("01.01.2014"), "243-4-13254-128-9", 199.95f, 2315, "Bla 10", 2);
	    
	    // Creating the Customers
	    Customer c1 = new Customer("Hans", "Muster", 1);
	    c1.setAddress(custAddr1);
	    Customer c2 = new Customer("Philipp", "Morris", 1);
	    c2.setAddress(custAddr2);
	    Customer c3 = new Customer("Daniela", "Meister", 1);
	    c3.setAddress(custAddr3);
	    Customer c4 = new Customer("Daliah", "Meier", 0);
	    c4.setAddress(custAddr4);
	    Customer c5 = new Customer("Peter", "Müller", 1);
	    c5.setAddress(custAddr5);
	    
	    // Creating the Librarians
	    Librarian l1 = new Librarian("Hans", "Guck in die Luft", 5000.0f);
	    l1.setAddress(libUsrAddr1);
	    Librarian l2 = new Librarian("Wilhelm", "Gebhardt", 6000.0f);
	    l2.setAddress(libUsrAddr2);
	    
	    // Creating the users
	    User u1 = new User("Daniel","Mengis","daniel@mengis.ch","1234");
	    u1.setAddress(usrAddr1);
	    User u2 = new User("Ulf","Marquardt","ulf@marquardt.ch","1234");
	    u2.setAddress(usrAddr2);

	    // Creating the libraries
		Library lib1 = new Library("Biblio 1", libAddr1);
		Library lib2 = new Library("Biblio 2", libAddr2);
		Library lib3 = new Library("Biblio 3", libAddr3);
		
		em.persist(b1);
		em.persist(b2);
		em.persist(b3);
		em.persist(b4);
		em.persist(b5);
		em.persist(b6);
		em.persist(b7);
		em.persist(b8);
		em.persist(b9);
		em.persist(b10);

		em.persist(c1);
		em.persist(c2);
		em.persist(c3);
		em.persist(c4);
		em.persist(c5);
		
		em.persist(l1);
		em.persist(l2);

		em.persist(u1);
		em.persist(u2);

		em.persist(lib1);
		em.persist(lib2);
		em.persist(lib3);
	}
	
	public Date convertToDate(String date) {
		Date date2 = new Date();
	    SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy");
	    try {
			date2 = ft.parse("01.01.2005");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    return date2;
	}

}