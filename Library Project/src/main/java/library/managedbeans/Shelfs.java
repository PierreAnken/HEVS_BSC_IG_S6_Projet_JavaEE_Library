package library.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.naming.InitialContext;

import library.businessobject.Book;
import library.businessobject.Reader;
import library.libraryservice.LibraryService;

@ManagedBean(name = "shelfs")
@ViewScoped
public class Shelfs implements Serializable{

	// Start Declaring the variables -->
	private static final long serialVersionUID = -8161450633737207398L;

	private LibraryService libraryService;
	private String filterText; // plain text search
	private String filterLanguage; // filter by language
	private String filterAuthor; // filter by author
	private int detailBookId; // id of the book to show details
	private List<Book> filteredBooks;
	// <-- End Declaring the variables

	@ManagedProperty(value="#{ReaderBag}")
	private ReaderBag readerBag;

	@ManagedProperty(value="#{LibrarianSession}")
	private LibrarianSession librarianSession;

	@PostConstruct
	public void initialize() throws Exception{

		System.out.println("OG_DEBUG: init Shelfs");
		filterText = "";
		filterAuthor = "";
		filterLanguage = "";
		InitialContext ctx = new InitialContext();
		libraryService = (LibraryService)ctx.lookup("java:global/Library-0.0.1/LibraryBean!library.libraryservice.LibraryService");
		filteredBooks = libraryService.getBooks();
		filterBooks();
	}

	public void filterBooks() {
		System.out.println("OG_DEBUG: entering book filter method");
		filteredBooks = new ArrayList<Book>();
		List<Book> allBooks;
		if (librarianSession.getCurrentLibrarian() != null) {
			allBooks = libraryService.getBooks(); // to do create query get all books if it is a librarian
		} else {
			allBooks = libraryService.getAvailableBooks(); // to do create query get only books without active reservations			
		}
		for (Book book : allBooks) {
			// check text filter
			if (!filterText.isEmpty()) {
				if (!book.getDescription().contains(filterText) && !book.getTitle().contains(filterText) && !book.getAuthor().contains(filterText)) {
					continue;
				}
			}
			// check filter language
			if (!filterLanguage.isEmpty()) {
				if (!book.getLanguage().equals(filterLanguage)) {
					continue;
				}
			}
			// check filter author
			if (!filterAuthor.isEmpty()) {
				if (!book.getAuthor().equals(filterAuthor)) {
					continue;
				}
			}
			// check if already in bag
			if (readerBag.isBookInBag(book.getId())) {
				continue;
			}
			filteredBooks.add(book);
		}
		
	}
	
	public void removeFilters() {
		filterAuthor = "";
		filterLanguage = "";
		filterText = "";
		filterBooks();
	}
	
	public List<Book> getAvailableBooks() {
		return libraryService.getAvailableBooks();
	}
	
	public void addBookToBag(Book b) {
		readerBag.addBookToBag(b);
		filterBooks();
	}

	public void removeBookFromBag(Book b) {
		readerBag.removeBook(b);
		filterBooks();
	}

	public String getFilterAuthor() {
		return filterAuthor;
	}

	public void setFilterAuthor(String filterAuthor) {
		System.out.println("OG_DEBUG : filtering by author " + filterAuthor);
		this.filterAuthor = filterAuthor;
		filterBooks();
	}

	public LibraryService getLibraryService() {
		return libraryService;
	}

	public void setLibraryService(LibraryService libraryService) {
		this.libraryService = libraryService;
	}

	public String getFilterText() {
		return filterText;
	}

	public void setFilterText(String filterText) {
		this.filterText = filterText;
		filterBooks();
	}

	public String getFilterLanguage() {
		return filterLanguage;
	}

	public void setFilterLanguage(String filterLanguage) {
		this.filterLanguage = filterLanguage;
		filterBooks();
	}

	public int getDetailBookId() {
		return detailBookId;
	}

	public void setDetailBookId(int detailBookId) {
		this.detailBookId = detailBookId;
	}

	public List<Book> getFilteredBooks() {
		return filteredBooks;
	}

	public void setFilteredBooks(List<Book> filteredBooks) {
		this.filteredBooks = filteredBooks;
	}

	public ReaderBag getReaderBag() {
		return readerBag;
	}

	public void setReaderBag(ReaderBag readerBag) {
		this.readerBag = readerBag;
	}

	public LibrarianSession getLibrarianSession() {
		return librarianSession;
	}

	public void setLibrarianSession(LibrarianSession librarianSession) {
		this.librarianSession = librarianSession;
	}

}