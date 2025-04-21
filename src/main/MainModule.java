package main;

import dao.OrderProcessor;
import dao.IOrderManagementRepository;
import entity.*;
import exception.*;

import java.util.*;

public class MainModule {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IOrderManagementRepository repo = new OrderProcessor();

        while (true) {
            System.out.println("\n===== Order Management System =====");
            System.out.println("1. Create User");
            System.out.println("2. Create Product");
            System.out.println("3. Place Order");
            System.out.println("4. Cancel Order");
            System.out.println("5. Get All Products");
            System.out.println("6. Get Orders By User");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1: // Create User
                        System.out.print("Enter User ID: ");
                        int userId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Username: ");
                        String username = sc.nextLine();
                        System.out.print("Enter Password: ");
                        String password = sc.nextLine();
                        System.out.print("Enter Role (Admin/User): ");
                        String role = sc.nextLine();

                        User user = new User(userId, username, password, role);
                        repo.createUser(user);
                        break;

                    case 2: // Create Product
                        System.out.print("Enter Admin User ID: ");
                        int adminId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Product ID: ");
                        int pid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Product Name: ");
                        String pname = sc.nextLine();
                        System.out.print("Enter Description: ");
                        String desc = sc.nextLine();
                        System.out.print("Enter Price: ");
                        double price = sc.nextDouble();
                        System.out.print("Enter Quantity: ");
                        int qty = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Type (Electronics/Clothing): ");
                        String type = sc.nextLine();

                        Product product = null;
                        if ("Electronics".equalsIgnoreCase(type)) {
                            System.out.print("Enter Brand: ");
                            String brand = sc.nextLine();
                            System.out.print("Enter Warranty (in months): ");
                            int warranty = sc.nextInt();
                            product = new Electronics(pid, pname, desc, price, qty, brand, warranty);
                        } else if ("Clothing".equalsIgnoreCase(type)) {
                            System.out.print("Enter Size: ");
                            String size = sc.nextLine();
                            System.out.print("Enter Color: ");
                            String color = sc.nextLine();
                            product = new Clothing(pid, pname, desc, price, qty, size, color);
                        }

                        User admin = new User(adminId, "", "", "Admin"); // dummy user to check ID
                        repo.createProduct(admin, product);
                        break;

                    case 3: // Place Order
                        System.out.print("Enter User ID: ");
                        int ordUserId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Username: ");
                        String ordUsername = sc.nextLine();
                        System.out.print("Enter Password: ");
                        String ordPassword = sc.nextLine();
                        System.out.print("Enter Role: ");
                        String ordRole = sc.nextLine();

                        User orderingUser = new User(ordUserId, ordUsername, ordPassword, ordRole);
                        List<Product> cart = new ArrayList<>();

                        while (true) {
                            System.out.print("Enter Product ID to order (or -1 to finish): ");
                            int orderPid = sc.nextInt();
                            if (orderPid == -1) break;

                            // create a dummy product with only ID (DB will resolve)
                            Product dummy = new Product();
                            dummy.setProductId(orderPid);
                            cart.add(dummy);
                        }

                        repo.createOrder(orderingUser, cart);
                        break;

                    case 4: // Cancel Order
                        System.out.print("Enter User ID: ");
                        int cancelUid = sc.nextInt();
                        System.out.print("Enter Order ID: ");
                        int cancelOid = sc.nextInt();
                        repo.cancelOrder(cancelUid, cancelOid);
                        break;

                    case 5: // Get All Products
                        List<Product> all = repo.getAllProducts();
                        for (Product p : all) {
                            System.out.println(p.getProductId() + " - " + p.getProductName() + " - " + p.getType());
                        }
                        break;

                    case 6: // Get Orders by User
                        System.out.print("Enter User ID: ");
                        int ouid = sc.nextInt();
                        sc.nextLine();
                        User orderUser = new User();
                        orderUser.setUserId(ouid);

                        List<Product> orders = repo.getOrderByUser(orderUser);
                        if (orders.isEmpty()) {
                            System.out.println("No orders found.");
                        } else {
                            for (Product p : orders) {
                                System.out.println(p.getProductName() + " - " + p.getType());
                            }
                        }
                        break;

                    case 7: // Exit
                        System.out.println("Exiting system.");
                        sc.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (Exception e) {
                System.out.println("⚠️ Error: " + e.getMessage());
            }
        }
    }
}
