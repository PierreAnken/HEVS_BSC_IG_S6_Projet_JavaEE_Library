package library.bookservice;

import java.util.List;
import javax.ejb.Local;
import library.businessobject.Address;
import library.businessobject.Book;
import library.businessobject.Customer;
import library.businessobject.Librarian;
import library.businessobject.Reader;

@Local
public interface BookInterface {
	
	void saveOrUpdate(Book b);
	void lendBook(Book b, int customerID);
	void bringBackBook(Book b, int customerID);
	void addBook(Book b);
	void updateBook(Book b);
	void deleteBook(Book b);
	
	Book getBookById(int id);
	
	public List<Librarian> getAllLibrarians();

	public List<Customer> getAllCustomers();

	public List<Reader> getAllReaders();

	List<String> getAllAuthors();

	public List<Book> getAllBooks();
	List<String> getAllBookStrings();
	public List<Book> getBooksByTextString(String text);
	public List<Book> getBooksByAuthorID(String author);

	void addAddress(Address ad);
	void updateAddress(Address ad);
	void deleteAddress(Address ad);
	
	public List<Address> getAllAddresses();
	
	public void populateLibraryDB();

}