package com.nikhil.springboot2securityjwt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="patient")
public class Patient {
        @Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id")
		private int id;
		
		@Column(name="dob")
		private String dob;
		
		@Column(name="firstname")
		private String firstname;

		@Column(name="lastname")
		private String lastname;

		@Column(name="email")
		private String email;

		@Column(name="address")
		private String address;

		@Column(name="phone")
		private String phone;

		@Column(name="sex")
		private String sex;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getDob() {
			return dob;
		}

		public void setDob(String dob) {
			this.dob = dob;
		}

		public String getFirstname() {
			return firstname;
		}

		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}

		public String getLastname() {
			return lastname;
		}

		public void setLastname(String lastname) {
			this.lastname = lastname;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public Patient(String dob, String firstname, String lastname, String email, String address, String phone,
				String sex) {
			super();
			this.dob = dob;
			this.firstname = firstname;
			this.lastname = lastname;
			this.email = email;
			this.address = address;
			this.phone = phone;
			this.sex = sex;
		}

		public Patient() {
			super();
		}
		
		
		
	

}
