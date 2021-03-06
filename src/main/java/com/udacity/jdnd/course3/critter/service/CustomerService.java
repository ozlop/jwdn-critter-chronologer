package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    private PetRepository petRepository;

    public CustomerService(CustomerRepository customerRepository, PetRepository petRepository) {
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
    }

    public Customer saveCustomer(Customer customer, List<Long> petIds) {
        List<Pet> pets = new ArrayList<>();

        if (petIds != null && !petIds.isEmpty()) {
            pets = petIds
                    .stream()
                    .map(petId -> petRepository.getOne(petId))
                    .collect(Collectors.toList());
        }

        customer.setPets(pets);

        return customerRepository.save(customer);
    }

    public Customer getCustomer(Long id) {

        return customerRepository.getOne(id);
    }

    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }

    public Customer getOwnerByPet(Long petId) {
        return customerRepository.getCustomerByPet(petId);
    }
}
