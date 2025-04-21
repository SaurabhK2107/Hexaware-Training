//task 8
package com.hexawareassignment.dao;

import com.hexawareassignment.entity.Courier;
import com.hexawareassignment.entity.CourierCompanyCollection;

import java.util.ArrayList;
import java.util.List;

public class CourierUserServiceCollectionImpl implements ICourierUserService {
    private static int trackingCounter = 1000;
    protected CourierCompanyCollection companyObj;

    public CourierUserServiceCollectionImpl(CourierCompanyCollection companyObj) {
        this.companyObj = companyObj;
    }

    public String placeOrder(Courier courierObj) {
        String trackingNumber = "TRK" + (++trackingCounter);
        courierObj.setTrackingNumber(trackingNumber);
        companyObj.getCourierDetails().add(courierObj);
        return trackingNumber;
    }

    public String getOrderStatus(String trackingNumber) {
        for (Courier c : companyObj.getCourierDetails()) {
            if (c.getTrackingNumber().equals(trackingNumber)) {
                return c.getStatus();
            }
        }
        return "Tracking number not found.";
    }

    public boolean cancelOrder(String trackingNumber) {
        for (Courier c : companyObj.getCourierDetails()) {
            if (c.getTrackingNumber().equals(trackingNumber)) {
                c.setStatus("Cancelled");
                return true;
            }
        }
        return false;
    }

    public List<Courier> getAssignedOrder(int courierStaffId) {
        // Optional logic for mapping couriers to staff
        return new ArrayList<>();
    }
}
