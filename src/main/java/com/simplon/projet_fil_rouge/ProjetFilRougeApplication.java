package com.simplon.projet_fil_rouge;

import com.simplon.projet_fil_rouge.entitys.Book;
import com.simplon.projet_fil_rouge.entitys.GenreBook;
import com.simplon.projet_fil_rouge.repositorys.BookRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class ProjetFilRougeApplication {

	@Autowired
	BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetFilRougeApplication.class, args);
	}

	@PostConstruct
	public void init() {
		if(bookRepository.count() == 0) {
			for(Book book : generate10Books()) {
				bookRepository.save(book);
			}
		}
	}

	private Book[] generate10Books() {
		Book[] books = new Book[10];
		LocalDate today = LocalDate.now();

		for (int i = 0; i < 10; i++) {
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

		return books;
	}

}
