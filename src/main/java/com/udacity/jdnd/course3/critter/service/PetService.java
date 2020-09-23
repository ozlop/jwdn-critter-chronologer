package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private PetRepository petRepository;

    private CustomerRepository customerRepository;

    public PetService(PetRepository petRepository, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    public Pet savePet(Pet pet, Long ownerId) {
        Customer customer = customerRepository.getOne(ownerId);
        pet.setCustomer(customer);
        pet = petRepository.save(pet);
        customer.getPets().add(pet);
        customer.setPets(customer.getPets());
        customerRepository.save(customer);

        return pet;
    }

    public Pet getPet(Long petId) {
        return petRepository.getOne(petId);
    }

    public List<Pet> getPetsByOwner(Long customerId) {
        return petRepository.findAllByCustomer_Id(customerId);
    }

    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

}
