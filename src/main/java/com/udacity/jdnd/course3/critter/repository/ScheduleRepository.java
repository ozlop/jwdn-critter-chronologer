package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM Schedule s JOIN s.pets p WHERE p.id = :petId")
    List<Schedule> getScheduleForPet(Long petId);

    List<Schedule> getAllByEmployeesEquals(Employee employee);

    List<Schedule> getAllByPetsEquals(Pet pet);
}
