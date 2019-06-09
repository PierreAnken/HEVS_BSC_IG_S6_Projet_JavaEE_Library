package library.managedbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;
import javax.faces.bean.SessionScoped;

import library.businessobject.Book;
import library.businessobject.Reader;

@SessionScoped
@Stateful
public class ReaderBag{

	private List<Book> booksInBag;
	private Reader currentReader;
	
	
	public void removeBook(Book b) {
		System.out.println("OG_DEBUG: Bag Been book remover " + b.getId());
		booksInBag.remove(b);
	}

	public int getBagSize() {
		return booksInBag.size();
	}
	
	public List<Book> getBagBooks() {
		return booksInBag;
	}

	public void addBook(Book b) {
		booksInBag.add(b);
	}

	public void removeBook(int bId) {
		for (Book b : booksInBag) {
			if (b.getId() == bId) {
				removeBook(b);
				break;
			}
		}
	}

	public Map<String, Object> getCurrentReader() {
		return Reader.convertToMap(currentReader);
	}

	public void setCurrentReader(Map<String, Object> reader) {
		currentReader = Reader.convertFromMap(reader);
	}

	public void initialize() {
		booksInBag = new ArrayList<Book>();
		System.out.println("PA_DEBUG: Init BagBean");
	}

	public boolean isBookInBag(String bookId) {
		boolean found = false;
		if (booksInBag.size() == 0) {
			
			return false;
		}
		for (Book b : booksInBag) {
			if (b.getId() == Integer.parseInt(bookId)) {
				found = true;
				break;
			}
		}
		return found;
	}
}