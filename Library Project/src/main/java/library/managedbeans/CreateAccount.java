package library.managedbeans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.naming.InitialContext;

import library.businessobject.Reader;
import library.libraryservice.LibraryService;

@ViewScoped
public class CreateAccount implements Serializable{

	private static final long serialVersionUID = -559640161255847941L;
	
	private Reader newReader;
	private LibraryService libraryService;
	private String error;
	private String answer;
	
	public void formValidator() {
		System.out.println("PA_DEBUG: CreateAccount>formValidator");
	}
	
	@ManagedProperty(value="#{ReaderBag}")
    private ReaderBag readerBag;
	
	@PostConstruct
	public void initialize() throws Exception{
		
		System.out.println("PA_DEBUG: init createAccount");
		InitialContext ctx = new InitialContext();
		setLibraryService((LibraryService)ctx.lookup("java:global/Library-0.0.1/LibraryBean!library.libraryservice.LibraryService"));	
		
		setNewReader(new Reader());
	}
	
	public ReaderBag getReaderBag() {
		return readerBag;
	}

	public void setReaderBag(ReaderBag readerBag) {
		this.readerBag = readerBag;
	}

	public Reader getNewReader() {
		return newReader;
	}

	public void setNewReader(Reader newReader) {
		this.newReader = newReader;
	}

	public LibraryService getLibraryService() {
		return libraryService;
	}

	public void setLibraryService(LibraryService libraryService) {
		this.libraryService = libraryService;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}


}