package library.libraryservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import library.businessobject.Address;
import library.businessobject.Book;
import library.businessobject.Librarian;
import library.businessobject.Library;
import library.businessobject.Reader;
import library.businessobject.Reservation;

@Stateless
public class LibraryBean implements LibraryService{

	@PersistenceContext(name = "LibraryPU")
	private EntityManager em;


	@Override
	public List<Book> getAvailableBooks() {
		return (List<Book>) em.createQuery("SELECT b FROM Book b where b.id NOT IN (select r.book.id from Reservation r) order by b.title asc").getResultList();
	}
	
	@Override
	public List<Map<String, Object>> getActiveReservationFromReader(String email) {
		
		List<Reservation> reservations = (List<Reservation>) em.createQuery("SELECT r FROM Reservation r JOIN r.reader u WHERE u.email =:email and r.bookReturned = FALSE").setParameter("email", email).getResultList();
		return Reservation.convertToMapList(reservations);
	}
	
	@Override
	public Map<String, Object> getLibrarianFromEmail(String email) {
		Librarian librarian = (Librarian) em.createQuery("FROM User u WHERE u.id = :email").setParameter("email", email).getSingleResult();
		return Librarian.convertToMap(librarian);
	}

	//get all 
	@Override
	public List<Book> getBooks() {
		return (List<Book>) em.createQuery("FROM Book b order by b.title asc").getResultList();
	}
	
	@Override
	public Book getBook(String bookId) {
		return (Book) em.createQuery("FROM Book b WHERE b.id = :bookId").setParameter("bookId", Long.parseLong(bookId)).getSingleResult();
	}

	@Override
	public List<Library> getLibraries() {
		return (List<Library>) em.createQuery("FROM Library l").getResultList();
	}
	
	@Override
	public int getMaxCardId() {
		return (int) em.createQuery("select max(r.cardId) FROM Reader r").getSingleResult();
	}

	@Override
	public List<Map<String, Object>> getLibrarians() {
		
		List<Librarian> librarians = (List<Librarian>) em.createQuery("FROM Librarian l").getResultList();
		return Librarian.convertToMapList(librarians);
	}
	
	private List<Librarian> getLibrariansP() {
		return (List<Librarian>) em.createQuery("FROM Librarian l").getResultList();
	}

	@Override
	public List<Map<String, Object>> getReaders() {
		List<Reader> readers = (List<Reader>) em.createQuery("FROM Reader r").getResultList();
		return Reader.convertToMapList(readers);
	}
	
	private List<Reader> getReadersP() {
		return (List<Reader>) em.createQuery("FROM Reader r").getResultList();
	}

	@Override
	public List<Reservation> getReservations() {
		return (List<Reservation>) em.createQuery("FROM Reservation r").getResultList();
	}
	
	@Override
	public Map<String, Object> getReader(long readerId) {
		Reader reader = (Reader) em.createQuery("FROM User u WHERE u.id = :readerId").setParameter("readerId", readerId).getSingleResult();
		return Reader.convertToMap(reader);
	}

	@Override
	public Map<String, Object> getReaderFromCardId(int cardId) {
		Reader reader = (Reader) em.createQuery("FROM Reader r WHERE r.cardId = :cardId").setParameter("cardId", cardId).getSingleResult();
		return Reader.convertToMap(reader);
	}
	
	@Override
	public List<Map<String, Object>> getReadersFromEmail(String email) {
		List<Reader> readers = (List<Reader>) em.createQuery("FROM Reader r WHERE r.email = :email").setParameter("email", email).getResultList();
		return Reader.convertToMapList(readers);
	}

	// add
	public Book addBook(Book b) {
		em.persist(b);
		return b;
	}
	
	public Library addLibrary(Library l) {
		em.persist(l);
		return l;
	}
	
	public Librarian addLibrarian(Librarian l) {
		em.persist(l);
		return l;
	}
	
	public Reader addReader(Reader r) {
		em.persist(r);
		return r;
	}
	
	public Map<String, Object> addReservation(Map<String, Object> r) {
		Reservation res = Reservation.convertFromMap(r);
		System.out.println("PA_DEBUG: Add Reservation "+ res.getReader().getEmail());
		em.persist(res);
		return Reservation.convertToMap(res);
	}
	
	@Override
	public Map<String, Object> addLibrarian(Map<String, Object> l) {
		Librarian librarian = Librarian.convertFromMap(l);
		em.persist(librarian);
		return Librarian.convertToMap(librarian);
	}

	@Override
	public Map<String, Object> addReader(Map<String, Object> r) {
		Reader reader = Reader.convertFromMap(r);
		em.persist(reader);
		return Reader.convertToMap(reader);
	}
	
	//update
	public void updateBook(Book b) {
		em.merge(b);
		System.out.println("OG_DEBUG: Starting update Book " + b.getId());
		System.out.println("OG_DEBUG: Book Language " + b.getLanguage());
		System.out.println("OG_DEBUG: Book Author " + b.getAuthor());
		System.out.println("OG_DEBUG: Book Description " + b.getDescription());
		System.out.println("OG_DEBUG: Update Book successful " + b.getId());
	}
	
	public Library updateLibrary(Library l) {
		em.merge(l);
		return l;
	}
	
	public Librarian updateLibrarian(Librarian l) {
		em.merge(l);
		return l;
	}
	
	public Map<String, Object> updateReader(Map<String, Object> r) {
		Reader reader = Reader.convertFromMap(r);
		em.merge(reader);
		return Reader.convertToMap(reader);
	}
	
	public Map<String, Object> updateReservation(Map<String, Object> r) {
		Reservation res = Reservation.convertFromMap(r);
		em.merge(res);
		return Reservation.convertToMap(res);
	}
	
	//delete
	@Override
	public void deleteBook(Book b) {
		System.out.println("PA_DEBUG : delete book " + b.getId());
		b = em.find(Book.class,b.getId());
		em.remove(b);
	}

	@Override
	public void deleteLibrary(Library l) {
		l = em.find(Library.class,l.getId());
		em.remove(l);
	}

	@Override
	public void deleteLibrarian(Librarian l) {
		l = em.find(Librarian.class,l.getEmail());
		em.remove(l);
	}

	@Override
	public void deleteReader(Map<String, Object> reader) {
		Reader r = Reader.convertFromMap(reader);
		r = em.find(Reader.class,r.getEmail());
		em.remove(r);
	}

	@Override
	public void deleteReservation(Reservation r) {
		r = em.find(Reservation.class,r.getId());
		em.remove(r);
	}
	
	@Override
	public Map<String, Object> getReaderFromCardId(String cardId) {
		return getReaderFromCardId(Integer.parseInt(cardId));
	}
	
	@PostConstruct
	public void initialize() throws Exception{
		populateLibraryDB();
	}
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public void populateLibraryDB() {

		//if db is empty we populate it
		if(getBooks().size() > 0){
			return;
		}
		
		System.out.println("PA_DEBUG: DB> deleting everything");
		
		// == Clear DB ==
		
		//Delete all book (cascade reservation)
		List<Book>booksTodelete = getBooks();
		for(int i=0; i<booksTodelete.size();i++)
			em.remove(booksTodelete.get(i));
		
		//delete old readers
		List<Reader> readers = getReadersP(); 
		for(int i=0; i<readers.size();i++)
			em.remove(readers.get(i));
		
		//Delete libraries and linked librarians
		List<Library> libraries = getLibraries(); 
		for(int i=0; i<libraries.size();i++)
			em.remove(libraries.get(i));
		
		//delete old librarians
		List<Librarian> librarians = getLibrariansP(); 
		for(int i=0; i<librarians.size();i++)
			em.remove(librarians.get(i));
		
		
		System.out.println("PA_DEBUG: DB> Populating");
		
		
		// == Populate DB ==
		
		Address sierre = new Address("3960", "Rue Notre Dame des Marais 5", "Sierre");
		Address sierre2 = new Address("3960", "Route de Sion 65", "Sierre");
		Address sierre3 = new Address("3960", "Rue du Grain d'or 14B", "Sierre");
		Address brig1 = new Address("3900", "Ronesand Strasse 21", "Brig");
		Address flanthey1 = new Address("3978", "Rue du moulin 13", "Flanthey");
	
		Library lib1 = new Library("Bibliotheque Sierre", sierre);
		em.persist(lib1);
		
		System.out.println("PA_DEBUG: DB> Library populated");
		
		// Creating the books
		List<Book> books = new ArrayList<Book>();
		
		books.add(new Book("You Deserve This", "Gesunde und natürliche Ernährung ist das, was Körper und Seele täglich verdienen. Und das hat rein gar nichts mit Verzicht zu tun! Sich langfristig grossartig zu fühlen, vor Energie zu sprühen und der Gesundheit etwas Gutes zu tun, ist auch im Alltag möglich – mit Bowls! Dabei werden einfache, natürliche und vollwertige Gerichte ohne stundenlange Küchenarbeit in schönen Schalen angerichtet und serviert. Hierfür hat Pamela Reif ihre liebsten Rezepte fotografiert und niedergeschrieben. Ergänzt wird das Bowl-Kochbuch durch eine grundlegende Lebensmittelkunde, die den Sinn und die Vorteile dieser ausgewogenen Ernährung leicht verständlich erläutert. Die ultimative Ernährungsformel ist nicht zwangsläufig low carb oder low fat - es kommt darauf an, dass die Nahrung echt und natürlich ist.", "Pamela Reif", "DE",lib1));
		books.add(new Book("Sœurs", "Mai 1993. Deux sœurs sont retrouvées mortes en bordure de Garonne. Vêtues de robes de communiantes et attachées à des troncs d'arbres. C'est la première enquête du jeune Martin Servaz qui vient d'intégrer la PJ de Toulouse. Très vite, il s'intéresse à Erik Lang, auteur de romans policiers à l'œuvre aussi cruelle que dérangeante. Les deux sœurs n'étaient-elles pas ses fans ? L'un de ses plus grands succès ne s'appelle-t-il pas La Communiante ? L'affaire connaît un dénouement inattendu, laissant Servaz rongé par le doute : dans cette enquête, estime-t-il, une pièce manque, une pièce essentielle. Février 2018. L'écrivain Erik Lang découvre sa femme assassinée... elle aussi vêtue en communiante. Vingt-cinq ans après le double crime, Martin Servaz est rattrapé par l'affaire. Le choc réveille ses premières craintes. Jusqu'à l'obsession. Une épouse, deux sœurs, trois communiantes... et si l'enquête de 1993 s'était trompée de coupable ?","Bernard Minier", "FR",lib1));
		books.add(new Book("Glacé", "Dans une vallée encaissée des Pyrénées, au petit matin d'une journée glaciale de décembre, les ouvriers d'une centrale hydroélectrique découvrent le corps sans tête d'un cheval, accroché à la falaise. Ce même jour une jeune psychologue prend son premier poste dans le centre psychiatrique de haute sécurité qui surplombe la vallée. Le commandant Servaz, flic hypocondriaque et intuitif, se voit confier l'enquête la plus étrange de toute sa carrière.","Bernard Minier", "FR",lib1));
		books.add(new Book("Nuit", "Nuit de tempête en mer du Nord. Secoué par des vents violents, l'hélicoptère dépose Kirsten Nigaard sur la plate-forme pétrolière. L'inspectrice norvégienne enquête sur le meurtre d'une technicienne de la base offshore. Un homme manque à l'appel. En fouillant sa cabine, Kirsten découvre une série de photos. Quelques jours plus tard, elle est dans le bureau de Martin Servaz. L'absent s'appelle Julian Hirtmann, le tueur retors et insaisissable que le policier poursuit depuis des années. Étrangement, sur plusieurs clichés, Martin Servaz apparaît. Kirsten lui tend alors une autre photo. Celle d'un enfant. Au dos, juste un prénom : Gustav. ","Bernard Minier", "FR",lib1));
		books.add(new Book("Le Dernier Secret du Vatican", "25. L'empereur Constantin convoque à Nicée un concile général des évêques de l'Empire romain afin de fixer les dogmes de l'Église chrétienne. 1070. L'Ordre religieux et militaire de Saint-Jean de Jérusalem est créé. Après l'expulsion des croisés de Terre sainte, il s'installe à Malte et reçoit, en 1312, les biens des Templiers. En 1798, sur les traces d'un secret bien gardé, Bonaparte arrive dans l'île et provoque l'éclatement de l'Ordre.  2019. À la mort du pape, un conclave se réunit. Une conspiration se trame alors autour de mystérieux documents, datant du concile de Nicée, qui, s'ils étaient dévoilés, pourraient mettre l'Église en péril. En quête de ceux-ci, Cotton Malone va devoir affronter une société occulte et décrypter bon nombre d'énigmes historiques et religieuses pour percer le dernier secret du Vatican. Plus de 5 millions d'amateurs de thrillers et de passionnés d'histoire ont déjà plébiscité à travers le monde les romans de Steve Berry où ésotérisme, action et suspense se conjuguent à merveille. ","Steve Berry", "FR",lib1));
		books.add(new Book("La Conspiration Hoover", "2000. Officier de marine, Cotton Malone est recruté par le ministère de la Justice pour récupérer au fond des mers une pièce de collection extrêmement rare. Celle-ci doit servir de monnaie d'échange pour obtenir d'un ancien opérationnel de la CIA des dossiers secrets relatifs aux agissements occultes du FBI dans les années 1960.  Alors que se dessine l'implication d'une branche clandestine du FBI dans un assassinat qui, en 1968, a bouleversé l'histoire, Malone est engagé dans une quête périlleuse, semée d'intrigues et de complots. Au centre de la toile, la figure d'Edgar J. Hoover, dont les secrets sont aussi nombreux qu'inavouables. Dans cette douzième aventure, Cotton Malone se remémore la création de la division Magellan, branche secrète du ministère de la Justice, et sa première enquête au sein de celle-ci. Les nombreux fans de Steve Berry ne seront pas déçus !","Steve Berry", "FR",lib1));
		books.add(new Book("L'héritage des Templiers", "1118 : Jérusalem, Terre sainte. Neuf chevaliers créent un ordre militaire, les ' Pauvres Chevaliers du Christ ' Le roi Baudoin II leur cède une partie de son palais, bâti sur les ruines du Temple de Salomon. Ils deviennent les ' chevaliers du Temple ', puis les ' Templiers '. 1307: Jacques de Molay, grand maître des Templiers, est arrêté sur ordre de Philippe le Bel. Il garde le silence sur le trésor des Templiers. 2006 : Cotton Malone, ex-agent du département de la Justice américaine, et son amie Stéphanie, entrent en possession de documents liés au fameux trésor. Commence alors une quête qui les mènera à Rennes-le-Château, cœur du mystère.","Steve Berry", "FR",lib1));
		books.add(new Book("Die dunklen Lande", "1629. Der 30 Jährige Krieg mit seinen Konflikten erschüttert Europa und tobt besonders gnadenlos in Deutschland. Die junge Abenteurerin Aenlin Kane reist in die neutrale Stadt Hamburg, um das Erbe ihres berühmten Vaters Solomon Kane zu ergründen. Zusammen mit ihrer Freundin Tahmina, einer persischen Mystikerin, gerät sie in die Wirren des Krieges. Sie nehmen einen folgenschweren Auftrag der West-Indischen Compagnie an: Eine zusammengewürfelte Truppe soll sich durch die Linien nach Süddeutschland durchschlagen, bis nach Bamberg, wo grausamste Hexenprozesse die Scheiterhaufen brennen lassen - doch es kommt vieles anders. Zu viel für einen Zufall! Aenlin und Tahmina wissen um das Böse und die Dämonen, die sich auf der Erde tummeln und die Wirren des Krieges zu ihrem Vorteil nutzen. Schon bald geht es um mehr als einen Auftrag der Compagnie. Und der Anführer der Truppe, Nicolas, hat ein düsteres Geheimnis …","Markus Heitz ", "DE",lib1));
		books.add(new Book("Die Klinge des Schicksals", "Seit vor 150 Jahren der Wald in Yarkin begonnen hat, sich unaufhaltsam auszubreiten, sind die Menschen immer weniger geworden. Die letzten Überlebenden wurden auf eine Halbinsel zurückgedrängt. Immer wieder hat man Expeditionen ausgesandt, um ein Mittel gegen das Vordringen der Bäume zu finden – keine kehrte zurück. Bis die legendäre Kriegerin Danèstra auf Kalenia trifft, die eine schier unglaubliche Geschichte erzählt: von einer Siedlung im Wald und einem grausamen Überfall, der das wahre Böse offenbart habe; und von einer Verschwörung unter den Menschen, die nur sie, Kalenia, aufdecken könne. Sie bittet die Kriegerin um Hilfe. Doch kann Danèstra ihr wirklich trauen?","Markus Heitz ", "DE",lib1));
		books.add(new Book("Des Teufels Gebetbuch", "Der ehemalige Spieler Tadeus Boch gelangt in Baden-Baden in den Besitz einer mysteriösen Spielkarte aus einem vergangenen Jahrhundert. Alsbald gerät er in einen Strudel unvorhergesehener und mysteriöser Ereignisse, in dessen Zentrum die uralte Karte zu stehen scheint. Die Rede ist von einem Fluch. Was hat es mit ihr auf sich? Wer erschuf sie? Gibt es noch weitere? Wo könnte man sie finden? Dafür interessieren sich viele, und bald wird Tadeus gejagt, während er versucht, dem Geheimnis auf die Spur zu kommen. Plötzlich steigt der Einsatz: Es ist nicht weniger als sein eigenes Leben.","Markus Heitz ", "DE",lib1));
		books.add(new Book("Die Zwerge", "»Die Zwerge« sind die meistgekaufte deutschsprachige Fantasyserie unserer Zeit. Über fünf Bände hat Markus Heitz die Abenteuer des einfachen Schmiedegesellen Tungdil erzählt, der zum unsterblichen Helden eines ganzen Volkes wird. Im Kampf gegen dunkle Albae, Verräter in den eigenen Reihen und zwielichtige Magier beweisen Tungdil und seine Verbündeten, dass auch die Kleinen ganz Großes leisten können. Erstmals liegen nun alle fünf Bände in einmaliger Sammlerausstattung vor.","Markus Heitz ", "DE",lib1));
		books.add(new Book("Vergessene Welt - Band 3", "Zwei Stunden. Zwei Stunden haben Jenna und Marek, um Leons Leben zu retten und ihn und Benjamin aus den Händen Roanars zu befreien. In einer kleinen Gruppe der M’atay finden sie neue Verbündete, jedoch zu einem hohen Preis. Sollten sie alle den Kampf in der Burg Camilor überleben, müssen sie nun auch noch die gefangenen Stammesmitglieder ihrer neuen Mitstreiter finden und befreien und damit den ‚Freien‘ offensiv den Krieg erklären. Einer Übermacht an Feinden in einem von magischen Fallen und Flüchen gepeinigten Land gegenüberzutreten, ist eine Aufgabe, der sich weder Jenna noch Marek gerne stellen. Doch haben sie eine andere Wahl?","Ina Linger", "DE",lib1));
		books.add(new Book("Die Mondiar-Trilogie", "Diese Regeln werden den Diatar von Kindesbeinen an eingebläut. Wer sich nicht an sie hält, ist des Todes. Das weiß auch der junge Krieger Jaro. Doch als er in einem Kampf mit den Monandor, den Dämonen der Dunkelheit, schwer verwundet wird, gelingt es ihm nicht mehr, vor Einbruch der Nacht zurück in sein Dorf zu kehren. Es ist ausgerechnet Risa, die ihn findet und in eine Höhle schleppt. Risa, die ihm zwar bereits das Leben rettete, als sie beide noch Kinder waren, die er jedoch seit Jahren nicht mehr gesehen hat. Sein Leben ein weiteres Mal in ihren Händen zu wissen, erfüllt Jaro mit Angst, denn eines weiß er mit Sicherheit: Niemand bleibt so unschuldig und gut, wie er als Kind einst gewesen ist – schon gar nicht eine menschenfressende Dämonin der Nacht …","Ina Linger", "DE",lib1));
		books.add(new Book("Fremde Welt - Band VI", "Demeons überraschendes Auftauchen in Falaysia macht Jennas und Leons Situation schwieriger als jemals zuvor, denn allen, die ihn kennen, ist sofort klar, dass der Zauberer seine ganz eigenen finsteren Pläne verfolgt und grandios darin ist, Menschen zu manipulieren und gegeneinander auszuspielen.  Die ohnehin nicht mehr ganz klaren Fronten im Machtkampf um die Länder Falaysias scheinen sich bald schon unter Demeons und Alentaras Einfluss weiter zu zersplittern und zwingen auf einmal Teile der verfeindeten Parteien dazu, sich miteinander zu verbünden. Den richtigen Weg zu finden und ihre eigenen Ziele im Auge zu behalten, fällt Jenna und Leon in diesem Durcheinander immer schwerer und sie sind mehr denn je auf die Hilfe ihrer Freunde angewiesen – der alten und der neuen, ungewollten …","Ina Linger", "DE",lib1));
		books.add(new Book("Good Omens", "According to The Nice and Accurate Prophecies of Agnes Nutter, Witch, Judgement Day is almost upon us and the world's going to end in a week . . . Now people have been predicting the end of the world almost from its very beginning, so it's only natural to be sceptical when a new date is set for Judgement Day. But what if, for once, the predictions are right, and the apocalypse really is due to arrive next Saturday, just after tea? You could spend the time left drowning your sorrows, giving away all your possessions in preparation for the rapture, or laughing it off as (hopefully) just another hoax. Or you could just try to do something about it. It's a predicament that Aziraphale, a somewhat fussy angel, and Crowley, a fast-living demon now finds themselves in. They've been living amongst Earth's mortals since The Beginning and, truth be told, have grown rather fond of the lifestyle and, in all honesty, are not actually looking forward to the coming Apocalypse.","Terry Pratchett", "EN",lib1));
		books.add(new Book("The Colour Of Magic", "In the beginning there was…a turtle. Somewhere on the frontier between thought and reality exists the Discworld, a parallel time and place which might sound and smell very much like our own, but which looks completely different. Particularly as it’s carried though space on the back of a giant turtle (sex unknown). It plays by different rules. But then, some things are the same everywhere. The Disc’s very existence is about to be threatened by a strange new blight: the world’s first tourist, upon whose survival rests the peace and prosperity of the land. Unfortunately, the person charged with maintaining that survival in the face of robbers, mercenaries and, well, Death, is a spectacularly inept wizard…","Terry Pratchett", "EN",lib1));
		books.add(new Book("Equal Rites", "The last thing the wizard Drum Billet did, before Death laid a bony hand on his shoulder, was to pass on his staff of power to the eighth son of an eighth son. Unfortunately for his colleagues in the chauvinistic (not to say misogynistic) world of magic, he failed to check that the baby in question was a son. Everybody knows that there's no such thing as a female wizard. But now it's gone and happened, there's nothing much anyone can do about it. Let the battle of the sexes begin...","Terry Pratchett", "EN",lib1));
		books.add(new Book("Time's Convert", "On the battlefields of the American Revolution, Matthew de Clermont meets Marcus MacNeil, a young surgeon from Massachusetts, during a moment of political awakening when it seems that the world is on the brink of a brighter future. When Matthew offers him a chance at immortality and a new life free from the restraints of his puritanical upbringing, Marcus seizes the opportunity to become a vampire. But his transformation is not an easy one and the ancient traditions and responsibilities of the de Clermont family clash with Marcus's deeply held beliefs in liberty, equality, and brotherhood. Fast-forward to contemporary Paris, where Phoebe Taylor--the young employee at Sotheby's whom Marcus has fallen for--is about to embark on her own journey to immortality. Though the modernized version of the process at first seems uncomplicated, the couple discovers that the challenges facing a human who wishes to be a vampire are no less formidable than they were in the eighteenth century. The shadows that Marcus believed he'd escaped centuries ago may return to haunt them both--forever. A passionate love story and a fascinating exploration of the power of tradition and the possibilities not just for change but for revolution, Time's Convert channels the supernatural world-building and slow-burning romance that made the All Souls Trilogy instant bestsellers to illuminate a new and vital moment in history, and a love affair that will bridge centuries.","Deborah Harkness ", "EN",lib1));
		books.add(new Book("A Discovery of Witches", "A richly inventive novel about a centuries-old vampire, a spellbound witch, and the mysterious manuscript that draws them together. Deep in the stacks of Oxford's Bodleian Library, young scholar Diana Bishop unwittingly calls up a bewitched alchemical manuscript in the course of her research. Descended from an old and distinguished line of witches, Diana wants nothing to do with sorcery; so after a furtive glance and a few notes, she banishes the book to the stacks. But her discovery sets a fantastical underworld stirring, and a horde of daemons, witches, and vampires soon descends upon the library. Diana has stumbled upon a coveted treasure lost for centuries-and she is the only creature who can break its spell. Debut novelist Deborah Harkness has crafted a mesmerizing and addictive read, equal parts history and magic, romance and suspense. Diana is a bold heroine who meets her equal in vampire geneticist Matthew Clairmont, and gradually warms up to him as their alliance deepens into an intimacy that violates age-old taboos. This smart, sophisticated story harks back to the novels of Anne Rice, but it is as contemporary and sensual as the Twilight series-with an extra serving of historical realism.","Deborah Harkness ", "EN",lib1));	
	
		for(int i=0; i<books.size();i++)
			em.persist(books.get(i));
		
		System.out.println("PA_DEBUG: DB> Books populated");
		
		Librarian l1 = new Librarian("hans.walther@gotank.lib", "Hans", "Walther",  sierre2);
		Librarian l2 = new Librarian("wilhelm.gebhardt@gotank.lib", "Wilhelm", "Gebhardt", sierre3);
		em.persist(l1);
		em.persist(l2);
		
		lib1.addLibrarian(l1);
		lib1.addLibrarian(l2);
		
		System.out.println("PA_DEBUG: DB> Librarians populated");
		
		Reader r1 = new Reader("george.p@gmail.com", "George", "Pochon", 25314, flanthey1);
		Reader r2 = new Reader("rodolf.ruth@live.com", "Rodolf", "Ruth", 23456, brig1);
		Reader r3 = new Reader("louis.d@bluewin.ch", "Louis", "Devanthery", 23458, sierre2);
		
		em.persist(r1);
		em.persist(r2);
		em.persist(r3);
		
		System.out.println("PA_DEBUG: DB> Readers populated");
		
		System.out.println("PA_DEBUG: End of database init: books "+getBooks().size()+" librarians "+getLibrariansP().size()+ "readers "+getReadersP().size());
	}

	@Override
	public void flush() {
		em.flush();
	}

}