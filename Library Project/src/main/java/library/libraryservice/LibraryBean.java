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

	public Book getBookById(int idBook) {
		return (Book) em.createQuery("FROM Book b where b.id =:id").setParameter("id", idBook).getSingleResult();
	}

	public void addLibrary(Library library) {
		em.persist(library);
	}
	
	public void lendBook(Book b, int customerID) {
		b.setCurrentOwner(customerID);
		em.persist(b);
	}

	public void bringBackBook(Book b, int customerID) {
		b.setCurrentOwner(customerID);
		em.persist(b);
	}

	public void addBook(Book b) {
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

	public List<String> getAllBookStrings() {
		return (List<String>) em.createQuery("FROM Book b").getResultList();
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

	
}