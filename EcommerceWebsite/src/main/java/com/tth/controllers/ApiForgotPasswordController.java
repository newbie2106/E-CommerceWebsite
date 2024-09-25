/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controllers;

import com.tth.pojo.ForgotPassword;
import com.tth.pojo.User;
import com.tth.services.EmailService;
import com.tth.services.ForgotPasswordService;
import com.tth.services.UserService;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tongh
 */
@RestController
@RequestMapping("/api/forgot-password")
@CrossOrigin
@PropertySource("classpath:configs.properties")
public class ApiForgotPasswordController {

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
    public ResponseEntity<String> verifyAccount(@PathVariable String username) {
        User user = this.userService.getUserByUsername(username);

        System.err.println("EMAILFROM: " + environment.getProperty("spring.mail.username"));
        System.err.println("EMAILTO: " + user.getCustomer().getEmail());

        int otp = otpGenerator();
        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            helper.setFrom(environment.getProperty("spring.mail.username"));
            helper.setTo(user.getCustomer().getEmail());
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
            return ResponseEntity.ok("Đã gửi mã OTP thành công tới email của bạn!");
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi gửi email! Vui lòng thử lại sau.");
        }

    }

    @PostMapping("/verifyOtp/{otp}/{username}")
    public ResponseEntity<String> verifyOtp(@PathVariable Integer otp, @PathVariable String username) {
        User user = this.userService.getUserByUsername(username);
        ForgotPassword fp = this.forgotPasswordService.findByOtpAndUSer(otp, user);

        if (fp.getExpirationTime().before(Date.from(Instant.now()))) {
            forgotPasswordService.deleteOtp(fp.getId());
            return new ResponseEntity<>("OTP has expired!", HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok("OTP đã xác thực!");
    }

    @PostMapping("/create-new-password/{username}")
    public ResponseEntity<String> changePasswordAdmin(
            @PathVariable("username") String username,
            @RequestBody Map<String, String> passwordData) {

        String newPassword = passwordData.get("newPassword");
        String confirmPassword = passwordData.get("confirmPassword");

        User user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy người dùng!");
        }

        if (!newPassword.equals(confirmPassword)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mật khẩu mới và xác nhận mật khẩu không khớp!");
        }
        user.setPassword(newPassword);
        this.userService.changePassword(user);

        return ResponseEntity.ok("Đổi mật khẩu thành công!");
    }

}
