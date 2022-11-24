package se.lu.ics.model;

import java.util.ArrayList;
// EmployeeRegister.java
public class EmployeeRegister {
	private ArrayList<Employee> employees;
	
	public EmployeeRegister() {
		employees = new ArrayList<Employee>();
	}
	
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
	
	public void removeEmployee(Employee employee) {
		employees.remove(employee);
	}
	
	public Employee findEmployeeByEmployeeId(String employeeId) {
		for(Employee tmpEmployee : employees) {
			if(tmpEmployee.getEmployeeId().equals(employeeId)) {
				return tmpEmployee;
			}
		}
		return null;
	}

	public ArrayList<Employee> getEmployees() {
		return employees;
	}
	
	
}
