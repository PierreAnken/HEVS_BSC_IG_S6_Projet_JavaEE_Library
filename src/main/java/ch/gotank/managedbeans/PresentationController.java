package ch.gotank.managedbeans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ch.gotank.businessobject.Librarian;
import ch.gotank.businessobject.Customer;
import ch.gotank.businessobject.User;
import ch.gotank.businessobject.Author;
import ch.gotank.businessobject.Book;
import ch.gotank.businessobject.Category;
import ch.gotank.bookservice.BookInterface;;

public class PresentationController
{
	// Book Instance
	private BookInterface bookInterface;

	// Variable to store the categorylist
	private List<String> categories;

	// Variable to store the librarylist
	private List<String> libraries;

	// Variable to store the imagelist
	private List<String> bigImages;

	// Variable to store the authorlist
	private List<String> authors;
	private Author selectedAuthor;

	// Variable to store the booklist
	private List<String> bookList;
	private Book selectedBook;

	// Variable to store the customer-, librarian and userlist
	private List<String> customers;
	private List<String> librarians;
	private List<String> users;

	// Variable to store the active and connected user
	private User activeUser;

	@PostConstruct
	public void initialize() throws NamingException {

		// Create reference to book EJB using JNDI
		InitialContext ctx = new InitialContext();
		bookInterface = (BookInterface) ctx.lookup("java:global/...");

		setBookList(new ArrayList<Book>());
		setBookList(bookInterface.getAllBooks());

		setAuthors(new ArrayList<Author>());
		setAuthors(bookInterface.getAllAuthors());

		setLibrarians(new ArrayList<Librarian>());
		setLibrarians(bookInterface.getAllLibrarians());

		setCustomers(new ArrayList<Customer>());
		setCustomers(bookInterface.getAllCustomers());

		setUsers(new ArrayList<User>());
		setUsers(bookInterface.getAllUsers());
		
		setCategories(new ArrayList<String>());
		setCategories(bookInterface.getAllCategories());

		setActiveUser(users.get(2));

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

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public List<String> getLibraries() {
		return libraries;
	}

	public void setLibraries(List<String> libraries) {
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

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public Author getSelectedAuthor() {
		return selectedAuthor;
	}

	public void setSelectedAuthor(Author selectedAuthor) {
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

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<Librarian> getLibrarians() {
		return librarians;
	}

	public void setLibrarians(List<Librarian> librarians) {
		this.librarians = librarians;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(User activeUser) {
		this.activeUser = activeUser;
	}

}