package ch.makery.address;

import java.io.IOException;

import ch.makery.address.model.Person;
import ch.makery.address.view.PersonEditDialogController;
import ch.makery.address.view.PersonOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	//Initializes the State
    private Stage primaryStage;
    // we will set RootLayer.fxml
    private BorderPane rootLayout;

    // ... AFTER THE OTHER VARIABLES ...

    /**
     * The data as an observable list of Persons. Observable as in its not some just array or anything but
     * an actual list visible to the end user. This variable is initialized here. Our <Person> is 
     * from the ch.makery.model folder cause it is an observable list of <Person>s.
     */
    private ObservableList<Person> personData = FXCollections.observableArrayList();

    /**
     * Constructor. This will just add <Person>s to our list of <Person>s which is called personData
     */
    public MainApp() {
        // Add some sample data for now
        personData.add(new Person("Hans", "Muster"));
        personData.add(new Person("Ruth", "Mueller"));
        personData.add(new Person("Heinz", "Kurz"));
        personData.add(new Person("Cornelia", "Meier"));
        personData.add(new Person("Werner", "Meyer"));
        personData.add(new Person("Lydia", "Kunz"));
        personData.add(new Person("Anna", "Best"));
        personData.add(new Person("Stefan", "Meier"));
        personData.add(new Person("Martin", "Mueller"));
    }

    /**
     * Returns the data as an observable list of Persons, when getPersonData is done. 
     * @return 
     */
    public ObservableList<Person> getPersonData() {
        return personData;
    }

    // ... THE REST OF THE CLASS ...
    
    
    // a stage is the main controller.
    // start(...) method receives a stage as
    // a parameter. A stage is really just like the border
    // and X minimize and close buttons. But it is
    // the place for everything to begin.
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        showPersonOverview();
    }

    /**
     * Initializes the root layout.
     * this is important, a stage then has a scene displayed on it.
     * in this case, RootLayout.fxml. A scene can be switched out
     * by another scene, so multiple displays inside one program.
     * inside the scene actual JavaFX nodes, like our AnchorPane and TextBox
     * are added. RootLayout is basic, so we will later load some stuff to
     * go on it.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file so that it can be put in the scene
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            // after loading it sets RootLayout.fxml to a private variable to manage
            // throughout the program. It was initialized at the top.
            rootLayout = (BorderPane) loader.load();

            /* Show the scene containing the root layout.
            * So its telling the program to show RootLayout
            * as the scene right now. 
            */     
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview, so it can become accessible
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
            
            // Give the controller access to the main app. PersonOverviewController
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     * 
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showPersonEditDialog(Person person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}