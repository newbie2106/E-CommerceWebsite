/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services.impl;

import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;
import com.tth.pojo.GoogleAccount;
import com.tth.repositories.GoogleAccountRepository;
import com.tth.services.GoogleAccountService;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tongh
 */
@Service
public class GoogleAccountServiceImpl implements GoogleAccountService {

    @Autowired
    private GoogleAccountRepository googleAccountRepo;

    @Override
    public GoogleAccount authenticateUser(String idToken) {
        try {
            String jwkSetUri = "https://www.googleapis.com/oauth2/v3/certs";

            // Lấy JWK Set từ Google
            HttpURLConnection conn = (HttpURLConnection) new URL(jwkSetUri).openConnection();
            conn.setRequestMethod("GET");

            // Đọc phản hồi
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Phân tích JWK Set
            JWKSet jwkSet = JWKSet.parse(response.toString());

            // Phân tích ID token
            SignedJWT signedJWT = (SignedJWT) JWTParser.parse(idToken);
            JWSHeader header = signedJWT.getHeader();

            // Lấy public key từ JWK Set
            JWK jwk = jwkSet.getKeyByKeyId(header.getKeyID());
            if (jwk == null) {
                throw new RuntimeException("Public key not found in JWK set");
            }

            RSAPublicKey publicKey = ((RSAKey) jwk).toRSAPublicKey();
            RSASSAVerifier verifier = new RSASSAVerifier(publicKey);

            // Kiểm tra tính hợp lệ của token
            if (!signedJWT.verify(verifier)) {
                throw new RuntimeException("Invalid ID token signature");
            }

            // Lấy thông tin claims
            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
            String googleId = claims.getStringClaim("sub"); // "sub"
            String email = claims.getStringClaim("email");
            String name = claims.getStringClaim("name");
            String pictureUrl = claims.getStringClaim("picture");
            GoogleAccount ggAccount = this.googleAccountRepo.getGoogleAccountByGoogleId(googleId);

            if (ggAccount != null) {
                ggAccount.setName(name);
                ggAccount.setEmail(email);
                ggAccount.setPictureUrl(pictureUrl);
                this.googleAccountRepo.addOrUpdateGoogleAccount(ggAccount);
                return ggAccount;
            } else {
                GoogleAccount newAccount = new GoogleAccount();
                newAccount.setGoogleId(googleId);
                newAccount.setName(name);
                newAccount.setEmail(email);
                newAccount.setPictureUrl(pictureUrl);
                this.googleAccountRepo.addOrUpdateGoogleAccount(newAccount);
                return newAccount;
            }

            
        } catch (Exception e) {
            throw new RuntimeException("Error during authentication", e);
        }
    }
}
