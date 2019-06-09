package library.libraryservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.faces.bean.SessionScoped;

import library.businessobject.Book;
import library.businessobject.Reader;

@Stateful
@SessionScoped
public class BagBean implements BagService {

	private List<Book> booksInBag;
	private Reader currentReader;
	
	
	@Override
	public void removeBook(Book b) {
		System.out.println("OG_DEBUG: Bag Been book remover " + b.getId());
		booksInBag.remove(b);
	}

	@Override
	public int getBagSize() {
		return booksInBag.size();
	}
	
	@Override
	public List<Book> getBagBooks() {
		return booksInBag;
	}

	@Override
	public void addBook(Book b) {
		booksInBag.add(b);
	}

	@Override
	public void removeBook(int bId) {
		for (Book b : booksInBag) {
			if (b.getId() == bId) {
				removeBook(b);
				break;
			}
		}
	}

	@Override
	public Map<String, Object> getCurrentReader() {
		return Reader.convertToMap(currentReader);
	}

	@Override
	public void setCurrentReader(Map<String, Object> reader) {
		currentReader = Reader.convertFromMap(reader);
	}

	@PostConstruct
	public void initialize() {
		booksInBag = new ArrayList<Book>();
		System.out.println("PA_DEBUG: Init BagBean");
	}

	@Override
	public boolean isBookInBag(String bookId) {
		boolean found = false;
		if (booksInBag.size() == 0) {
//			System.out.println("OG_DEBUG: Book " + bookId + " in bag : false because size 0");
			return false;
		}
		for (Book b : booksInBag) {
			if (b.getId() == Integer.parseInt(bookId)) {
				found = true;
				break;
			}
		}
//		System.out.println("OG_DEBUG: Book " + bookId + " in bag : " + found);
		return found;
	}
}