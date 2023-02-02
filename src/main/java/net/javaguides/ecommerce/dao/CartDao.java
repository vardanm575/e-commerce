package net.javaguides.ecommerce.dao;

import net.javaguides.ecommerce.model.Cart;
import net.javaguides.ecommerce.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDao extends CrudRepository<Cart, Integer> {
    public List<Cart> findByUser(User user);
}
