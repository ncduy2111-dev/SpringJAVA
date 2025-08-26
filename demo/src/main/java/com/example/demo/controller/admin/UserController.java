package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.User;
import com.example.demo.service.UploadFileService;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;
    private final UploadFileService uploadFileService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UploadFileService uploadFileService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadFileService = uploadFileService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> arrUser = this.userService.getAllUsers();
        model.addAttribute("users", arrUser);
        return "admin/user/show";
    }

    @RequestMapping("/admin/user/detail/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getOneUserByID(id);
        model.addAttribute("user", user);
        return "admin/user/detail";
    }

    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User currentUser = this.userService.getOneUserByID(id);
        model.addAttribute("newUser", currentUser);
        return "admin/user/update";
    }

    @PostMapping(value = "/admin/user/update")
    public String postUpdateUserPage(Model model, @ModelAttribute("newUser") User user) {
        User currentUser = this.userService.getOneUserByID(user.getId());

        if (currentUser != null) {
            currentUser.setFullName(user.getFullName());
            currentUser.setPhone(user.getPhone());
            currentUser.setAddress(user.getAddress());
        }

        this.userService.handleSaveUser(currentUser);
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        model.addAttribute("newUser", new User());
        return "admin/user/delete";
    }

    @PostMapping(value = "/admin/user/delete")
    public String postDeleteUserPage(Model model, @ModelAttribute("newUser") User user) {
        this.userService.getDeleteByID(user.getId());
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping("/admin/user/")
    public String createUserPage(Model model, @ModelAttribute("newUser") @Valid User user,
            BindingResult newUserBindingResult, @RequestParam("imgAvatar") MultipartFile file) {
        String avatar = this.uploadFileService.handleUploadFile(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(user.getPassword());

        if (newUserBindingResult.hasErrors()) {
            return "/admin/user/create";
        }

        user.setAvatar(avatar);
        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName(user.getRole().getName()));
        this.userService.handleSaveUser(user);
        return "redirect:/admin/user";
    }
}
