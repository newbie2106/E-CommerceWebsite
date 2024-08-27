/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.advice;

import java.util.List;
import java.util.Map;

/**
 *
 * @author tongh
 */
public class ValidationException extends RuntimeException {

    private Map<String, List<String>> errors;

    public ValidationException(Map<String, List<String>> errors) {
        super(errors.toString());
        this.errors = errors;
    }

    public Map<String, List<String>> getErrors() {
        return errors;
    }
}