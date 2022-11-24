package se.lu.ics.application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import se.lu.ics.controller.EmployeeController;
import se.lu.ics.model.Address;
import se.lu.ics.model.Employee;
import se.lu.ics.model.EmployeeRegister;

// Main.java
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			EmployeeRegister employeeRegister = new EmployeeRegister();
			
			// Test Data
			Employee e1 = new Employee("E1", "Ham", "CEO", 80000);
			Employee e2 = new Employee("E2", "Sam", "CTO", 75000);
			Employee e3 = new Employee("E3", "Vim", "Consultant", 60000);
			Employee e4 = new Employee("E4", "Ken", "Consultant", 65000);
			Employee e5 = new Employee("E5", "Sue", "Manager", 70000);
			Employee e6 = new Employee("E6", "Joe", "Accountant", 65000);
			
			Address a1 = new Address(2, "Paradisgatan", "Lund", "222 23", "Sweden");
			Address a2 = new Address(6, "Kyrkogatan", "Lund", "222 61", "Sweden");
			Address a3 = new Address(3, "Bantorget", "Lund", "222 11", "Sweden");
			Address a4 = new Address(10, "Bredgatan", "Lund", "223 11", "Sweden");
			Address a5 = new Address(11, "Bankgatan", "Lund", "222 55", "Sweden");
			
			e1.addAddress(a1);
			e1.addAddress(a2);
			e1.addAddress(a3);
			
			e2.addAddress(a4);
			e2.addAddress(a5);
			
			employeeRegister.addEmployee(e1);
			employeeRegister.addEmployee(e2);
			employeeRegister.addEmployee(e3);
			employeeRegister.addEmployee(e4);
			employeeRegister.addEmployee(e5);
			employeeRegister.addEmployee(e6);
		
			// This allows us to get a reference to the controller after it 
			// has been instantiated (JavaFX instantiates it automatically
			// "behind the scenes" (no pun intended)
			// In this way we can give the controller a reference to the register
			// and thus coupling Model with Controller
			FXMLLoader employeeLoader = new FXMLLoader(getClass().getResource("/resources/fxml/Employee.fxml"));
			AnchorPane root = new AnchorPane();
			root.getChildren().add(employeeLoader.load());
			EmployeeController employeeController = employeeLoader.getController();
			employeeController.setEmployeeRegister(employeeRegister);
			employeeController.initializeTextAreas();

			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Employee Register");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
