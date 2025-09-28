package com.example.demo.controller.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Cart;
import com.example.demo.domain.CartDetail;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.service.CartService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ItemController {

    private final ProductService productService;
    private final CartService cartService;
    private final OrderService orderService;

    public ItemController(ProductService productService, CartService cartService, OrderService orderService) {
        this.productService = productService;
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping("/products")
    public String getProductPage(Model model,
            @RequestParam("page") Optional<String> pageOptional,
            @RequestParam("name") Optional<String> nameOptional,
            @RequestParam("price") Optional<String> priceOptional,
            @RequestParam("min-price") Optional<String> minPriceOptional,
            @RequestParam("max-Price") Optional<String> maxPriceOptional,
            @RequestParam("factory") Optional<String> factoryOptional) {
        int page = 1;

        try {
            if (pageOptional.isPresent()) {
                // convert from String to int
                page = Integer.parseInt(pageOptional.get());
            } else {
                // page = 1
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        Pageable pageable = PageRequest.of(page - 1, 20);

        String name = nameOptional.isPresent() ? nameOptional.get() : "";
        double minPrice = minPriceOptional.isPresent() ? Double.parseDouble(minPriceOptional.get()) : 0;
        double maxPrice = maxPriceOptional.isPresent() ? Double.parseDouble(maxPriceOptional.get()) : 0;
        String factory = factoryOptional.isPresent() ? factoryOptional.get() : "";
        // List<String> factories = Arrays.asList(factoryOptional.get().split(","));
        String price = priceOptional.isPresent() ? priceOptional.get() : "";
        // Page<Product> pageProduct =
        // this.productService.getAllProductsWithSpecMinPrice(pageable, minPrice);
        Page<Product> pageProduct = this.productService.getAllProductsWithSpecBetweenPrice(pageable, price);

        List<Product> arrProduct = pageProduct.getContent();

        model.addAttribute("arrProducts", arrProduct);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", pageProduct.getTotalPages());

        return "client/product/show";
    }

    @GetMapping("/product/{id}")
    public String getProductPage(Model model, @PathVariable long id) {
        model.addAttribute("product", productService.getProductById(id));

        model.addAttribute("countApple", this.productService.getCountProductByFactory("APPLE"));
        model.addAttribute("countDell", this.productService.getCountProductByFactory("DELL"));
        model.addAttribute("countAsus", this.productService.getCountProductByFactory("ASUS"));
        model.addAttribute("countAcer", this.productService.getCountProductByFactory("ACER"));
        model.addAttribute("countLenovo", this.productService.getCountProductByFactory("LENOVO"));
        model.addAttribute("countLG", this.productService.getCountProductByFactory("LG"));
        return "client/product/detail";
    }

    @PostMapping("/add-product-to-cart/{id}")
    public String addProductToCart(@PathVariable long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        long productId = id;
        String email = (String) session.getAttribute("email");

        productService.handleAddProductToCart(email, productId, session, 1);
        return "redirect:/";
    }

    @PostMapping("/add-product-from-view-detail")
    public String postpostAddProductFromDetail(
            @RequestParam("id") long id,
            @RequestParam("quantity") long quantity,
            HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        String email = (String) session.getAttribute("email");
        this.productService.handleAddProductToCart(email, id, session, quantity);
        return "redirect:/product/" + id;
    }

    @GetMapping("/cart")
    public String getCartPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");

        User currentUser = new User();
        currentUser.setId(id);

        Cart cart = this.cartService.getOneCartByUser(currentUser);
        List<CartDetail> cartDetails = cart == null ? new ArrayList<>() : cart.getCartDetails();

        double totalPrice = 0;
        for (CartDetail item : cartDetails) {
            totalPrice += item.getProduct().getPrice() * item.getQuantity();
        }

        model.addAttribute("cart", cart); // quan trọng
        model.addAttribute("cartItems", cartDetails);
        model.addAttribute("totalPrice", totalPrice);

        return "client/cart/show";
    }

    @PostMapping("/delete-cart-product/{id}")
    public String deleteCartDetail(@PathVariable long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        long productId = id;
        this.cartService.handleRemoveCartDetail(productId, session);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String getCheckOutPage(Model model, HttpServletRequest request) {
        User currentUser = new User(); // null
        HttpSession session = request.getSession(false);
        long id = (long) session.getAttribute("id");
        currentUser.setId(id);

        Cart cart = this.cartService.getOneCartByUser(currentUser);

        List<CartDetail> cartDetails = cart == null ? new ArrayList<CartDetail>() : cart.getCartDetails();

        double totalPrice = 0;
        for (CartDetail cd : cartDetails) {
            totalPrice += cd.getPrice() * cd.getQuantity();
        }

        model.addAttribute("cartItems", cartDetails);
        model.addAttribute("totalPrice", totalPrice);

        return "client/cart/checkout";
    }

    @PostMapping("/confirm-checkout")
    public String getCheckOutPage(@ModelAttribute("cart") Cart cart) {
        List<CartDetail> cartDetails = cart == null ? new ArrayList<>() : cart.getCartDetails();
        this.cartService.handleUpdateCartBeforeCheckout(cartDetails);
        return "redirect:/checkout";
    }

    @PostMapping("/place-order")
    public String handlePlaceOrder(
            HttpServletRequest request,
            @RequestParam("receiverName") String receiverName,
            @RequestParam("receiverAddress") String receiverAddress,
            @RequestParam("receiverPhone") String receiverPhone) {
        HttpSession session = request.getSession(false);

        User currentUser = new User(); // null
        long id = (long) session.getAttribute("id");
        currentUser.setId(id);

        this.orderService.handlePlaceOrder(currentUser, session, receiverName, receiverAddress, receiverPhone);
        return "redirect:/thanks";
    }

    @GetMapping("/thanks")
    public String getThanksPage(Model model) {
        return "client/cart/thanks";
    }

}
