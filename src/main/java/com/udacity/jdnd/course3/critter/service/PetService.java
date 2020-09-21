package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
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
