package se.lu.ics.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import se.lu.ics.model.Address;
import se.lu.ics.model.Employee;
import se.lu.ics.model.EmployeeRegister;
// EmployeeController.java
public class EmployeeController {
	private EmployeeRegister employeeRegister;
	
	@FXML
	private TextField textFieldEmployeeId;
	
	@FXML
	private TextField textFieldEmployeeName;
	
	@FXML
	private TextField textFieldEmployeeTitle;
	
	@FXML
	private TextField textFieldEmployeeSalary;
	
	@FXML
	private TextField textFieldAddressStreetNumber;
	
	@FXML
	private TextField textFieldAddressStreetName;
	
	@FXML
	private TextField textFieldAddressCity;
	
	@FXML
	private TextField textFieldAddressPostalCode;
	
	@FXML
	private TextField textFieldAddressCountry;
	
	@FXML
	private TextArea textAreaEmployeeAddress;
	
	@FXML
	private TextArea textAreaEmployee;
	
	@FXML
	private Label labelError;
		
	@FXML
	public void buttonFindEmployee_Click(ActionEvent event) {
		String employeeId = textFieldEmployeeId.getText();
		
		Employee foundEmployee = employeeRegister.findEmployeeByEmployeeId(employeeId);
		
		if(foundEmployee != null) {
			int employeeSalary = foundEmployee.getSalary();
			String employeeSalaryString = Integer.toString(employeeSalary);
			
			textFieldEmployeeName.setText(foundEmployee.getName());
			textFieldEmployeeTitle.setText(foundEmployee.getTitle());
			textFieldEmployeeSalary.setText(employeeSalaryString);
			
			this.refreshTextAreaEmployee();
			this.refreshTextAreaEmployeeAddress(foundEmployee);
		} else {
			labelError.setText("No employee found. Please try another employee ID");
		}

	}
	
	@FXML
	public void buttonAddEmployee_Click(ActionEvent event) {
		String employeeId = textFieldEmployeeId.getText();
		String employeeName = textFieldEmployeeName.getText();
		String employeeTitle = textFieldEmployeeTitle.getText();
		String employeeSalaryString = textFieldEmployeeSalary.getText();

		try {
			int employeeSalary = Integer.parseInt(employeeSalaryString);

			Employee newEmployee = new Employee(employeeId, employeeName, employeeTitle, employeeSalary);

			employeeRegister.addEmployee(newEmployee);
			textFieldEmployeeId.setText("");
			textFieldEmployeeName.setText("");
			textFieldEmployeeTitle.setText("");
			textFieldEmployeeSalary.setText("");
			
			this.refreshTextAreaEmployee();
			this.refreshTextAreaEmployeeAddress(newEmployee);
		} catch(NumberFormatException exception) {
			labelError.setText("Salary can only contain numbers. Do not use special characters or letters!");
		}
		
	}
	
	@FXML
	public void buttonRemoveEmployee_Click(ActionEvent event) {
		String employeeId = textFieldEmployeeId.getText();

		Employee foundEmployee = employeeRegister.findEmployeeByEmployeeId(employeeId);

		employeeRegister.removeEmployee(foundEmployee);
		textFieldEmployeeId.setText("");
		textFieldEmployeeName.setText("");
		textFieldEmployeeTitle.setText("");
		textFieldEmployeeSalary.setText("");
		
		this.refreshTextAreaEmployee();
		this.refreshTextAreaEmployeeAddress(null);
	}
	
	@FXML
	public void buttonAddAddress_Click(ActionEvent event) {
		String employeeId = textFieldEmployeeId.getText();
		Employee foundEmployee = employeeRegister.findEmployeeByEmployeeId(employeeId);
		
		String streetNumberString = textFieldAddressStreetNumber.getText();
		String streetName = textFieldAddressStreetName.getText();
		String city = textFieldAddressCity.getText();
		String postalCode = textFieldAddressPostalCode.getText();
		String country = textFieldAddressCountry.getText();
		
		try {
			int streetNumber = Integer.parseInt(streetNumberString);
			
			Address newAddress = new Address(streetNumber, streetName, city, postalCode, country);
			foundEmployee.addAddress(newAddress);
			
			this.refreshTextAreaEmployee();
			this.refreshTextAreaEmployeeAddress(foundEmployee);
		} catch(NumberFormatException exception) {
			labelError.setText("Street number can only contain numbers. Do not use special characters or letters!");
		} catch (NullPointerException exception) {
			labelError.setText("Employee not found. Employee ID field must contain Employee ID of an existing employee. Gör om, gör rätt");
		}
	}

	public void setEmployeeRegister(EmployeeRegister employeeRegister) {
		this.employeeRegister = employeeRegister;
	}
	
	
	public void initializeTextAreas() {
		this.refreshTextAreaEmployee();
	}
	
	private void refreshTextAreaEmployee() {
		String columnHeaders = "ID\t\t\tName\t\tTitle\t\t\tSalary\n";
		String columnHeaderBottomBorders = "-----\t\t\t-----\t\t\t-----\t\t\t-----\n";
		String rows = "";
		for(Employee tmpEmployee : employeeRegister.getEmployees()) {
			rows += tmpEmployee.getEmployeeId() + "\t\t\t"
			+ tmpEmployee.getName() + "\t\t\t";
			
			// Fix number of tabs according to title String length
			// This is why we have TableViews ...
			if(tmpEmployee.getTitle().length() < 4) {
				rows += tmpEmployee.getTitle() + "\t\t\t";
			} else if(tmpEmployee.getTitle().length() < 8) {
				rows += tmpEmployee.getTitle() + "\t\t";
			} else {
				rows += tmpEmployee.getTitle() + "\t";
			}

			rows += tmpEmployee.getSalary() + "\t\n";;
		}
		String table = columnHeaders + columnHeaderBottomBorders + rows;
		textAreaEmployee.setText(table);
	}

	private void refreshTextAreaEmployeeAddress(Employee employee) {
		if(employee != null) {
		String columnHeaders = "No.\t\t\tStreet\t\t\tCity\t\t\tCode\t\tCountry\n";
		String columnHeaderBottomBorders = "-----\t\t\t-----\t\t\t\t-----\t\t\t-----\t\t\t-----\n";
		String rows = "";
		for(Address tmpAddress : employee.getAddresses()) {
			rows += tmpAddress.getStreetNumber() + "\t\t\t";
			if(tmpAddress.getStreetName().length() >= 10) {
				rows += tmpAddress.getStreetName() + "\t\t";
			} else {
				rows += tmpAddress.getStreetName() + "\t\t\t";
			}
			
			rows += tmpAddress.getCity() + "\t\t";
			rows += tmpAddress.getPostalCode() + "\t\t";
			rows += tmpAddress.getCountry() + "\t\n";
		}
		String table = columnHeaders + columnHeaderBottomBorders + rows;
		textAreaEmployeeAddress.setText(table);
		} else{
			textAreaEmployeeAddress.setText("");
		}
	}
}
