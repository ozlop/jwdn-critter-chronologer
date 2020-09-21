package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = convertPetDTOToPet(petDTO);
        pet.setId(null);

        return convertPetToPetDTO(petService.savePet(pet));

    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {

        return convertPetToPetDTO(petService.getPet(petId));

    }

    @GetMapping
    public List<PetDTO> getPets(){

        return petService.findAllPets()
                .stream()
                .map(this::convertPetToPetDTO)
                .collect(Collectors.toList());

    }


    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {

        return petService.getPetsByOwner(ownerId)
                .stream()
                .map(this::convertPetToPetDTO)
                .collect(Collectors.toList());

    }

    PetDTO convertPetToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
//        petDTO.setOwnerId(pet.getCustomer().getId());

        return petDTO;

    }

    Pet convertPetDTOToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);

//        Customer customer = customerService.getCustomer();
//        pet.setCustomer(customer);

        return pet;

    }

}
