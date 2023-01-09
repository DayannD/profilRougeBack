package com.simplon.projet_fil_rouge.controllers;

import com.simplon.projet_fil_rouge.entitys.Book;
import com.simplon.projet_fil_rouge.repositorys.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class GeneralController {

    @Autowired
    BookRepository bookRepository;

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

    @RequestMapping(path = "/books/news", method = RequestMethod.GET)
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
    }

    @RequestMapping(path="/books/delete",  method = RequestMethod.GET)
    @ResponseBody
    private boolean deleteAllBooks() {
        bookRepository.deleteAll();
        return true;
    }
}
