package library.libraryservice;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import library.businessobject.Book;
import library.businessobject.Librarian;
import library.businessobject.Library;
import library.businessobject.Reader;
import library.businessobject.Reservation;

@Remote
public interface LibraryService{
	
	//add update
	Book addUpdateBook(Book b);
	Library addUpdateLibrary(Library l);
	Librarian addUpdateLibrarian(Librarian l);
	Reader addUpdateReader(Reader r);
	Reservation addUpdateReservation(Reservation r);
	
	//delete
	void deleteBook(Book b);
	void deleteLibrary(Library l);
	void deleteLibrarian(Librarian l);
	void deleteReader(Reader r);
	void deleteReservation(Reservation r);
	
	//getall
	List<Book> getBooks();
	List<Library> getLibraries();
	List<Map<String, Object>> getLibrarians();
	List<Map<String, Object>> getReaders();
	List<Reservation> getReservations();

	Book getBook(String bookId);
	void populateLibraryDB();
	List<Book> getBooksByAuthor(String author);
	List<Book> getBooksByLanguage(String lang);
	Map<String, Object> getReader(long id);
	Map<String, Object> getReaderFromCardId(int cardId);
	Map<String, Object> getReaderFromCardId(String cardId);
}