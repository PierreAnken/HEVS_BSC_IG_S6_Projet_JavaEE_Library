package library.bookservice;

import java.util.List;
import javax.ejb.Stateful;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import library.businessobject.Address;
import library.businessobject.Book;
import library.businessobject.Language;
import library.businessobject.Librarian;
import library.businessobject.Library;
import library.businessobject.Reader;

@Stateful
@ManagedBean
@ApplicationScoped
public class BookBean implements BookInterface {

	@PersistenceContext(name = "GotankLibrary")
	private EntityManager em;

	@Override
	public Book getBookById(int idBook) {
		return (Book) em.createQuery("FROM Book b where b.id =:id").setParameter("id", idBook).getSingleResult();
	}

	@Override
	public void lendBook(Book b, int customerID) {
		b.setCurrentOwner(customerID);
		em.persist(b);
	}

	@Override
	public void bringBackBook(Book b, int customerID) {
		b.setCurrentOwner(customerID);
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
	public List<Book> getAllBooks() {
		return (List<Book>) em.createQuery("FROM BOOK b").getResultList();
	}

	@Override
	public List<String> getAllBookStrings() {
		return (List<String>) em.createQuery("FROM BOOK b").getResultList();
	}

	@Override
	public List<Librarian> getAllLibrarians() {
		return (List<Librarian>) em.createQuery("FROM Librarian l").getResultList();
	}

	@Override
	public List<Reader> getAllReaders() {
		return (List<Reader>) em.createQuery("FROM Reader r").getResultList();
	}

	@Override
	public List<String> getAllAuthors() {
		return (List<String>) em.createQuery("author FROM Book b").getResultList();
	}

	@Override
	public List<Book> getBooksByTextString(String text) {
		return (List<Book>) em.createQuery("FROM BOOK b WHERE b.TITLE=:title").setParameter("title", text)
				.getResultList();
	}

	@Override
	public List<Book> getBooksByAuthorID(String author) {
		return (List<Book>) em.createQuery("FROM BOOK b WHERE b.Author=:author").setParameter("author", author)
				.getResultList();
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
	public void populateLibraryDB() {

		// Creating the addresses - Libraries
		Address libAddr1 = new Address("8000", "Paradeplatz", "Zürich");
		Address libAddr2 = new Address("1200", "Gare Cornavin", "Genève");
		Address libAddr3 = new Address("3000", "Bärenplatz", "Bern");

		// Creating the addresses - Readers
		Address rAddr1 = new Address("1234", "Dummystreet", "Testcity");
		Address rAddr2 = new Address("1234", "Dummystreet", "Testcity");

		// Creating the addresses - Librarians
		Address lAddr1 = new Address("1234", "Dummystreet", "Testcity");
		Address lAddr2 = new Address("1234", "Dummystreet", "Testcity");

		// Creating the libraries
		Library lib1 = new Library("Zürich", libAddr1);
		Library lib2 = new Library("Genève", libAddr2);
		Library lib3 = new Library("Bern", libAddr3);

		// Creating the books
		Book b1 = new Book("Testbook", "Description bla", "Heinrich Heine", 0, Language.German);
		Book b2 = new Book("Testbook", "Description bla", "Heinrich Heine", 0, Language.English);
		Book b3 = new Book("Testbook", "Description bla", "Heinrich Heine", 0, Language.French);
		Book b4 = new Book("Testbook", "Description bla", "Heinrich Heine", 0, Language.French);
		Book b5 = new Book("Testbook", "Description bla", "Heinrich Heine", 0, Language.Italian);
		Book b6 = new Book("Testbook", "Description bla", "Heinrich Heine", 0, Language.German);
		Book b7 = new Book("Testbook", "Description bla", "Heinrich Heine", 0, Language.German);
		Book b8 = new Book("Testbook", "Description bla", "Heinrich Heine", 0, Language.French);
		Book b9 = new Book("Testbook", "Description bla", "Heinrich Heine", 0, Language.English);
		Book b10 = new Book("Testbook", "Description bla", "Heinrich Heine", 0, Language.German);

		// Creating the Librarians
		Librarian l1 = new Librarian("hans.walther@gotank.lib", "1234", "Hans", "Walther", 134, lAddr1);
		Librarian l2 = new Librarian("wilhelm.gebhardt@gotank.lib", "1234", "Wilhelm", "Gebhardt", 135, lAddr2);

		// Creating the readers
		Reader r1 = new Reader("ulf.marquardt@bluewin.ch", "1234", "Ulf", "Marquardt", 93241, rAddr1);
		Reader r2 = new Reader("daniel.mengis@gmx.ch", "1234", "Daniel", "Mengis", 85341, rAddr2);

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

		em.persist(l1);
		em.persist(l2);

		em.persist(l1);
		em.persist(l2);

		em.persist(r1);
		em.persist(r2);

		em.persist(lib1);
		em.persist(lib2);
		em.persist(lib3);
	}

}