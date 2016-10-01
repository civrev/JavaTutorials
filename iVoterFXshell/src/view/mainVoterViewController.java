//    This is a JavaFX shell of an iVoter program I got from my professor.
//    I have stripped it of all functionality and removed the combo box.
//    https://github.com/abegaz <- The original author of this program

package view;

import application.MainFXApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class mainVoterViewController {

	Stage stage;
	Scene scene;
	Parent root;
	//always reference main method, and build constructor
	private MainFXApp main;
	public void setMain(MainFXApp mainIn)
	{
	main=mainIn;
	}

	@FXML private Button displayButton;
    @FXML private Button addButton;
    @FXML private Button updateButton;

    @FXML
	void ClickButton(ActionEvent event) throws Exception {
    	try{
    	//finds what stage the button is in
		stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
		//this gets the name of button
		String temp = ((Node) event.getSource()).getId().toString();
		//debugging
		System.out.println("(Node) event.getSource()).getId().toString() = " + temp);
		//this will allow for all buttons to go through one method, loading the
		//right fxml file

		switch (temp) {
		case "displayButton":
			root = FXMLLoader.load(getClass().getResource("/view/displayVoters.fxml"));
			displayVotersController con1=new displayVotersController();
			con1.setMain(main);
			break;
		case "addButton":
			root = FXMLLoader.load(getClass().getResource("/view/addVoter.fxml"));
			addVoterController con2=new addVoterController();
			con2.setMain(main);
			break;
		case "updateButton":
			root = FXMLLoader.load(getClass().getResource("/view/updateVoter.fxml"));
			updateVoterController con3=new updateVoterController();
			con3.setMain(main);
			break;
		default:
			root = FXMLLoader.load(getClass().getResource("/view/mainVoterView.fxml"));
			mainVoterViewController con4=new mainVoterViewController();
			con4.setMain(main);
			break;
		}
		//sets fxml file as a scene
		scene = new Scene(root);
		//loads the scene on top of whatever stage the button is in
		stage.setScene(scene);
	} catch (Exception e){
		e.printStackTrace();
	}
    }


}
