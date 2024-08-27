/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories;

import com.tth.pojo.ForgotPassword;
import com.tth.pojo.User;
import java.util.Optional;

/**
 *
 * @author tongh
 */
public interface ForgotPasswordRepository {

    public void AddForgotPassword(ForgotPassword fp);

    public ForgotPassword findByOtpAndUSer(int otp, User user);

    public void deleteOtp(int id);

    public ForgotPassword getForgotPasswordById(int id);

}
