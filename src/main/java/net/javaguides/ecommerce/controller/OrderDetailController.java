package net.javaguides.ecommerce.controller;

import net.javaguides.ecommerce.model.OrderInput;
import net.javaguides.ecommerce.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderDetailController {

    @Autowired
    OrderDetailService orderDetailService;


    @PreAuthorize("hasRole('User')")
    @PostMapping("/placeOrder")
    public void placeOrder(@RequestBody OrderInput orderInput){
        orderDetailService.placeOrder(orderInput);
    }
}
