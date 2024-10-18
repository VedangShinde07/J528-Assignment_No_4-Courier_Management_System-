package com.hexaware.cms.entity;

public class Employee {
	
	private int employeeId;
	private String name;
	private String email;
	private String contactNumber;
	private String role;
	private double salary;
	
	
	public Employee() {
		super();
	}


	public Employee(int employeeId, String name, String email, String contactNumber, String role, double salary) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.email = email;
		this.contactNumber = contactNumber;
		this.role = role;
		this.salary = salary;
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getContactNumber() {
		return contactNumber;
	}


	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public double getSalary() {
		return salary;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}


	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", email=" + email + ", contactNumber="
				+ contactNumber + ", role=" + role + ", salary=" + salary + "]";
	}
	
	
	
	
	
	

}
