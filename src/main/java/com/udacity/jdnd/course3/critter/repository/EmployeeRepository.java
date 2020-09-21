package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface EmployeeRepository extends AppUserRepository<Employee> {
}
