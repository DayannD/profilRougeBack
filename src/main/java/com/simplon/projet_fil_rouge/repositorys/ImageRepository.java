package com.simplon.projet_fil_rouge.repositorys;

import com.simplon.projet_fil_rouge.entitys.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {
    Image findByPath(String path);
}