package com.hexawareassignment.main;

import com.hexawareassignment.dao.CourierServiceDb;
import com.hexawareassignment.entity.Courier;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainModule {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CourierServiceDb courierService = new CourierServiceDb();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        while (true) {
            System.out.println("\n--- Courier Management System ---");
            System.out.println("1. Add New Courier");
            System.out.println("2. View All Couriers");
            System.out.println("3. Update Courier Status");
            System.out.println("4. View Delivery History (by Tracking Number)");
            System.out.println("5. Shipment Status Report");
            System.out.println("6. Revenue Report");

            System.out.println("7. Exit");
            System.out.print("Choose option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1:
                    try {
                        Courier courier = new Courier();
                        System.out.print("Enter Sender Name: ");
                        courier.setSenderName(scanner.nextLine());

                        System.out.print("Enter Sender Address: ");
                        courier.setSenderAddress(scanner.nextLine());

                        System.out.print("Enter Receiver Name: ");
                        courier.setReceiverName(scanner.nextLine());

                        System.out.print("Enter Receiver Address: ");
                        courier.setReceiverAddress(scanner.nextLine());

                        System.out.print("Enter Weight: ");
                        courier.setWeight(scanner.nextDouble());
                        scanner.nextLine();

                        System.out.print("Enter Status (e.g., Shipped/Delivered): ");
                        courier.setStatus(scanner.nextLine());

                        System.out.print("Enter Tracking Number: ");
                        courier.setTrackingNumber(scanner.nextLine());

                        System.out.print("Enter Delivery Date (yyyy-MM-dd): ");
                        String dateStr = scanner.nextLine();
                        Date deliveryDate = sdf.parse(dateStr);
                        courier.setDeliveryDate(deliveryDate);

                        boolean success = courierService.addCourier(courier);
                        if (success) {
                            System.out.println("Courier added successfully!");
                        } else {
                            System.out.println("Failed to add courier.");
                        }

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    List<Courier> couriers = courierService.getAllCouriers();
                    System.out.println("\n--- Courier List ---");
                    for (Courier c : couriers) {
                        System.out.println(c);
                    }
                    break;
                   
                case 3:
                    System.out.print("Enter Tracking Number: ");
                    String trackNo = scanner.nextLine();

                    System.out.print("Enter New Status: ");
                    String newStatus = scanner.nextLine();

                    boolean updated = courierService.updateCourierStatus(trackNo, newStatus);
                    if (updated) {
                        System.out.println("Status updated successfully.");
                    } else {
                        System.out.println("Status update failed.");
                    }
                    break;
                    
                case 4:
                    System.out.print("Enter Tracking Number: ");
                    String tracking = scanner.nextLine();
                    Courier result = courierService.getCourierByTrackingNumber(tracking);

                    if (result != null) {
                        System.out.println("Delivery Details:\n" + result);
                    } else {
                        System.out.println("No record found for that tracking number.");
                    }
                    break;
                    
                case 5:
                    courierService.getShipmentStatusReport();
                    break;

                case 6:
                    courierService.getRevenueReport();
                    break;
                   
                case 7:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
