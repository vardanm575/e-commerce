package net.javaguides.ecommerce.dao;

import net.javaguides.ecommerce.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends CrudRepository<Product,Integer> {
    public List<Product> findAll(Pageable pageable);

}
