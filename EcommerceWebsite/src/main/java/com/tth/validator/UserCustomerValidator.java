/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.validator;

import com.tth.DTO.UserCustomerDTO;
import com.tth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author tongh
 */

@Component
@PropertySource("classpath:messages.properties")
public class UserCustomerValidator implements Validator{
     @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserCustomerDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserCustomerDTO customer = (UserCustomerDTO) target;

        if (customer.getUsername().equals(userService.getUserByUsername(customer.getUsername()).getUsername())) {
            errors.rejectValue("username", "user.username.usernameExisted");
        }
        if (customer.getUsername().isBlank()) {
            errors.rejectValue("username", "user.username.usernameNotNull");
        }
        if (!customer.getUsername().matches("^.{6,50}$")) {
            errors.rejectValue("username", "user.username.usernameLengthError");
        }
        if (customer.getPassword().isBlank()) {
            errors.rejectValue("password", "user.password.passwordNotNull");
        }
        if (!customer.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!_])[A-Za-z\\d@#$%^&+=!_]{8,}$")) {
            errors.rejectValue("password", "user.password.passwordIsNotStrong");
        }
       
        if (customer.getFirstName().isBlank()) {
            errors.rejectValue("firstName", "user.firstName.firstNameNotNull");
        }
        if (customer.getLastName().isBlank()) {
            errors.rejectValue("lastName", "user.lastName.lastNameNotNull");
        }
        if (customer.getPhone().isBlank()) {
            errors.rejectValue("phone", "userAdmin.phone.phoneNotNull");
        }
        if (customer.getAddress().isBlank()) {
            errors.rejectValue("address", "userAmin.address.addressNotNull");
        }
        if (customer.getEmail().isBlank()) {
            errors.rejectValue("email", "userAdmin.email.emailNotNull");
        }
      
    }
}
