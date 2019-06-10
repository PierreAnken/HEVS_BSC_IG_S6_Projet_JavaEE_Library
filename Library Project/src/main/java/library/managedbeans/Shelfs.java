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
	private int editBookId; // id of the book to show details
	private List<Book> filteredBooks;
	// <-- End Declaring the variables

	@ManagedProperty(value="#{UserSession}")
	private UserSession userSession;

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
		if (userSession.getCurrentLibrarian() != null) {
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
			if (userSession.isBookInBag(book.getId())) {
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
		userSession.addBookToBag(b);
		filterBooks();
	}

	public void removeBookFromBag(Book b) {
		userSession.removeBook(b);
		filterBooks();
	}

	public String getFilterAuthor() {
		return filterAuthor;
	}
	
	public void deleteBookFromShelf(Book b) {
		libraryService.deleteBook(b);
		filterBooks();
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

	public int getEditBookId() {
		return editBookId;
	}

	public void setEditBookId(int editBookId) {
		this.editBookId = editBookId;
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

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

}