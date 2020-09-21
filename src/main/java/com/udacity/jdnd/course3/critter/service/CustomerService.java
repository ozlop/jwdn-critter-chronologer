package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    public Customer getCustomer(Long id) {

        return customerRepository.getOne(id);
    }

    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }
}
