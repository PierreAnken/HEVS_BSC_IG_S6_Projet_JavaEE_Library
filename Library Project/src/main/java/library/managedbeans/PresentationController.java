package library.managedbeans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import library.businessobject.Librarian;
import library.businessobject.Library;
import library.businessobject.Reader;
import library.businessobject.User;
import library.businessobject.Book;
import library.bookservice.BookInterface;

public class PresentationController
{
	// Book Instance
	private BookInterface bookInterface;

	// Variable to store the librarylist
	private List<Library> libraries;

	// Variable to store the imagelist
	private List<String> bigImages;

	// Variable to store the authorlist
	private List<String> authors;
	private String selectedAuthor;

	// Variable to store the booklist
	private List<Book> bookList;
	private Book selectedBook;

	// Variable to store the customer-, librarian and userlist
	private List<Librarian> librarians;
	private List<Reader> readers;

	// Variable to store the active and connected user
	private User activeUser;

	@PostConstruct
	public void initialize() throws NamingException {

		// Create reference to book EJB using JNDI
		InitialContext ctx = new InitialContext();
		bookInterface = (BookInterface) ctx.lookup("java:global/...");

		setBookList(new ArrayList<Book>());
		setBookList(bookInterface.getAllBooks());

		setAuthors(new ArrayList<String>());
		setAuthors(bookInterface.getAllAuthors());

		setLibrarians(new ArrayList<Librarian>());
		setLibrarians(bookInterface.getAllLibrarians());

		setReaders(new ArrayList<Reader>());
		setReaders(bookInterface.getAllReaders());
		
		setActiveUser(readers.get(2));

		loadImages();
	}

	public void populateDatabase(){
		bookInterface.populateLibraryDB();
	}

	public void loadImages(){
		bigImages = new ArrayList<String>();
		bigImages.add("brecht.jpg");
		bigImages.add("goethe.jpg");
		bigImages.add("heine.jpg");
		bigImages.add("hesse.jpg");
		bigImages.add("kaestner.jpg");
		bigImages.add("kafka.jpg");
		bigImages.add("schiller.jpg");
		bigImages.add("shakespeare.jpg");
	}

	public List<Library> getLibraries() {
		return libraries;
	}

	public void setLibraries(List<Library> libraries) {
		this.libraries = libraries;
	}

	public BookInterface getBookInterface() {
		return bookInterface;
	}

	public void setBookInterface(BookInterface bookInterface) {
		this.bookInterface = bookInterface;
	}

	public List<String> getBigImages() {
		return bigImages;
	}

	public void setBigImages(List<String> bigImages) {
		this.bigImages = bigImages;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public String getSelectedAuthor() {
		return selectedAuthor;
	}

	public void setSelectedAuthor(String selectedAuthor) {
		this.selectedAuthor = selectedAuthor;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
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

	public User getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(User activeUser) {
		this.activeUser = activeUser;
	}

}