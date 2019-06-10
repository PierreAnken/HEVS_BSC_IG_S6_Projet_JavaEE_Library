package library.managedbeans;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	private boolean readerCreated;
	private String error;
	private String answer;
	
	public void formValidator() {
		System.out.println("PA_DEBUG: CreateAccount>formValidator check");
		StringBuilder errorMsg = new StringBuilder("Following field(s) are incorrect: ");
		
		if(newReader.getFirstname().isEmpty()) {
			errorMsg.append("Firstname ");
		}
		
		if(newReader.getLastname().isEmpty()) {
			errorMsg.append("Lastname ");
		}
		
		Pattern emailP = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = emailP.matcher(newReader.getEmail());
		
		if(!matcher.find()) {
			errorMsg.append("Email ");
		}
		
		error = errorMsg.toString();
	}
	
	@ManagedProperty(value="#{ReaderBag}")
    private ReaderBag readerBag;
	
	@PostConstruct
	public void initialize() throws Exception{
		
		System.out.println("PA_DEBUG: init createAccount");
		InitialContext ctx = new InitialContext();
		setLibraryService((LibraryService)ctx.lookup("java:global/Library-0.0.1/LibraryBean!library.libraryservice.LibraryService"));
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

	public boolean isReaderCreated() {
		return readerCreated;
	}

	public void setReaderCreated(boolean readerCreated) {
		this.readerCreated = readerCreated;
	}


}