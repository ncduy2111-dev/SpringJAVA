package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Order;
import com.example.demo.service.OrderService;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/admin/order")
    public String getDashboard(Model model) {
        List<Order> arrOrder = this.orderService.getAllOrders();
        model.addAttribute("orders", arrOrder);
        return "admin/order/show";
    }

    @GetMapping("/admin/order/detail/{id}")
    public String getOrderDetailPage(@PathVariable long id, Model model) {
        Order order = this.orderService.getOneOrderById(id);
        model.addAttribute("order", order);
        return "admin/order/detail";
    }

    @GetMapping("/admin/order/update/{id}")
    public String getUpdateOrderPage(@PathVariable long id, Model model) {
        Order currentOrder = this.orderService.getOneOrderById(id);
        model.addAttribute("newOrder", currentOrder);
        return "admin/order/update";
    }

    @PostMapping("/admin/order/update")
    public String postUpdateOrderPage(@ModelAttribute("newOrder") Order order, Model model) {
        Order currentOrder = this.orderService.getOneOrderById(order.getId());

        if (currentOrder != null) {
            currentOrder.setStatus(order.getStatus());

            this.orderService.handleSaveOrder(currentOrder);
        }

        return "redirect:/admin/order";
    }

    @RequestMapping("/admin/order/delete/{id}")
    public String getDeleteOrderPage(Model model, @PathVariable long id) {
        model.addAttribute("newOrder", new Order());
        return "admin/order/delete";
    }

    @PostMapping("/admin/order/delete")
    public String postDeleteOrderPage(Model model, @ModelAttribute("newOrder") Order order) {
        this.orderService.deleteOrderById(order.getId());
        return "redirect:/admin/order";
    }

}
