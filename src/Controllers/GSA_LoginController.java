package Controllers;

import DataStructures.Appointment;
import DatabaseAccess.AppointmentDatabaseAccess;
import DatabaseAccess.EmployeeDatabaseAccess;
import Main.DatabaseConnectionManager;
import Main.HelperMethods;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created class GSA_LoginController.java
 * <p>
 * This class controls the login screen for the application.
 *
 * @author Jamie Barbour-Moore
 */
public class GSA_LoginController implements Initializable {
    /**
     * The label for the login screen's password input field.
     */
    @FXML
    private Label PasswordLabel;

    /**
     * The label for the login screen's username input field.
     */
    @FXML
    private Label UserNameLabel;

    /**
     * The label for the login screen's localization display.
     */
    @FXML
    private Label LocalizationLabel;

    /**
     * The label for the global scheduling application.
     */
    @FXML
    private Label GSALabel;

    /**
     * The button to submit the login form.
     */
    @FXML
    private Button LoginButton;

    /**
     * The button to exit the application.
     */
    @FXML
    private Button ExitButton;

    /**
     * The Password field so the user can enter their password.
     */
    @FXML
    private PasswordField PasswordInputField;

    /**
     * The Text field so the user can enter their username.
     */
    @FXML
    private TextField UserNameInputField;


    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String loginAlertTitle;
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String loginAlertHeader;
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String loginAlertDialog;
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String appFoundAlertTitle;
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String appFoundAlertHeader;
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String appFoundAlertDialog;
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String appNotFoundAlertTitle;
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String appNotFoundAlertHeader;
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String appNotFoundAlertDialog;

    /**
     * This method exits the Application
     *
     * @param event the data from the button press event which triggered this method
     */
    @FXML
    public void onExitButtonClick(ActionEvent event) {
        if (HelperMethods.exitDialog()) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
            DatabaseConnectionManager.closeConnection();
        }
    }

    /**
     * This method attempts to log the user in when they press "login".
     * It also records all login attempts to a text file.
     * It also checks if there are any appointments within 15 minutes of the user's login.
     *
     * @param event the event which triggered this method
     * @throws IOException when the load fails
     */
    @FXML
    protected void onLoginButtonClick(ActionEvent event) throws IOException {
        String enteredPassword = PasswordInputField.getText();
        String enteredUsername = UserNameInputField.getText();

        try {
            Boolean userAndPasswordExists = EmployeeDatabaseAccess.checkUserAndPassword(enteredUsername, enteredPassword);

            FileWriter fileWriter = new FileWriter("LoginHistory.txt", true);
            PrintWriter outputFile = new PrintWriter(fileWriter);

            if (userAndPasswordExists) {
                DatabaseConnectionManager.setCurrentUser(enteredUsername);
                outputFile.print(enteredUsername + " logged in at " + Timestamp.valueOf(LocalDateTime.now()) + "\n");
                outputFile.close();

                try {
                    checkForAppointmentWithinTimeFrameMinutes(15);
                } catch (Exception e) {
                }

                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(GSA_MainController.class.getResource("/GSA_MainScreen.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 640, 400);
                stage.setTitle("Global Scheduling Application - Main View");
                stage.setScene(scene);
                stage.show();
            } else {
                outputFile.print("Failed Login Attempt : " + enteredUsername + " at " + Timestamp.valueOf(LocalDateTime.now()) + "\n");
                outputFile.close();

                HelperMethods.infoDialog(loginAlertTitle, loginAlertHeader, loginAlertDialog);
            }
        } catch (Exception e) {
            HelperMethods.infoDialog(loginAlertTitle, loginAlertHeader, loginAlertDialog);
        }
    }

    /**
     * This method checks for appointments within a certain time window.
     *
     * @param timeFrameInMinutes how large a time window you wish to check
     * @throws SQLException if the database access fails
     */
    private void checkForAppointmentWithinTimeFrameMinutes(int timeFrameInMinutes) throws SQLException {
        ObservableList<Appointment> allAppointments = AppointmentDatabaseAccess.getAllAppointments();

        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
        LocalDateTime nowInUTC = HelperMethods.getDateTimeForUTC(now);
        LocalDateTime plusTimeframe = nowInUTC.plusMinutes(timeFrameInMinutes);
        LocalDateTime minusTimeFrame = nowInUTC.minusMinutes(timeFrameInMinutes);

        LocalDateTime start_Time = null;
        LocalDateTime found_start_time = null;
        int appointment_ID = 0;
        boolean userHasAppointmentSoon = false;

        for (Appointment appointment : allAppointments) {
            start_Time = appointment.getStart();
            if ((start_Time.isAfter(minusTimeFrame) || start_Time.isEqual(minusTimeFrame)) && (start_Time.isBefore(plusTimeframe) || (start_Time.isEqual(plusTimeframe)))) {
                appointment_ID = appointment.getAppointment_ID();
                found_start_time = start_Time;
                userHasAppointmentSoon = true;
            }
        }

        if (userHasAppointmentSoon) {
            HelperMethods.infoDialog(appFoundAlertTitle, String.format(appFoundAlertHeader, timeFrameInMinutes), String.format(appFoundAlertDialog, appointment_ID, HelperMethods.formatDateTimeDisplay(found_start_time)));
        } else {
            HelperMethods.infoDialog(appNotFoundAlertTitle, String.format(appNotFoundAlertHeader, timeFrameInMinutes), String.format(appNotFoundAlertDialog, timeFrameInMinutes));
        }
    }

    /**
     * This method initializes the login screen and runs localization for French and English.
     *
     * @param location  the url
     * @param resources the resource bundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        HelperMethods.setLocale(Locale.getDefault());

        ResourceBundle resourceBundle = ResourceBundle.getBundle("Login", HelperMethods.getLocale());

        HelperMethods.setLocalizationText(resourceBundle.getString("LocalizationText") + ZoneId.systemDefault());
        LocalizationLabel.setText(HelperMethods.getLocalizationText());
        HelperMethods.setExitDialog(resourceBundle.getString("exitDialogTitle"), resourceBundle.getString("exitDialogHeader"), resourceBundle.getString("exitDialogMessage"));

        loginAlertTitle = resourceBundle.getString("loginAlertTitle");
        loginAlertHeader = resourceBundle.getString("loginAlertHeader");
        loginAlertDialog = resourceBundle.getString("loginAlertDialog");

        PasswordLabel.setText(resourceBundle.getString("PasswordLabelText"));
        UserNameLabel.setText(resourceBundle.getString("UserNameLabelText"));
        LoginButton.setText(resourceBundle.getString("LoginButtonText"));

        HelperMethods.setExitButtonText(resourceBundle.getString("ExitButtonText"));
        ExitButton.setText(HelperMethods.getExitButtonText());
        HelperMethods.setHeaderText(resourceBundle.getString("GSALabelText"));
        GSALabel.setText(HelperMethods.getHeaderText());

        appFoundAlertTitle = resourceBundle.getString("appFoundAlertTitle");
        appFoundAlertHeader = resourceBundle.getString("appFoundAlertHeader");
        appFoundAlertDialog = resourceBundle.getString("appFoundAlertDialog");

        appNotFoundAlertTitle = resourceBundle.getString("appNotFoundAlertTitle");
        appNotFoundAlertHeader = resourceBundle.getString("appNotFoundAlertHeader");
        appNotFoundAlertDialog = resourceBundle.getString("appNotFoundAlertDialog");

        HelperMethods.setBackDialog(resourceBundle.getString("backDialogTitle"), resourceBundle.getString("backDialogHeader"), resourceBundle.getString("backDialogMessage"));
        HelperMethods.setBackButtonText(resourceBundle.getString("BackButtonText"));

        HelperMethods.setMainScreenText(resourceBundle.getString("mainScreenText"));
        HelperMethods.setAppointmentScreenText(resourceBundle.getString("appointmentScreenText"));
        HelperMethods.setAuthorScreenText(resourceBundle.getString("authorScreenText"));
        HelperMethods.setReportScreenText(resourceBundle.getString("reportScreenText"));
        HelperMethods.setEditorScreenText(resourceBundle.getString("editorScreenText"));
    }

}