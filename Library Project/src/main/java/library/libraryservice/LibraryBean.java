package library.libraryservice;

import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import library.businessobject.Address;
import library.businessobject.Book;
import library.businessobject.Librarian;
import library.businessobject.Library;
import library.businessobject.Reader;

@Stateful
public class LibraryBean implements library.libraryservice.LibraryService {

	@PersistenceContext(name = "LibraryPU", type=PersistenceContextType.EXTENDED)
	private EntityManager em;

	
	public void addBook(Book b) {
		em.persist(b);
	}
	

	public List<String> getAllBookStrings() {
		return (List<String>) em.createQuery("FROM Book b").getResultList();
	}
	
	public Book getBookById(int idBook) {
		return (Book) em.createQuery("FROM Book b where b.id =:id").setParameter("id", idBook).getSingleResult();
	}

	public void addLibrary(Library library) {
		em.persist(library);
	}
	
	public void lendBook(Book b, int customerID) {
		//b.setCurrentOwner(customerID);
		em.persist(b);
	}

	public void bringBackBook(Book b, int customerID) {
		//b.setCurrentOwner(customerID);
		em.persist(b);
	}



	public void updateBook(Book b) {
		em.persist(b);
	}

	public void deleteBook(Book b) {
		em.remove(b);
	}

	public List<Book> getAllBooks() {
		return (List<Book>) em.createQuery("FROM Book b").getResultList();
	}


	public List<Librarian> getAllLibrarians() {
		return (List<Librarian>) em.createQuery("FROM Librarian l").getResultList();
	}

	public List<Reader> getAllReaders() {
		return (List<Reader>) em.createQuery("FROM Reader r").getResultList();
	}

	public List<Book> getBooksByTextString(String text) {
		return (List<Book>) em.createQuery("FROM Book b WHERE b.TITLE=:title").setParameter("title", text)
				.getResultList();
	}

	public List<Book> getBooksByAuthorID(String author) {
		return (List<Book>) em.createQuery("FROM Book b WHERE b.Author=:author").setParameter("author", author)
				.getResultList();
	}

	public void addAddress(Address ad) {
		em.persist(ad);
	}

	public void updateAddress(Address ad) {
		em.persist(ad);
	}

	public void deleteAddress(Address ad) {
		em.remove(ad);
	}

	public List<Address> getAllAddresses() {
		return (List<Address>) em.createQuery("FROM Address a").getResultList();
	}

	public void saveOrUpdate(Book b) {
		// TODO Auto-generated method stub

	}


	@Override
	public void populateLibraryDB() {

		System.out.println("PA_DEBUG: Start DB populate");
		/*
		 * books = libraryService.getAllBooks(); for(Book book : books)
		 * libraryService.deleteBook(book);
		 * 
		 * // Creating the books books.add(new Book("Testbook", "Description bla",
		 * "Heinrich Heine", "FR"));
		 * 
		 * for(Book book : books) libraryService.addBook(book);
		 */
		
		
		
		Address libAddr1 = new Address("8000", "Paradeplatz", "Zurich");
		Library lib1 = new Library("Sierre1", libAddr1);
		addLibrary(lib1);
		
		System.out.println("PA_DEBUG: Libraries done");
		
// Creating the addresses - Libraries
//		Address libAddr1 = new Address("8000", "Paradeplatz", "Zürich");
//		Address libAddr2 = new Address("1200", "Gare Cornavin", "Genève");
//		Address libAddr3 = new Address("3000", "Bärenplatz", "Bern");

//		// Creating the addresses - Readers
//		Address rAddr1 = new Address("1234", "Dummystreet", "Testcity");
//		Address rAddr2 = new Address("1234", "Dummystreet", "Testcity");
//
//		// Creating the addresses - Librarians
//		Address lAddr1 = new Address("1234", "Dummystreet", "Testcity");
//		Address lAddr2 = new Address("1234", "Dummystreet", "Testcity");
//
		// Creating the libraries
//		Library lib1 = new Library("Zürich", libAddr1);
//		Library lib2 = new Library("Genève", libAddr2);
//		Library lib3 = new Library("Bern", libAddr3);
		
		
//		// Creating the Librarians
//		Librarian l1 = new Librarian("hans.walther@gotank.lib", "1234", "Hans", "Walther", 134, lAddr1);
//		Librarian l2 = new Librarian("wilhelm.gebhardt@gotank.lib", "1234", "Wilhelm", "Gebhardt", 135, lAddr2);
//
//		// Creating the readers
//		Reader r1 = new Reader("ulf.marquardt@bluewin.ch", "1234", "Ulf", "Marquardt", 93241, rAddr1);
//		Reader r2 = new Reader("daniel.mengis@gmx.ch", "1234", "Daniel", "Mengis", 85341, rAddr2);

//		em.persist(b1);
//		em.persist(b2);
//		em.persist(b3);
//		em.persist(b4);
//		em.persist(b5);
//		em.persist(b6);
//		em.persist(b7);
//		em.persist(b8);
//		em.persist(b9);
//		em.persist(b10);
//
//		em.persist(l1);
//		em.persist(l2);
//
//		em.persist(l1);
//		em.persist(l2);
//
//		em.persist(r1);
//		em.persist(r2);

//		em.persist(lib1);
//		em.persist(lib2);
//		em.persist(lib3);
	}

}