//Task 5
package com.hexawareassignment.entity;

public class Employee {
    private int employeeId;
    private String employeeName;
    private String email;
    private String contactNumber;
    private String role;
    private double salary;

    public Employee() {}

    public Employee(int employeeId, String employeeName, String email, String contactNumber, String role, double salary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.role = role;
        this.salary = salary;
    }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String toString() {
        return "Employee [employeeId=" + employeeId + ", name=" + employeeName + ", role=" + role + "]";
    }
}
