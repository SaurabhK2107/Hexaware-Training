//Task 6
package com.hexawareassignment.dao;

import com.hexawareassignment.entity.Courier;
import java.util.List;

public interface ICourierUserService {

    
    String placeOrder(Courier courierObj);

    
    String getOrderStatus(String trackingNumber);

    
    boolean cancelOrder(String trackingNumber);

    
    List<Courier> getAssignedOrder(int courierStaffId);
}

