package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;

// All fields where FXML needs access must be annotated 
// with @FXML, well only private ones, but its better private
// with annotation than public...
public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Reference to the main application.
    // Which is in ch.makery.address parent folder
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded. Since FXML fields should have
     * been initialized already.
     */
    @FXML
    private void initialize() {
        /** Initialize the person table with the two columns. And lets the table
    	* know what info goes in which column.
    	* 
    	* You can only use setCellValueFactory like in this code when you are
    	* using a STRING. If you use a double, int whatever then you
    	* must add .asObject() to the end of it. Example:
    	* myIntegerColumn.setCellValueFactory(cellData -> 
      	* cellData.getValue().myIntegerProperty().asObject());
    	*/
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        
        //We will now add a listener to here is that we can tell when buttons are touched
        // Clear person details. Like a reset for a person
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        // JavaFX changeListener has 3 parts, observable, oldValue,newValue
        //with this listener, when we select a name from the table it will display values associated
        // with that object
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
        }
    
    /**
     * This following method has been added so that all the labels are filled with a setText or cleared
     * 
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object. Labels were set using SceneBuilder
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());

            // TODO: We need a way to convert the birthday into a String! Here it is now!
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
            // birthdayLabel.setText(...);
        } else {
            // if Person is null, remove all the text. Should clear anyone if deleted
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }
    
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }
    
    /**
     * Called when the user clicks on the delete button. this is a listener sets up
     * with SceneBuilder (because of @FXML) in SB we have it handled from Delete button Code
     * in PersonOverview.fxml we select this new method handleDeletePerson to Delete Button On Action
     * this also causes an error in getSelectedIndex() when no Person is selected
     * so to handle this we added a pop up warning if selected index is -1 *nothing
     * so that only a popup occurs onAction
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected. Popup needs many of the same stuff as a basic window
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
}