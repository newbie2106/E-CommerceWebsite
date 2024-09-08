/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controllers;

import com.tth.DTO.ChangePasswordDTO;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private Integer otpGenerator() {
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }

    @GetMapping("/verify-account")
    public String showVerifyAccountPage() {
        return "verifyAccount";
    }

    @PostMapping(value = "/verify-account")
    public String verifyAccount(@RequestParam("username") String username, Model model,
            RedirectAttributes redirectAttributes) {
        User user = this.userService.getUserByUsername(username);
        System.out.println("userNgoai" + user);

        if (user.getUsername() == null) {
            System.out.println("userIF" + user);
            redirectAttributes.addFlashAttribute("errorMessage", "Người dùng không tồn tại hoặc không phải là Admin.");
            return "redirect:/verify-account";

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
            System.out.println("userTry" + user);

            // Điều hướng đến trang nhập OTP
            redirectAttributes.addFlashAttribute("username", username);
            return "redirect:/verify-otp";
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("userCatch" + user);

            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi xác thực tài khoản! Vui lòng thử lại sau.");
            return "redirect:/verify-account";
        }
    }

    @GetMapping("/verify-otp")
    public String showVerifyOtpPage(@ModelAttribute("username") String username, Model model) {
        model.addAttribute("username", username);
        return "verifyOtp";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam("otp") Integer otp, @RequestParam("username") String username,
            RedirectAttributes redirectAttributes) {
        User user = this.userService.getUserByUsername(username);
        ForgotPassword fp = this.forgotPasswordService.findByOtpAndUSer(otp, user);

        if (fp == null || fp.getExpirationTime().before(Date.from(Instant.now()))) {
            redirectAttributes.addFlashAttribute("error", "OTP Hết hạn");
            return "redirect:/verify-otp";
        }

        // OTP hợp lệ, điều hướng đến trang đổi mật khẩu
        redirectAttributes.addFlashAttribute("username", username);
        return "redirect:/create-password"; // Trang đổi mật khẩu
    }

    @GetMapping("/create-password")
    public String showCreatePasswordPage(@ModelAttribute("username") String username, Model model) {
        model.addAttribute("username", username);
        return "createPassword";
    }

    @PostMapping("/create-password")
    public String changePasswordAdminByForgotPassword(
            @RequestParam("username") String username,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("rePassword") String rePassword,
            RedirectAttributes redirectAttributes) {

        User user = userService.getUserByUsername(username);
    
// LÀM PASSWWORD KHÓ (CHUA LAM)
        if (!newPassword.equals(rePassword)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Mật khẩu mới và xác nhận mật khẩu không khớp!");
            return "redirect:/create-password";
        }

        user.setPassword(newPassword);
        this.userService.changePassword(user);

        redirectAttributes.addFlashAttribute("successMessage", "Đổi mật khẩu thành công!");
        return "redirect:/create-password";
    }
}
