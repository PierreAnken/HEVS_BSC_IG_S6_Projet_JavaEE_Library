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
import library.toolbox.Tb;

@ManagedBean(name = "shelfs")
@ViewScoped
public class Shelfs implements Serializable{

	private static final long serialVersionUID = -8161450633737207398L;

	private LibraryService libraryService;
	private String filterText; 
	private String filterLanguage; 
	private String filterAuthor; 
	private int detailBookId;
	private int editBookId;
	private List<Book> filteredBooks;

	@ManagedProperty(value="#{UserSession}")
    private UserSession userSession;

	@PostConstruct
	public void initialize() throws Exception{

		System.out.println("OG_DEBUG: init Shelfs ");
		InitialContext ctx = new InitialContext();
		setLibraryService((LibraryService)ctx.lookup("java:global/Library-0.0.1/LibraryBean!library.libraryservice.LibraryService"));
		filterBooks();
		
	}

	public void filterBooks() {
		System.out.println("OG_DEBUG: entering book filter method");
		
		List<Book> allBooks = new ArrayList<Book>();
		setFilteredBooks(new ArrayList<Book>());
		
		if (userSession.getCurrentLibrarian() != null) {
			allBooks = libraryService.getBooks(); 
		} else {
			allBooks = libraryService.getBooks();
		}
		
		for (Book book : allBooks) {
			// check text filter
			if (Tb.stringExists(filterText)) {
				if (!book.getDescription().toLowerCase().contains(filterText.toLowerCase()) && !book.getTitle().toLowerCase().contains(filterText.toLowerCase()) && !book.getAuthor().toLowerCase().contains(filterText.toLowerCase())) {
					continue;
				}
			}
			// check filter language
			if (Tb.stringExists(filterLanguage)) {
				if (!book.getLanguage().equals(filterLanguage)) {
					continue;
				}
			}
			// check filter author
			if (Tb.stringExists(filterAuthor)) {
				if (!book.getAuthor().equals(filterAuthor)) {
					continue;
				}
			}
			System.out.println("PA_DEBUG:isBookInBag ");
			//check if already in bag
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