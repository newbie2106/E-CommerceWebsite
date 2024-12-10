/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.services;

import com.tth.DTO.SaleOrderDTO;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author tongh
 */
public interface PaymentService {

    ResponseEntity<String> pay(SaleOrderDTO saleOrderDTO, HttpServletRequest request) throws Exception;

    String generateSignature(String rawSignature, String secretKey) throws Exception;

    ResponseEntity<Map<String, String>> payWithMoMo(HttpServletRequest request, SaleOrderDTO saleOrderDTO) throws Exception;
}
