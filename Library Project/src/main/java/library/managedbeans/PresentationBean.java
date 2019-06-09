package library.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.InitialContext;
import library.businessobject.Librarian;
import library.businessobject.Library;
import library.businessobject.Reader;
import library.libraryservice.LibraryService;
import library.businessobject.Book;

@ManagedBean
@ViewScoped
public class PresentationBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private LibraryService libraryService;
	
	@PostConstruct
	public void initialize() throws Exception{

		// Create reference to book EJB using JNDI
		InitialContext ctx = new InitialContext();
		libraryService = (LibraryService)ctx.lookup("java:global/Library-0.0.1/LibraryBean!library.libraryservice.LibraryService");
		
		System.out.println("PA_DEBUG: Init PresentationBean");
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
//		System.out.println("OG_DEBUG: GetBookList" + libraryService.getBooks().size());
//		System.out.println("OG_DEBUG: Bag Service Size > > > > > > " + bagService.getBagSize());
		return libraryService.getBooks();
	}

	public List<Librarian> getLibrarians() {
		return Librarian.convertFromMapList(libraryService.getLibrarians());
	}


	public List<Reader> getReaders() {
		return Reader.convertFromMapList(libraryService.getReaders());
	}

	public Reader getReader(long Id) {
		
		return Reader.convertFromMap(libraryService.getReader(Id));
	}
}