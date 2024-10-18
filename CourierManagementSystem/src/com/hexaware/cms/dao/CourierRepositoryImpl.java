package com.hexaware.cms.dao;

import com.hexaware.cms.entity.Courier;
import com.hexaware.cms.entity.Employee;
import com.hexaware.cms.exceptions.TrackingNumberNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;

public class CourierRepositoryImpl implements ICourierDao {

	private Connection conn = DBUtil.getDBConnection();

    public CourierRepositoryImpl() {
    }

	@Override
	public String placeOrder(Courier courierObj) {
		String insertCourier = "INSERT INTO Couriers (sender_name, sender_address, receiver_name, receiver_address, weight, status, tracking_number, delivery_date, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    String trackingNumber = null;

	    try {
	        PreparedStatement pstmt = this.conn.prepareStatement(insertCourier);
	        pstmt.setString(1, courierObj.getSenderName());
	        pstmt.setString(2, courierObj.getSenderAddress());
	        pstmt.setString(3, courierObj.getReceiverName());
	        pstmt.setString(4, courierObj.getReceiverAddress());
	        pstmt.setDouble(5, courierObj.getWeight());
	        pstmt.setString(6, courierObj.getStatus());
	        pstmt.setString(7, courierObj.getTrackingNumber());
	        
	        LocalDate localDate = courierObj.getDeliveryDate(); 
            pstmt.setDate(8, Date.valueOf(localDate));
	        
	        pstmt.setInt(9, courierObj.getUserId());
	        
	        int rowsAffected = pstmt.executeUpdate();
	        if (rowsAffected > 0) {
	            trackingNumber = courierObj.getTrackingNumber(); 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return trackingNumber; 
	}

	@Override
	public String getOrderStatus(String trackingNumber) {
		 String selectQuery = "SELECT status FROM Courier WHERE trackingNumber = ?";
		    String status = null;

		    try (PreparedStatement pstmt = this.conn.prepareStatement(selectQuery)) {
		        pstmt.setString(1, trackingNumber);
		        ResultSet rs = pstmt.executeQuery();

		        if (rs.next()) {
		            status = rs.getString("status"); // Get the status from the result set
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return status;
	}

	@Override
	public boolean cancelOrder(String trackingNumber) throws TrackingNumberNotFoundException {
		 String selectQuery = "SELECT CourierID FROM Courier WHERE trackingNumber = ?";
	        String deleteQuery = "DELETE FROM Courier WHERE trackingNumber = ?";
	        int courierId = -1;
	        boolean isDeleted = false;

	        try (PreparedStatement selectStmt = this.conn.prepareStatement(selectQuery)) {
	            // Set the tracking number in the select query
	            selectStmt.setString(1, trackingNumber);
	            ResultSet rs = selectStmt.executeQuery();

	            // Check if the tracking number exists
	            if (rs.next()) {
	                courierId = rs.getInt("CourierID"); // Get the courier ID
	            }

	            // If courierId is still -1, throw TrackingNumberNotFoundException
	            if (courierId == -1) {
	                throw new TrackingNumberNotFoundException("Tracking number " + trackingNumber + " not found.");
	            }

	            // If the tracking number exists, proceed with deletion
	            try (PreparedStatement deleteStmt = this.conn.prepareStatement(deleteQuery)) {
	                deleteStmt.setString(1, trackingNumber);
	                int rowsAffected = deleteStmt.executeUpdate();
	                isDeleted = (rowsAffected > 0); // Return true if deletion was successful
	            }

	        } catch (SQLException e) {
	            e.printStackTrace(); // Handle SQL exceptions here
	        }

	        return isDeleted;
	    }
	

	@Override
	public List<Courier> getAssignedOrders(int courierStaffId) {
		List<Courier> list = new ArrayList<>();
	    String selectAssignedOrders = "SELECT * FROM Courier WHERE CourierID = ?"; 

	    try {
	        PreparedStatement pstmt = this.conn.prepareStatement(selectAssignedOrders);
	        pstmt.setInt(1, courierStaffId);
	        ResultSet rs = pstmt.executeQuery();

	        while (rs.next()) {
	        	 int courierId = rs.getInt("CourierID");
	        	    String senderName = rs.getString("SenderName");
	        	    String senderAddress = rs.getString("SenderAddress");
	        	    String receiverName = rs.getString("ReceiverName");
	        	    String receiverAddress = rs.getString("ReceiverAddress");
	        	    double weight = rs.getDouble("Weight");
	        	    String status = rs.getString("Status");
	        	    String trackingNumber = rs.getString("TrackingNumber");
	        	    Date sqlDeliveryDate = rs.getDate("DeliveryDate");
	        	    LocalDate deliveryDate = sqlDeliveryDate.toLocalDate(); 
	        	    int userId = rs.getInt("UserID"); 

	        	    // Create a new Courier object
	        	    Courier courier = new Courier(courierId, senderName, senderAddress, receiverName, receiverAddress, weight, status, trackingNumber, deliveryDate, userId);
	        	    list.add(courier);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return list; 
	}

	@Override
	public int addCourierStaff(Employee obj) {
		int count = 0;
	    String insert = "INSERT INTO Employees (employeeId, name, email, contactNumber, role, salary) VALUES (?, ?, ?, ?, ?, ?)";

	    try {
	        PreparedStatement pstmt = this.conn.prepareStatement(insert);
	        pstmt.setInt(1, obj.getEmployeeId()); 
	        pstmt.setString(2, obj.getName()); 
	        pstmt.setString(3, obj.getEmail()); 
	        pstmt.setString(4, obj.getContactNumber()); 
	        pstmt.setString(5, obj.getRole()); 
	        pstmt.setDouble(6, obj.getSalary()); 
	        count = pstmt.executeUpdate();
	    } catch (SQLException var5) {
	        SQLException e = var5;
	        e.printStackTrace();
	    }

	    return count;
	}
	
}
	

