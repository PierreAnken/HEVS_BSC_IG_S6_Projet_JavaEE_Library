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
		return libraryService.getLibraries();
	}

	public List<Book> getBookList() {
		return libraryService.getBooks();
	}


	public List<Librarian> getLibrarians() {
		return libraryService.getLibrarians();
	}


	public List<Reader> getReaders() {
		return libraryService.getReaders();
	}
	
	public void addBookToBag(Book b) {
		bagService.addBook(b);
	}
	
	public void removeBookFromBag(Book b) {
		bagService.removeBook(b);
	}

}