package dao;

import entity.Product;
import entity.User;
import exception.OrderNotFoundException;
import exception.UserNotFoundException;

import java.util.List;

public interface IOrderManagementRepository {

    void createUser(User user) throws Exception;

    void createProduct(User adminUser, Product product) throws Exception;

    void createOrder(User user, List<Product> productList) throws Exception;

    void cancelOrder(int userId, int orderId) throws UserNotFoundException, OrderNotFoundException, Exception;

    List<Product> getAllProducts() throws Exception;

    List<Product> getOrderByUser(User user) throws UserNotFoundException, Exception;
}
