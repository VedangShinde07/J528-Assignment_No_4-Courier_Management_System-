package com.hexaware.cms.service;

import com.hexaware.cms.entity.Courier;
import com.hexaware.cms.entity.Employee;
import com.hexaware.cms.exceptions.TrackingNumberNotFoundException;

import java.util.List;

public interface CourierService {
	

	String placeOrder(Courier courierObj);
    
    String getOrderStatus(String trackingNumber);
    
    boolean cancelOrder(String trackingNumber) throws TrackingNumberNotFoundException;
    
    List<Courier> getAssignedOrders(int courierStaffId);
    
    int addCourierStaff(Employee obj);
}
