/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.repositories;

import com.tth.pojo.GoogleAccount;

/**
 *
 * @author tongh
 */
public interface GoogleAccountRepository {

    GoogleAccount getGoogleAccountByGoogleId(String googleId);

    public void addOrUpdateGoogleAccount(GoogleAccount googleAccount);

}
