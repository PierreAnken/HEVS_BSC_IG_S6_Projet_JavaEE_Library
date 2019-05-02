package ch.gotank.bookservice;

import java.util.List;
import javax.ejb.Local;
import ch.gotank.businessobject.Address;
import ch.gotank.businessobject.Author;
import ch.gotank.businessobject.Book;
import ch.gotank.businessobject.Category;
import ch.gotank.businessobject.Customer;
import ch.gotank.businessobject.Librarian;
import ch.gotank.businessobject.User;

@Local
public interface BookInterface {
	
	void saveOrUpdate(Book b);
	void lendBook(Book b, int customerID);
	void bringBackBook(Book b, int customerID);
	void addBook(Book b);
	void updateBook(Book b);
	void deleteBook(Book b);
	
	Book getBookById(int id);
	Book getBookByISBN(String isbn);
	
	public List<Category> getAllCategories();
	
	public List<Librarian> getAllLibrarians();

	public List<Customer> getAllCustomers();

	public List<User> getAllUsers();

	public List<Book> getAllBooks();
	public List<Book> getBooksByTextString(String text);
	public List<Book> getBooksByRelaseYear(int year);
	public List<Book> getBooksByAuthorID(int id);
	public List<Book> getBooksByAuthorName(String lastname, String firstname);

	void addAuthor(Author a);
	void updateAuthor(Author a);
	void deleteAuthor(Author a);
	
	public List<Author> getAllAuthors();

	void addAddress(Address ad);
	void updateAddress(Address ad);
	void deleteAddress(Address ad);
	
	public List<Address> getAllAddresses();
	
	public void populateLibraryDB();

}