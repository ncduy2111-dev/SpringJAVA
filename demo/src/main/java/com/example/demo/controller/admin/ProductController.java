package com.example.demo.controller.admin;

import com.example.demo.service.ProductService;
import com.example.demo.service.UploadFileService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String createProduct(@ModelAttribute("newProduct") @Valid Product product,
            BindingResult newProductBindingResult,
            @RequestPart("imageProductFile") MultipartFile file) {
        String image = this.uploadFileService.handleUploadFile(file, "product");

        if (newProductBindingResult.hasErrors()) {
            return "/admin/product/create";
        }

        product.setImage(image);
        this.productService.handleSaveProduct(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/detail/{id}")
    public String getProductDetailPage(Model model, @PathVariable long id) {
        model.addAttribute("product", this.productService.getProductById(id));
        return "admin/product/detail";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String getProductDeletePage(Model model, @PathVariable long id) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/delete";
    }

    @PostMapping(value = "/admin/product/delete")
    public String postDeleteProductPage(Model model, @ModelAttribute("newProduct") Product product) {
        this.productService.getDeleteByID(product.getId());
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/update/{id}")
    public String getProductUpdatePage(Model model, @PathVariable long id) {
        Product currentProduct = this.productService.getOneProductByID(id);
        model.addAttribute("newProduct", currentProduct);
        return "admin/product/update";
    }

    @PostMapping(value = "/admin/product/update")
    public String postProductUpdatePage(Model model, @ModelAttribute("newProduct") @Valid Product product,
            BindingResult newProductBindingResult,
            @RequestPart("imageProductFile") MultipartFile file) {
        Product currentProduct = this.productService.getOneProductByID(product.getId());

        if (newProductBindingResult.hasErrors()) {
            return "/admin/product/update";
        }

        if (currentProduct != null) {
            if (!file.isEmpty()) {
                String image = this.uploadFileService.handleUploadFile(file, "product");

                currentProduct.setImage(image);
            }

            currentProduct.setName(product.getName());
            currentProduct.setPrice(product.getPrice());
            currentProduct.setDetailDesc(product.getDetailDesc());
            currentProduct.setShortDesc(product.getShortDesc());
            currentProduct.setQuantity(product.getQuantity());
            currentProduct.setFactory(product.getFactory());
            currentProduct.setTarget(product.getTarget());
        }

        this.productService.handleSaveProduct(currentProduct);
        return "redirect:/admin/product";
    }
}
