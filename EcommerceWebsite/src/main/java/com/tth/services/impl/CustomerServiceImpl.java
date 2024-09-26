/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services.impl;

import com.tth.pojo.Customer;
import com.tth.repositories.CustomerRepository;
import com.tth.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tongh
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository CustomerRepo;

    @Override
    public boolean addUserCustomer(Customer customer) {
        return this.CustomerRepo.addUserCustomer(customer);

    }

    @Override
    public Customer getCustomerByUsername(String username) {
        return this.CustomerRepo.getCustomerByUsername(username);
    }

}
