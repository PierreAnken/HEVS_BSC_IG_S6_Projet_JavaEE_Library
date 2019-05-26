package library.managedbeans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import library.businessobject.Librarian;
import library.businessobject.Library;
import library.businessobject.Reader;
import library.businessobject.User;
import library.libraryservice.LibraryService;
import library.businessobject.Address;
import library.businessobject.Book;

public class PresentationBean {

	private List<Library> libraries;
	private List<String> bigImages;
	private List<Book> books;
	private Book selectedBook;
	private List<Librarian> librarians;
	private List<Reader> readers;
	private User activeUser;
	
	private LibraryService libraryService;
	

	@PostConstruct
	public void initialize() throws Exception{

		System.out.println("PA_DEBUG:initializePresentationBean");
		
		// Create reference to book EJB using JNDI
		InitialContext ctx = new InitialContext();
		libraryService = (LibraryService)ctx
				.lookup("java:global/Library-0.0.1/LibraryBean!library.libraryservice.LibraryService");

		Address libAddr1 = new Address("8000", "Paradeplatz", "Zurich");
		Library lib1 = new Library("Zurich", libAddr1);
		libraryService.addLibrary(lib1);
	}
	
	
	public void populateLibraryDB() {

		Address libAddr1 = new Address("8000", "Paradeplatz", "Zurich");
		Library lib1 = new Library("Zurich", libAddr1);
		libraryService.addLibrary(lib1);

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

//		// Creating the books
//		Book b1 = new Book("Testbook", "Description bla", "Heinrich Heine", 0, Language.German);
//		Book b2 = new Book("Testbook", "Description bla", "Heinrich Heine", 0, Language.English);
//		Book b3 = new Book("Testbook", "Description bla", "Heinrich Heine", 0, Language.French);
//		Book b4 = new Book("Testbook", "Description bla", "Heinrich Heine", 0, Language.French);
//		Book b5 = new Book("Testbook", "Description bla", "Heinrich Heine", 0, Language.Italian);
//		Book b6 = new Book("Testbook", "Description bla", "Heinrich Heine", 0, Language.German);
//		Book b7 = new Book("Testbook", "Description bla", "Heinrich Heine", 0, Language.German);
//		Book b8 = new Book("Testbook", "Description bla", "Heinrich Heine", 0, Language.French);
//		Book b9 = new Book("Testbook", "Description bla", "Heinrich Heine", 0, Language.English);
//		Book b10 = new Book("Testbook", "Description bla", "Heinrich Heine", 0, Language.German);
//
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

	
	public void loadImages() {
		bigImages = new ArrayList<String>();
		bigImages.add("brecht.jpg");
		bigImages.add("goethe.jpg");
		bigImages.add("heine.jpg");
		bigImages.add("hesse.jpg");
		bigImages.add("kaestner.jpg");
		bigImages.add("kafka.jpg");
		bigImages.add("schiller.jpg");
		bigImages.add("shakespeare.jpg");
	}

	public List<Library> getLibraries() {
		return libraries;
	}

	public void setLibraries(List<Library> libraries) {
		this.libraries = libraries;
	}

	public List<String> getBigImages() {
		return bigImages;
	}

	public void setBigImages(List<String> bigImages) {
		this.bigImages = bigImages;
	}

	public List<Book> getBookList() {
		return books;
	}

	public void setBookList(List<Book> bookList) {
		this.books = bookList;
	}

	public Book getSelectedBook() {
		return selectedBook;
	}

	public void setSelectedBook(Book selectedBook) {
		this.selectedBook = selectedBook;
	}

	public List<Librarian> getLibrarians() {
		return librarians;
	}

	public void setLibrarians(List<Librarian> librarians) {
		this.librarians = librarians;
	}

	public List<Reader> getReaders() {
		return readers;
	}

	public void setReaders(List<Reader> readers) {
		this.readers = readers;
	}

	public User getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(User activeUser) {
		this.activeUser = activeUser;
	}

}