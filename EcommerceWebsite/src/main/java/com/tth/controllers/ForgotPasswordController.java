/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controllers;

import com.tth.pojo.ForgotPassword;
import com.tth.pojo.User;
import com.tth.services.ForgotPasswordService;
import com.tth.services.UserService;
import java.time.Instant;
import java.util.Date;
import java.util.Random;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author tongh
 */
@Controller
public class ForgotPasswordController {

    @Autowired
    private UserService userService;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ForgotPasswordService forgotPasswordService;
    @Autowired
    private Environment environment;

    private Integer otpGenerator() {
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }

    @PostMapping(value = "/verifyAccount/{username}")
    public ResponseEntity<String> verifyAccount(@PathVariable String username, Model model) {
        User user = this.userService.getUserByUsername(username);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        int otp = otpGenerator();
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            helper.setFrom(environment.getProperty("spring.mail.username"));
            helper.setTo(user.getAdmin().getEmail());
            String content = "Thông báo từ E-commerce Website "
                    + "đây là mã OTP của bạn " + otp + " và đừng gửi nó cho ai khác";
            helper.setText(content, false);
            helper.setSubject("OTP cho yêu cầu quên mật khẩu của bạn");

            ForgotPassword fp = new ForgotPassword();
            fp.setOtp(otp);
            fp.setExpirationTime(new Date(System.currentTimeMillis() + 600 * 1000));
            fp.setUser(user);

            this.forgotPasswordService.AddForgotPassword(fp);
            javaMailSender.send(mimeMessage);

            // Điều hướng đến trang nhập OTP
            model.addAttribute("username", username);
            return new ResponseEntity<>("OTP sent", HttpStatus.OK);
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi gửi email! Vui lòng thử lại sau.");
        }
    }

    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestParam("otp") Integer otp, @RequestParam("username") String username, Model model) {
        User user = this.userService.getUserByUsername(username);
        ForgotPassword fp = this.forgotPasswordService.findByOtpAndUSer(otp, user);

        if (fp == null || fp.getExpirationTime().before(Date.from(Instant.now()))) {
            model.addAttribute("error", "OTP Hết hạn");
            return "otpPage";
        }

        // OTP hợp lệ, điều hướng đến trang đổi mật khẩu
        model.addAttribute("username", username);
        return "changePassword"; // Trang đổi mật khẩu
    }
}
