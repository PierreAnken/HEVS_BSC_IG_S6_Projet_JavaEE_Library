package library.libraryservice;

import java.util.List;
import javax.ejb.Local;
import library.businessobject.Address;
import library.businessobject.Book;
import library.businessobject.Librarian;
import library.businessobject.Library;
import library.businessobject.Reader;

@Local
public interface LibraryService{
	
	void saveOrUpdate(Book b);
	void lendBook(Book b, int customerID);
	void bringBackBook(Book b, int customerID);
	void addBook(Book b);
	void updateBook(Book b);
	void deleteBook(Book b);
	
	Book getBookById(int id);
	
	public List<Librarian> getAllLibrarians();
	public List<Reader> getAllReaders();


	public List<Book> getAllBooks();
	List<String> getAllBookStrings();
	public List<Book> getBooksByTextString(String text);
	public List<Book> getBooksByAuthorID(String author);
	
	public List<Address> getAllAddresses();
	
	void addLibrary(Library l);
	void populateLibraryDB();
}