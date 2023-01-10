package com.simplon.projet_fil_rouge.repositorys;

import com.simplon.projet_fil_rouge.entitys.Book;
import com.simplon.projet_fil_rouge.entitys.DetailsProduct;
import com.simplon.projet_fil_rouge.entitys.TypeProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsProductRepository extends CrudRepository<DetailsProduct, Long> {
    DetailsProduct findByType(TypeProduct type);
}