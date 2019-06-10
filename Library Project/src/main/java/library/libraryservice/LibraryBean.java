package library.libraryservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
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
public class LibraryBean implements LibraryService{

	@PersistenceContext(name = "LibraryPU")
	private EntityManager em;
	
	@Override
	public List<Book> getAvailableBooks() {
		return (List<Book>) em.createQuery("SELECT b FROM Book b where b.id NOT IN (select r.book.id from Reservation r)").getResultList();
	}

	// add
	public Book addBook(Book b) {
		em.persist(b);
		return b;
	}
	
	public Library addLibrary(Library l) {
		em.persist(l);
		return l;
	}
	
	public Librarian addLibrarian(Librarian l) {
		em.persist(l);
		return l;
	}
	
	public Reader addReader(Reader r) {
		em.persist(r);
		return r;
	}
	
	public Reservation addReservation(Reservation r) {
		em.persist(r);
		return r;
	}
	
	@Override
	public Map<String, Object> addLibrarian(Map<String, Object> l) {
		Librarian librarian = Librarian.convertFromMap(l);
		em.persist(librarian);
		return Librarian.convertToMap(librarian);
	}

	@Override
	public Map<String, Object> addReader(Map<String, Object> r) {
		Reader reader = Reader.convertFromMap(r);
		em.persist(reader);
		return Reader.convertToMap(reader);
	}
	
	//update
	public Book updateBook(Book b) {
		em.merge(b);
		return b;
	}
	
	public Library updateLibrary(Library l) {
		em.merge(l);
		return l;
	}
	
	public Librarian updateLibrarian(Librarian l) {
		em.merge(l);
		return l;
	}
	
	public Reader updateReader(Map<String, Object> r) {
		Reader reader = Reader.convertFromMap(r);
		em.merge(reader);
		return reader;
	}
	
	public Reservation updateReservation(Reservation r) {
		em.merge(r);
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
	public List<Library> getLibraries() {
		return (List<Library>) em.createQuery("FROM Library l").getResultList();
	}
	
	@Override
	public int getMaxCardId() {
		return (int) em.createQuery("select max(r.cardId) FROM Reader r").getSingleResult();
	}

	@Override
	public List<Map<String, Object>> getLibrarians() {
		
		List<Librarian> librarians = (List<Librarian>) em.createQuery("FROM Librarian l").getResultList();
		return Librarian.convertToMapList(librarians);
	}

	@Override
	public List<Map<String, Object>> getReaders() {
		List<Reader> readers = (List<Reader>) em.createQuery("FROM Reader r").getResultList();
		return Reader.convertToMapList(readers);
	}

	@Override
	public List<Reservation> getReservations() {
		return (List<Reservation>) em.createQuery("FROM Reservation r").getResultList();
	}
	
	@Override
	public Map<String, Object> getReader(long readerId) {
		Reader reader = (Reader) em.createQuery("FROM User u WHERE u.id = :readerId").setParameter("readerId", readerId).getSingleResult();
		return Reader.convertToMap(reader);
	}

	@Override
	public Map<String, Object> getReaderFromCardId(int cardId) {
		Reader reader = (Reader) em.createQuery("FROM Reader r WHERE r.cardId = :cardId").setParameter("cardId", cardId).getSingleResult();
		return Reader.convertToMap(reader);
	}
	
	@Override
	public List<Map<String, Object>> getReadersFromEmail(String email) {
		List<Reader> readers = (List<Reader>) em.createQuery("FROM Reader r WHERE r.email = :email").setParameter("email", email).getResultList();
		return Reader.convertToMapList(readers);
	}
	
	@Override
	public Map<String, Object> getReaderFromCardId(String cardId) {
		return getReaderFromCardId(Integer.parseInt(cardId));
	}
	
	@PostConstruct
	public void initialize() throws Exception{
		populateLibraryDB();
	}
	
	public void populateLibraryDB() {

		//if db is empty we populate it
		if(getBooks().size() > 0)
			return;
		
		System.out.println("PA_DEBUG: Start DB init");
		
		//Delete libraries
		List<Library> libraries = getLibraries(); 
		for(int i=0; i<libraries.size();i++)
			em.remove(libraries.get(i));
		
		//Add
		Address sierre = new Address("3960", "Rue Notre Dame des Marais 5", "Sierre");
		Library lib1 = new Library("Bibliotheque Sierre", sierre);
		
		// Creating the books 
		List<Book> books = new ArrayList<Book>();
		
		books.add(new Book("You Deserve This", "Gesunde und natürliche Ernährung ist das, was Körper und Seele täglich verdienen. Und das hat rein gar nichts mit Verzicht zu tun! Sich langfristig grossartig zu fühlen, vor Energie zu sprühen und der Gesundheit etwas Gutes zu tun, ist auch im Alltag möglich – mit Bowls! Dabei werden einfache, natürliche und vollwertige Gerichte ohne stundenlange Küchenarbeit in schönen Schalen angerichtet und serviert. Hierfür hat Pamela Reif ihre liebsten Rezepte fotografiert und niedergeschrieben. Ergänzt wird das Bowl-Kochbuch durch eine grundlegende Lebensmittelkunde, die den Sinn und die Vorteile dieser ausgewogenen Ernährung leicht verständlich erläutert. Die ultimative Ernährungsformel ist nicht zwangsläufig low carb oder low fat - es kommt darauf an, dass die Nahrung echt und natürlich ist.", "Pamela Reif", "DE",lib1));
		books.add(new Book("Testbook2", "Description bla","Heinrich Heine", "FR",lib1));
		books.add(new Book("Testbook3", "Description bla","Erich Kaestner", "DE",lib1));
		books.add(new Book("Testbook4", "Description bla","Heinrich Heine", "FR",lib1));
		books.add(new Book("Testbook5", "Description bla","Heinrich Heine", "EN",lib1));
		books.add(new Book("Testbook6", "Description bla","Friedrich Schiller", "FR",lib1));
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
		List<Reader> readers = Reader.convertFromMapList(getReaders()); 
		for(int i=0; i<readers.size();i++)
			em.remove(readers.get(i));
		
		Reader r1 = new Reader("george.p@gmail.com", "George", "Pochon", 1568963, flanthey1);
		Reader r2 = new Reader("rodolf.ruth@live.com", "Rodolf", "Ruth", 6895127, brig1);
		
		em.persist(r1);
		em.persist(r2);
		
		System.out.println("PA_DEBUG: End of database init");
	}

}