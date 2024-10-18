package com.hexaware.cms.entity;

public class User {

		private int userId;
		private String name;
		private String email;
		private String password;
		private String contactNumber;
		private String address;
		
		public User() {
			super();
		}

		public User(int userId, String name, String email, String password, String contactNumber, String address) {
			super();
			this.userId = userId;
			this.name = name;
			this.email = email;
			this.password = password;
			this.contactNumber = contactNumber;
			this.address = address;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
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

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getContactNumber() {
			return contactNumber;
		}

		public void setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		@Override
		public String toString() {
			return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
					+ ", contactNumber=" + contactNumber + ", address=" + address + "]";
		}
		
			
		
		
	}



