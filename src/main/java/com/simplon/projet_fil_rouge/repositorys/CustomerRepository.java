package com.simplon.projet_fil_rouge.repositorys;

import com.simplon.projet_fil_rouge.entitys.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByFirstNameAndLastName(String firstName, String lastName);
}
