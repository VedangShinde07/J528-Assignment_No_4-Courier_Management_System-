package com.hexaware.tms.test;

/*
 * @Author: Vedang Shinde
 * @Description: TransportTest Test File.
 * Date: 21 October 2024
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.hexaware.tms.service.TransportManagementSystemImpl;
import com.hexaware.tms.service.TransportManagementService;
import com.hexaware.tms.entity.Vehicle;
import com.hexaware.tms.exceptions.BookingNotFoundException;
import com.hexaware.tms.exceptions.VehicleNotFoundException;


class TransportTests {

	private static TransportManagementService transportService;

    @BeforeAll
    public static void beforeAll() {
        transportService = new TransportManagementSystemImpl(); 
    }
    
    
    @Test
    void testAddVehicle() {
        // Create a new vehicle instance with required details
        Vehicle vehicle = new Vehicle(0, "Toyota Corolla", 5.0, "Sedan", "Available");

        // Call the service method to add the vehicle
        boolean isAdded = transportService.addVehicle(vehicle);

        // Assert that the vehicle was successfully added
        assertTrue(isAdded);
    }
    	
    
    
    
    @Test
    void testBookingNotFoundException() {
        int nonExistingBookingId = 999;  
        
       
        BookingNotFoundException exception = assertThrows(BookingNotFoundException.class, () -> {
            transportService.cancelBooking(nonExistingBookingId);  
        });
        
        assertEquals("Booking Not Found", exception.getMessage());
    }
    
    @Test
    void testVehicleNotFoundException() {
        int nonExistingVehicleId = 999;

        VehicleNotFoundException deleteException = assertThrows(VehicleNotFoundException.class, () -> {
            transportService.deleteVehicle(nonExistingVehicleId);
        });
        assertEquals("Vehicle Not Found", deleteException.getMessage());

        Vehicle vehicle = new Vehicle(nonExistingVehicleId, "New Model", 100.0, "Truck", "Available");
        VehicleNotFoundException updateException = assertThrows(VehicleNotFoundException.class, () -> {
            transportService.updateVehicle(vehicle); 
        });
        assertEquals("Vehicle Not Found", updateException.getMessage());
    }
    
    
}