package com.hexaware.tms.presentation;

/*
 * @Author: Vedang Shinde
 * @Description: TransportManagementApp main presentation.
 * Date: 21 October 2024
 */

import com.hexaware.tms.util.*;
import com.hexaware.tms.entity.Booking;
import com.hexaware.tms.entity.Driver;
import com.hexaware.tms.entity.Vehicle;
import com.hexaware.tms.exceptions.*;
import com.hexaware.tms.service.TransportManagementService;
import com.hexaware.tms.service.TransportManagementSystemImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class TransportManagementApp {

	 static Scanner scanner;

	    static {
	        scanner = new Scanner(System.in);
	    }
	    private static Connection conn = DBUtil.getConnection();

	    public TransportManagementApp() {
	    }

	
	public static void main(String[] args)   {
		 boolean flag = true;
	     TransportManagementService transportService = new TransportManagementSystemImpl();	     
		
	     while(true) {
	    	 while(flag) {
	    		 System.out.println("***** Welcome to Transport Management System *****");
	                System.out.println("1. Add Vehicle");
	                System.out.println("2. Update Vehicle");
	                System.out.println("3. Remove Vehicle");
	                System.out.println("4. Cancel Trip");	             
	                System.out.println("5. Cancel Booking");
	                System.out.println("6. Allocate Driver");
	                System.out.println("7. Deallocate Driver");
	                System.out.println("8. List Bookings by Passenger");
	                System.out.println("9. List Bookings by Trips");
	                System.out.println("10. List Available Drivers");
	                System.out.println("0. Exit / Logout");
	                int choice = scanner.nextInt();
	                scanner.nextLine(); 

	                switch (choice) {
	                    case 0:
	                        flag = false;
	                        System.out.println("Thank you, logout successfully.");
	                        break;

	                    case 1:
	                    	Vehicle vehicle = readVehicleData(); 

	                        
	                        boolean addResult = transportService.addVehicle(vehicle);

	                        if (addResult) {
	                            System.out.println("Vehicle added successfully with ID: " + vehicle.getVehicleId());
	                        } else {
	                            System.out.println("Failed to add vehicle.");
	                        }
	                        break;

	                    case 2:
	                    	int vehicleIdToUpdate = 0;
	                        while (true) {
	                            try {
	                                System.out.print("Enter vehicle ID to update: ");
	                                vehicleIdToUpdate = scanner.nextInt();
	                                scanner.nextLine();  
	                                if (vehicleIdToUpdate <= 0) {
	                                    System.out.println("Vehicle ID must be a positive number.");
	                                    continue;
	                                }
	                                break;
	                            } catch (InputMismatchException e) {
	                                System.out.println("Invalid input! Please enter a valid number for vehicle ID.");
	                                scanner.nextLine(); 
	                            }
	                        }

	                        try {
	                            if (!checkVehicleExists(vehicleIdToUpdate)) {
	                                break;  
	                            }

	                            Vehicle updatedVehicle = readVehicleData(); 

	                            boolean updateResult = transportService.updateVehicle(
	                                new Vehicle(vehicleIdToUpdate, updatedVehicle.getModel(), updatedVehicle.getCapacity(), updatedVehicle.getType(), updatedVehicle.getStatus())
	                            );
	                            
	                            System.out.println(updateResult ? "Vehicle updated successfully." : "Failed to update vehicle.");
	                        } catch (VehicleNotFoundException e) {
	                            System.out.println(e.getMessage());
	                        }
	                        break;
	                    case 3:
	                    	int vehicleIdToRemove = 0;
	                        while (true) {
	                            try {
	                                System.out.print("Enter vehicle ID to remove: ");
	                                vehicleIdToRemove = scanner.nextInt();
	                                scanner.nextLine(); 
	                                if (vehicleIdToRemove <= 0) {
	                                    System.out.println("Vehicle ID must be a positive number.");
	                                    continue;
	                                }
	                                break;
	                            } catch (InputMismatchException e) {
	                                System.out.println("Invalid input! Please enter a valid number for vehicle ID.");
	                                scanner.nextLine();  
	                            }
	                        }

	                        try {
	                            if (!checkVehicleExists(vehicleIdToRemove)) {
	                                break;
	                            }

	                            boolean removeResult = transportService.deleteVehicle(vehicleIdToRemove);
	                            System.out.println(removeResult ? "Vehicle removed successfully." : "Failed to remove vehicle.");

	                        } catch (VehicleNotFoundException e) {
	                            System.out.println(e.getMessage());
	                        }
	                        break;
	                
	                    case 4:
	                    	int tripIdToCancel = 0;
	                        while (true) {
	                            try {
	                                System.out.print("Enter trip ID to cancel: ");
	                                tripIdToCancel = scanner.nextInt();
	                                scanner.nextLine(); 
	                                if (tripIdToCancel <= 0) {
	                                    System.out.println("Trip ID must be a positive number.");
	                                    continue;
	                                }
	                                break;
	                            } catch (InputMismatchException e) {
	                                System.out.println("Invalid input! Please enter a valid number for trip ID.");
	                                scanner.nextLine(); 
	                            }
	                        }

	                        if (!checkTripExists(tripIdToCancel)) {
	                            System.out.println("Trip ID " + tripIdToCancel + " not found.");
	                            break; 
	                        }

	                        boolean cancelResult = transportService.cancelTrip(tripIdToCancel);
	                        System.out.println(cancelResult ? "Trip canceled successfully." : "Failed to cancel trip.");
	                        break;
	               
	                    case 5:
	                    	int cancelBookingId = 0;
	                    	while (true) {
	                    	    try {
	                    	        System.out.print("Enter booking ID to cancel: ");
	                    	        cancelBookingId = scanner.nextInt();
	                    	        scanner.nextLine();  // Clear the newline character
	                    	        if (cancelBookingId <= 0) {
	                    	            System.out.println("Booking ID must be a positive number.");
	                    	            continue;
	                    	        }
	                    	        break;
	                    	    } catch (InputMismatchException e) {
	                    	        System.out.println("Invalid input! Please enter a valid number for booking ID.");
	                    	        scanner.nextLine();  // Clear the invalid input
	                    	    }
	                    	}

	                    	// Check if booking exists before attempting to cancel
	                    	if (!checkBookingExists(cancelBookingId)) {
	                    	    System.out.println("Booking ID " + cancelBookingId + " not found.");
	                    	} else {
	                    	    boolean cancelBookingResult = false;  // Initialize the variable

	                    	    try {
	                    	        cancelBookingResult = transportService.cancelBooking(cancelBookingId);
	                    	        // Output the result only if no exception is thrown
	                    	        if (cancelBookingResult) {
	                    	            System.out.println("Booking canceled successfully.");
	                    	        } else {
	                    	            System.out.println("Failed to cancel booking.");
	                    	        }
	                    	    } catch (BookingNotFoundException e) {
	                    	        System.out.println("Error: " + e.getMessage());
	                    	    }
	                    	}

	                    	// After processing the booking cancellation, return to the main menu
	                    	break;
	                    case 6:
	                    	int tripIdToAllocate = 0;
	                        int driverIdToAllocate = 0;

	                        while (true) {
	                            try {
	                                System.out.print("Enter trip ID: ");
	                                tripIdToAllocate = scanner.nextInt();
	                                scanner.nextLine();  
	                                if (tripIdToAllocate <= 0) {
	                                    System.out.println("Trip ID must be a positive number.");
	                                    continue;
	                                }
	                                break;
	                            } catch (InputMismatchException e) {
	                                System.out.println("Invalid input! Please enter a valid number for trip ID.");
	                                scanner.nextLine();  
	                            }
	                        }

	                        if (!checkTripExists(tripIdToAllocate)) {
	                            System.out.println("Trip ID " + tripIdToAllocate + " not found.");
	                            break;  
	                        }

	                        while (true) {
	                            try {
	                                System.out.print("Enter driver ID: ");
	                                driverIdToAllocate = scanner.nextInt();
	                                scanner.nextLine();  
	                                if (driverIdToAllocate <= 0) {
	                                    System.out.println("Driver ID must be a positive number.");
	                                    continue;
	                                }
	                                break;
	                            } catch (InputMismatchException e) {
	                                System.out.println("Invalid input! Please enter a valid number for driver ID.");
	                                scanner.nextLine();  
	                            }
	                        }
	                        if (!checkDriverExists(driverIdToAllocate)) {
	                            System.out.println("Driver ID " + driverIdToAllocate + " not found.");
	                            break;  
	                        }

	                        boolean allocateResult = transportService.allocateDriver(tripIdToAllocate, driverIdToAllocate);
	                        System.out.println(allocateResult ? "Driver allocated successfully." : "Failed to allocate driver.");
	                        break;

	                    case 7:
	                    	int tripIdToDeallocate = 0;

	                        while (true) {
	                            try {
	                                System.out.print("Enter trip ID to deallocate driver: ");
	                                tripIdToDeallocate = scanner.nextInt();
	                                scanner.nextLine(); 
	                                if (tripIdToDeallocate <= 0) {
	                                    System.out.println("Trip ID must be a positive number.");
	                                    continue;
	                                }
	                                break;
	                            } catch (InputMismatchException e) {
	                                System.out.println("Invalid input! Please enter a valid number for trip ID.");
	                                scanner.nextLine();  
	                            }
	                        }

	                        if (!transportService.deallocateDriver(tripIdToDeallocate)) {
	                            System.out.println("Trip ID " + tripIdToDeallocate + " not found.");
	                            break;  
	                        }

	                        boolean deallocateResult = transportService.deallocateDriver(tripIdToDeallocate);
	                        System.out.println(deallocateResult ? "Driver deallocated successfully." : "Failed to deallocate driver.");
	                        break;
	                    case 8:
	                    	int passengerIdToList = 0;
	                        while (true) {
	                            try {
	                                System.out.print("Enter passenger ID to list bookings: ");
	                                passengerIdToList = scanner.nextInt();
	                                scanner.nextLine();
	                                
	                                if (passengerIdToList <= 0) {
	                                    System.out.println("Passenger ID must be a positive number.");
	                                    continue;
	                                }
	                                break;
	                            } catch (InputMismatchException e) {
	                                System.out.println("Invalid input! Please enter a valid number for passenger ID.");
	                                scanner.nextLine();
	                            }
	                        }

	                        try {
	                            List<Booking> bookings = transportService.getBookingsByPassenger(passengerIdToList);

	                            System.out.println("Bookings for passenger ID " + passengerIdToList + ":");
	                            for (Booking booking : bookings) {
	                                System.out.println(booking);
	                            }
	                        } catch (BookingNotFoundException e) {
	                            System.out.println(e.getMessage());
	                        }
	                        break;
	                        
	                    case 9:
	                    	int tripIdToList = 0;
	                        while (true) {
	                            try {
	                                System.out.print("Enter trip ID to list bookings: ");
	                                if (!scanner.hasNextInt()) {
	                                    System.out.println("Invalid input! Please enter a valid trip ID (integer).");
	                                    scanner.next();  
	                                } else {
	                                    tripIdToList = scanner.nextInt();
	                                    scanner.nextLine();  

	                                    try {
	                                        List<Booking> bookingsByTrip = transportService.getBookingsByTrip(tripIdToList);

	                                        if (bookingsByTrip == null || bookingsByTrip.isEmpty()) {
	                                            throw new BookingNotFoundException("No bookings found for trip ID " + tripIdToList);
	                                        } else {
	                                            System.out.println("Bookings for trip ID " + tripIdToList + ":");
	                                            for (Booking booking : bookingsByTrip) {
	                                                System.out.println(booking);
	                                            }
	                                        }
	                                    } catch (BookingNotFoundException e) {
	                                        System.out.println(e.getMessage());
	                                    }
	                                    break; 
	                                }
	                            } catch (InputMismatchException e) {
	                                System.out.println("Invalid input! Please enter a valid trip ID (integer).");
	                                scanner.nextLine();
	                            }
	                        }
	                        break;

	                    case 10:
	                        List<Driver> availableDrivers = transportService.getAvailableDrivers();
	                        if (availableDrivers.isEmpty()) {
	                            System.out.println("No available drivers.");
	                        } else {
	                            System.out.println("Available Drivers:");
	                            for (Driver driver : availableDrivers) {
	                                System.out.println(driver);
	                            }
	                        }
	                        break;

	                    default:
	                        System.err.println("Invalid choice, please try again.");
	                        break;
	    	 }
	     }
	}
	     
}
	
	//Case 1:  
	public static Vehicle readVehicleData() {


	    
	    int vehicleId = 0;
	    while (true) {
	        try {
	            System.out.print("Enter vehicle ID: ");
	            vehicleId = scanner.nextInt();
	            if (vehicleId <= 0) {
	                System.out.println("Vehicle ID must be a positive number.");
	                continue;
	            }
	            break;
	        } catch (InputMismatchException e) {
	            System.out.println("Invalid input! Please enter a valid number for vehicle ID.");
	            scanner.nextLine(); 
	        }
	    }

	    scanner.nextLine();  
	    System.out.print("Enter vehicle model: ");
	    String model = scanner.nextLine();

	    double capacity = 0;
	    while (true) {
	        try {
	            System.out.print("Enter vehicle capacity (number): ");
	            capacity = scanner.nextDouble();
	            if (capacity <= 0) {
	                System.out.println("Capacity must be greater than 0.");
	                continue;
	            }
	            break;
	        } catch (InputMismatchException e) {
	            System.out.println("Invalid input! Please enter a valid number for vehicle capacity.");
	            scanner.nextLine(); 
	        }
	    }

	    scanner.nextLine();  

	    String type = "";
	    while (true) {
	        System.out.print("Enter vehicle type (e.g., Truck, Van, Bus): ");
	        type = scanner.nextLine();
	        if (type.equalsIgnoreCase("Truck") || type.equalsIgnoreCase("Van") || type.equalsIgnoreCase("Bus")) {
	            break;
	        } else {
	            System.out.println("Invalid input! Please enter a valid vehicle type (Truck, Van, Bus).");
	        }
	    }
	    
	    String status = "";
	    while (true) {
	        System.out.print("Enter vehicle status (e.g., Available, On Trip, Maintenance): ");
	        status = scanner.nextLine();
	        if (status.equalsIgnoreCase("Available") || status.equalsIgnoreCase("On Trip") || status.equalsIgnoreCase("Maintenance")) {
	            break;
	        } else {
	            System.out.println("Invalid input! Please enter a valid vehicle status (Available, On Trip, Maintenance).");
	        }
	    }

	    return new Vehicle(vehicleId, model, capacity, type, status);
	}
	
	public static int getValidVehicleId() {
	    int vehicleId = 0;

	    while (true) {
	        try {
	            System.out.print("Enter vehicle ID to update: ");
	            vehicleId = scanner.nextInt();
	            if (vehicleId <= 0) {
	                System.out.println("Vehicle ID must be a positive number. Please try again.");
	                continue; 
	            }
	            break;
	        } catch (InputMismatchException e) {
	            System.out.println("Invalid input! Please enter a valid number for vehicle ID.");
	            scanner.nextLine();
	        }
	    }
	    return vehicleId;
	}
	
	
	//Case 2:
	public static boolean checkVehicleExists(int vehicleId) throws VehicleNotFoundException {
		String checkVehicleQuery = "SELECT COUNT(*) FROM Vehicles WHERE vehicleId = ?";
	    try (PreparedStatement stmt = conn.prepareStatement(checkVehicleQuery)) {
	        stmt.setInt(1, vehicleId);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                if (rs.getInt(1) > 0) {
	                    return true; 
	                } else {
	                    throw new VehicleNotFoundException("Vehicle ID " + vehicleId + " not found.");
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	public static boolean checkDriverExists(int driverId) {
	    String checkDriverQuery = "SELECT COUNT(*) FROM Drivers WHERE driver_Id = ?";
	    try (PreparedStatement stmt = conn.prepareStatement(checkDriverQuery)) {
	        stmt.setInt(1, driverId);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1) > 0;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
		
	//Case 4:
	public static boolean checkTripExists(int tripId) {
	    String checkTripQuery = "SELECT COUNT(*) FROM Trips WHERE tripId = ?";
	    try (PreparedStatement stmt = conn.prepareStatement(checkTripQuery)) {
	        stmt.setInt(1, tripId);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1) > 0;  
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	
	//Case 5:
	public static boolean checkBookingExists(int bookingId) {
	    String checkBookingQuery = "SELECT COUNT(*) FROM Bookings WHERE bookingId = ?";
	    try (PreparedStatement stmt = conn.prepareStatement(checkBookingQuery)) {
	        stmt.setInt(1, bookingId);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1) > 0;  // Return true if the booking exists
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	
	//Case 7:
	public static boolean canDeallocateDriver(int tripId) {
	    String checkTripQuery = "SELECT COUNT(*) FROM Trips WHERE tripId = ?";
	    try (PreparedStatement stmt = conn.prepareStatement(checkTripQuery)) {
	        stmt.setInt(1, tripId);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1) > 0;  // Returns true if the trip exists
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

}