/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controllers;

import com.tth.DTO.SaleOrderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tth.configs.Config;
import com.tth.services.PaymentService;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author tongh
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiPaymentController {

    private static final int MOMO_SUCCESS_CODE = 0;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/create-payment/")
    public ResponseEntity<?> createPayment(HttpServletRequest request, @RequestParam long amount) throws UnsupportedEncodingException {
        // Tạo mã giao dịch ngẫu nhiên
        String vnp_TxnRef = Config.getRandomNumber(8);
        String vnp_TmnCode = Config.vnp_TmnCode;
        String vnp_IpAddr = Config.getIpAddress(request);
        String orderType = "other";

        // Khởi tạo các tham số thanh toán
        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", Config.vnp_Version);
        vnp_Params.put("vnp_Command", Config.vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount * 100)); // Chuyển đổi sang đơn vị nhỏ nhất (VND)
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", "NCB");
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
        vnp_Params.put("vnp_OrderType", orderType);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        // Xây dựng chuỗi dữ liệu để hash
        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        for (String fieldName : fieldNames) {
            String fieldValue = vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                // Build hash data
                hashData.append(fieldName).append('=').append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                // Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString())).append('=').append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                query.append('&');
                hashData.append('&');
            }
        }

        // Bỏ ký tự '&' cuối cùng
        query.deleteCharAt(query.length() - 1);
        hashData.deleteCharAt(hashData.length() - 1);

        // Tạo chữ ký bảo mật
        String queryURL = query.toString();
        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
        queryURL += "&vnp_SecureHash=" + vnp_SecureHash;
        query.append("&vnp_SecureHash=").append(vnp_SecureHash);

        // Tạo URL thanh toán
        String paymentUrl = Config.vnp_PayUrl + "?" + queryURL;

        // Trả về phản hồi
        Map<String, Object> response = new HashMap<>();
        response.put("code", "00");
        response.put("message", "Success");
        response.put("paymentUrl", paymentUrl);

        // Trả về trạng thái thành công với URL thanh toán
        return ResponseEntity.ok(response);
    }

    @GetMapping("/vnpay-return/")
    public ResponseEntity<?> handleVNPayReturn(HttpServletRequest request) {
        String redirectUrl = "http://localhost:5173/payment-return";

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(redirectUrl)).build();
    }

    @PostMapping("/payment-momo/")
    public ResponseEntity<Map<String, String>> pay(@RequestBody SaleOrderDTO saleOrderDTO, HttpServletRequest request) {
        try {
            // Gọi dịch vụ thanh toán và trả về phản hồi
            return paymentService.payWithMoMo(request, saleOrderDTO);
        } catch (Exception e) {
            // Xử lý lỗi và trả về thông báo lỗi
            Map<String, String> errorResponse = Map.of("error", "Error processing payment: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

//    @PostMapping("/ipn-momo")
//    public ResponseEntity<String> ipnMomo(@RequestBody Map<String, String> requestData) {
//        try {
//            if (!requestData.containsKey("resultCode") || !Integer.toString(MOMO_SUCCESS_CODE).equals(requestData.get("resultCode"))) {
//                return new ResponseEntity<>("Transaction is not success", HttpStatus.BAD_REQUEST);
//            }
//
//            Invoice invoice = this.invoiceService.checkrequestId(requestData.get("requestId"));
//            System.out.println("CHeck invoice: " + invoice);
//            if (invoice != null) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            } else {
//                return new ResponseEntity<>("Redirected data is invalid", HttpStatus.BAD_REQUEST);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
