package com.hexawareassignment.entity;
import java.util.Date;

public class Courier {
    private int courierId;
    private String senderName;
    private String senderAddress;
    private String receiverName;
    private String receiverAddress;
    private double weight;
    private String status;
    private String trackingNumber;
    private Date deliveryDate;

    public Courier() {}
    public Courier(int courierId, String senderName, String senderAddress,
                   String receiverName, String receiverAddress, double weight,
                   String status, String trackingNumber, Date deliveryDate) {
        this.courierId = courierId;
        this.senderName = senderName;
        this.senderAddress = senderAddress;
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
        this.weight = weight;
        this.status = status;
        this.trackingNumber = trackingNumber;
        this.deliveryDate = deliveryDate;
    }

    public int getCourierId() { return courierId; }
    public void setCourierId(int courierId) { this.courierId = courierId; }

    public String getSenderName() { return senderName; }
    public void setSenderName(String senderName) { this.senderName = senderName; }

    public String getSenderAddress() { return senderAddress; }
    public void setSenderAddress(String senderAddress) { this.senderAddress = senderAddress; }

    public String getReceiverName() { return receiverName; }
    public void setReceiverName(String receiverName) { this.receiverName = receiverName; }

    public String getReceiverAddress() { return receiverAddress; }
    public void setReceiverAddress(String receiverAddress) { this.receiverAddress = receiverAddress; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }

    public Date getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(Date deliveryDate) { this.deliveryDate = deliveryDate; }

    public String toString() {
        return "Courier [courierId=" + courierId + ", sender=" + senderName +
               ", receiver=" + receiverName + ", trackingNumber=" + trackingNumber + "]";
    }
}
