package net.javaguides.ecommerce.dao;

import net.javaguides.ecommerce.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends CrudRepository<User,String> {
}
