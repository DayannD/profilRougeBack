package com.simplon.projet_fil_rouge;

import com.simplon.projet_fil_rouge.entitys.*;
import com.simplon.projet_fil_rouge.repositorys.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class ProjetFilRougeApplication {

	@Autowired
	BookRepository bookRepository;
	@Autowired
	DetailsProductRepository detailsProductRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	GroupBookRepository groupBookRepository;
	@Autowired
	ImageRepository imageRepository;
	@Autowired
	OrderCommandeRepository orderCommandeRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetFilRougeApplication.class, args);
	}

	@PostConstruct
	public void init() {
		//initialize some books OK
		if(bookRepository.count() == 0) {
			for(Book book : generate12Books())
				bookRepository.save(book);
		}
		//Initialize the 3 type_produit OK
		if(detailsProductRepository.count() == 0) {
			for(DetailsProduct dp: initializeDetailsProducts())
				detailsProductRepository.save(dp);
		}
		//initialize some  client
		if(customerRepository.count() == 0) {
			for(Customer customer: generate3User())
				customerRepository.save(customer);
		}
		//create a few votes
		//Make some group
		if(groupBookRepository.count() == 0) {
			for (GroupBook groupBook : generate3GroupBook())
				groupBookRepository.save(groupBook);
		}
		//initialize an image
		if(imageRepository.count() == 0) {
			for(Image image: generate1Image())
				imageRepository.save(image);
		}
		//Create a command
		if(orderCommandeRepository.count() == 0) {
			for(OrderCommande command: generated1Command())
				orderCommandeRepository.save(command);
		}

	}

	private Book[] generate12Books() {
		Book[] books = new Book[12];
		LocalDate today = LocalDate.now();

		for (int i = 0; i < books.length; i++) {
			Book book = new Book();
			book.setVotesAverage(i%3);
			book.setDateAdded(today.minusDays(i%4));
			books[i] = book;

			today.plusDays(i%4);
		}

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String pathCovers = "booksCovers/";
		books[0].setTitle("La voie des rois");
		books[0].setAuthor("Brandon Sanderson");
		books[0].setCoverPath(pathCovers + "les-archives-de-roshar-tome-1-la-voie-des-rois-i-619268.jpg");
		books[0].setSummary("Roshar, monde de pierres et de tempêtes. Des siècles ont passé depuis...");
		books[0].setGenre(GenreBook.FANTASY);
		//Series
		books[1].setTitle("Qadehar le sorcier");
		books[1].setAuthor("Erik L'Homme");
		books[1].setCoverPath(pathCovers + "le-livre-des-etoiles-tome-1-qadehar-le-sorcier-287581.jpg");
		books[1].setSummary("Yssemble un pays ...");
		books[1].setGenre(GenreBook.FANTASY);
		books[2].setTitle("Le neveu du magicien");
		books[2].setAuthor("C. S. Lewis");
		books[2].setCoverPath(pathCovers + "le-monde-de-narnia-tome-1-le-neveu-du-magicien-48664.jpg");
		books[2].setSummary("Polly trouve parfois...");
		books[2].setGenre(GenreBook.FANTASY);
		books[3].setTitle("100 jours en enfer");
		books[3].setAuthor("Robert Muchamore");
		books[3].setCoverPath(pathCovers + "cherub-tome-1-100-jours-en-enfer-20428.jpg");
		books[3].setSummary("James Choke est un adolescent violent et ...");
		books[3].setGenre(GenreBook.ESPIONNAGE);
		books[4].setTitle("Dune");
		books[4].setAuthor("Frank Herbert");
		books[4].setCoverPath(pathCovers + "le-cycle-de-dune-tome-1-dune-1477958.jpg");
		books[4].setSummary("Sur Dune, la planète des sables, germe l'épice qui donne longévité et prescience. A cause ...");
		books[4].setGenre(GenreBook.SF);
		books[5].setTitle("La Horde du contrevent");
		books[5].setAuthor("Alain Damasio");
		books[5].setCoverPath(pathCovers + "la-horde-du-contrevent-594679.jpg");
		books[5].setSummary("Un groupe d'élite, formé dès l'enfance à faire face, part des confins d'une terre féroce...");
		books[5].setGenre(GenreBook.SF);
		books[6].setTitle("Le Simarillion");
		books[6].setAuthor("J. R. R. Tomkien");
		books[6].setCoverPath(pathCovers + "le-silmarillion-331308.jpg");
		books[6].setSummary("Les Premiers Jours du Monde étaient à peine passés quand Fëanor, le plus doué des elfes, créa...");
		books[6].setGenre(GenreBook.FANTASY);
		books[7].setTitle("Hypérion 1");
		books[7].setAuthor("Dan Simmons");
		books[7].setCoverPath(pathCovers + "les-cantos-dhyperion-tome-1-hyperion-1-5138.jpg");
		books[7].setSummary("Quand les sept pèlerins se posent à Hypérion, le port spatial offre un spectacle de fin du monde...");
		books[7].setGenre(GenreBook.SF);
		books[8].setTitle("Le Nom du Vent");
		books[8].setAuthor("Patrick Rothfuss");
		books[8].setCoverPath(pathCovers + "chronique-du-tueur-de-roi-premiere-journee-le-nom-du-vent-78244.jpg");
		books[8].setSummary("J'ai libéré des princesses, j'ai incendié la ville de Trebon. J'ai suivi des pistes au clair de lune que personne n'ose évoquer durant le jour...");
		books[8].setGenre(GenreBook.FANTASY);
		books[9].setTitle("Le Pion blanc des présages");
		books[9].setAuthor("David Eddings");
		books[9].setCoverPath(pathCovers + "la-belgariade-tome-1-le-pion-blanc-des-presages-4185093.jpg");
		books[9].setSummary("Et les dieux créèrent l'homme, et chaque dieu choisit son peuple. Mais Torak, le dieu jaloux, vola...");
		books[9].setGenre(GenreBook.FANTASY);
		books[10].setTitle("Le Seigneur Sha");
		books[10].setAuthor("Erik L'Homme");
		books[10].setCoverPath(pathCovers + "le-livre-des-etoiles-tome-2-le-seigneur-sha-728636.jpg");
		books[10].setSummary("Guillemot rejoint le monastère de Gifdu afin d'y poursuivre son apprentissage de la magie. La Guilde ..");
		books[10].setGenre(GenreBook.FANTASY);
		books[11].setTitle("Le Messie de Dune");
		books[11].setAuthor("Frank Herbert");
		books[11].setCoverPath(pathCovers + "le-cycle-de-dune-tome-2-le-messie-de-dune-713.jpg");
		books[11].setSummary("Paul Atréides a triomphé de ses ennemis. En douze ans de guerre sainte, ses Fremen ont conquis...");
		books[11].setGenre(GenreBook.SF);

		return books;
	}

	private DetailsProduct[] initializeDetailsProducts() {
		DetailsProduct[] detailsProducts = new DetailsProduct[3];
		for (int i = 0; i < detailsProducts.length; i++) {
			detailsProducts[i] =  new DetailsProduct();
		}
		detailsProducts[0].setType(TypeProduct.POSTER);
		detailsProducts[0].setTarif(2000); // en centimes
		detailsProducts[0].setFournisseur("XXX");
		detailsProducts[1].setType(TypeProduct.T_SHIRT);
		detailsProducts[1].setTarif(2500);
		detailsProducts[1].setFournisseur("XXX");
		detailsProducts[2].setType(TypeProduct.MUG);
		detailsProducts[2].setTarif(1500);
		detailsProducts[2].setFournisseur("XXX");

		return  detailsProducts;
	}

	private Customer[] generate3User() {
		Customer[] customers = new Customer[3];
		for (int i = 0; i < customers.length; i++) {
			customers[i] =  new Customer();
		}
		customers[0].setFirstName("Alexandre");
		customers[0].setLastName("Arbre");
		customers[0].setEmail("alexandre.arbre@gmail.com");
		customers[0].setAdress("15 rue du Maréchal Quelqun");
		customers[1].setFirstName("Baptiste");
		customers[1].setLastName("Bateau");
		customers[1].setEmail("baptiste.bateau@gmail.com");
		customers[1].setAdress("03 Quai Gpas Didee");
		customers[2].setFirstName("Cédric");
		customers[2].setLastName("Celier");
		customers[2].setEmail("cedric.celier@gmail.com");
		customers[2].setAdress("324 boulevard de Nulle Part");
		return customers;
	}

	private GroupBook[] generate3GroupBook() {
		GroupBook[] groupBooks = new GroupBook[3];
		for (int i = 0; i < groupBooks.length; i++) {
			groupBooks[i] =  new GroupBook();
			List<Book> books = new ArrayList<>();
			groupBooks[i].setBooks(books);
		}

		List<Book> books = groupBooks[0].getBooks();
		groupBooks[0].setName("Le livre des étoiles");
		books.add(bookRepository.findByTitle("Qadehar le sorcier"));
		books.add(bookRepository.findByTitle("Le Seigneur Sha"));
		groupBooks[0].setBooks(books);
		groupBooks[1].setName("Le cycle de dune");
		books = groupBooks[1].getBooks();
		books.add(bookRepository.findByTitle("Dune"));
		books.add(bookRepository.findByTitle("Le Messie de Dune"));
		groupBooks[1].setBooks(books);
		groupBooks[2].setName("Classique SF");
		books = groupBooks[2].getBooks();
		books.add(bookRepository.findByTitle("Dune"));
		groupBooks[2].setBooks(books);
		return groupBooks;
	}

	private Image[] generate1Image() {
		Image[] images = new Image[1];
		List<Book> books;
		List<GroupBook> groupBooks;
		for(int i=0; i<images.length; i++){
			images[i] =  new Image();
		}
		String pathImages = "generatedImages/";
		images[0].setPath(pathImages+"image-dune.jpeg");
		books = new ArrayList<>();
		books.add(bookRepository.findByTitle("Dune"));
		images[0].setBooks(books);
		return images;
	}
	private OrderCommande[] generated1Command() {
		OrderCommande[] commands = new OrderCommande[1];
		for (int i = 0; i < commands.length; i++) {
			commands[i] = new OrderCommande();
			commands[i].setDateOrder(LocalDate.now());
			commands[i].setStatesOrder(StatesOrder.IN_WAITING);
		}
		commands[0].setCustomer(customerRepository.findByFirstNameAndLastName("Alexandre", "Arbre"));
		commands[0].setPrice(0);
		commands[0].setImage(imageRepository.findByPath("generatedImages/image-dune.jpeg"));
		commands[0].setProduct(detailsProductRepository.findByType(TypeProduct.MUG));
		commands[0].setNumberOfProducts(1);
		return commands;
	}

}
