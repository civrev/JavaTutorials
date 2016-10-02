package view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.MainFXApp;
import application.iVoterDBConfig;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Voter;

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

	@FXML  private TableView<Voter> voterTable;
    @FXML  private TableColumn<Voter, String> firstNameColumn;
    @FXML  private TableColumn<Voter, String> lastNameColumn;
    @FXML  private TableColumn<Voter, String> addressColumn;
    @FXML  private TableColumn<Voter, String> zipColumn;
    @FXML  private TableColumn<Voter, String> countyColumn;
    @FXML  private TableColumn<Voter, String> stateColumn;
    @FXML  private TableColumn<Voter, String> phoneColumn;
    @FXML  private TableColumn<Voter, String> vidColumn;

    @FXML private Button cancelButton;
    @FXML private Button goButton;
    @FXML private Button displayAllButton;
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

    	fNameL.setText("");
	    lNameL.setText("");
	    addressL.setText("");
	    locationL.setText("Invalid Voter ID");
	    zipL.setText("");
	    phoneL.setText("");

		try{
		Connection conn = iVoterDBConfig.getConnection();
		Statement statement = conn.createStatement();
    	ResultSet RS = null;
    	String query = ("select * from voter where voterid=" + vID);
    	//this will execute the String 'query' exactly as if you were in SQL console
    	//and it returns a result set which contains everything we want, but we need to decode it first
    	RS = statement.executeQuery(query);
    	//if the query goes through, RS will no longer be null
    	if(RS != null){
    		while (RS.next()) {
  		      // I am getting the data in each column by using the column name directly


  		      fNameL.setText(RS.getString("firstName"));
  		      lNameL.setText(RS.getString("lastName"));
  		      addressL.setText(RS.getString("address"));
  		      locationL.setText(RS.getString("county") + ", " + RS.getString("state"));
  		      zipL.setText(Integer.toString(RS.getInt("zipcode")));
  		      phoneL.setText(Integer.toString(RS.getInt("phone")));

  		    }
		}


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }

    @FXML
	void ClickDisplayAllButton(ActionEvent event) throws Exception {
    	//debugging
    	System.out.println("Display All Button pressed");
    	try{
    		Connection conn = iVoterDBConfig.getConnection();
    		Statement statement = conn.createStatement();
        	ResultSet RS = null;
        	int vID = 0;
        	String firstName = null, lastName = null, address = null,
        			zipCode = null,state = null,county = null,  phoneNumber = null;
        	String query = ("select * from voter");

        	List<Voter> list = new ArrayList<Voter>();
        	ObservableList<Voter> voterList = FXCollections.observableList(list);

        	//this will execute the String 'query' exactly as if you were in SQL console
        	//and it returns a result set which contains everything we want, but we need to decode it first
        	RS = statement.executeQuery(query);
        	//if the query goes through, RS will no longer be null
        		while (RS.next()) {

      		    vID = RS.getInt("voterid");
        		firstName = RS.getString("firstName");
        		lastName = (RS.getString("lastName"));
        		address = (RS.getString("address"));
        		zipCode = (Integer.toString(RS.getInt("zipcode")));
        		county = RS.getString("state");
        		state = RS.getString("state");
        		phoneNumber = (Integer.toString(RS.getInt("phone")));

  		      voterList.add(new Voter(vID, firstName, lastName, address, county, state, zipCode, phoneNumber));
      		    }

        		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
                lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
                addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
                countyColumn.setCellValueFactory(cellData -> cellData.getValue().countyProperty());
                stateColumn.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
                zipColumn.setCellValueFactory(cellData -> cellData.getValue().zipProperty());
                phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
                vidColumn.setCellValueFactory(cellData -> cellData.getValue().vidProperty().asString());
                voterTable.setItems(voterList);


    	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
    }

}
