package sk.upjs.veggieprofit.dao;

import sk.upjs.veggieprofit.entity.User;


public interface UserDao {
    User findByUsername (String username);
    User save (User user);
}
