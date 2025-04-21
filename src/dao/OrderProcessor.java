package dao;

import entity.*;
import exception.*;
import util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderProcessor implements IOrderManagementRepository {

    public void createUser(User user) throws Exception {
        Connection conn = DBConnUtil.getDBConn();
        String query = "INSERT INTO users (userId, username, password, role) VALUES (?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, user.getUserId());
        stmt.setString(2, user.getUsername());
        stmt.setString(3, user.getPassword());
        stmt.setString(4, user.getRole());

        int rows = stmt.executeUpdate();
        if (rows > 0) {
            System.out.println("User created successfully.");
        } else {
            System.out.println("User creation failed.");
        }

        stmt.close();
        conn.close();
    }

    public List<Product> getAllProducts() throws Exception {
        List<Product> productList = new ArrayList<>();
        Connection conn = DBConnUtil.getDBConn();
        String query = "SELECT * FROM products";

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            String type = rs.getString("type");

            if ("Electronics".equalsIgnoreCase(type)) {
                Electronics e = new Electronics(
                    rs.getInt("productId"),
                    rs.getString("productName"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getInt("quantityInStock"),
                    rs.getString("brand"),
                    rs.getInt("warrantyPeriod")
                );
                productList.add(e);
            } else if ("Clothing".equalsIgnoreCase(type)) {
                Clothing c = new Clothing(
                    rs.getInt("productId"),
                    rs.getString("productName"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getInt("quantityInStock"),
                    rs.getString("size"),
                    rs.getString("color")
                );
                productList.add(c);
            }
        }

        rs.close();
        stmt.close();
        conn.close();

        return productList;
    }

    public void createProduct(User adminUser, Product product) throws Exception {
        Connection conn = DBConnUtil.getDBConn();

        String checkUserQuery = "SELECT role FROM users WHERE userId = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkUserQuery);
        checkStmt.setInt(1, adminUser.getUserId());
        ResultSet rs = checkStmt.executeQuery();

        if (!rs.next()) {
            throw new UserNotFoundException("Admin user not found with ID: " + adminUser.getUserId());
        }

        String role = rs.getString("role");
        if (!"Admin".equalsIgnoreCase(role)) {
            throw new InvalidRoleException("Only admin users can create products.");
        }

        String insertQuery = "INSERT INTO products (productId, productName, description, price, quantityInStock, type, brand, warrantyPeriod, size, color) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        stmt.setInt(1, product.getProductId());
        stmt.setString(2, product.getProductName());
        stmt.setString(3, product.getDescription());
        stmt.setDouble(4, product.getPrice());
        stmt.setInt(5, product.getQuantityInStock());
        stmt.setString(6, product.getType());

        if (product instanceof Electronics) {
            Electronics e = (Electronics) product;
            stmt.setString(7, e.getBrand());
            stmt.setInt(8, e.getWarrantyPeriod());
            stmt.setNull(9, Types.VARCHAR);
            stmt.setNull(10, Types.VARCHAR);
        } else if (product instanceof Clothing) {
            Clothing c = (Clothing) product;
            stmt.setNull(7, Types.VARCHAR);
            stmt.setNull(8, Types.INTEGER);
            stmt.setString(9, c.getSize());
            stmt.setString(10, c.getColor());
        } else {
            stmt.setNull(7, Types.VARCHAR);
            stmt.setNull(8, Types.INTEGER);
            stmt.setNull(9, Types.VARCHAR);
            stmt.setNull(10, Types.VARCHAR);
        }

        int rows = stmt.executeUpdate();
        if (rows > 0) {
            System.out.println("Product created successfully.");
        } else {
            System.out.println("Failed to create product.");
        }

        rs.close();
        checkStmt.close();
        stmt.close();
        conn.close();
    }

    public void createOrder(User user, List<Product> productList) throws Exception {
        Connection conn = DBConnUtil.getDBConn();
        conn.setAutoCommit(false);

        try {
            // Check if user exists
            String checkUser = "SELECT userId FROM users WHERE userId = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkUser);
            checkStmt.setInt(1, user.getUserId());
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                String insertUser = "INSERT INTO users (userId, username, password, role) VALUES (?, ?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertUser);
                insertStmt.setInt(1, user.getUserId());
                insertStmt.setString(2, user.getUsername());
                insertStmt.setString(3, user.getPassword());
                insertStmt.setString(4, user.getRole());
                insertStmt.executeUpdate();
                insertStmt.close();
            }
            checkStmt.close();
            rs.close();

            // Create order
            String insertOrder = "INSERT INTO orders (userId, orderDate) VALUES (?, NOW())";
            PreparedStatement orderStmt = conn.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS);
            orderStmt.setInt(1, user.getUserId());
            orderStmt.executeUpdate();

            ResultSet orderKeys = orderStmt.getGeneratedKeys();
            int orderId = -1;
            if (orderKeys.next()) {
                orderId = orderKeys.getInt(1);
            }
            orderStmt.close();
            orderKeys.close();

            // Insert items
            String insertItem = "INSERT INTO order_items (orderId, productId, quantity) VALUES (?, ?, ?)";
            PreparedStatement itemStmt = conn.prepareStatement(insertItem);

            for (Product p : productList) {
                itemStmt.setInt(1, orderId);
                itemStmt.setInt(2, p.getProductId());
                itemStmt.setInt(3, 1);
                itemStmt.addBatch();

                String updateStock = "UPDATE products SET quantityInStock = quantityInStock - 1 WHERE productId = ?";
                PreparedStatement stockStmt = conn.prepareStatement(updateStock);
                stockStmt.setInt(1, p.getProductId());
                stockStmt.executeUpdate();
                stockStmt.close();
            }

            itemStmt.executeBatch();
            itemStmt.close();

            conn.commit();
            System.out.println("Order placed successfully with order ID: " + orderId);

        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }

    public void cancelOrder(int userId, int orderId) throws UserNotFoundException, OrderNotFoundException, Exception {

        Connection conn = null;

        try {
            conn = DBConnUtil.getDBConn();

            String checkUser = "SELECT * FROM users WHERE userId = ?";
            PreparedStatement userStmt = conn.prepareStatement(checkUser);
            userStmt.setInt(1, userId);
            ResultSet userRS = userStmt.executeQuery();

            if (!userRS.next()) {
                throw new UserNotFoundException("User ID " + userId + " not found.");
            }

            String checkOrder = "SELECT * FROM orders WHERE orderId = ? AND userId = ?";
            PreparedStatement orderStmt = conn.prepareStatement(checkOrder);
            orderStmt.setInt(1, orderId);
            orderStmt.setInt(2, userId);
            ResultSet orderRS = orderStmt.executeQuery();

            if (!orderRS.next()) {
                throw new OrderNotFoundException("Order ID " + orderId + " not found for User ID " + userId);
            }

            String deleteItems = "DELETE FROM order_items WHERE orderId = ?";
            PreparedStatement delItemsStmt = conn.prepareStatement(deleteItems);
            delItemsStmt.setInt(1, orderId);
            delItemsStmt.executeUpdate();
            delItemsStmt.close();

            String deleteOrder = "DELETE FROM orders WHERE orderId = ?";
            PreparedStatement delOrderStmt = conn.prepareStatement(deleteOrder);
            delOrderStmt.setInt(1, orderId);
            delOrderStmt.executeUpdate();
            delOrderStmt.close();

        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    public List<Product> getOrderByUser(User user) throws UserNotFoundException, Exception {
        List<Product> userProducts = new ArrayList<>();
        Connection conn = DBConnUtil.getDBConn();

        String checkUser = "SELECT * FROM users WHERE userId = ?";
        PreparedStatement userStmt = conn.prepareStatement(checkUser);
        userStmt.setInt(1, user.getUserId());
        ResultSet userRs = userStmt.executeQuery();

        if (!userRs.next()) {
            throw new UserNotFoundException("User ID " + user.getUserId() + " not found.");
        }

        String query = "SELECT p.* FROM products p " +
                       "JOIN order_items oi ON p.productId = oi.productId " +
                       "JOIN orders o ON o.orderId = oi.orderId " +
                       "WHERE o.userId = ?";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, user.getUserId());
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String type = rs.getString("type");

            if ("Electronics".equalsIgnoreCase(type)) {
                Electronics e = new Electronics(
                    rs.getInt("productId"),
                    rs.getString("productName"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getInt("quantityInStock"),
                    rs.getString("brand"),
                    rs.getInt("warrantyPeriod")
                );
                userProducts.add(e);
            } else if ("Clothing".equalsIgnoreCase(type)) {
                Clothing c = new Clothing(
                    rs.getInt("productId"),
                    rs.getString("productName"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getInt("quantityInStock"),
                    rs.getString("size"),
                    rs.getString("color")
                );
                userProducts.add(c);
            }
        }

        rs.close();
        stmt.close();
        userRs.close();
        userStmt.close();
        conn.close();

        return userProducts;
    }
}
