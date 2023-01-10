package com.simplon.projet_fil_rouge.controllers;

import com.simplon.projet_fil_rouge.entitys.Book;
import com.simplon.projet_fil_rouge.repositorys.BookRepository;
import com.simplon.projet_fil_rouge.repositorys.GroupBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200/")
@Controller
public class BooksController {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    GroupBookRepository groupBookRepository;

    @RequestMapping(path = "/book", method = RequestMethod.GET)
    @ResponseBody //Sans le réponse body ça envoie une référence à book et donc problème de boucle infinie
    private Book getBookById(@RequestParam Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()) {
            return book.get();
        }
        return null;
    }

    @RequestMapping(path = "/books", method = RequestMethod.GET)
    @ResponseBody
    private Iterable<Book> getAllBooks() {
        Iterable<Book> books = bookRepository.findAll();
        return books;
    }

   /* @RequestMapping(path = "/books/news", method = RequestMethod.GET)
    @ResponseBody
    private List<Book> getNewsBooks() {
        Iterable<Book> iterableBooks = bookRepository.findAll();
        List<Book> books = new ArrayList<>();
        iterableBooks.forEach(books::add);
        Comparator<Book> byDateAdded = Comparator.comparing(Book::getDateAdded);
        return books.stream().sorted(byDateAdded.reversed()).limit(5).toList();
    }

    @RequestMapping(path = "/books/bestVotes", method = RequestMethod.GET)
    @ResponseBody
    private List<Book> getBooksSortedbyVotes() {
        Iterable<Book> iterableBooks = bookRepository.findAll();
        List<Book> books = new ArrayList<>();
        iterableBooks.forEach(books::add);
        Comparator<Book> byVoteAverage = Comparator.comparing(Book::getVotesAverage);
        return books.stream().sorted(byVoteAverage.reversed()).toList();
    }*/

    @RequestMapping(path="/books/delete",  method = RequestMethod.GET)
    @ResponseBody
    private boolean deleteAllBooks() {
        groupBookRepository.deleteAll();
        bookRepository.deleteAll();
        return true;
    }

    @RequestMapping(path = "/books/news", method = RequestMethod.GET)
    @ResponseBody
    private List<Book> getBooksbyNew() {
        return bookRepository.findXBooksSortedByDateAdded(5);
    }

    @RequestMapping(path = "/books/votes", method = RequestMethod.GET)
    @ResponseBody
    private List<Book> getBooksbyVotes() {
        //return bookRepository.findByTitle("La voie des rois");
        return bookRepository.findTop4ByOrderByVotesAverageDesc();
    }

    @RequestMapping(path = "/createBook", method = RequestMethod.POST)
    @ResponseBody
    public boolean createBook(@RequestBody Book bookToSave) {
        Book book = new Book();
        book.setTitle(bookToSave.getTitle());
        book.setAuthor(bookToSave.getAuthor());
        book.setGenre(bookToSave.getGenre());
        book.setSummary(bookToSave.getSummary());
        book.setCoverPath(bookToSave.getCoverPath());
        book.setVotesAverage(0);
        book.setDateAdded(LocalDate.now());
        bookRepository.save(book);
        return true;
    }
}
