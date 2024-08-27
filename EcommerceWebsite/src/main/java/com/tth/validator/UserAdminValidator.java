/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.validator;

import com.tth.DTO.UserAdminDTO;
import com.tth.pojo.Admin;
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
public class UserAdminValidator implements Validator{

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserAdminDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserAdminDTO admin = (UserAdminDTO) target;

        if (admin.getUsername().equals(userService.getUserByUsername(admin.getUsername()).getUsername())) {
            errors.rejectValue("username", "user.username.usernameExisted");
        }
        if (admin.getUsername().isBlank()) {
            errors.rejectValue("username", "user.username.usernameNotNull");
        }
        if (!admin.getUsername().matches("^.{6,50}$")) {
            errors.rejectValue("username", "user.username.usernameLengthError");
        }
        if (admin.getPassword().isBlank()) {
            errors.rejectValue("password", "user.password.passwordNotNull");
        }
        if (!admin.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!_])[A-Za-z\\d@#$%^&+=!_]{8,}$")) {
            errors.rejectValue("password", "user.password.passwordIsNotStrong");
        }
        if (!admin.getRePassword().matches(admin.getPassword()))
            errors.rejectValue("rePassword", "user.rePassword.rePasswordIsNotMatch");
        if (admin.getFirstName().isBlank()) {
            errors.rejectValue("firstName", "user.firstName.firstNameNotNull");
        }
        if (admin.getLastName().isBlank()) {
            errors.rejectValue("lastName", "user.lastName.lastNameNotNull");
        }
        if (admin.getPhone().isBlank()) {
            errors.rejectValue("phone", "userAdmin.phone.phoneNotNull");
        }
        if (admin.getAddress().isBlank()) {
            errors.rejectValue("address", "userAmin.address.addressNotNull");
        }
        if (admin.getEmail().isBlank()) {
            errors.rejectValue("email", "userAdmin.email.emailNotNull");
        }
        if(admin.getPersonalId().isBlank()) {
            errors.rejectValue("personalId", "userAdmin.personalId.personalIdNotNull");
        }
        if (!admin.getPersonalId().matches("^[0-9]{9,12}$")) {
            errors.rejectValue("personalId", "userAdmin.personalId.personalIdNotAppropriate");
        }
    }
}
