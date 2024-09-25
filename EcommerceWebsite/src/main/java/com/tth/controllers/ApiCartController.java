/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controllers;

import com.tth.DTO.CartProductDTO;
import com.tth.services.CartService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tongh
 */
@RestController
@RequestMapping("/api/cart")
@CrossOrigin
public class ApiCartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add/")
    public ResponseEntity<String> addToCart(@RequestParam String username,
            @RequestParam Integer productId, @RequestParam Integer quantity) {
        this.cartService.addOrUpdateCart(username, productId, quantity);
        return ResponseEntity.ok("Product added to cart successfully");
    }

    @PostMapping("/update/")
    public ResponseEntity<String> updateQuantityToCart(@RequestParam String username,
            @RequestParam Integer productId, @RequestParam Integer quantity) {
        this.cartService.updateCartQuantity(username, productId, quantity);
        return ResponseEntity.ok("Product updated to cart successfully");
    }

    @GetMapping("/items/")
    public ResponseEntity<List<CartProductDTO>> getCartItems(@RequestParam String username) {
        List<CartProductDTO> cartItems = this.cartService.getCartByUsername(username);
        return ResponseEntity.ok(cartItems);
    }
    
    @DeleteMapping("/remove/{username}/{productId}/")
    public ResponseEntity<String> removeFromCart(@PathVariable String username, @PathVariable Integer productId) {
        cartService.updateCartQuantity(username, productId, 0); // Giả sử nếu số lượng <= 0 thì xóa sản phẩm
        return ResponseEntity.ok("Product removed from cart successfully");
    }

    // Xóa tất cả sản phẩm khỏi giỏ hàng của người dùng
    @DeleteMapping("/clear/{username}/")
    public ResponseEntity<String> clearCart(@PathVariable String username) {
        cartService.deleteAllCartItem(username);
        return ResponseEntity.ok("All items cleared from cart");
    }
}
