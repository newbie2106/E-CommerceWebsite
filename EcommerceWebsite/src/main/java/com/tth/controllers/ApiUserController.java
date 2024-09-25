/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controllers;

import com.tth.DTO.LoginRequest;
import com.tth.DTO.UserCustomerDTO;
import com.tth.components.JwtService;
import com.tth.pojo.Customer;
import com.tth.pojo.Districts;
import com.tth.pojo.Provinces;
import com.tth.pojo.Role;
import com.tth.pojo.User;
import com.tth.pojo.Wards;
import com.tth.services.CustomerService;
import com.tth.services.DistrictService;
import com.tth.services.ProvinceService;
import com.tth.services.RoleService;
import com.tth.services.UserService;
import com.tth.services.WardService;
import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author tongh
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiUserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private WardService wardService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private RoleService roleService ;

    @PostMapping("/login/")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

        if (this.userService.authUser(loginRequest.getUsername(), loginRequest.getPassword()) == true) {
            String token = this.jwtService.generateTokenLogin(loginRequest.getUsername());
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>("error" + loginRequest.getUsername(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/current-user/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getCurrentUser(Principal p) {
        User u = this.userService.getUserByUsername(p.getName());
        System.out.println("NEM1" + p.getName());
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @PostMapping(path = "/register/",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity createUserCustomer(@RequestParam Map<String, String> params, @RequestPart MultipartFile avatar) {
        String username = params.get("username");
        String password = params.get("password");
        MultipartFile file = avatar;
        String firstName = params.get("firstName");
        String lastName = params.get("lastName");
        String address = params.get("address");
        String phone = params.get("phone");
        String email = params.get("email");

        String provinceCode = params.get("provinceCode");
        String districtCode = params.get("districtCode");
        String wardCode = params.get("wardCode");
        Provinces province = this.provinceService.getProvinceById(provinceCode);
        Districts district = this.districtService.getDistrictById(districtCode);
        Wards ward = this.wardService.getWardById(wardCode);

        User u = new User(username, password, firstName, lastName);
        u.setFile(file);
        Role role = this.roleService.getRoleById(2);
        u.setRole(role);
        Customer customer = new Customer(username, address, email, phone, province, district, ward, u);
        if (this.userService.addOrUpdateUser(u)) {
            if (this.customerService.addUserCustomer(customer)) {
                return new ResponseEntity("Suscess", HttpStatus.CREATED);
            }
        }

        return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping("/users/{username}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "username") String username) {
        this.userService.deleteUser(username);
    }

    @PostMapping("/change-password/{username}")
    public ResponseEntity<String> changePasswordAdmin(
            @PathVariable("username") String username,
            @RequestBody Map<String, String> passwordData) {

        String oldPassword = passwordData.get("oldPassword");
        String newPassword = passwordData.get("newPassword");
        String confirmPassword = passwordData.get("confirmPassword");

        User user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy người dùng!");
        }

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mật khẩu cũ không đúng!");
        }

        if (!newPassword.equals(confirmPassword)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mật khẩu mới và xác nhận mật khẩu không khớp!");
        }
        user.setPassword(newPassword);
        this.userService.changePassword(user);

        return ResponseEntity.ok("Đổi mật khẩu thành công!");
    }
}
