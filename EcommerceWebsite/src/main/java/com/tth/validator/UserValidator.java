/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.validator;

import com.tth.pojo.User;
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
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

     @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (user.getUsername().equals(userService.getUserByUsername(user.getUsername()).getUsername()))
            errors.rejectValue("username", "user.username.usernameExisted");
        if (user.getUsername().equals(""))
            errors.rejectValue("username", "user.username.usernameNotNull");
        if (!user.getUsername().matches("^.{6,50}$"))
            errors.rejectValue("username", "user.username.usernameLengthError");
        if (user.getPassword().equals(""))
            errors.rejectValue("password", "user.password.passwordNotNull");
        if (!user.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!_])[A-Za-z\\d@#$%^&+=!_]{8,}$"))
            errors.rejectValue("password", "user.password.passwordIsNotStrong");
        if (user.getFirstName().isBlank())
            errors.rejectValue("firstName", "user.firstName.firstNameNotNull");
        if (user.getLastName().isBlank())
            errors.rejectValue("lastName", "user.lastName.lastNameNotNull");
        }
    
    
}
