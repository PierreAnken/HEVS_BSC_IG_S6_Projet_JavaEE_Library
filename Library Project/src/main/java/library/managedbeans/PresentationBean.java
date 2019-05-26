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

	}
	
	public String populateLibraryDB() {
		libraryService.populateLibraryDB();
		return "index?faces-redirect=true";
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