package Controllers;

import Main.DatabaseConnectionManager;
import Main.HelperMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created class GSA_MainController.java
 * <p>
 * This class controls the main screen for the application.
 *
 * @author Jamie Barbour-Moore
 */
public class GSA_MainController implements Initializable {

    /**
     * The Button to allow the user to view the appointments.
     */
    @FXML
    private Button ViewAppointmentButton;
    /**
     * The button to allow the user to view the authors.
     */
    @FXML
    private Button viewAuthorsButton;
    /**
     * The button to allow the user to view the editors.
     */
    @FXML
    private Button ViewEditorsButton;
    /**
     * The button to allow the user to view the reports.
     */
    @FXML
    private Button ViewReportsButton;
    /**
     * The label to welcome the user to the application.
     */
    @FXML
    private Label WelcomeText;
    /**
     * The label for the screen title.
     */
    @FXML
    private Label GSALabel;
    /**
     * The label for the application's localization data.
     */
    @FXML
    private Label LocalizationLabel;
    /**
     * The button to exit the application.
     */
    @FXML
    private Button ExitButton;

    /**
     * This method opens the Appointments screen.
     *
     * @param event the event which triggered this method
     * @throws IOException if load fails
     */
    @FXML
    protected void ViewAppointmentsClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(GSA_AppointmentsController.class.getResource("/GSA_AppointmentsScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        stage.setTitle("Global Scheduling Application - Appointments View");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method opens the View Authors screen.
     *
     * @param event the event which triggered this method
     * @throws IOException if load fails
     */
    @FXML
    protected void viewAuthorsClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(GSA_AuthorController.class.getResource("/GSA_AuthorScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        stage.setTitle("Global Scheduling Application - Author View");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method opens the View Editors screen.
     *
     * @param event the event which triggered this method
     * @throws IOException if load fails
     */
    public void viewEditorsClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(GSA_AuthorController.class.getResource("/GSA_EditorScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        stage.setTitle("Global Scheduling Application - Editor View");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method opens the Reports screen.
     *
     * @param event the event which triggered this method
     * @throws IOException if load fails
     */
    @FXML
    protected void ViewReportsClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(GSA_ReportsController.class.getResource("/GSA_ReportScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);
        stage.setTitle("Global Scheduling Application - Reports View");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method exits the application.
     *
     * @param event the event triggered by the exit button
     */
    @FXML
    protected void onExitButtonClick(ActionEvent event) {
        if (HelperMethods.exitDialog()) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
            DatabaseConnectionManager.closeConnection();
        }
    }

    /**
     * This method initializes the main screen and runs localization for French and English.
     *
     * @param location  the url
     * @param resources the resource bundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LocalizationLabel.setText(HelperMethods.getLocalizationText());
        ExitButton.setText(HelperMethods.getExitButtonText());
        GSALabel.setText(HelperMethods.getHeaderText() + " - " + HelperMethods.getMainScreenText());

        ResourceBundle resourceBundle = ResourceBundle.getBundle("Main", HelperMethods.getLocale());

        viewAuthorsButton.setText(resourceBundle.getString("viewAuthorsText"));
        ViewReportsButton.setText(resourceBundle.getString("viewReportsText"));
        ViewEditorsButton.setText(resourceBundle.getString("viewEditorsText"));
        WelcomeText.setText(resourceBundle.getString("welcomeText"));
        ViewAppointmentButton.setText(resourceBundle.getString("viewAppointmentsText"));
    }


}
