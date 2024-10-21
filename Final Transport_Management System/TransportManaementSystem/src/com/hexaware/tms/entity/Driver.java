package com.hexaware.tms.entity;

public class Driver {
	
	private int driverId;
    private String driverName;
    private String contactNumber;
    private String email;
    private boolean isAvailable;
    
	public Driver() {
		super();
	}


	public Driver(int driverId, String driverName, String contactNumber, String email, boolean isAvailable) {
		super();
		this.driverId = driverId;
		this.driverName = driverName;
		this.contactNumber = contactNumber;
		this.email = email;
		this.isAvailable = isAvailable;
	}
	

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}


	public String getContactNumber() {
		return contactNumber;
	}


	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public String toString() {
		return "Driver [driverId=" + driverId + ", driverName=" + driverName + ", contactNumber=" + contactNumber
				+ ", email=" + email + ", isAvailable=" + isAvailable + "]";
	}

	



	
	
    
    

}
