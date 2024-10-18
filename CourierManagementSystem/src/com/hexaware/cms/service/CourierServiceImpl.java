package com.hexaware.cms.service;

import com.hexaware.cms.dao.CourierRepositoryImpl;
import com.hexaware.cms.dao.ICourierDao;
import com.hexaware.cms.entity.Courier;
import com.hexaware.cms.entity.Employee;
import com.hexaware.cms.exceptions.TrackingNumberNotFoundException;

import java.util.List;

public class CourierServiceImpl implements CourierService {
	
	private ICourierDao courierDao = new CourierRepositoryImpl();

    public CourierServiceImpl() {
    }

	@Override
	public String placeOrder(Courier courierObj) {
		return courierDao.placeOrder(courierObj);
	}

	@Override
	public String getOrderStatus(String trackingNumber) {
		String orderStatus = courierDao.getOrderStatus(trackingNumber); // Ensure this method fetches the status correctly
	    return orderStatus != null ? orderStatus : "Order not found";
	}

	@Override
	public boolean cancelOrder(String trackingNumber) throws TrackingNumberNotFoundException {
		return courierDao.cancelOrder(trackingNumber);
	}

	@Override
	public List<Courier> getAssignedOrders(int courierStaffId) {
		 return courierDao.getAssignedOrders(courierStaffId);
	}

	@Override
	public int addCourierStaff(Employee obj) {
		return courierDao.addCourierStaff(obj);
	}

	


}
