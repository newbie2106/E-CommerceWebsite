/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services;

import com.tth.pojo.User;
import org.springframework.mail.javamail.JavaMailSender;

/**
 *
 * @author tongh
 */
public interface EmailService {
    
   public void sendSimpleMessage(User u);
}
