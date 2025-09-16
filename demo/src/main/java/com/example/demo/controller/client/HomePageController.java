package com.example.demo.controller.client;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.domain.DTO.RegisterDTO;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@Controller
public class HomePageController {

    private final ProductService productService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public HomePageController(ProductService productService, UserService userService, PasswordEncoder passwordEncoder) {
        this.productService = productService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String getMethodName(Model model) {
        List<Product> arrProducts = productService.getAllProducts();
        model.addAttribute("arrProducts", arrProducts);
        return "client/homepage/show";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/register";
    }

    @PostMapping("/register")
    public String postRegisterPage(Model model, @ModelAttribute("registerUser") @Valid RegisterDTO registerDTO,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "client/auth/register";
        }

        User user = userService.registerDTOtoUser(registerDTO);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(userService.getRoleByName("USER"));

        userService.handleSaveUser(user);
        model.addAttribute("registerUser", registerDTO);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        // model.addAttribute("loginUser", new LoginDTO());
        return "client/auth/login";
    }

    @GetMapping("/403")
    public String get403Page(Model model) {
        // model.addAttribute("loginUser", new LoginDTO());
        return "client/auth/403";
    }

    @GetMapping("/history")
    public String getHistoryPage(Model model) {
        // List<Purchase> purchaseHistory = purchaseService.getPurchaseHistory();
        // model.addAttribute("purchaseHistory", purchaseHistory);
        return "client/cart/history";
    }

}