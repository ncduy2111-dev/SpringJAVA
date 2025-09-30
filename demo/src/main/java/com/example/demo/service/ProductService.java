package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Cart;
import com.example.demo.domain.CartDetail;
import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.repository.CartDetailRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.specification.ProductSpecs;

import jakarta.servlet.http.HttpSession;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private CartRepository cartRepository;
    private CartDetailRepository cartDetailRepository;
    private UserService userService;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository,
            CartDetailRepository cartDetailRepository, UserService userService) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
    }

    public void handleSaveProduct(Product product) {
        this.productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Page<Product> getAllProductsByPage(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    public Page<Product> getAllProductsWithSpec(Pageable pageable, String name) {
        return this.productRepository.findAll(ProductSpecs.nameLike(name), pageable);
    }

    public Page<Product> getAllProductsWithSpecMinPrice(Pageable pageable, double minPrice) {
        return this.productRepository.findAll(ProductSpecs.minPrice(minPrice), pageable);
    }

    public Page<Product> getAllProductsWithSpecMaxPrice(Pageable pageable, double maxPrice) {
        return this.productRepository.findAll(ProductSpecs.maxPrice(maxPrice), pageable);
    }

    public Page<Product> getAllProductsWithSpecFactory(Pageable pageable, String factory) {
        return this.productRepository.findAll(ProductSpecs.factoryLike(factory), pageable);
    }

    public Page<Product> getAllProductsWithSpecFactories(Pageable pageable, List<String> factories) {
        return this.productRepository.findAll(ProductSpecs.factoryMapping(factories), pageable);
    }

    public Page<Product> getAllProductsWithSpecBetweenPrice(Pageable pageable, List<String> prices) {
        Specification<Product> spec = Specification.where(null);
        long count = 0;

        for (String price : prices) {
            switch (price) {
                case "DUOI-5TR":
                    spec = spec.or(ProductSpecs.maxPrice(5_000_000));
                    count++;
                    break;
                case "5-10TR":
                    spec = spec.or(ProductSpecs.priceBetween(5_000_000, 10_000_000));
                    count++;
                    break;
                case "10-15TR":
                    spec = spec.or(ProductSpecs.priceBetween(10_000_000, 15_000_000));
                    count++;
                    break;
                case "15-20TR":
                    spec = spec.or(ProductSpecs.priceBetween(15_000_000, 20_000_000));
                    count++;
                    break;
                case "TREN-20TR":
                    spec = spec.or(ProductSpecs.minPrice(20_000_000));
                    count++;
                    break;
            }
        }

        if (count == 0) {
            return this.productRepository.findAll(pageable);
        } else
            return this.productRepository.findAll(spec, pageable);
    }

    public Product getProductById(long id) {
        return this.productRepository.findById(id);
    }

    public void getDeleteByID(long id) {
        this.productRepository.deleteById(id);
    }

    public Product getOneProductByID(long id) {
        return this.productRepository.findById(id);
    }

    public List<CartDetail> getAllCartDetails() {
        return this.cartDetailRepository.findAll();
    }

    public long getCountProduct() {
        return this.productRepository.count();
    }

    public long getCountProductByFactory(String factory) {
        return this.productRepository.countByFactory(factory);
    }

    public void handleAddProductToCart(String email, long productId, HttpSession session, long quantity) {
        User user = userService.getOneUserByEmail(email);

        if (user != null) {
            Cart cart = cartRepository.findByUser(user);

            if (cart == null) {
                Cart otherCart = new Cart();
                otherCart.setUser(user);
                otherCart.setSum(0);

                cart = this.cartRepository.save(otherCart);
            }

            Product productOptional = this.productRepository.findById(productId);
            if (productOptional != null) {
                Product realProduct = productOptional;

                // Tìm xem sản phẩm đã có trong giỏ hàng chưa
                CartDetail oldDetail = cartDetailRepository.findByCartAndProduct(cart, realProduct);
                if (oldDetail == null) {
                    CartDetail cartDetail = new CartDetail();
                    cartDetail.setCart(cart);
                    cartDetail.setProduct(realProduct);
                    cartDetail.setPrice(realProduct.getPrice());
                    cartDetail.setQuantity(quantity);
                    this.cartDetailRepository.save(cartDetail);

                    // Cập nhật tổng số lượng sản phẩm trong giỏ hàng
                    int sum = cart.getSum() + 1;
                    cart.setSum(sum);
                    session.setAttribute("sum", sum);
                    this.cartRepository.save(cart);
                } else {
                    oldDetail.setQuantity(oldDetail.getQuantity() + quantity);
                    this.cartDetailRepository.save(oldDetail);
                }
            }
        }
    }
}
