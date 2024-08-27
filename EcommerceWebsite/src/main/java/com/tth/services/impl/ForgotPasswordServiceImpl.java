/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services.impl;

import com.tth.pojo.ForgotPassword;
import com.tth.pojo.User;
import com.tth.repositories.ForgotPasswordRepository;
import com.tth.services.ForgotPasswordService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tongh
 */
@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    @Autowired
    private ForgotPasswordRepository forgotPasswordRepo;

    @Override
    public void AddForgotPassword(ForgotPassword fp) {
        this.forgotPasswordRepo.AddForgotPassword(fp);
    }

    @Override
    public ForgotPassword findByOtpAndUSer(int otp, User user) {
        return this.forgotPasswordRepo.findByOtpAndUSer(otp, user);
    }

    @Override
    public void deleteOtp(int id) {
        this.forgotPasswordRepo.deleteOtp(id);
    }

    @Override
    public ForgotPassword getForgotPasswordById(int id) {
        return this.forgotPasswordRepo.getForgotPasswordById(id);
    }

}
