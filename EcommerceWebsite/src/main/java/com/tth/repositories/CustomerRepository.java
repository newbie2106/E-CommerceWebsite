/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories;

import com.tth.pojo.Customer;
/**
 *
 * @author tongh
 */
public interface CustomerRepository {

    boolean addUserCustomer(Customer customer);

    boolean usernameCustomerExists(String username);

    Customer getCustomerByUsername(String username);

}
