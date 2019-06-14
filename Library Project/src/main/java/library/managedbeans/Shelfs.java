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
	private Book bookInEdit;

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
		
		allBooks = libraryService.getAvailableBooks(); 
		if(userSession.getCurrentLibrarian() != null)
		allBooks = libraryService.getBooks();
			
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
			
			filteredBooks.add(book);
		}
		
		setEditBookId(0);
		
	}
	
	public int getBagSize() {
		return userSession.getBagSize();
	}
	
	public boolean isBookInBag(int id) {
		return userSession.isBookInBag(id);
	}
	
	public void removeFilters() {
		filterAuthor = null;
		filterLanguage = null;
		filterText = null;
		filterBooks();
	}
	
	public void updateBook() {
		libraryService.updateBook(bookInEdit);
		System.out.println("PA_DEBUG: book saved  "+bookInEdit.getTitle());
		setDetailBookId(0);
		setEditBookId(0);
		filterBooks();
	}
	
	public List<Book> getAvailableBooks() {
		return libraryService.getAvailableBooks();
	}
	
	public String addBookToBag(Book b) {
		userSession.addBookToBag(b);
		return "";
	}

	public String removeBookFromBag(Book b) {
		userSession.removeBook(b);
		return "";
	}

	public String getFilterAuthor() {
		return filterAuthor;
	}
	
	public String deleteBookFromShelf(Book b) {
		libraryService.deleteBook(b);
		filterBooks();
		return "";
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
		if(this.editBookId == editBookId) { 	
			this.editBookId = 0;
			this.detailBookId = 0;
		} 
		else {
			
			for(Book b : filteredBooks)
				if(b.getId() == (long)editBookId){
					setBookInEdit(b);
					break;
				}
			
			this.editBookId = editBookId;
			this.detailBookId = editBookId;
		}
	}

	public int getDetailBookId() {
		return detailBookId;
	}

	public String setDetailBookId(int detailBookId) {
		this.detailBookId = detailBookId;
		return "";
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

	public int getSizeBottom() {
		return (19-filteredBooks.size())*24;
	}

	public Book getBookInEdit() {
		return bookInEdit;
	}

	public void setBookInEdit(Book bookInEdit) {
		this.bookInEdit = bookInEdit;
	}


}