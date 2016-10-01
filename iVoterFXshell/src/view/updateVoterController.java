//    This is a JavaFX shell of an iVoter program I got from my professor.
//    I have stripped it of all functionality and removed the combo box.
//    https://github.com/abegaz <- The original author of this program

package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Voter;

public class updateVoterController {

	Stage stage;
	Scene scene;
	Parent root;
	//always reference main method, and build constructor
	private MainFXApp main;
	public void setMain(MainFXApp mainIn)
	{
	main=mainIn;
	}

	//This is all the stuff that needs to be worked with.
    @FXML private TabPane tabPane;
    @FXML private Button goButton;
    @FXML private Button cancelButton;
    @FXML private Button submitButton;
    @FXML private Label fNameL;
    @FXML private Label lNameL;
    @FXML private Label addressL;
    @FXML private Label countyL;
    @FXML private Label stateL;
    @FXML private Label zipL;
    @FXML private Label phoneL;
    @FXML private TextField voteridTF;
    @FXML private TextField fNameTF;
    @FXML private TextField lNameTF;
    @FXML private TextField addressTF;
    @FXML private TextField countyTF;
    @FXML private TextField stateTF;
    @FXML private TextField zipTF;
    @FXML private TextField phoneTF;

    @FXML
	void ClickCancelButton(ActionEvent event) throws Exception {
    	//finds what stage the button is in
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		//gets some fxml file
		root = FXMLLoader.load(getClass().getResource("/view/mainVoterView.fxml"));
		//sets fxml file as a scene
		scene = new Scene(root);
		//loads the scene on top of whatever stage the button is in
		stage.setScene(scene);
	}
    @FXML
	void ClickGoButton(ActionEvent event) {
    	String vID = voteridTF.getText();
    	//debugging
    	System.out.println("ClickGoButton (updateVoterController) vID = " + vID);
    }

    @FXML
    void ClickSubmitUpdateButton(ActionEvent event) {
    	//debugging
    	System.out.println("ClickSubmitUpdateButton (updateVoterController)");
    }


}
