package com.simplon.projet_fil_rouge.repositorys;

import com.simplon.projet_fil_rouge.entitys.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
