/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controllers;

import com.tth.DTO.RecentlyViewedDTO;
import com.tth.services.RecentlyViewedService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tongh
 */
@RestController
@RequestMapping("/api/recently-viewed")
@CrossOrigin
public class ApiRecentlyViewedController {

    @Autowired
    private RecentlyViewedService recentlyViewedService;

    @PostMapping("/add/")
    public void addRecentlyViewed(@RequestParam String username, @RequestParam Integer productId) {
        this.recentlyViewedService.findByUsernameAndProductId(username, productId);
    }

    @GetMapping("/user/{username}/")
    public List<RecentlyViewedDTO> getRecentlyViewed(@PathVariable String username) {
        return this.recentlyViewedService.findRecentViewsByUsername(username);
    }
}
