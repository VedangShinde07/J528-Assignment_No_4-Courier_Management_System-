package com.hexaware.tms.entity;

import java.time.LocalDate;

public class Booking {
	
	private int bookingId;
	private int tripId;
	private int passengerId;
	private LocalDate bookingDate;
	private String status;
	
	public Booking() {
		super();
	}

	public Booking(int bookingId, int tripId, int passengerId, LocalDate bookingDate, String status) {
		this.bookingId = bookingId;
		this.tripId = tripId;
		this.passengerId = passengerId;
		this.bookingDate = bookingDate;
		this.status = status;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getTripId() {
		return tripId;
	}

	public void setTripId(int tripId) {
		this.tripId = tripId;
	}

	public int getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Bookings [bookingId=" + bookingId + ", tripId=" + tripId + ", passengerId=" + passengerId
				+ ", bookingDate=" + bookingDate + ", status=" + status + "]";
	}

}
