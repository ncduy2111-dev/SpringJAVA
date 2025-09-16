package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Cart;
import com.example.demo.domain.CartDetail;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderDetail;
import com.example.demo.domain.User;
import com.example.demo.repository.CartDetailRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository,
            CartRepository cartRepository, CartDetailRepository cartDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
    }

    public void handleSaveOrder(Order order) {
        this.orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    public Order getOneOrderById(long id) {
        return this.orderRepository.findById(id).orElse(null);
    }

    public void deleteOrderById(long id) {
        this.orderRepository.deleteById(id);
    }

    public long getCountOrder() {
        return this.orderRepository.count();
    }

    public void handlePlaceOrder(User user, HttpSession session, String receiverName, String receiverAddress,
            String receiverPhone) {

        Cart cart = this.cartRepository.findByUser(user);
        if (cart != null) {
            List<CartDetail> cartDetails = cart.getCartDetails();

            // Tạo db order
            Order order = new Order();
            order.setUser(user);
            order.setReceiverName(receiverName);
            order.setReceiverAddress(receiverAddress);
            order.setReceiverPhone(receiverPhone);
            order.setStatus("PENDING");

            double total = 0;
            for (CartDetail cartDetail : cartDetails) {
                total += cartDetail.getPrice() * cartDetail.getQuantity();
            }
            order.setTotalPrice(total);
            order = this.orderRepository.save(order);

            // Tạo db order detail
            for (CartDetail cartDetail : cartDetails) {
                OrderDetail orderDetail = new OrderDetail();

                orderDetail.setPrice(cartDetail.getPrice());
                orderDetail.setQuantity(cartDetail.getQuantity());
                orderDetail.setProduct(cartDetail.getProduct());
                orderDetail.setOrder(order);

                this.orderDetailRepository.save(orderDetail);
            }

            // Xoá các sản phẩm đó khỏi giỏ hàng
            for (CartDetail cartDetail : cartDetails) {
                this.cartDetailRepository.deleteById(cartDetail.getId());
            }

            this.cartRepository.delete(cart);

            session.setAttribute("sum", 0);
        }

    }
}
