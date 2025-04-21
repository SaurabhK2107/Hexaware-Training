create database ordermgmt;
use ordermgmt;
CREATE TABLE users (
    userId INT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(10) CHECK (role IN ('Admin', 'User'))
);

CREATE TABLE products (
    productId INT PRIMARY KEY,
    productName VARCHAR(100),
    description TEXT,
    price DOUBLE,
    quantityInStock INT,
    type VARCHAR(20) CHECK (type IN ('Electronics', 'Clothing')),

    brand VARCHAR(50),
    warrantyPeriod INT,

    size VARCHAR(10),
    color VARCHAR(30)
);
CREATE TABLE orders (
    orderId INT AUTO_INCREMENT PRIMARY KEY,
    userId INT,
    orderDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (userId) REFERENCES users(userId)
);
CREATE TABLE order_items (
    orderItemId INT AUTO_INCREMENT PRIMARY KEY,
    orderId INT,
    productId INT,
    quantity INT DEFAULT 1,
    FOREIGN KEY (orderId) REFERENCES orders(orderId),
    FOREIGN KEY (productId) REFERENCES products(productId)
);
