package library.libraryservice;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;

import library.businessobject.Book;
import library.businessobject.Reader;

@Stateful
public class BagBean implements BagService {

	private List<Book> booksInBag;
	private Reader currentReader;
	private String cardId;
	
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
	public Reader getCurrentReader() {
		return currentReader;
	}

	@Override
	public void setCurrentReader(Reader currentReader) {
		this.currentReader = currentReader;
	}

	@PostConstruct
	public void initialize() {
		booksInBag = new ArrayList<Book>();
	}

	@PreDestroy
	public void clear() {
		booksInBag = null;
		setCurrentReader(null);
		cardId = "";
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
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