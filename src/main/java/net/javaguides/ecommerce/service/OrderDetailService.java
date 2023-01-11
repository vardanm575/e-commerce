package net.javaguides.ecommerce.service;

import net.javaguides.ecommerce.config.JwtRequestFilter;
import net.javaguides.ecommerce.dao.OrderDetailDao;
import net.javaguides.ecommerce.dao.ProductDao;
import net.javaguides.ecommerce.dao.UserDao;
import net.javaguides.ecommerce.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    public static final String ORDER_PLACED="Placed";

    public void placeOrder(OrderInput orderInput) {

        List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();
        for (OrderProductQuantity o: productQuantityList) {
            Product product = productDao.findById(o.getProductId()).get();
            String currentUser = JwtRequestFilter.CURRENT_USER;

            User user = userDao.findById(currentUser).get();

            OrderDetail orderDetail = new OrderDetail(
                    orderInput.getFullName(),
                    orderInput.getFullAddress(),
                    orderInput.getContactNumber(),
                    orderInput.getAlternateContactNumber(),
                    ORDER_PLACED,
                    product.getProductDiscountedPrice() * o.getQuantity(),
                    product,
                    user
            );

            orderDetailDao.save(orderDetail);
        }
    }
}
