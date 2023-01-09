package com.simplon.projet_fil_rouge.repositorys;

import com.simplon.projet_fil_rouge.entitys.Book;
import com.simplon.projet_fil_rouge.entitys.DetailsProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsProductRepository extends CrudRepository<DetailsProduct, Long> {
}