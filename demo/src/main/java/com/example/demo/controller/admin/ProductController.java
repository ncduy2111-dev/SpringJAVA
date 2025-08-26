package com.example.demo.controller.admin;

import com.example.demo.service.ProductService;
import com.example.demo.service.UploadFileService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.Product;

@Controller
public class ProductController {

    private final ProductService productService;
    private final UploadFileService uploadFileService;

    ProductController(ProductService productService, UploadFileService uploadFileService) {
        this.productService = productService;
        this.uploadFileService = uploadFileService;
    }

    @GetMapping("/admin/product")
    public String getDashboard(Model model) {
        List<Product> arrProduct = this.productService.getAllProducts();
        model.addAttribute("products", arrProduct);

        return "/admin/product/show";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "/admin/product/create";
    }

    @PostMapping("/admin/product")
    public String createProduct(@ModelAttribute("newProduct") Product product,
            @RequestPart("imageProductFile") MultipartFile file) {
        String image = this.uploadFileService.handleUploadFile(file, "image");

        product.setImage(image);
        this.productService.handleSaveProduct(product);
        return "redirect:/admin/product";
    }
}
