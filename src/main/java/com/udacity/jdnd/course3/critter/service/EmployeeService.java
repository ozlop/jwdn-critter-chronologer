package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }

    public Employee getEmployee(Long id) {

        return employeeRepository.getOne(id);
    }

    public List<Employee> getEmployeeForService(LocalDate date, Set<EmployeeSkill> skills) {

        return employeeRepository.getAllByDaysAvailableContains(date.getDayOfWeek())
                .stream()
                .filter(employee -> employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());
    }

}
