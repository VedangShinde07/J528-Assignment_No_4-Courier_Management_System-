package com.hexaware.tms.entity;

import java.time.LocalDate;

public class Trips {
	
	private int tripsId;
	private int vehicleId;
	private int routeId;
	private LocalDate departureDate;
	private LocalDate arrivalDate;
	private String status;
	private String tripType;
	private int maxPassengers;
	
	public Trips() {
		super();
	}

	public Trips(int tripsId, int vehicleId, int routeId, LocalDate departureDate, LocalDate arrivalDate, String status,
			String tripType, int maxPassengers) {
		super();
		this.tripsId = tripsId;
		this.vehicleId = vehicleId;
		this.routeId = routeId;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.status = status;
		this.tripType = tripType;
		this.maxPassengers = maxPassengers;
	}

	public int getTripsId() {
		return tripsId;
	}

	public void setTripsId(int tripsId) {
		this.tripsId = tripsId;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTripType() {
		return tripType;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}

	public int getMaxPassengers() {
		return maxPassengers;
	}

	public void setMaxPassengers(int maxPassengers) {
		this.maxPassengers = maxPassengers;
	}
	

	@Override
	public String toString() {
		return "Trips [tripsId=" + tripsId + ", vehicleId=" + vehicleId + ", routeId=" + routeId + ", departureDate="
				+ departureDate + ", arrivalDate=" + arrivalDate + ", status=" + status + ", tripType=" + tripType
				+ ", maxPassengers=" + maxPassengers + "]";
	}

	
	

}
