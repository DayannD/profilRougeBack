package com.simplon.projet_fil_rouge.repositorys;

import com.simplon.projet_fil_rouge.entitys.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    @Query(value = "SELECT b FROM Book b ORDER BY b.dateAdded DESC LIMIT :x")
    List<Book> findXBooksSortedByDateAdded(@Param("x") Integer x);
    Book findByTitle(String Title);
//    List<Book> findTop5ByOrderByDateAddedDesc();
    List<Book> findTop4ByOrderByVotesAverageDesc();
}
