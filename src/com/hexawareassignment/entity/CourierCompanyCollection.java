//Task 8
package com.hexawareassignment.entity;

import java.util.ArrayList;

public class CourierCompanyCollection {
    private String companyName;
    private ArrayList<Courier> courierDetails;
    private ArrayList<Employee> employeeDetails;
    private ArrayList<Location> locationDetails;

    public CourierCompanyCollection() {
        this.courierDetails = new ArrayList<>();
        this.employeeDetails = new ArrayList<>();
        this.locationDetails = new ArrayList<>();
    }

    public CourierCompanyCollection(String companyName) {
        this();
        this.companyName = companyName;
    }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public ArrayList<Courier> getCourierDetails() { return courierDetails; }
    public void setCourierDetails(ArrayList<Courier> courierDetails) { this.courierDetails = courierDetails; }

    public ArrayList<Employee> getEmployeeDetails() { return employeeDetails; }
    public void setEmployeeDetails(ArrayList<Employee> employeeDetails) { this.employeeDetails = employeeDetails; }

    public ArrayList<Location> getLocationDetails() { return locationDetails; }
    public void setLocationDetails(ArrayList<Location> locationDetails) { this.locationDetails = locationDetails; }

    public String toString() {
        return "CourierCompanyCollection [companyName=" + companyName +
                ", totalCouriers=" + courierDetails.size() +
                ", totalEmployees=" + employeeDetails.size() +
                ", totalLocations=" + locationDetails.size() + "]";
    }
}
