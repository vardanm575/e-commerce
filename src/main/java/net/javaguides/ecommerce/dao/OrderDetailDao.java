package net.javaguides.ecommerce.dao;

import net.javaguides.ecommerce.model.OrderDetail;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailDao extends CrudRepository<OrderDetail,Integer> {


}
