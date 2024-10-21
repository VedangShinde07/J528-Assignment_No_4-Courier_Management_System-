package com.hexaware.tms.dao;

/*
 * @Author: Vedang Shinde
 * @Description: Transport Management Impl is the implementation of ITransportManagementDao Interface.
 * Date: 21 October 2024
 */

import com.hexaware.tms.entity.Booking;
import com.hexaware.tms.entity.Driver;
import com.hexaware.tms.entity.Vehicle;
import com.hexaware.tms.exceptions.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.hexaware.tms.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class TransportManagementImpl implements ITransportManagementDao {
	
	 private Connection conn = DBUtil.getConnection();

	    public TransportManagementImpl() {
	    }

	@Override
	public boolean addVehicle(Vehicle vehicle) {
		String checkVehicleQuery = "SELECT COUNT(*) FROM Vehicles WHERE vehicleId = ?";
	    String createVehicleQuery = "INSERT INTO Vehicles (vehicleId, model, capacity, type, status) VALUES (?, ?, ?, ?, ?)";
	    
	    try (PreparedStatement checkStmt = this.conn.prepareStatement(checkVehicleQuery)) {
	        checkStmt.setInt(1, vehicle.getVehicleId());
	        ResultSet rs = checkStmt.executeQuery();
	        
	        if (rs.next() && rs.getInt(1) > 0) {
	            System.out.println("Vehicle with ID " + vehicle.getVehicleId() + " already exists.");
	            return false;
	        }
	        
	        try (PreparedStatement insertStmt = this.conn.prepareStatement(createVehicleQuery)) {
	            insertStmt.setInt(1, vehicle.getVehicleId());
	            insertStmt.setString(2, vehicle.getModel());
	            insertStmt.setDouble(3, vehicle.getCapacity());
	            insertStmt.setString(4, vehicle.getType());
	            insertStmt.setString(5, vehicle.getStatus());

	            int rowsAffected = insertStmt.executeUpdate();
	            return rowsAffected > 0;  // Returns true if insertion was successful
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean updateVehicle(Vehicle vehicle) throws VehicleNotFoundException {
		String checkVehicleExistsQuery = "SELECT COUNT(*) FROM vehicles WHERE vehicleID = ?";
	    
	    try (PreparedStatement stmt = this.conn.prepareStatement(checkVehicleExistsQuery)) {
	        stmt.setInt(1, vehicle.getVehicleId());
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next() && rs.getInt(1) == 0) {
	            throw new VehicleNotFoundException("Vehicle Not Found");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }

	    String updateVehicleQuery = "UPDATE vehicles SET model = ?, capacity = ?, type = ?, status = ? WHERE vehicleID = ?";
	    try (PreparedStatement stmt = this.conn.prepareStatement(updateVehicleQuery)) {
	        stmt.setString(1, vehicle.getModel());
	        stmt.setDouble(2, vehicle.getCapacity());
	        stmt.setString(3, vehicle.getType());
	        stmt.setString(4, vehicle.getStatus());
	        stmt.setInt(5, vehicle.getVehicleId());
	        
	        int rowsAffected = stmt.executeUpdate();
	        return rowsAffected > 0; 
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return false;
	}

	@Override
	public boolean deleteVehicle(int vehicleId) throws VehicleNotFoundException {
		String checkVehicleExistsQuery = "SELECT COUNT(*) FROM vehicles WHERE vehicleID = ?";
	    
	    try (PreparedStatement stmt = this.conn.prepareStatement(checkVehicleExistsQuery)) {
	        stmt.setInt(1, vehicleId);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next() && rs.getInt(1) == 0) {
	            throw new VehicleNotFoundException("Vehicle Not Found");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }

	    String deleteVehicleQuery = "DELETE FROM Vehicles WHERE VehicleID = ?";
	    try (PreparedStatement stmt = this.conn.prepareStatement(deleteVehicleQuery)) {
	        stmt.setInt(1, vehicleId);
	        int rowsAffected = stmt.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
	}

	@Override
	public boolean cancelTrip(int tripId) {
		 String cancelTripQuery = "DELETE FROM Trips WHERE tripId = ?";

	        try (PreparedStatement stmt = this.conn.prepareStatement(cancelTripQuery)) {
	            stmt.setInt(1, tripId);
	            int rowsAffected = stmt.executeUpdate();
	            return rowsAffected > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	}


	@Override
	public boolean cancelBooking(int bookingId) throws BookingNotFoundException {
		String checkBookingQuery = "SELECT COUNT(*) FROM Bookings WHERE bookingId = ?";
	    
	    try (PreparedStatement stmt = this.conn.prepareStatement(checkBookingQuery)) {  
	        stmt.setInt(1, bookingId);
	        ResultSet rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            int bookingCount = rs.getInt(1);
	            if (bookingCount == 0) {
	                throw new BookingNotFoundException("Booking Not Found");
	            }
	        } else {

	            throw new BookingNotFoundException("Booking Not Found");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }

	    // If the booking exists, proceed to delete it
	    String cancelBookingQuery = "DELETE FROM Bookings WHERE bookingId = ?";
	    try (PreparedStatement stmt = this.conn.prepareStatement(cancelBookingQuery)) {
	        stmt.setInt(1, bookingId);
	        int rowsAffected = stmt.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean allocateDriver(int tripId, int driverId) {
		 if (!doesTripExist(tripId) || !doesDriverExist(driverId)) {
	            return false; 
	        }

	        String allocateDriverQuery = "UPDATE Trips SET driver_id = ? WHERE tripId = ?";
	        try (PreparedStatement stmt = this.conn.prepareStatement(allocateDriverQuery)) {
	            stmt.setInt(1, driverId);
	            stmt.setInt(2, tripId);
	            int rowsAffected = stmt.executeUpdate();
	            return rowsAffected > 0;
	        } catch (SQLException e) {
	            e.printStackTrace(); 
	            return false;
	        }
	    }

	    private boolean doesTripExist(int tripId) {
	        String checkTripQuery = "SELECT COUNT(*) FROM Trips WHERE TripID = ?";
	        try (PreparedStatement stmt = this.conn.prepareStatement(checkTripQuery)) {
	            stmt.setInt(1, tripId);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                int count = rs.getInt(1); 
	                return count > 0; 
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); 
	        }
	        return false; 
	    }

	    private boolean doesDriverExist(int driverId) {
	        String checkDriverQuery = "SELECT COUNT(*) FROM drivers WHERE driver_id = ?";
	        try (PreparedStatement stmt = this.conn.prepareStatement(checkDriverQuery)) {
	            stmt.setInt(1, driverId);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                int count = rs.getInt(1); 
	                return count > 0; 
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); 
	        }
	        return false;
	}

	@Override
	public boolean deallocateDriver(int tripId) {
		String deallocateDriverQuery = "UPDATE Trips SET driver_id = NULL WHERE tripId = ?";

        try (PreparedStatement stmt = this.conn.prepareStatement(deallocateDriverQuery)) {
            stmt.setInt(1, tripId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public List<Booking> getBookingsByPassenger(int passengerId) throws BookingNotFoundException {
		List<Booking> bookings = new ArrayList<>();
	    String query = "SELECT * FROM Bookings WHERE passengerId = ?";

	    try (PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setInt(1, passengerId);

	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                int bookingId = rs.getInt("bookingId");
	                int tripId = rs.getInt("tripId");
	                LocalDate bookingDate = rs.getDate("bookingDate").toLocalDate();
	                String status = rs.getString("status");
	                
	                bookings.add(new Booking(bookingId, tripId, passengerId, bookingDate, status));
	            }
	        }

	        if (bookings.isEmpty()) {
	            throw new BookingNotFoundException("No bookings found for passenger ID " + passengerId);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    }

	    return bookings;	    
	}

	@Override
	public List<Booking> getBookingsByTrip(int tripId) throws BookingNotFoundException {
		List<Booking> bookings = new ArrayList<>();
	    String query = "SELECT * FROM Bookings WHERE tripId = ?";

	    try (PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setInt(1, tripId);

	        // Execute the query and process the result set
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                int bookingId = rs.getInt("bookingId");
	                int passengerId = rs.getInt("passengerId");
	                LocalDate bookingDate = rs.getDate("bookingDate").toLocalDate();
	                String status = rs.getString("status");

	                // Create a Booking object and add it to the list
	                bookings.add(new Booking(bookingId, tripId, passengerId, bookingDate, status));
	            }
	        }

	        // If no bookings are found, throw a custom exception
	        if (bookings.isEmpty()) {
	            throw new BookingNotFoundException("No bookings found for trip ID " + tripId);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();  // Handle SQL exceptions
	    }

	    return bookings;
	}

	@Override
	public List<Driver> getAvailableDrivers() {
		List<Driver> drivers = new ArrayList<>();
        String getAvailableDriversQuery = "SELECT * FROM Drivers WHERE is_available = 1";

        try (PreparedStatement stmt = this.conn.prepareStatement(getAvailableDriversQuery);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Driver driver = new Driver();
                driver.setDriverId(rs.getInt("driver_id"));
                driver.setDriverName(rs.getString("driver_name"));
                driver.setContactNumber(rs.getString("contact_number"));
                driver.setEmail(rs.getString("email"));
                driver.setAvailable(rs.getBoolean("is_available"));
                drivers.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }
	
	public Booking getBookingById(int bookingId) throws BookingNotFoundException {
        String getBookingQuery = "SELECT * FROM bookings WHERE BookingID = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(getBookingQuery)) {
            stmt.setInt(1, bookingId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Booking booking = new Booking();
                    booking.setBookingId(rs.getInt("BookingID"));
                    booking.setTripId(rs.getInt("TripID"));
                    booking.setPassengerId(rs.getInt("PassengerID"));
                    booking.setBookingDate(rs.getDate("BookingDate").toLocalDate());
                    booking.setStatus(rs.getString("Status"));
                    return booking;
                } else {
                    throw new BookingNotFoundException("Booking with ID " + bookingId + " not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addNewBooking(Booking booking) {
        String insertBookingQuery = "INSERT INTO bookings (BookingID, TripID, PassengerID, BookingDate, Status) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(insertBookingQuery)) {
            stmt.setInt(1, booking.getBookingId());
            stmt.setInt(2, booking.getTripId());
            stmt.setInt(3, booking.getPassengerId());
            stmt.setDate(4, Date.valueOf(booking.getBookingDate()));
            stmt.setString(5, booking.getStatus());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Booking inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to add new booking.");
        }
    }
    
    public Vehicle getVehicleById(int vehicleId) throws VehicleNotFoundException {
        String getVehicleQuery = "SELECT * FROM vehicles WHERE VehicleID = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(getVehicleQuery)) {
            stmt.setInt(1, vehicleId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Vehicle found, return it
                    Vehicle vehicle = new Vehicle();
                    vehicle.setVehicleId(rs.getInt("VehicleID"));
                    vehicle.setModel(rs.getString("Model"));
                    vehicle.setCapacity(rs.getDouble("Capacity"));
                    vehicle.setType(rs.getString("Type"));
                    vehicle.setStatus(rs.getString("Status"));
                    return vehicle;
                } else {                  
                    throw new VehicleNotFoundException("Vehicle with ID " + vehicleId + " not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

	
	

