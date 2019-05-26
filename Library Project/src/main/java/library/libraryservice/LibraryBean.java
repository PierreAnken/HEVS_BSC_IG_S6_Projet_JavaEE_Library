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
import library.businessobject.Reservation;

@Stateful
public class LibraryBean implements LibraryService {

	@PersistenceContext(name = "LibraryPU", type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	// add or update
	public Book addUpdateBook(Book b) {
		b = em.merge(b);
		em.persist(b);
		return b;
	}
	
	public Library addUpdateLibrary(Library l) {
		l = em.merge(l);
		em.persist(l);
		return l;
	}
	
	public Librarian addUpdateLibrarian(Librarian l) {
		l = em.merge(l);
		em.persist(l);
		return l;
	}
	
	public Reader addUpdateReader(Reader r) {
		r = em.merge(r);
		em.persist(r);
		return r;
	}
	
	public Reservation addUpdateReservation(Reservation r) {
		r = em.merge(r);
		em.persist(r);
		return r;
	}

	
	//delete
	@Override
	public void deleteBook(Book b) {
		em.remove(b);
	}

	@Override
	public void deleteLibrary(Library l) {
		em.remove(l);
	}

	@Override
	public void deleteLibrarian(Librarian l) {
		em.remove(l);
	}

	@Override
	public void deleteReader(Reader r) {
		em.remove(r);
	}

	@Override
	public void deleteReservation(Reservation r) {
		em.remove(r);
	}
	
	
	//get all 
	@Override
	public List<Book> getBooks() {
		return (List<Book>) em.createQuery("FROM Book b").getResultList();
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
	public void populateLibraryDB() {

		System.out.println("PA_DEBUG: Start DB populate");
		
		
		List<Library> libraries = getLibraries(); 
		for(int i=0; i<libraries.size();i++)
			deleteLibrary(libraries.get(i));
		
		Address sierre = new Address("3960", "Rue Notre Dame des Marais 5", "Sierre");
		Library lib1 = addUpdateLibrary(new Library("Bibliothèque-Médiathèque Sierre", sierre));

		
		System.out.println("PA_DEBUG: Libraries done");
		
		List<Book> books = getBooks(); 
		for(int i=0; i<books.size();i++)
			deleteBook(books.get(i));
			 
		System.out.println("PA_DEBUG: Existing book deleted");
		
		// Creating the books 
		books.add(new Book("Testbook", "Description bla","Heinrich Heine", "FR",lib1));
		books.add(new Book("Testbook2", "Description bla","Heinrich Heine", "FR",lib1));
		books.add(new Book("Testbook3", "Description bla","Heinrich Heine", "FR",lib1));
		books.add(new Book("Testbook4", "Description bla","Heinrich Heine", "FR",lib1));
		books.add(new Book("Testbook5", "Description bla","Heinrich Heine", "FR",lib1));
		books.add(new Book("Testbook6", "Description bla","Heinrich Heine", "FR",lib1));
		books.add(new Book("Testbook7", "Description bla","Heinrich Heine", "FR",lib1));
		
		System.out.println("PA_DEBUG: Books in DB: "+getBooks().size());	 
		for(Book book : books) { 
			addUpdateBook(book);
		}

		System.out.println("PA_DEBUG: New books added");

		
// Creating the addresses - Libraries
//		Address libAddr1 = new Address("8000", "Paradeplatz", "ZÃ¼rich");
//		Address libAddr2 = new Address("1200", "Gare Cornavin", "GenÃ¨ve");
//		Address libAddr3 = new Address("3000", "BÃ¤renplatz", "Bern");

//		// Creating the addresses - Readers
//		Address rAddr1 = new Address("1234", "Dummystreet", "Testcity");
//		Address rAddr2 = new Address("1234", "Dummystreet", "Testcity");
//
//		// Creating the addresses - Librarians
//		Address lAddr1 = new Address("1234", "Dummystreet", "Testcity");
//		Address lAddr2 = new Address("1234", "Dummystreet", "Testcity");
//
		// Creating the libraries
//		Library lib1 = new Library("ZÃ¼rich", libAddr1);
//		Library lib2 = new Library("GenÃ¨ve", libAddr2);
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