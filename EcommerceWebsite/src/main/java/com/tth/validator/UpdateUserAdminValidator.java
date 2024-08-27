/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.validator;

import com.tth.DTO.UserAdminDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author tongh
 */

@Component
public class UpdateUserAdminValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserAdminDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserAdminDTO admin = (UserAdminDTO) target;
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
        if (admin.getPersonalId().isBlank()) {
            errors.rejectValue("personalId", "userAdmin.personalId.personalIdNotNull");
        }
        if (!admin.getPersonalId().matches("^[0-9]{9,12}$")) {
            errors.rejectValue("personalId", "userAdmin.personalId.personalIdNotAppropriate");
        }
    }
}
