package view;

import application.MainFXApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class displayVotersController {

	Stage stage;
	Scene scene;
	Parent root;

	//always reference main method, and build constructor
	private MainFXApp main;
	public void setMain(MainFXApp mainIn)
	{
	main=mainIn;
	}

    @FXML private Button cancelButton;
    @FXML private Button goButton;
    @FXML private Label fNameL;
    @FXML private Label lNameL;
    @FXML private Label addressL;
    @FXML private Label locationL;
    @FXML private Label zipL;
    @FXML private Label phoneL;
    @FXML private TextField voteridTF;

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
    	System.out.println("ClickGoButton vID = " + vID);
    }


}
