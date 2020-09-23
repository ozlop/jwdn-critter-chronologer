package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CustomerRepository extends AppUserRepository<Customer> {

    @Query("SELECT c FROM Customer c JOIN c.pets p where p.id = :petId ")
    Customer getCustomerByPet(Long petId);
}
