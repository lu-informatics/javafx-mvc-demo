package se.lu.ics.model;

import java.util.ArrayList;
//Employee.java
public class Employee {
	private String employeeId;
	private String name;
	private String title;
	private int salary;
	private ArrayList<Address> addresses;

	public Employee() {
		this.addresses = new ArrayList<Address>();
	}

	public Employee(String employeeId, String name, String title, int salary) {
		this.employeeId = employeeId;
		this.name = name;
		this.title = title;
		this.salary = salary;
		this.addresses = new ArrayList<Address>();
	}
	
	public void addAddress(Address address) {
		this.addresses.add(address);
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public ArrayList<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(ArrayList<Address> addresses) {
		this.addresses = addresses;
	}
}
