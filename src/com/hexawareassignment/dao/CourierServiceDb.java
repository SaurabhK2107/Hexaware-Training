package com.hexawareassignment.dao;

import com.hexawareassignment.entity.Courier;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourierServiceDb {
    private final String url = "jdbc:mysql://localhost:3306/hexawareassignt1";
    private final String username = "root";
    private final String password = "root";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public List<Courier> getAllCouriers() {
        List<Courier> couriers = new ArrayList<>();
        String sql = "SELECT * FROM couriers";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Courier courier = new Courier();
                courier.setCourierId(rs.getInt("courierid"));
                courier.setSenderName(rs.getString("sendername"));
                courier.setSenderAddress(rs.getString("senderaddress"));
                courier.setReceiverName(rs.getString("recievername"));
                courier.setReceiverAddress(rs.getString("receiveraddress"));
                courier.setWeight(rs.getDouble("weight"));
                courier.setStatus(rs.getString("status"));
                courier.setTrackingNumber(rs.getString("trackingnumber"));
                courier.setDeliveryDate(rs.getDate("delieverydate"));

                couriers.add(courier);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return couriers;
    }

    public boolean addCourier(Courier courier) {
        String sql = "INSERT INTO couriers (sendername, senderaddress, recievername, receiveraddress, " +
                     "weight, status, trackingnumber, delieverydate) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, courier.getSenderName());
            stmt.setString(2, courier.getSenderAddress());
            stmt.setString(3, courier.getReceiverName());
            stmt.setString(4, courier.getReceiverAddress());
            stmt.setDouble(5, courier.getWeight());
            stmt.setString(6, courier.getStatus());
            stmt.setString(7, courier.getTrackingNumber());
            stmt.setDate(8, new java.sql.Date(courier.getDeliveryDate().getTime()));

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public boolean updateCourierStatus(String trackingNumber, String newStatus) {
        String sql = "UPDATE couriers SET status = ? WHERE trackingnumber = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newStatus);
            stmt.setString(2, trackingNumber);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error updating status: " + e.getMessage());
            return false;
        }
    }
    public Courier getCourierByTrackingNumber(String trackingNumber) {
        String sql = "SELECT * FROM couriers WHERE tracking_number = ?";
        Courier courier = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, trackingNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                courier = new Courier();
                courier.setCourierId(rs.getInt("courier_id"));
                courier.setSenderName(rs.getString("sender_name"));
                courier.setSenderAddress(rs.getString("sender_address"));
                courier.setReceiverName(rs.getString("receiver_name"));
                courier.setReceiverAddress(rs.getString("receiver_address"));
                courier.setWeight(rs.getDouble("weight"));
                courier.setStatus(rs.getString("status"));
                courier.setTrackingNumber(rs.getString("tracking_number"));
                courier.setDeliveryDate(rs.getDate("delivery_date"));
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving delivery history: " + e.getMessage());
        }

        return courier;
    }
    public void getShipmentStatusReport() {
        String sql = "SELECT status, COUNT(*) AS count FROM couriers GROUP BY status";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- Shipment Status Report ---");
            while (rs.next()) {
                System.out.println(rs.getString("status") + ": " + rs.getInt("count") + " parcels");
            }

        } catch (SQLException e) {
            System.out.println("Error generating status report: " + e.getMessage());
        }
    }
    public void getRevenueReport() {
        String sql = "SELECT SUM(amount) AS total_revenue FROM payment";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                System.out.println("\n--- Revenue Report ---");
                System.out.println("Total Revenue: ₹" + rs.getDouble("total_revenue"));
            }

        } catch (SQLException e) {
            System.out.println("Error generating revenue report: " + e.getMessage());
        }
    }



}
