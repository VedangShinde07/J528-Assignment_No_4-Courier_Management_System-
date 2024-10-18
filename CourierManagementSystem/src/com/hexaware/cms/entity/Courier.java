package com.hexaware.cms.entity;

import java.time.LocalDate;

public class Courier {
	
	private int courierId;
	private String senderName;
	private String senderAddress;
	private String receiverName;
	private String receiverAddress;
	private double weight;
	private String status;
	private String trackingNumber;
	private LocalDate deliveryDate;
	private int userId;
	
	
	public Courier() {
		super();
	}


	public Courier(int courierId, String senderName, String senderAddress, String receiverName, String receiverAddress,
			double weight, String status, String trackingNumber, LocalDate deliveryDate, int userId) {
		super();
		this.courierId = courierId;
		this.senderName = senderName;
		this.senderAddress = senderAddress;
		this.receiverName = receiverName;
		this.receiverAddress = receiverAddress;
		this.weight = weight;
		this.status = status;
		this.trackingNumber = trackingNumber;
		this.deliveryDate = deliveryDate;
		this.userId = userId;
	}


	public int getCourierId() {
		return courierId;
	}


	public void setCourierId(int courierId) {
		this.courierId = courierId;
	}


	public String getSenderName() {
		return senderName;
	}


	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}


	public String getSenderAddress() {
		return senderAddress;
	}


	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}


	public String getReceiverName() {
		return receiverName;
	}


	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}


	public String getReceiverAddress() {
		return receiverAddress;
	}


	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}


	public double getWeight() {
		return weight;
	}


	public void setWeight(double weight) {
		this.weight = weight;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getTrackingNumber() {
		return trackingNumber;
	}


	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}


	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}


	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "Courier [courierId=" + courierId + ", senderName=" + senderName + ", senderAddress=" + senderAddress
				+ ", receiverName=" + receiverName + ", receiverAddress=" + receiverAddress + ", weight=" + weight
				+ ", status=" + status + ", trackingNumber=" + trackingNumber + ", deliveryDate=" + deliveryDate
				+ ", userId=" + userId + "]";
	}

}
