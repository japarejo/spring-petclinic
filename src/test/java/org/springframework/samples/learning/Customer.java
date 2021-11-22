package org.springframework.samples.learning;





public class Customer {
    private String firstName;
    private String sureName;
    private String address;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSureName() {
		return sureName;
	}
	public void setSureName(String sureName) {
		this.sureName = sureName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Customer(String firstName, String sureName, String address) {
		super();
		this.firstName = firstName;
		this.sureName = sureName;
		this.address = address;
	}
    
    
	
}
