package library.managedbeans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import library.businessobject.Librarian;
import library.businessobject.Library;
import library.businessobject.Reader;
import library.libraryservice.BagService;
import library.libraryservice.LibraryService;
import library.businessobject.Book;

public class PresentationBean {

	private List<Library> libraries;
	private List<Book> books;
	private List<Librarian> librarians;
	private List<Reader> readers;
	
	private LibraryService libraryService;
	private BagService bagService;

	@PostConstruct
	public void initialize() throws Exception{

		// Create reference to book EJB using JNDI
		InitialContext ctx = new InitialContext();
		libraryService = (LibraryService)ctx
				.lookup("java:global/Library-0.0.1/LibraryBean!library.libraryservice.LibraryService");

		bagService =  (BagService)ctx
				.lookup("java:global/Library-0.0.1/BagBean!library.bagservice.BagService");
		
		//init library if needed
		libraryService.populateLibraryDB();
	}

	public List<Library> getLibraries() {
		return libraries;
	}

	public void setLibraries(List<Library> libraries) {
		this.libraries = libraries;
	}

	public List<Book> getBookList() {
		return books;
	}

	public void setBookList(List<Book> bookList) {
		this.books = bookList;
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

}