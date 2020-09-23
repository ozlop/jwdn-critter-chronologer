package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    private ScheduleRepository scheduleRepository;

    private EmployeeRepository employeeRepository;

    private PetRepository petRepository;

    private CustomerRepository customerRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, EmployeeRepository employeeRepository, PetRepository petRepository, CustomerRepository customerRepository) {
        this.scheduleRepository = scheduleRepository;
        this.employeeRepository = employeeRepository;
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    public Schedule createSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        List<Employee> employees = employeeIds
                .stream()
                .map(employeeId -> employeeRepository.getOne(employeeId))
                .collect(Collectors.toList());
        List<Pet> pets = petIds
                .stream()
                .map(petId -> petRepository.getOne(petId))
                .collect(Collectors.toList());

        schedule.setEmployees(employees);
        schedule.setPets(pets);

       return scheduleRepository.save(schedule);
    }

    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();

    }

    public List<Schedule> getScheduleForPet(Long petId) {
        return scheduleRepository.getScheduleForPet(petId);
    }

    public List<Schedule> getEmployeeSchedule(Long employeeId) {
        return scheduleRepository.getAllByEmployeesEquals(employeeRepository.getOne(employeeId));
    }

    public List<Schedule> getCustomerSchedule(Long customerId) {

        return petRepository.findAllByCustomer_Id(customerId)
                .stream()
                .map(pet -> scheduleRepository.getAllByPetsEquals(pet))
                .collect(Collectors.toList())
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

    }
}
