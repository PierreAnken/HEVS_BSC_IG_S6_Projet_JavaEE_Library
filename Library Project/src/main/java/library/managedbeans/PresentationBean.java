package library.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.InitialContext;
import library.businessobject.Librarian;
import library.businessobject.Library;
import library.businessobject.Reader;
import library.libraryservice.BagService;
import library.libraryservice.LibraryService;
import library.businessobject.Book;

@ManagedBean
@ViewScoped
public class PresentationBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private LibraryService libraryService;
	
	@EJB
	private BagService bagService;

	
	
	@PostConstruct
	public void initialize() throws Exception{

		// Create reference to book EJB using JNDI
		InitialContext ctx = new InitialContext();
		libraryService = (LibraryService)ctx
				.lookup("java:global/Library-0.0.1/LibraryBean!library.libraryservice.LibraryService");
	}
	
	public BagService getBagService() {
		return bagService;
	}
	public void setBagService(BagService bagService) {
		this.bagService = bagService;
	}
	
	public List<Library> getLibraries() {
		return libraryService.getLibraries();
	}


	public Book getBook(String bookId) {
		return libraryService.getBook(bookId);
	}

	public List<Book> getBooksByAuthor(String author) {
		return libraryService.getBooksByAuthor(author);
	}

	public List<Book> getBooksByLanguage(String lang) {
		return libraryService.getBooksByLanguage(lang);
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

	public Reader getReader(long Id) {
		
		return Reader.convertFromMap(libraryService.getReader(Id));
	}

	public void addBookToBag(Book b) {
		bagService.addBook(b);
		System.out.println(bagService.toString());
	}

	public void removeBookFromBag(Book b) {
		bagService.removeBook(b);
	}

	public Reader getCurrentReader() {
		return bagService.getCurrentReader();
	}

	public void setCurrentReader(Reader r) {
		bagService.setCurrentReader(r);
	}

	
	public boolean isBookInBag(String bookId) {
		return bagService.isBookInBag(bookId);
	}
}