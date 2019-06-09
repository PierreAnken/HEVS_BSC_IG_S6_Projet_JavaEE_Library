package library.managedbeans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
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
	private BagService bagService;
	
	@PostConstruct
	public void initialize() throws Exception{

		// Create reference to book EJB using JNDI
		InitialContext ctx = new InitialContext();
		libraryService = (LibraryService)ctx.lookup("java:global/Library-0.0.1/LibraryBean!library.libraryservice.LibraryService");
		bagService = (BagService)ctx.lookup("java:global/Library-0.0.1/BagBean!library.libraryservice.BagService");
		
		System.out.println("PA_DEBUG: Init PresentationBean");
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
//		System.out.println("OG_DEBUG: GetBookList" + libraryService.getBooks().size());
//		System.out.println("OG_DEBUG: Bag Service Size > > > > > > " + bagService.getBagSize());
		return libraryService.getBooks();
	}

	public int getBagSize() {
		return bagService.getBagSize();
	}

	public List<Book> getBagBooks() {
		return bagService.getBagBooks();
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

	public String addBookToBag(Book b) {
		bagService.addBook(b);
//		System.out.println("OG_DEBUG: Add Book To Bag >>>>>>>>>>>>>>>>> " + b.getId());
		return "";

	}

	public String removeBookFromBag(Book b) {
		bagService.removeBook(b);
		System.out.println("OG_DEBUG: Removing Book From Bag >>>>>>>>>>>>>>>>> " + b.getId());
		return "";

	}

	public Map<String, Object> getCurrentReader() {
		return bagService.getCurrentReader();
	}

	public void setCurrentReader(Map<String, Object> r) {
		bagService.setCurrentReader(r);
	}

	
	public boolean isBookInBag(String bookId) {
//		System.out.println("OG_DEBUG: isBookInBag ID " + bookId);
//		System.out.println("OG_DEBUG: isBookInBag BOOLEAN " + bagService.isBookInBag(bookId));
		return bagService.isBookInBag(bookId);
	}
}