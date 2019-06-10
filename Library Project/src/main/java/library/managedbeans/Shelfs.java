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

	@PostConstruct
	public void initialize() throws Exception{

		System.out.println("OG_DEBUG: init Shelfs");
		InitialContext ctx = new InitialContext();
		libraryService = (LibraryService)ctx.lookup("java:global/Library-0.0.1/LibraryBean!library.libraryservice.LibraryService");

		filteredBooks = libraryService.getBooks();
	}

	public void filterBooks() {
		filteredBooks = new ArrayList<Book>();
		List<Book> allBooks = libraryService.getBooks(); // to do create query get only books without active reservations
		for (Book book : allBooks) {
			// check text filter
			if (!filterText.isEmpty() && filterText != null) {
				if (!book.getDescription().contains(filterText) && !book.getTitle().contains(filterText) && !book.getAuthor().contains(filterText)) {
					continue;
				}
			}
			// check filter language
			if (!filterLanguage.isEmpty() && filterLanguage != null) {
				if (!book.getLanguage().equals(filterLanguage)) {
					continue;
				}
			}
			// check filter author
			if (!filterAuthor.isEmpty() && filterAuthor != null) {
				if (!book.getAuthor().equals(filterAuthor)) {
					continue;
				}
			}
			filteredBooks.add(book);
		}
		
	}
	
	public String getFilterAuthor() {
		return filterAuthor;
	}

	public void setFilterAuthor(String filterAuthor) {
		this.filterAuthor = filterAuthor;
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
	}

	public String getFilterLanguage() {
		return filterLanguage;
	}

	public void setFilterLanguage(String filterLanguage) {
		this.filterLanguage = filterLanguage;
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

}