package view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.MainFXApp;
import application.iVoterDBConfig;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Voter;

public class addVoterController {

	Stage stage;
	Scene scene;
	Parent root;
	//always reference main method, and build constructor
	private MainFXApp main;
	public void setMain(MainFXApp mainIn)
	{
	main=mainIn;
	}

    @FXML private Tab tabPane;
    @FXML private Button cancelButton;
    @FXML private Button submitButton;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField addressTextField;
    @FXML private TextField countyTextField;
    @FXML private TextField stateTextField;
    @FXML private TextField zipCodeTextField;
    @FXML private TextField phoneTextField;
    @FXML private Label statusLabel;

    //for some reason I needed a try-catch for this
    //but it works fine without try-catch in updateVoterController and displayVoterController
    @FXML
	void ClickCancelButton(ActionEvent event) throws Exception {
    	try{
    	//finds what stage the button is in
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		//gets some fxml file
		root = FXMLLoader.load(getClass().getResource("/view/mainVoterView.fxml"));
		//sets fxml file as a scene
		scene = new Scene(root);
		//loads the scene on top of whatever stage the button is in
		stage.setScene(scene);
	} catch (Exception e){
		e.printStackTrace();
	}
    }

    @FXML
    void ClickSubmitButton(ActionEvent event) throws SQLException {
    	//debugging
    	System.out.println("ClickSubmitButton (addVoterController)");
    	Connection conn = iVoterDBConfig.getConnection();
		Statement statement = conn.createStatement();
    	int RS;


    	try{
    	//gets values from user
    	String firstName, lastName, address, zipCode,state,county,  phoneNumber;
		firstName = firstNameTextField.getText();
		lastName = lastNameTextField.getText();
		address = addressTextField.getText();
		zipCode = zipCodeTextField.getText();
		county = countyTextField.getText();
		state = stateTextField.getText();
		phoneNumber = phoneTextField.getText();

		// checks to see if these values are OK
		if (firstName == null || firstName == "" || firstName.trim().isEmpty()) {
			throw new Exception("invalid first name.");
		}
		if (lastName == null || lastName == "" || lastName.trim().isEmpty()) {
			throw new Exception("invalid last name.");
		}
		if (address == null || address == "" || address.trim().isEmpty()) {
			throw new Exception("invalid address.");
		}
		if (state == null || state == "" || state.trim().isEmpty() || state.length() != 2) {
			throw new Exception("invalid state");
		}
		if (county == null || county == "" || county.trim().isEmpty()) {
			throw new Exception("invalid county code.");
		}
		if (zipCode == null || zipCode == "" || zipCode.trim().isEmpty() || zipCode.length() != 5) {
			throw new Exception("invalid zip code.");
		}
		if (phoneNumber == null || phoneNumber == "" || phoneNumber.trim().isEmpty() || phoneNumber.length() != 10) {
		throw new Exception("invalid phone number.");
		}

		//I am putting together a String that is basically exactly how you would code it into SQL from XAMMP or MySQL
		String update = ("insert into voter (firstName, lastName, address, county, state, zipcode, phone)" +
				" values(");
		update = update+ "'" +
				firstName+ "', '" +
				lastName+ "', '" +
				address+ "', '" +
				county+ "', '" +
				state+ "', '" +
				zipCode+ "', '" +
				phoneNumber+ "')";
		//debugging
		System.out.println("Update = " + update);

		// this will shove the string update into the SQL database
		// and execute it exactly as if it were in the console
		RS = statement.executeUpdate(update);
		// statement.executeUpdate() will return a 1 if the DB actually adds 1 new voter
		// and will return 0 if it doesn't. Probably also generating errors too.
		if (RS == 1){
			statusLabel.setText("Status: New voter added successfully!");
		}
    } catch (Exception e) {
		statusLabel.setText("Status: operation failed due to: " + e.getMessage());
		System.out.println(e.getMessage());
	}
    }
    }
