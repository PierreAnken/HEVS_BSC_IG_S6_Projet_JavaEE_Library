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
		booksInBag.add(b);
	}

	@Override
	public void addBook(Book b) {
		booksInBag.remove(b);
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

4
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



}