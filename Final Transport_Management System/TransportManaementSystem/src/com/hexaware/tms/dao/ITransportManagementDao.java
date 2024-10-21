package com.hexaware.tms.dao;

/*
 * @Author: Vedang Shinde
 * @Description:  ITransportManagementDao is an Interface.
 * Date: 21 October 2024
 */

import com.hexaware.tms.entity.Booking;
import com.hexaware.tms.entity.Driver;
import com.hexaware.tms.entity.Vehicle;
import com.hexaware.tms.exceptions.BookingNotFoundException;
import com.hexaware.tms.exceptions.VehicleNotFoundException;

import java.util.List;

public interface ITransportManagementDao {
	
	boolean addVehicle(Vehicle vehicle);
    boolean updateVehicle(Vehicle vehicle) throws VehicleNotFoundException;
    boolean deleteVehicle(int vehicleId) throws VehicleNotFoundException;
    boolean cancelTrip(int tripId);
    boolean cancelBooking(int bookingId) throws BookingNotFoundException;
    boolean allocateDriver(int tripId, int driverId);
    boolean deallocateDriver(int tripId);
    List<Booking> getBookingsByPassenger(int passengerId) throws BookingNotFoundException;
    List<Booking> getBookingsByTrip(int tripId) throws BookingNotFoundException;
    List<Driver> getAvailableDrivers();
	
	
}
