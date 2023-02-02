package net.javaguides.ecommerce.service;

import net.javaguides.ecommerce.config.JwtRequestFilter;
import net.javaguides.ecommerce.dao.CartDao;
import net.javaguides.ecommerce.dao.ProductDao;
import net.javaguides.ecommerce.dao.UserDao;
import net.javaguides.ecommerce.exception.ProductNotFoundException;
import net.javaguides.ecommerce.exception.UserNotFoundException;
import net.javaguides.ecommerce.model.Cart;
import net.javaguides.ecommerce.model.Product;
import net.javaguides.ecommerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    public Cart addToCart(Integer productId) {
        try {
            Product product = productDao.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException("Product with id " + productId + " not found"));
            String currentUser = JwtRequestFilter.CURRENT_USER;
            User user = userDao.findById(currentUser)
                    .orElseThrow(() -> new UserNotFoundException("User with id " + currentUser + " not found"));
            Cart cart = Cart.builder()
                    .product(product)
                    .user(user)
                    .build();
            return cartDao.save(cart);
        } catch (ProductNotFoundException | UserNotFoundException e) {
            throw e;
        }
    }


    public List<Cart> getCartDetails() {
        String username = JwtRequestFilter.CURRENT_USER;
        User user = userDao.findById(username)
                .orElseThrow(() -> new UserNotFoundException("User with id " + username + " not found"));
        return cartDao.findByUser(user);
    }
}
