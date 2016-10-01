package view;

import application.MainFXApp;
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
    void ClickSubmitButton(ActionEvent event) {
    	//debugging
    	System.out.println("ClickSubmitButton (addVoterController)");
    }

}
