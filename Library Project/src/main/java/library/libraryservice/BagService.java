package library.libraryservice;

import java.util.Map;

import javax.ejb.Remote;

import library.businessobject.Book;

@Remote
public interface BagService{
	
	void removeBook(Book b);
	void removeBook(int bId);
	void addBook(Book b);
	
	Map<String, Object> getCurrentReader();
	void setCurrentReader(Map<String, Object> r);

	boolean isBookInBag(String bookId);
}