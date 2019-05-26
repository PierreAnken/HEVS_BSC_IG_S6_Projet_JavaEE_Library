package library.libraryservice;

import java.util.List;
import javax.ejb.Local;
import library.businessobject.Book;
import library.businessobject.Librarian;
import library.businessobject.Library;
import library.businessobject.Reader;
import library.businessobject.Reservation;

@Local
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
	List<Librarian> getLibrarians();
	List<Reader> getReaders();
	List<Reservation> getReservations();

	void populateLibraryDB();
}