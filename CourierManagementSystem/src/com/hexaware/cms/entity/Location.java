package com.hexaware.cms.entity;

public class Location {
	
	private int locationId;
	private String locationName;
	private String address;
	
	
	public Location() {
		super();
	}


	public Location(int locationId, String locationName, String address) {
		super();
		this.locationId = locationId;
		this.locationName = locationName;
		this.address = address;
	}


	public int getLocationId() {
		return locationId;
	}


	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}


	public String getLocationName() {
		return locationName;
	}


	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "Location [locationId=" + locationId + ", locationName=" + locationName + ", address=" + address + "]";
	}
	
	
	

}
