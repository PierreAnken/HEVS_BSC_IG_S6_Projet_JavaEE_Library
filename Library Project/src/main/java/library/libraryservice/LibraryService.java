package library.libraryservice;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.persistence.EntityManager;

import library.businessobject.Book;
import library.businessobject.Librarian;
import library.businessobject.Library;
import library.businessobject.Reader;
import library.businessobject.Reservation;

@Remote
public interface LibraryService{
	
	//add 
	Book addBook(Book b);
	Library addLibrary(Library l);
	Librarian addLibrarian(Librarian l);
	Reader addReader(Reader r);
	
	Map<String, Object> addLibrarian(Map<String, Object> l);
	Map<String, Object> addReader(Map<String, Object> r);
	
	Map<String, Object> addReservation(Map<String, Object> r);
	
	//update
	void updateBook(Book b);
	Library updateLibrary(Library l);
	Librarian updateLibrarian(Librarian l);
	Map<String, Object> updateReader(Map<String, Object> r);
	Map<String, Object> updateReservation(Map<String, Object> r);
	
	//delete
	void deleteBook(Book b);
	void deleteLibrary(Library l);
	void deleteLibrarian(Librarian l);
	void deleteReader(Map<String, Object> r);
	void deleteReservation(Reservation r);
	
	//getall
	List<Book> getBooks();
	List<Library> getLibraries();
	List<Map<String, Object>> getLibrarians();
	List<Map<String, Object>> getReaders();
	List<Reservation> getReservations();

	Book getBook(String bookId);
	List<Reservation> getActiveReservationFromReader(String email);
	void populateLibraryDB();
	Map<String, Object> getReader(long id);
	Map<String, Object> getReaderFromCardId(int cardId);
	Map<String, Object> getReaderFromCardId(String cardId);
	List<Map<String, Object>> getReadersFromEmail(String email);
	Map<String, Object> getLibrarianFromEmail(String email);
	int getMaxCardId();
	List<Book> getAvailableBooks();
	
	EntityManager getEm();
	void setEm(EntityManager em);
	void flush();
}