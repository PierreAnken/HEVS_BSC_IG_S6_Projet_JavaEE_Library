package library.libraryservice;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import library.businessobject.Address;
import library.businessobject.Book;
import library.businessobject.Librarian;
import library.businessobject.Library;
import library.businessobject.Reader;
import library.businessobject.Reservation;

@Stateless
public class LibraryBean implements LibraryService {

	@PersistenceContext(name = "LibraryPU")
	private EntityManager em;
	
	// add or update
	public Book addUpdateBook(Book b) {
		em.persist(b);
		return b;
	}
	
	public Library addUpdateLibrary(Library l) {
		em.persist(l);
		return l;
	}
	
	public Librarian addUpdateLibrarian(Librarian l) {
		em.persist(l);
		return l;
	}
	
	public Reader addUpdateReader(Reader r) {
		em.persist(r);
		return r;
	}
	
	public Reservation addUpdateReservation(Reservation r) {
		em.persist(r);
		return r;
	}

	
	//delete
	@Override
	public void deleteBook(Book b) {
		b = em.find(Book.class,b);
		em.remove(b);
	}

	@Override
	public void deleteLibrary(Library l) {
		l = em.find(Library.class,l);
		em.remove(l);
	}

	@Override
	public void deleteLibrarian(Librarian l) {
		l = em.find(Librarian.class,l);
		em.remove(l);
	}

	@Override
	public void deleteReader(Reader r) {
		r = em.find(Reader.class,r);
		em.remove(r);
	}

	@Override
	public void deleteReservation(Reservation r) {
		r = em.find(Reservation.class,r);
		em.remove(r);
	}
	
	
	//get all 
	@Override
	public List<Book> getBooks() {
		return (List<Book>) em.createQuery("FROM Book b").getResultList();
	}

	@Override
	public Book getBook(String bookId) {
		return (Book) em.createQuery("FROM Book b WHERE b.id = :bookId").setParameter("bookId", Long.parseLong(bookId)).getSingleResult();
	}

	@Override
	public List<Book> getBooksByAuthor(String author) {
		return (List<Book>) em.createQuery("FROM Book b WHERE b.author = :author").setParameter("author", author).getResultList();
	}

	@Override
	public List<Book> getBooksByLanguage(String lang) {
		return (List<Book>) em.createQuery("FROM Book b WHERE b.language = :lang").setParameter("lang", lang).getResultList();
	}

	@Override
	public List<Library> getLibraries() {
		return (List<Library>) em.createQuery("FROM Library l").getResultList();
	}

	@Override
	public List<Librarian> getLibrarians() {
		return (List<Librarian>) em.createQuery("FROM Librarian l").getResultList();
	}

	@Override
	public List<Reader> getReaders() {
		return (List<Reader>) em.createQuery("FROM Reader r").getResultList();
	}

	@Override
	public List<Reservation> getReservations() {
		return (List<Reservation>) em.createQuery("FROM Reservation r").getResultList();
	}
	
	@Override
	public Reader getReader(long readerId) {
		return (Reader) em.createQuery("FROM Reader r WHERE b.id = :readerId").setParameter("readerId", readerId).getSingleResult();
	}

	
	
	public void populateLibraryDB() {

		System.out.println("PA_DEBUG: Start DB init");
		
		//if db is empty we populate it
		if(getBooks().size() > 0)
			return;
		
		//Delete libraries
		List<Library> libraries = getLibraries(); 
		for(int i=0; i<libraries.size();i++)
			em.remove(libraries.get(i));
		
		//Add
		Address sierre = new Address("3960", "Rue Notre Dame des Marais 5", "Sierre");
		Library lib1 = new Library("Bibliothèque-Médiathèque Sierre", sierre);
		
		// Creating the books 
		List<Book> books = new ArrayList<Book>();
		
		books.add(new Book("Testbook", "Description bla","Heinrich Heine", "FR",lib1));
		books.add(new Book("Testbook2", "Description bla","Heinrich Heine", "FR",lib1));
		books.add(new Book("Testbook3", "Description bla","Heinrich Heine", "DE",lib1));
		books.add(new Book("Testbook4", "Description bla","Heinrich Heine", "FR",lib1));
		books.add(new Book("Testbook5", "Description bla","Heinrich Heine", "EN",lib1));
		books.add(new Book("Testbook6", "Description bla","Heinrich Heine", "FR",lib1));
		books.add(new Book("Testbook7", "Description bla","Heinrich Heine", "FR",lib1));
		
		System.out.println("PA_DEBUG: Books in DB: "+getBooks().size());	 
		for(Book book : books) { 
			em.persist(book);
		}
		
		// Creating the Librarians
		Address sierre2 = new Address("3960", "Route de Sion 65", "Sierre");
		Address sierre3 = new Address("3960", "Rue du Grain d'or 14B", "Sierre");
		Librarian l1 = new Librarian("hans.walther@gotank.lib", "Hans", "Walther",  sierre2);
		Librarian l2 = new Librarian("wilhelm.gebhardt@gotank.lib", "Wilhelm", "Gebhardt", sierre3);
		
		lib1.addLibrarian(l1);
		lib1.addLibrarian(l2);
		
		em.persist(lib1);
		
		Address brig1 = new Address("3900", "Ronesand Strasse 21", "Brig");
		Address flanthey1 = new Address("3978", "Rue du moulin 13", "Flanthey");
		
		//delete old readers
		List<Reader> readers = getReaders(); 
		for(int i=0; i<readers.size();i++)
			em.remove(readers.get(i));
		
		Reader r1 = new Reader("george.p@gmail.com", "George", "Pochon", 1568963, flanthey1);
		Reader r2 = new Reader("rodolf.ruth@live.com", "Rodolf", "Ruth", 6895127, brig1);
		
		em.persist(r1);
		em.persist(r2);
		
		System.out.println("PA_DEBUG: End of database init");
	}

}