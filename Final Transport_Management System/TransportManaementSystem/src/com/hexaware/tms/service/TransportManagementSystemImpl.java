package com.hexaware.tms.service;

/*
 * @Author: Vedang Shinde
 * @Description: TransportManagementSystemImpl is the implementation of TransportManagementService.
 * Date: 21 October 2024
 */

import com.hexaware.tms.dao.ITransportManagementDao;

import com.hexaware.tms.dao.TransportManagementImpl;
import com.hexaware.tms.entity.Booking;
import com.hexaware.tms.entity.Driver;
import com.hexaware.tms.entity.Vehicle;
import com.hexaware.tms.exceptions.BookingNotFoundException;
import com.hexaware.tms.exceptions.VehicleNotFoundException;

import java.util.List;

public class TransportManagementSystemImpl implements TransportManagementService{

	private ITransportManagementDao transportManagementDao = new TransportManagementImpl();


	public boolean addVehicle(Vehicle vehicle) {
		return this.transportManagementDao.addVehicle(vehicle);
	}


	public boolean updateVehicle(Vehicle vehicle) throws VehicleNotFoundException {
		return this.transportManagementDao.updateVehicle(vehicle);
	}


	public boolean deleteVehicle(int vehicleId) throws VehicleNotFoundException {
		 return this.transportManagementDao.deleteVehicle(vehicleId);
	}


	public boolean cancelTrip(int tripId) {
		return this.transportManagementDao.cancelTrip(tripId);
	}

	
	public boolean cancelBooking(int bookingId) throws BookingNotFoundException {
		return this.transportManagementDao.cancelBooking(bookingId);
	}

	
	public boolean allocateDriver(int tripId, int driverId) {
		return this.transportManagementDao.allocateDriver(tripId, driverId);
	}

	public boolean deallocateDriver(int tripId) {
		 return this.transportManagementDao.deallocateDriver(tripId);
	}


	public List<Booking> getBookingsByPassenger(int passengerId) throws BookingNotFoundException {
		 return this.transportManagementDao.getBookingsByPassenger(passengerId);
	}

	public List<Booking> getBookingsByTrip(int tripId) throws BookingNotFoundException {
		 return this.transportManagementDao.getBookingsByTrip(tripId);
	}


	public List<Driver> getAvailableDrivers() {
		return this.transportManagementDao.getAvailableDrivers();
	}

}
