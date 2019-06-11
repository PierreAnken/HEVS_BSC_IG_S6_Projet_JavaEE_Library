package library.managedbeans;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.naming.InitialContext;

import library.businessobject.Address;
import library.businessobject.Reader;
import library.libraryservice.LibraryService;
import library.toolbox.Tb;

@ViewScoped
public class CreateAccount implements Serializable{

	private static final long serialVersionUID = -559640161255847941L;
	
	private Reader newReader;
	private LibraryService libraryService;
	private boolean readerCreated;
	private String error;
	private String answer;
	private boolean alreadyExist;
	
	
	@ManagedProperty(value="#{UserSession}")
    private UserSession userSession;
	
	@PostConstruct
	public void initialize() throws Exception{
		
		System.out.println("PA_DEBUG: init createAccount");
		InitialContext ctx = new InitialContext();
		setLibraryService((LibraryService)ctx.lookup("java:global/Library-0.0.1/LibraryBean!library.libraryservice.LibraryService"));
		newReader = new Reader(new Address());
	}
	
	public void formValidator() {
		
		System.out.println("PA_DEBUG: CreateAccount>formValidator");
		StringBuilder errorMsg = new StringBuilder();
		
		if(!Tb.stringExists(newReader.getFirstname())) {
			errorMsg.append("Firstname ");
		}
		
		if(!Tb.stringExists(newReader.getLastname())) {
			errorMsg.append("Lastname ");
		}
		
		if(!Tb.stringExists(newReader.getAddress().getcity())) {
			errorMsg.append("City ");
		}
		
		if(!Tb.stringExists(newReader.getAddress().getstreet())) {
			errorMsg.append("Street ");
		}
		
		if(!Tb.stringExists(newReader.getAddress().getzipCode())) {
			errorMsg.append("City code ");
		}
		
		if(Tb.stringExists(newReader.getEmail())) {
			Pattern emailP = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
			Matcher matcher = emailP.matcher(newReader.getEmail());
			
			//check if email already exist
			List<Reader> readers = Reader.convertFromMapList(libraryService.getReadersFromEmail(newReader.getEmail()));
			alreadyExist = !readers.isEmpty();
			System.out.println("PA_DEBUG: CreateAccount>formValidator>Email exist: "+alreadyExist);
			
			if(!matcher.find() || alreadyExist) {
				errorMsg.append("Email ");
			}
		}
		else {
			errorMsg.append("Email ");
			alreadyExist = false;

		}
		error = errorMsg.toString();
		
		if(error.isEmpty()) {
			createNewReader();
		}
		System.out.println("PA_DEBUG: CreateAccount>formValidator>Error: "+error);
	}
	
	public void createNewReader() {
		System.out.println("PA_DEBUG: CreateAccount>createNewReader");
		newReader.setCardId(libraryService.getMaxCardId()+1);
		newReader = Reader.convertFromMap(libraryService.addReader(Reader.convertToMap(newReader)));
		readerCreated = true;
		System.out.println("PA_DEBUG: yyy");
		userSession.setCurrentReader(Reader.convertToMap(newReader));
		System.out.println("PA_DEBUG: CreateAccount>createNewReader "+newReader.getCardId());
	}
	
	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
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

	public boolean isAlreadyExist() {
		return alreadyExist;
	}

	public void setAlreadyExist(boolean alreadyExist) {
		this.alreadyExist = alreadyExist;
	}


}