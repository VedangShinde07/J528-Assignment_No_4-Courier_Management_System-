package com.hexaware.tms.entity;

public class Passengers {
	
	private int passengerId;
	private String firstName;
	private String genger;
	private int age;
	private String email;
	private long phoneNumber;
	
	public Passengers() {
		super();
	}
	
	
	public Passengers(int passengerId, String firstName, String genger, int age, String email, long phoneNumber) {
		super();
		this.passengerId = passengerId;
		this.firstName = firstName;
		this.genger = genger;
		this.age = age;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}


	public int getPassengerId() {
		return passengerId;
	}


	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getGenger() {
		return genger;
	}


	public void setGenger(String genger) {
		this.genger = genger;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public long getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	@Override
	public String toString() {
		return "Passengers [passengerId=" + passengerId + ", firstName=" + firstName + ", genger=" + genger + ", age="
				+ age + ", email=" + email + ", phoneNumber=" + phoneNumber + "]";
	}

}
