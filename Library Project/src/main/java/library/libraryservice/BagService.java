package library.libraryservice;

import javax.ejb.Remote;

import library.businessobject.Book;



@Remote
public interface BagService{
	
	void removeBook(Book b);
	void removeBook(int bId);
	void addBook(Book b);

}