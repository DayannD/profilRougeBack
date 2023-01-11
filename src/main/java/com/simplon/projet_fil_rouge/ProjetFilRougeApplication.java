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
		books[0].setSummary("Roshar, monde de pierres et de tempêtes. Des siècles ont passé depuis la chute des Chevaliers Radieux, mais leurs avatars, des épées et des armures mystiques qui transforment des hommes ordinaires en guerriers invincibles, sont toujours là. Pour elles, les hommes s'entre-déchirent. Dans le paysage dévasté des Plaines Brisées, Kaladin, enrôlé de force, lutte dans une guerre insensée qui dure depuis dix ans, où plusieurs armées combattent séparément un unique ennemi.\n" +
				"\n" +
				"Dalinar Kholin, chef de l'une de ces armées, est fasciné par un texte ancien appelé La Voie des Rois. Hanté par des visions des temps anciens, il commence à douter de sa santé mentale. De l'autre côté de l'océan, la jeune et ambivalente Shallan apprend la magie, et découvre certains secrets des Chevaliers Radieux."
		);
		books[0].setGenre(GenreBook.FANTASY);
		//Series
		books[1].setTitle("Qadehar le sorcier");
		books[1].setAuthor("Erik L'Homme");
		books[1].setCoverPath(pathCovers + "le-livre-des-etoiles-tome-1-qadehar-le-sorcier-287581.jpg");
		books[1].setSummary("Ys semble un pays en tous points pareil au nôtre, à la différence qu'on y côtoie, entre ordinateurs et salles de cinéma, chevaliers en armure et sorciers aux pouvoirs étonnants.\n" +
				"\n" +
				"Guillemot de Troïl est un garçon d'Ys, enfant timide et rêveur. D'où lui viennent ses dons exceptionnels pour la sorcellerie que lui enseigne patiemment Maître Qadehar ? Qu'est devenu Le Livre des Étoiles, dérobé voilà longtemps et qui renferme le secret de sortilèges puissants ? Pourquoi Agathe de Balangru, sa pire ennemi, a-t-elle été enlevée ?\n" +
				"\n" +
				"Dans sa quête de la vérité, Guillemot entraîne avec lui ses amis de toujours, Romaric, Gontrand, Coralie et l'intrépide Ambre. Ensemble, ils franchissent la Porte qui conduit dans le Monde Incertain...");
		books[1].setGenre(GenreBook.FANTASY);
		books[2].setTitle("Le neveu du magicien");
		books[2].setAuthor("C. S. Lewis");
		books[2].setCoverPath(pathCovers + "le-monde-de-narnia-tome-1-le-neveu-du-magicien-48664.jpg");
		books[2].setSummary("Polly trouve parfois que la vie à Londres n'est guère passionnante .. jusqu'au jour où elle rencontre son nouveau voisin, Digory. Il vit avec sa mère, gravement malade, et un vieil oncle au comportement étrange. Celui-ci force les deux enfants à essayer des bagues magiques qui les transportent dans un monde inconnu. Commence alors la plus extraordinaire des aventures ...");
		books[2].setGenre(GenreBook.FANTASY);
		books[3].setTitle("100 jours en enfer");
		books[3].setAuthor("Robert Muchamore");
		books[3].setCoverPath(pathCovers + "cherub-tome-1-100-jours-en-enfer-20428.jpg");
		books[3].setSummary("James Choke est un adolescent violent et révolté, qui subit les moqueries de ses camarades de classes. Le jour où sa mère, délinquante mais dépressive, vient à mourir, il est placé dans un orphelinat tandis que sa demi-sœur, Lauren, est placée contre son gré sous la garde de son père à elle, Ron.\n" +
				"\n" +
				"Une surprise attend James à l’orphelinat. Certes, il n’est pas plus discipliné qu’avant, mais s’entend bien avec son partenaire de chambre, Kyle.\n" +
				"\n" +
				"Jusqu’au moment où sa vie, déjà instable, bascule totalement quand il se réveille dans une cellule.\n" +
				"\n" +
				"Kyle est en fait un agent de CHERUB en mission de recrutement pour s’être mal conduit. James réussira-t-il l’examen d’entrée ? Etes-vous partant pour 100 jours en Enfer ?\n" +
				"\n" +
				"Les agents de CHERUB ont entre 10 et 17 ans, ce qui leur permet de tromper la vigilance des adultes et de prendre au piège plusieurs criminels, déjouer des complots et obtenir de précieuses informations. Pour raison d’Etat ces enfants n’existent pas. ");
		books[3].setGenre(GenreBook.ESPIONNAGE);
		books[4].setTitle("Dune");
		books[4].setAuthor("Frank Herbert");
		books[4].setCoverPath(pathCovers + "le-cycle-de-dune-tome-1-dune-1477958.jpg");
		books[4].setSummary("Sur Dune, la planète des sables, germe l'épice qui donne longévité et prescience. A cause de l'épice, tout l'empire galactique du Padishah Shaddam IV tourne autour de Dune, âprement convoitée pour les nobles maisons du Landsraad et la Guilde des Navigateurs.\n" +
				"\n" +
				"Leto Atreides, Duc et Cousin de l'Empereur, a reçu Dune en fief. Pour peu de temps. En 10191, il meurt assassiné. Mais son fils Paul, avec sa mère, trouve asile dans les repaires du peuple Fremen, indompté, invaincu, la lie de Dune pour certains, le sel de la terre pour d'autres. Paul grandit dans le désert et forge l'arme de sa vengeance.\n" +
				"\n" +
				"Mais ne va-t-il pas dépasser son but, lancer les légions Fremen en une effroyable croisade ? Il a, dit-on, le pouvoir de connaître l'avenir. Aura-t-il celui de l'éviter ?");
		books[4].setGenre(GenreBook.SF);
		books[5].setTitle("La Horde du contrevent");
		books[5].setAuthor("Alain Damasio");
		books[5].setCoverPath(pathCovers + "la-horde-du-contrevent-594679.jpg");
		books[5].setSummary("Un groupe d'élite, formé dès l'enfance à faire face, part des confins d'une terre féroce, saignée de rafales, pour aller chercher l'origine du vent. Ils sont vingt-trois, un bloc, un nœud de courage : la Horde. Ils sont pilier, ailier, traceur, aéromaître et géomaître, feuleuse et sourcière, troubadour et scribe. Ils traversent leur monde debout, à pied, en quête d'un Extrême-Amont qui fuit devant eux comme un horizon fou.\n" +
				"\n" +
				"Expérience de lecture unique, La Horde du Contrevent est un livre-univers qui fond d'un même feu l'aventure et la poésie des parcours, le combat nu et la quête d'un sens profond du vivant qui unirait le mouvement et le lien. Chaque mot résonne, chaque, fuse : Alain Damasio joue de sa plume comme d'un pinceau, d'une caméra ou d'une arme...\n" +
				"\n" +
				"Chef-d'oeuvre porté par un bouche-à-oreille rare, le roman a été logiquement récompensé par le Grand Prix de l'Imaginaire.");
		books[5].setGenre(GenreBook.SF);
		books[6].setTitle("Le Simarillion");
		books[6].setAuthor("J. R. R. Tomkien");
		books[6].setCoverPath(pathCovers + "le-silmarillion-331308.jpg");
		books[6].setSummary("Les Premiers Jours du Monde étaient à peine passés quand Fëanor, le plus doué des elfes, créa...");
		books[6].setGenre(GenreBook.FANTASY);
		books[7].setTitle("Hypérion 1");
		books[7].setAuthor("Dan Simmons");
		books[7].setCoverPath(pathCovers + "les-cantos-dhyperion-tome-1-hyperion-1-5138.jpg");
		books[7].setSummary("Quand les sept pèlerins se posent à Hypérion, le port spatial offre un spectacle de fin du monde. Des millions de personnes s'entassent derrière les grilles : les habitants de la planète sont sûrs que le gritche va venir les prendre et ils veulent fuir. Mais l'Hégémonie ne veut rien savoir. Une guerre s'annonce et les routes du ciel doivent être dégagées. Et tout ce que le gouvernement a trouvé, c'est d'envoyer les sept pèlerins. La présidente le leur a dit d'emblée : Il est essentiel que les secrets des Tombeaux du Temps soient percés. C'est notre dernière chance. \" Mais les pèlerins n'y comprennent rien, et ne se connaissent même pas ! Heureusement, le voyage leur permettra de se rapprocher. Chacun raconte son histoire, et l'on s'aperçoit vite que nul n'a été pris au hasard. Celui qui a fait la sélection, au fil des confidences, parait avoir fait preuve d'une lucidité... diabolique. Et d'une cruauté... raffinée!");
		books[7].setGenre(GenreBook.SF);
		books[8].setTitle("Le Nom du Vent");
		books[8].setAuthor("Patrick Rothfuss");
		books[8].setCoverPath(pathCovers + "chronique-du-tueur-de-roi-premiere-journee-le-nom-du-vent-78244.jpg");
		books[8].setSummary("J'ai libéré des princesses, j'ai incendié la ville de Trebon. J'ai suivi des pistes au clair de lune que personne n'ose évoquer durant le jour. J'ai conversé avec les dieux, aimé des femmes et écrit des chansons qui font pleurer les ménestrels.\n" +
				"\n" +
				"J'ai été exclu de l'Université à un âge où l'on est encore trop jeune pour y entrer. J'y étais allé pour apprendre la magie, celle dont on parle dans les histoires.\n" +
				"\n" +
				"Je voulais apprendre le nom du vent.\n" +
				"\n" +
				"Mon nom est Kvothe.\n" +
				"\n" +
				"Vous avez du entendre parler de moi. ");
		books[8].setGenre(GenreBook.FANTASY);
		books[9].setTitle("Le Pion blanc des présages");
		books[9].setAuthor("David Eddings");
		books[9].setCoverPath(pathCovers + "la-belgariade-tome-1-le-pion-blanc-des-presages-4185093.jpg");
		books[9].setSummary("Et les dieux créèrent l'homme, et chaque dieu choisit son peuple.\n" +
				"\n" +
				"Mais Torak, le dieu jaloux, vola l'Orbe d'Aldur, le joyau vivant façonné par l'aîné des dieux, et ce fut la guerre. Le félon fut châtié ; à Cthol Mishrak, la Cité de la Nuit, il dort toujours d'un long sommeil hanté par la souffrance. Le fleuve des siècles a passé sur les royaumes du Ponant. Les livres des présages sont formels : Torak va s'éveiller. Et justement l'Orbe disparaît pour la seconde fois.\n" +
				"\n" +
				"Que le maudit la trouve à son réveil et il établira son empire sur toutes choses. Belgarath le sorcier parviendra-t-il à conjurer le sort ? Dans cette partie d'échecs cosmique, il a réussi à préserver une pièce maîtresse : le dernier descendant des Gardiens de l'Orbe, désigné par les présages, mais qui n'est encore qu'un petit garçon. Un simple pion, et si vulnérable...");
		books[9].setGenre(GenreBook.FANTASY);
		books[10].setTitle("Le Seigneur Sha");
		books[10].setAuthor("Erik L'Homme");
		books[10].setCoverPath(pathCovers + "le-livre-des-etoiles-tome-2-le-seigneur-sha-728636.jpg");
		books[10].setSummary("Guillemot rejoint le monastère de Gifdu afin d'y poursuivre son apprentissage de la magie. La Guilde des Sorciers envoie une expédition avec, à sa tête, Qadehar, pour mettre un terme aux menaces de l'Ombre. Mais l'attaque échoue.\n" +
				"\n" +
				"Dès lors, les événements se précipitent : Maître Qadehar doit s'enfuir. Un mystérieux personnage, le Seigneur Sha, s'introduit dans le monastère de Gifdu. Guillemot et ses fidèles amis sont pris au piège des redoutables Korrigans.\n" +
				"\n" +
				"Y aurait-il un traître dans la Guilde ? Qui se cache derrière le Seigneur Sha ? Guillemot aura besoin de tous ses talents d'Apprenti Sorcier dans ces nouvelles épreuves...");
		books[10].setGenre(GenreBook.FANTASY);
		books[11].setTitle("Le Messie de Dune");
		books[11].setAuthor("Frank Herbert");
		books[11].setCoverPath(pathCovers + "le-cycle-de-dune-tome-2-le-messie-de-dune-713.jpg");
		books[11].setSummary("Paul Atréides a triomphé de ses ennemis. En douze ans de guerre sainte, ses Fremen ont conquis l'univers. Il est devenu l'empereur Muad'Dib. fresque un Dieu, puisqu'il voit l'avenir. Ses ennemis, il les connaît. Il sait quand et comment ils frapperont. Ils vont essayer de lui reprendre l'épice qui donne la prescience et peut-être de percer le secret de son pouvoir. Il peut déjouer leurs plans, mais voit plus loin encore. Il sait que tous les futurs possibles mènent au désastre. Il est hanté par la vision de sa propre mort. Et s'il n'avait le choix qu'entre plusieurs suicides ? Et s'il ruinait son œuvre en matant ses ennemis ? Peut-être n'y a-t-il pour le prescient pas d'autre liberté que celle du sacrifice...");
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
