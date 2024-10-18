package com.hexaware.cms.presentation;

import com.hexaware.cms.entity.Courier;
import com.hexaware.cms.entity.Employee;
import com.hexaware.cms.exceptions.TrackingNumberNotFoundException;
import com.hexaware.cms.service.CourierService;
import com.hexaware.cms.service.CourierServiceImpl;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;


public class CourierApp {
	static Scanner scanner;
	 
	 static {
	        scanner = new Scanner(System.in);
	    }
	 
	public CourierApp() {
	}

	public static void main(String[] args) {
		boolean flag = true;
		CourierService courierService = new CourierServiceImpl();
		
		while(true) {
			while(flag) {
				 System.out.println("***** Welcome to Courier Management System *****");
			        System.out.println("1. PLACE ORDER");
			        System.out.println("2. GET ORDER STATUS");
			        System.out.println("3. CANCEL ORDER");
			        System.out.println("4. GET ASSIGNED ORDERS");
			        System.out.println("5. ADD COURIER STAFF");
			        System.out.println("0. EXIT / LOGOUT");
			        
			        int choice = scanner.nextInt();
			        scanner.nextLine();
			        switch (choice) {
			            case 0:
			                flag = false;
			                System.out.println("Thank you, logout successfully..");
			                break;

			            case 1: 
			                Courier courier = readCourierData(); 
			                String placeOrderResult = courierService.placeOrder(courier);
			                System.out.println(placeOrderResult); 
			                break;

			            case 2: 
			            	System.out.print("Enter Tracking Number to get order status: ");
		                    String trackingNumber = scanner.nextLine(); // Use nextLine to capture string input
		                    System.out.println("Debug: Tracking number entered = '" + trackingNumber + "'");
		                    String orderStatus = courierService.getOrderStatus(trackingNumber);
		                    System.out.println("Order Status: " + (orderStatus != null ? orderStatus : "Order not found"));
		                    break;

			            case 3: 
			            	System.out.print("Enter Tracking Number to cancel the order: ");
		                    String cancelTrackingNumber = scanner.nextLine(); // Use nextLine to capture string input
		                    System.out.println("Debug: Tracking number entered = '" + cancelTrackingNumber + "'");

		                    try {
		                        boolean cancelResult = courierService.cancelOrder(cancelTrackingNumber);
		                        if (cancelResult) {
		                            System.out.println("Order canceled successfully.");
		                        } else {
		                            System.err.println("Failed to cancel the order.");
		                        }
		                    } catch (TrackingNumberNotFoundException e) {
		                        System.err.println(e.getMessage()); // Print the custom exception message
		                    } catch (Exception e) {
		                        System.err.println("An unexpected error occurred: " + e.getMessage());
		                        e.printStackTrace(); // Print stack trace for unexpected exceptions
		                    }
		                    break;


			            case 4: 
			                System.out.print("Enter Courier Staff ID to get assigned orders: ");
			                int courierStaffId = scanner.nextInt();
			                List<Courier> assignedOrders = courierService.getAssignedOrders(courierStaffId);
			                System.out.println("Assigned Orders:");
			                for (Courier c : assignedOrders) {
			                    System.out.println(c); 
			                }
			                break;

			            case 5: 
			                Employee employee = readEmployeeData(); 
			                int staffCount = courierService.addCourierStaff(employee);
			                if (staffCount > 0) {
			                    System.out.println("Courier staff added successfully..");
			                } else {
			                    System.err.println("Failed to add courier staff...");
			                }
			                break;

			            default:
			                System.err.println("Invalid choice, please try again.");
			                break;
				
				
			}
		}
		
      }	

		
		
}	
	public static Courier readCourierData() {
	    Courier courier = new Courier(); 

	    System.out.println("Enter Courier ID:");
	    courier.setCourierId(scanner.nextInt()); 

	    System.out.println("Enter Sender Name:");
	    courier.setSenderName(scanner.next()); 

	    System.out.println("Enter Sender Address:");
	    courier.setSenderAddress(scanner.next()); 

	    System.out.println("Enter Receiver Name:");
	    courier.setReceiverName(scanner.next()); 

	    System.out.println("Enter Receiver Address:");
	    courier.setReceiverAddress(scanner.next()); 

	    System.out.println("Enter Weight:");
	    courier.setWeight(scanner.nextDouble()); 

	    System.out.println("Enter Status:");
	    courier.setStatus(scanner.next()); 

	    System.out.println("Enter Tracking Number:");
	    courier.setTrackingNumber(scanner.next()); 

	    System.out.println("Enter Delivery Date (yyyy-MM-dd):");
	    String deliveryDateString = scanner.next(); 
	    LocalDate deliveryDate = LocalDate.parse(deliveryDateString); 
	    courier.setDeliveryDate(deliveryDate); 

	    
	    System.out.print("Enter User ID (or retrieve from context): ");
	    courier.setUserId(scanner.nextInt()); 

	    return courier; 
	}
	
	public static Employee readEmployeeData() {
	    Employee employee = new Employee(); // Assuming Employee class exists

	    System.out.println("Enter Employee ID:");
	    employee.setEmployeeId(scanner.nextInt()); // Assuming Employee class has a setEmployeeId method

	    System.out.println("Enter Name:");
	    employee.setName(scanner.next()); // Assuming Employee class has a setName method

	    System.out.println("Enter Email:");
	    employee.setEmail(scanner.next()); // Assuming Employee class has a setEmail method

	    System.out.println("Enter Contact Number:");
	    employee.setContactNumber(scanner.next()); // Assuming Employee class has a setContactNumber method

	    System.out.println("Enter Role:");
	    employee.setRole(scanner.next()); // Assuming Employee class has a setRole method

	    System.out.println("Enter Salary:");
	    employee.setSalary(scanner.nextDouble()); // Assuming Employee class has a setSalary method

	    return employee; // Return the populated Employee object
	}
	
}
		


