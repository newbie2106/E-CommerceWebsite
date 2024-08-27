/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services.impl;

import com.tth.pojo.User;
import com.tth.services.EmailService;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author tongh
 */

@Service
@PropertySource("classpath:configs.properties")
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    private Environment environment;
    
     private Integer otpGenerator(){
       Random r = new Random();
       return r.nextInt(100_000, 999_999);
   }

    @Override
    public void sendSimpleMessage(User u) {
        SimpleMailMessage mes = new SimpleMailMessage();
        int otp = otpGenerator();
        mes.setFrom(environment.getProperty("spring.mail.username"));
        mes.setTo(u.getAdmin().getEmail());
        mes.setText("Thông báo từ E-commerce Website "
                + "đây là mã OTP của bạn " + otp + " và đừng gửi nó cho ai khác");
        mes.setSubject("OTP cho yêu cầu quên mật khẩu của bạn");
        
        javaMailSender.send(mes);
    }
}
