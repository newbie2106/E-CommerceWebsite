/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controllers;

import com.tth.pojo.GoogleAccount;
import com.tth.services.GoogleAccountService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@PropertySource("classpath:configs.properties")
public class ApiGoogleAccountController {

    @Autowired
    private GoogleAccountService googleAccountService;
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    @PostMapping("/google-login")
    public ResponseEntity loginWithGoogle(@RequestBody Map<String, String> request) {
        try {
            String idToken = request.get("token");

            GoogleAccount ggAccount = this.googleAccountService.authenticateUser(idToken);

            // Tạo JWT cho người dùng
            System.out.println("T0k3n: " + request.get("token"));

            String jwtToken = generateJwtToken(ggAccount);

            Map<String, Object> response = new HashMap<>();
            response.put("token", jwtToken);
            response.put("user", ggAccount);

            return new ResponseEntity(response, HttpStatus.OK);

        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("LoiT0k3n: " + request.get("token"));
            System.out.println("JWT Secret: " + jwtSecret);
            System.out.println("JWT Expiration: " + jwtExpiration);
            return new ResponseEntity("Invalid Token", HttpStatus.BAD_REQUEST);
        }
    }

    private String generateJwtToken(GoogleAccount ggAccount) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        System.out.println("JWT Secret: " + jwtSecret);
        System.out.println("JWT Expiration: " + jwtExpiration);

        return Jwts.builder()
                .setSubject(ggAccount.getGoogleId().toString())
                .claim("name", ggAccount.getName())
                .claim("email", ggAccount.getEmail())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, jwtSecret.getBytes())
                .compact();
    }
}
