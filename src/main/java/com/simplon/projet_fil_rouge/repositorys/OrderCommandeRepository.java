package com.simplon.projet_fil_rouge.repositorys;

import com.simplon.projet_fil_rouge.entitys.OrderCommande;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCommandeRepository extends CrudRepository<OrderCommande, Long> {
}