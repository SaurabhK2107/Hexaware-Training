//Task 5
package com.hexawareassignment.entity;

public class User {
    private int userId;
    private String userName;
    private String email;
    private String password;
    private String contactNumber;
    private String address;

    public User() {}

    public User(int userId, String userName, String email, String password, String contactNumber, String address) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + ", email=" + email +
                ", contactNumber=" + contactNumber + ", address=" + address + "]";
    }
}
