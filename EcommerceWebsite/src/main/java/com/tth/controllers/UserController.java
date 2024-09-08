/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tth.controllers;

import com.tth.DTO.ChangePasswordDTO;
import com.tth.DTO.UserAdminDTO;
import com.tth.pojo.Admin;
import com.tth.pojo.Districts;
import com.tth.pojo.Provinces;
import com.tth.pojo.User;
import com.tth.pojo.Wards;
import com.tth.repositories.UserRepository;
import com.tth.services.AdminService;
import com.tth.services.DistrictService;
import com.tth.services.ProvinceService;
import com.tth.services.RoleService;
import com.tth.services.UserService;
import com.tth.services.WardService;
import com.tth.validator.UpdateUserAdminValidator;
import com.tth.validator.UserAdminValidator;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author tongh
 */
@Controller
@PropertySource("classpath:configs.properties")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserAdminValidator userAdminValidator;
    @Autowired
    private UpdateUserAdminValidator updateUserAdminValidator;
    @Autowired
    private Environment env;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private WardService wardService;

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Sai tài khoản hoặc mật khẩu");
        }
        return "login";
    }

    @GetMapping("/users")
    public String createViewUserAdmin(Model model) {
        model.addAttribute("user", new UserAdminDTO());
        model.addAttribute("provinces", this.provinceService.getProvinces());
        return "users";
    }

    @PostMapping("/users")
    public String createUserAdmin(@ModelAttribute(value = "user") UserAdminDTO userAdminDTO,
            BindingResult bindingResult, Model model) {
        userAdminValidator.validate(userAdminDTO, bindingResult);
        if (!bindingResult.hasErrors()) {
            model.addAttribute("provinces", this.provinceService.getProvinces());
            try {

                String username = userAdminDTO.getUsername();
                String password = userAdminDTO.getPassword();
                MultipartFile file = userAdminDTO.getFile();
                String firstName = userAdminDTO.getFirstName();
                String lastName = userAdminDTO.getLastName();
                String address = userAdminDTO.getAddress();
                String phone = userAdminDTO.getPhone();
                String email = userAdminDTO.getEmail();
                String personalId = userAdminDTO.getPersonalId();

                String provinceCode = userAdminDTO.getProvinceCode();
                String districtCode = userAdminDTO.getDistrictCode();
                String wardCode = userAdminDTO.getWardCode();
                Provinces province = this.provinceService.getProvinceById(provinceCode);
                Districts district = this.districtService.getDistrictById(districtCode);
                Wards ward = this.wardService.getWardById(wardCode);

                User u = new User(username, password, firstName, lastName);
                u.setFile(file);

                Admin admin = new Admin(username, address, email, phone, personalId, province, district, ward, u);
                if (this.userService.addOrUpdateUser(u)) {
                    if (this.adminService.addOrUpdateUserAdmin(admin)) {
                        return "redirect:/manage-users";
                    }
                }
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        model.addAttribute("provinces", this.provinceService.getProvinces());

        return "users";
    }

    @GetMapping("/update-user/{username}")
    public String updateViewUser(Model model, @PathVariable(value = "username") String username) {
        model.addAttribute("user", this.userService.getUserAdminDTOByUsername(username));
        model.addAttribute("provinces", this.provinceService.getProvinces());
        return "updateInfoUser";
    }

    @PostMapping("/update-user")
    public String updateUserAdmin(@ModelAttribute(value = "user") UserAdminDTO userAdminDTO,
            BindingResult bindingResult, Model model) {
        updateUserAdminValidator.validate(userAdminDTO, bindingResult);
        if (!bindingResult.hasErrors()) {
            model.addAttribute("provinces", this.provinceService.getProvinces());
            try {

                String username = userAdminDTO.getUsername();
                String password = userAdminDTO.getPassword();
                MultipartFile file = userAdminDTO.getFile();
                String firstName = userAdminDTO.getFirstName();
                String lastName = userAdminDTO.getLastName();
                String address = userAdminDTO.getAddress();
                String phone = userAdminDTO.getPhone();
                String email = userAdminDTO.getEmail();
                String personalId = userAdminDTO.getPersonalId();

                String provinceCode = userAdminDTO.getProvinceCode();
                String districtCode = userAdminDTO.getDistrictCode();
                String wardCode = userAdminDTO.getWardCode();
                Provinces province = this.provinceService.getProvinceById(provinceCode);
                Districts district = this.districtService.getDistrictById(districtCode);
                Wards ward = this.wardService.getWardById(wardCode);

                User u = new User(username, password, firstName, lastName);
                u.setFile(file);

                Admin admin = new Admin(username, address, email, phone, personalId, province, district, ward, u);
                if (this.userService.addOrUpdateUser(u)) {
                    if (this.adminService.addOrUpdateUserAdmin(admin)) {
                        return "redirect:/manage-users";
                    }
                }
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        model.addAttribute("provinces", this.provinceService.getProvinces());

        return "updateInfoUser";
    }

    @GetMapping("/change-password")
    public String showChangePasswordPage(@ModelAttribute("username") String username,
            Model model) {
        model.addAttribute("username", username);
        model.addAttribute("changePassword", new ChangePasswordDTO());
        return "changePassword";
    }

    @PostMapping("/change-password")
    public String changePasswordAdminByForgotPassword(
            @RequestParam("username") String username,
            @ModelAttribute(value = "changePassword") ChangePasswordDTO changePasswordDTO,
            RedirectAttributes redirectAttributes) {

        User user = userService.getUserByUsername(username);
        if (user == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy người dùng!");
            return "redirect:/change-password";
        }

        if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Mật khẩu cũ không đúng!");
            return "redirect:/change-password";
        }
// LÀM PASSWWORD KHÓ (CHUA LAM)
        if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getRePassword())) {
            redirectAttributes.addFlashAttribute("errorMessage", "Mật khẩu mới và xác nhận mật khẩu không khớp!");
            return "redirect:/change-password";
        }

        user.setPassword(changePasswordDTO.getNewPassword());
        this.userService.changePassword(user);

        redirectAttributes.addFlashAttribute("successMessage", "Đổi mật khẩu thành công!");
        return "redirect:/change-password";

    }

    @RequestMapping("/manage-users")
    public String UserManagement(Model model) {

        model.addAttribute("users", this.userService.getUsers());
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.userService.countUser();
        model.addAttribute("count", Math.ceil(count * 1.0 / pageSize));
        return "manageUsers";
    }

}
