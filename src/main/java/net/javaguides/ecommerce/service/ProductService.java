package net.javaguides.ecommerce.service;

import net.javaguides.ecommerce.config.JwtRequestFilter;
import net.javaguides.ecommerce.dao.CartDao;
import net.javaguides.ecommerce.dao.ProductDao;
import net.javaguides.ecommerce.dao.UserDao;
import net.javaguides.ecommerce.model.Cart;
import net.javaguides.ecommerce.model.Product;
import net.javaguides.ecommerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductDao productDao;

    @Autowired
    UserDao userDao;
    
    @Autowired
    CartDao cartDao;


    public Product addNewProduct(Product product){
        return productDao.save(product);
    }

    public List<Product> getAllProducts(int pageNumber, String searchKey){
        Pageable pageable = PageRequest.of(pageNumber, 10);
        if (searchKey.equals("")){
            return (List<Product>) productDao.findAll(pageable);
        }else{
            return (List<Product>) productDao.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(searchKey, searchKey, pageable);
        }
    }

    public void deleteProductDetails(Integer productId) {

        productDao.deleteById(productId);
    }

    public Product getProductDetailsById(Integer productId) {
        return productDao.findById(productId).get();
    }

    public List<Product> getProductDetails(boolean isSingleProductCheckout, Integer productId){
        if(isSingleProductCheckout && productId !=0){

            List<Product> list = new ArrayList<>();
            Product product = productDao.findById(productId).get();
            list.add(product);
            return list;

        } else {

            String currentUser = JwtRequestFilter.CURRENT_USER;
            User user = userDao.findById(currentUser).get();
            List<Cart> carts = cartDao.findByUser(user);

            return carts.stream().map(x -> x.getProduct()).collect(Collectors.toList());

        }
    }
}
