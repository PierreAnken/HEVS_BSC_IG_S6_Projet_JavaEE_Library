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
	private List<Book> books;
	private Book selectedBook;
	private List<Librarian> librarians;
	private List<Reader> readers;
	private User activeUser;
	
	private LibraryService libraryService;
	

	@PostConstruct
	public void initialize() throws Exception{

		// Create reference to book EJB using JNDI
		InitialContext ctx = new InitialContext();
		libraryService = (LibraryService)ctx
				.lookup("java:global/Library-0.0.1/LibraryBean!library.libraryservice.LibraryService");

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

}