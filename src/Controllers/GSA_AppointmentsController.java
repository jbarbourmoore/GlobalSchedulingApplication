package Controllers;

import DataStructures.Appointment;
import DataStructures.Employee;
import DataStructures.Person;
import DatabaseAccess.AppointmentDatabaseAccess;
import DatabaseAccess.AuthorDatabaseAccess;
import DatabaseAccess.EditorDatabaseAccess;
import DatabaseAccess.EmployeeDatabaseAccess;
import Main.DatabaseConnectionManager;
import Main.HelperMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * Created class GSA_AppointmentsController.java
 * <p>
 * This class controls the appointment screen for the application.
 *
 * @author Jamie Barbour-Moore
 */
public class GSA_AppointmentsController implements Initializable {

    /**
     * the label for the app page
     */
    @FXML
    private Label GSALabel;
    /**
     * the label for the localization information
     */
    @FXML
    private Label LocalizationLabel;
    /**
     * The label for the input field for selected appointment title.
     */
    @FXML
    private Label titleLabel;
    /**
     * The label for the input field for selected appointment description.
     */
    @FXML
    private Label descriptionLabel;
    /**
     * The label for the input field for selected appointment location.
     */
    @FXML
    private Label locationLabel;
    /**
     * The label for the input field for selected appointment editor.
     */
    @FXML
    private Label editorLabel;
    /**
     * The label for the input field for selected appointment start date.
     */
    @FXML
    private Label startDateLabel;
    /**
     * The label for the input field for selected appointment start time.
     */
    @FXML
    private Label startTimeLabel;
    /**
     * The label for the input field for selected appointment end date.
     */
    @FXML
    private Label endDateLabel;
    /**
     * The label for the input field for selected appointment end time.
     */
    @FXML
    private Label endTimeLabel;
    /**
     * The label for the input field for selected appointment employee.
     */
    @FXML
    private Label employeeLabel;
    /**
     * The label for the input field for selected appointment author.
     */
    @FXML
    private Label authorLabel;
    /**
     * The label for the input field for selected appointment type.
     */
    @FXML
    private Label typeLabel;
    /**
     * The label for the input field for selected appointment create date.
     */
    @FXML
    private Label createDateLabel;
    /**
     * The label for the input field for selected appointment created by.
     */
    @FXML
    private Label createdByLabel;
    /**
     * The label for the input field for selected appointment update date.
     */
    @FXML
    private Label updateDateLabel;
    /**
     * The label for the input field for selected appointment updated by.
     */
    @FXML
    private Label updatedByLabel;
    /**
     * The dropdown for the selected appointment employee.
     */
    @FXML
    private ChoiceBox<String> EmployeeDropdown;
    /**
     * The dropdown for the selected appointment author.
     */
    @FXML
    private ChoiceBox<String> AuthorDropdown;
    /**
     * The text field for the selected appointment create date.
     */
    @FXML
    private TextField CreateDateText;
    /**
     * The text field for the selected appointment created by.
     */
    @FXML
    private TextField CreatedByText;
    /**
     * The text field for the selected appointment update date.
     */
    @FXML
    private TextField UpdateDateText;
    /**
     * The text field for the selected appointment updated by.
     */
    @FXML
    private TextField UpdatedByText;
    /**
     * The radio button for the selected appointment monthly view.
     */
    @FXML
    private RadioButton MonthlyViewRadio;
    /**
     * The radio button for the selected appointment all view.
     */
    @FXML
    private RadioButton AllViewRadio;
    /**
     * The button to unselect the appointment.
     */
    @FXML
    private Button UnselectAppButton;
    /**
     * The button to add the appointment.
     */
    @FXML
    private Button AddAppButton;
    /**
     * The button to modify the appointment.
     */
    @FXML
    private Button ModAppButton;
    /**
     * The button to delete the appointment.
     */
    @FXML
    private Button DeleteAppButton;
    /**
     * The table view for the appointments.
     */
    @FXML
    private TableView<Appointment> ScheduleTable;
    /**
     * The table column for the appointments' id.
     */
    @FXML
    private TableColumn<?, ?> ScheduleTableIDColumn;
    /**
     * The table column for the appointments' title.
     */
    @FXML
    private TableColumn<?, ?> ScheduleTableTitleColumn;
    /**
     * The table column for the appointments' description.
     */
    @FXML
    private TableColumn<?, ?> ScheduleTableDesColumn;
    /**
     * The table column for the appointments' location.
     */
    @FXML
    private TableColumn<?, ?> ScheduleTableLocColumn;
    /**
     * The table column for the appointments' contact.
     */
    @FXML
    private TableColumn<?, ?> ScheduleTableEditorColumn;
    /**
     * The table column for the appointments' type.
     */
    @FXML
    private TableColumn<?, ?> ScheduleTableTypeColumn;
    /**
     * The table column for the appointments' start date and time.
     */
    @FXML
    private TableColumn<?, ?> ScheduleTableStartColumn;
    /**
     * The table column for the appointments' end date and time.
     */
    @FXML
    private TableColumn<?, ?> ScheduleTableEndColumn;
    /**
     * The table column for the appointments' customer id.
     */
    @FXML
    private TableColumn<?, ?> ScheduleTableAuthorColumn;
    /**
     * The table column for the appointments' user id.
     */
    @FXML
    private TableColumn<?, ?> ScheduleTableEmployeeColumn;
    /**
     * The choice box for the start time options.
     */
    @FXML
    private TextField StartTimeText;
    /**
     * The choice box for the end time options.
     */
    @FXML
    private TextField EndTimeText;
    /**
     * The choice box for the contact options.
     */
    @FXML
    private ChoiceBox<String> EditorDropdown;
    /**
     * The radio button for the weekly schedule view.
     */
    @FXML
    private RadioButton WeeklyViewRadio;
    /**
     * The text field for the selected Appointment ID.
     */
    @FXML
    private TextField ApIdText;
    /**
     * The text field for the selected Appointment Title.
     */
    @FXML
    private TextField TitleText;
    /**
     * The text field for the selected Appointment Description.
     */
    @FXML
    private TextField DescriptionText;
    /**
     * The text field for the selected Appointment location.
     */
    @FXML
    private TextField LocationText;
    /**
     * The text field for the selected Appointment type.
     */
    @FXML
    private TextField ApTypeText;
    /**
     * The date picker for the selected Appointment Start
     */
    @FXML
    private DatePicker StartDatePicker;
    /**
     * The date picker for the selected appointment end.
     */
    @FXML
    private DatePicker EndDatePicker;
    /**
     * the button to go back to the main screen
     */
    @FXML
    private Button BackButton;

    /**
     * the button to exit the application
     */
    @FXML
    private Button ExitButton;

    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String deleteAppConfirmTitle = "Delete appointment?";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String confirmString = "Confirm";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String deleteAppConfirmMessage = "Would you like to delete the selected appointment? ID: %d Type: %s";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String deleteFailedTitle = "Cannot Delete Appointment";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String deleteFailedHeader = "No Appointment Selected";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String deleteFailedMessage = "Please select the appointment you want to delete";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String confirmModAppTitle = "Modify appointment?";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String confirmModAppMessage = "Would you like to modify the selected appointment?";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String modAppFailedTitle = "Modify Appointment Failed";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String modAppFailedHeader = "Unable to Modify Appointment";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String appOutsideBusinessHoursString = "Appointment Time is outside of the company's business day.";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String appOverlapString = "Appointments cannot overlap.";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String checkInputsString = "Please check all your inputs and try again.";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String noAppSelectedString = "No Appointment Selected";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String selectAnAppString = "Please select an appointment";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String confirmAddAppTitle = "Add appointment?";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String confirmAddAppMessage = "Would you like to add a new appointment with the information you have entered?";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String addAppFailedTitle = "Add Appointment Failed";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String addAppFailedHeader = "Unable to Add Appointment";

    /**
     * The observable list containing all the appointments.
     */
    private ObservableList<Appointment> allAppointments;

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
     * This method swaps the table between events in the next week and events during the current month.
     **/
    @FXML
    protected void ViewRadioPressed() {
        unselect();
        if (WeeklyViewRadio.isSelected()) {
            displayAppointmentsThisWeek();
        } else if (MonthlyViewRadio.isSelected()) {
            displayAppointmentsThisMonth();
        } else {
            ScheduleTable.setItems(allAppointments);
        }
    }


    /**
     * This method adds the appointment that the user has inputted data for.
     */
    @FXML
    protected void AddAppClicked() {
        if (HelperMethods.confirmDialog(confirmAddAppTitle, confirmString, confirmAddAppMessage)) {
            Appointment appointmentToAdd = ScheduleTable.getSelectionModel().getSelectedItem();
            if (appointmentToAdd == null) {
                Appointment addedAppointment;
                try {
                    if (appointmentDataValid()) {
                        if (customerAppointmentsDoNotOverlap()) {
                            if (appointmentTimesDuringBusinessDay()) {
                                int addedAppointmentID = getNewID();
                                addedAppointment = createAppointmentObjectFromForm(addedAppointmentID);
                                AppointmentDatabaseAccess.addAppointment(addedAppointment);
                                allAppointments = AppointmentDatabaseAccess.getAllAppointments();
                                ViewRadioPressed();
                            } else {
                                HelperMethods.infoDialog(addAppFailedTitle, addAppFailedHeader, appOutsideBusinessHoursString);
                            }
                        } else {
                            HelperMethods.infoDialog(addAppFailedTitle, addAppFailedHeader, appOverlapString);
                        }
                    } else {
                        HelperMethods.infoDialog(addAppFailedTitle, addAppFailedHeader, checkInputsString);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    HelperMethods.infoDialog(addAppFailedTitle, addAppFailedHeader, checkInputsString);
                }
            } else {
                HelperMethods.infoDialog(addAppFailedHeader, noAppSelectedString, selectAnAppString);
            }
        }
    }

    /**
     * This method modifies the appointment that the user has selected.
     */
    @FXML
    protected void ModifyAptClicked() {
        if (HelperMethods.confirmDialog(confirmModAppTitle, confirmString, confirmModAppMessage)) {

            Appointment appointmentToModify = ScheduleTable.getSelectionModel().getSelectedItem();
            if (appointmentToModify != null) {
                Appointment modifiedAppointment;
                try {
                    if (appointmentDataValid()) {
                        if (customerAppointmentsDoNotOverlap(appointmentToModify.getAppointment_ID())) {
                            if (appointmentTimesDuringBusinessDay()) {
                                int modifiedAppointmentID = appointmentToModify.getAppointment_ID();
                                modifiedAppointment = createAppointmentObjectFromForm(modifiedAppointmentID);
                                AppointmentDatabaseAccess.modifyAppointment(modifiedAppointment);
                                allAppointments = AppointmentDatabaseAccess.getAllAppointments();
                                ViewRadioPressed();
                            } else {
                                HelperMethods.infoDialog(modAppFailedTitle, modAppFailedHeader, appOutsideBusinessHoursString);
                            }
                        } else {
                            HelperMethods.infoDialog(modAppFailedTitle, modAppFailedHeader, appOverlapString);
                        }
                    } else {
                        HelperMethods.infoDialog(modAppFailedTitle, modAppFailedHeader, checkInputsString);
                    }
                } catch (Exception e) {
                    HelperMethods.infoDialog(modAppFailedTitle, modAppFailedHeader, checkInputsString);
                }
            } else {
                HelperMethods.infoDialog(modAppFailedTitle, noAppSelectedString, selectAnAppString);
            }
        }

    }

    /**
     * This method checks to see whether the inputs are valid to save or modify the appointment.
     *
     * @return a boolean which is true if the inputs are valid
     */
    private boolean appointmentDataValid() {
        boolean isDataValid = true;
        try {
            LocalDateTime appointmentStart = LocalDateTime.of(StartDatePicker.getValue(), LocalTime.parse(StartTimeText.getText()));
            LocalDateTime appointmentEnd = LocalDateTime.of(EndDatePicker.getValue(), LocalTime.parse(EndTimeText.getText()));

            if (TitleText.getText().equalsIgnoreCase("") || DescriptionText.getText().equalsIgnoreCase("") || LocationText.getText().equalsIgnoreCase("") || ApTypeText.getText().equalsIgnoreCase("") || StartTimeText.getText().equalsIgnoreCase("") || EndTimeText.getText().equalsIgnoreCase("")) {
                isDataValid = false;
            } else if (EditorDropdown.getSelectionModel().getSelectedItem() == null || EmployeeDropdown.getSelectionModel().getSelectedItem() == null || AuthorDropdown.getSelectionModel().getSelectedItem() == null) {
                isDataValid = false;
            } else if (appointmentStart.isAfter(appointmentEnd) || appointmentEnd.isEqual(appointmentStart)) {
                isDataValid = false;
            }
        } catch (Exception e) {
            isDataValid = false;
        }
        return isDataValid;
    }

    /**
     * This method checks to see whether the appointment is during the business day.
     * Business Day is 8 am to 10 pm est.
     *
     * @return a boolean which is true if the appointment is during the business day
     */
    private boolean appointmentTimesDuringBusinessDay() {
        boolean duringBusinessDay = true;

        LocalTime earliest = LocalTime.MIN.plusHours(8);
        LocalTime latest = LocalTime.MAX.minusHours(2);

        LocalTime startTimeSystemDefault = LocalTime.parse(StartTimeText.getText());
        LocalTime startTimeEST = HelperMethods.convertLocalTimeToEST(startTimeSystemDefault);
        LocalTime endTimeSystemDefault = LocalTime.parse(EndTimeText.getText());
        LocalTime endTimeEST = HelperMethods.convertLocalTimeToEST(endTimeSystemDefault);

        if (startTimeEST.isBefore(earliest) || startTimeEST.isAfter(latest) || endTimeEST.isBefore(earliest) || endTimeEST.isAfter(latest)) {
            duringBusinessDay = false;
        }
        return duringBusinessDay;

    }

    /**
     * This method makes sure employee, author or editor do not end up with overlapping appointments.
     *
     * @param appointmentID the id of the appointment being modified
     * @return a boolean that is true if there will be no overlap for the employee, author or editor
     * @throws SQLException if database access fails
     */
    private boolean customerAppointmentsDoNotOverlap(int appointmentID) throws SQLException {
        boolean isDataValid = true;

        LocalDateTime appointmentStart = LocalDateTime.of(StartDatePicker.getValue(), LocalTime.parse(StartTimeText.getText()));
        LocalDateTime appointmentEnd = LocalDateTime.of(EndDatePicker.getValue(), LocalTime.parse(EndTimeText.getText()));

        LocalDateTime appointmentStartUTC = HelperMethods.getDateTimeForUTC(appointmentStart);
        LocalDateTime appointmentEndUTC = HelperMethods.getDateTimeForUTC(appointmentEnd);
        if (EditorDropdown.getSelectionModel().getSelectedItem() != null) {
            int appointmentEditorID = 0;
            ObservableList<Person> allEditors = EditorDatabaseAccess.getAllEditors();
            for (Person editor : allEditors) {
                if (editor.getName().equalsIgnoreCase(EditorDropdown.getSelectionModel().getSelectedItem())) {
                    appointmentEditorID = editor.getID();
                }
            }
            ObservableList<Appointment> allAppointments = AppointmentDatabaseAccess.getAllAppointments();
            for (Appointment appointment : allAppointments) {
                if (appointment.getEditor_ID() == appointmentEditorID && appointment.getAppointment_ID() != appointmentID) {
                    if (appointment.getStart().isBefore(appointmentEndUTC) && appointment.getEnd().isAfter(appointmentStartUTC)) {
                        isDataValid = false;
                    }
                }
            }
        }

        if (AuthorDropdown.getSelectionModel().getSelectedItem() != null) {
            int appointmentAuthorID = 0;
            ObservableList<Person> allAuthors = AuthorDatabaseAccess.getAllAuthors();
            for (Person author : allAuthors) {
                if (author.getName().equalsIgnoreCase(AuthorDropdown.getSelectionModel().getSelectedItem())) {
                    appointmentAuthorID = author.getID();
                }
            }
            ObservableList<Appointment> allAppointments = AppointmentDatabaseAccess.getAllAppointments();
            for (Appointment appointment : allAppointments) {
                if (appointment.getAuthor_ID() == appointmentAuthorID && appointment.getAppointment_ID() != appointmentID) {
                    if (appointment.getStart().isBefore(appointmentEndUTC) && appointment.getEnd().isAfter(appointmentStartUTC)) {
                        isDataValid = false;
                    }
                }
            }
        }

        if (EmployeeDropdown.getSelectionModel().getSelectedItem() != null) {
            int appointmentEmployeeID = 0;
            ObservableList<Employee> allEmployees = EmployeeDatabaseAccess.getAllEmployees();
            for (Employee employee : allEmployees) {
                if (employee.getName().equalsIgnoreCase(EmployeeDropdown.getSelectionModel().getSelectedItem())) {
                    appointmentEmployeeID = employee.getID();
                }
            }
            ObservableList<Appointment> allAppointments = AppointmentDatabaseAccess.getAllAppointments();
            for (Appointment appointment : allAppointments) {
                if (appointment.getEmployee_ID() == appointmentEmployeeID && appointment.getAppointment_ID() != appointmentID) {
                    if (appointment.getStart().isBefore(appointmentEndUTC) && appointment.getEnd().isAfter(appointmentStartUTC)) {
                        isDataValid = false;
                    }
                }
            }
        }

        return isDataValid;
    }

    /**
     * This method makes sure customers do not end up with overlapping appointments.
     *
     * @return a boolean that is true if there will be no overlap for customers
     * @throws SQLException if database access fails
     */
    private boolean customerAppointmentsDoNotOverlap() throws SQLException {
        boolean isDataValid = true;

        LocalDateTime appointmentStart = LocalDateTime.of(StartDatePicker.getValue(), LocalTime.parse(StartTimeText.getText()));
        LocalDateTime appointmentEnd = LocalDateTime.of(EndDatePicker.getValue(), LocalTime.parse(EndTimeText.getText()));

        LocalDateTime appointmentStartUTC = HelperMethods.getDateTimeForUTC(appointmentStart);
        LocalDateTime appointmentEndUTC = HelperMethods.getDateTimeForUTC(appointmentEnd);
        if (EditorDropdown.getSelectionModel().getSelectedItem() != null) {
            int appointmentEditorID = 0;
            ObservableList<Person> allEditors = EditorDatabaseAccess.getAllEditors();
            for (Person editor : allEditors) {
                if (editor.getName().equalsIgnoreCase(EditorDropdown.getSelectionModel().getSelectedItem())) {
                    appointmentEditorID = editor.getID();
                }
            }
            ObservableList<Appointment> allAppointments = AppointmentDatabaseAccess.getAllAppointments();
            for (Appointment appointment : allAppointments) {
                if (appointment.getEditor_ID() == appointmentEditorID) {
                    if (appointment.getStart().isBefore(appointmentEndUTC) && appointment.getEnd().isAfter(appointmentStartUTC)) {
                        isDataValid = false;
                    }
                }
            }
        }

        if (AuthorDropdown.getSelectionModel().getSelectedItem() != null) {
            int appointmentAuthorID = 0;
            ObservableList<Person> allAuthors = AuthorDatabaseAccess.getAllAuthors();
            for (Person author : allAuthors) {
                if (author.getName().equalsIgnoreCase(AuthorDropdown.getSelectionModel().getSelectedItem())) {
                    appointmentAuthorID = author.getID();
                }
            }
            ObservableList<Appointment> allAppointments = AppointmentDatabaseAccess.getAllAppointments();
            for (Appointment appointment : allAppointments) {
                if (appointment.getAuthor_ID() == appointmentAuthorID) {
                    if (appointment.getStart().isBefore(appointmentEndUTC) && appointment.getEnd().isAfter(appointmentStartUTC)) {
                        isDataValid = false;
                    }
                }
            }
        }

        if (EmployeeDropdown.getSelectionModel().getSelectedItem() != null) {
            int appointmentEmployeeID = 0;
            ObservableList<Employee> allEmployees = EmployeeDatabaseAccess.getAllEmployees();
            for (Employee employee : allEmployees) {
                if (employee.getName().equalsIgnoreCase(EmployeeDropdown.getSelectionModel().getSelectedItem())) {
                    appointmentEmployeeID = employee.getID();
                }
            }
            ObservableList<Appointment> allAppointments = AppointmentDatabaseAccess.getAllAppointments();
            for (Appointment appointment : allAppointments) {
                if (appointment.getEmployee_ID() == appointmentEmployeeID) {
                    if (appointment.getStart().isBefore(appointmentEndUTC) && appointment.getEnd().isAfter(appointmentStartUTC)) {
                        isDataValid = false;
                    }
                }
            }
        }

        return isDataValid;
    }

    /**
     * This method creates an appointment object based on the information in the form.
     *
     * @param appointmentID the appointment's id
     * @return an appointment object
     * @throws SQLException if the database access fails
     */
    private Appointment createAppointmentObjectFromForm(int appointmentID) throws SQLException {
        Appointment appointment;

        String appointmentTitle = TitleText.getText();
        String appointmentDescription = DescriptionText.getText();
        String appointmentLocation = LocationText.getText();
        String appointmentType = ApTypeText.getText();
        LocalDateTime appointmentStart = LocalDateTime.of(StartDatePicker.getValue(), LocalTime.parse(StartTimeText.getText()));
        LocalDateTime appointmentEnd = LocalDateTime.of(EndDatePicker.getValue(), LocalTime.parse(EndTimeText.getText()));

        String appointmentEditorName = EditorDropdown.getSelectionModel().getSelectedItem();
        int appointmentEditorID = 0;
        ObservableList<Person> allEditors = EditorDatabaseAccess.getAllEditors();
        for (Person editor : allEditors) {
            if (editor.getName().equalsIgnoreCase(appointmentEditorName)) {
                appointmentEditorID = editor.getID();
            }
        }

        String appointmentAuthorName = AuthorDropdown.getSelectionModel().getSelectedItem();
        int appointmentAuthorID = 0;
        ObservableList<Person> allAuthors = AuthorDatabaseAccess.getAllAuthors();
        for (Person author : allAuthors) {
            if (author.getName().equalsIgnoreCase(appointmentAuthorName)) {
                appointmentAuthorID = author.getID();
            }
        }

        String appointmentEmployeeName = EmployeeDropdown.getSelectionModel().getSelectedItem();
        int appointmentEmployeeID = 0;
        ObservableList<Employee> allEmployees = EmployeeDatabaseAccess.getAllEmployees();
        for (Employee employee : allEmployees) {
            if (employee.getName().equalsIgnoreCase(appointmentEmployeeName)) {
                appointmentEmployeeID = employee.getID();
            }
        }

        appointment = new Appointment(appointmentID, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, appointmentAuthorID, appointmentEmployeeID, appointmentEditorID);

        return appointment;
    }

    /**
     * This method deletes the appointment that the user has selected.
     *
     * @throws SQLException may be thrown if database access fails
     */
    @FXML
    protected void DeleteAppClicked() throws SQLException {
        Appointment appointmentToDelete = ScheduleTable.getSelectionModel().getSelectedItem();
        if (appointmentToDelete != null) {
            if (HelperMethods.confirmDialog(deleteAppConfirmTitle, confirmString, String.format(deleteAppConfirmMessage, appointmentToDelete.getAppointment_ID(), appointmentToDelete.getType()))) {
                AppointmentDatabaseAccess.deleteAppointment(appointmentToDelete.getAppointment_ID());
                allAppointments = AppointmentDatabaseAccess.getAllAppointments();
                ViewRadioPressed();
            }
        } else {
            HelperMethods.infoDialog(deleteFailedTitle, deleteFailedHeader, deleteFailedMessage);
        }

    }

    /**
     * This method takes the user back to the main screen.
     *
     * @param event the event which triggered this method
     * @throws IOException may be thrown if load fails
     */
    @FXML
    protected void BackClicked(ActionEvent event) throws IOException {
        if (HelperMethods.backDialog()) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(GSA_MainController.class.getResource("/GSA_MainScreen.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 640, 400);
            stage.setTitle("Global Scheduling Application - Main View");
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * This method initializes the appointment view.
     * It loads all appointments into the table, and fills in the drop-down options.
     *
     * @param location  the url
     * @param resources the resource bundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LocalizationLabel.setText(HelperMethods.getLocalizationText());
        ExitButton.setText(HelperMethods.getExitButtonText());
        GSALabel.setText(HelperMethods.getHeaderText() + " - " + HelperMethods.getAppointmentScreenText());
        BackButton.setText(HelperMethods.getBackButtonText());

        ResourceBundle resourceBundle = ResourceBundle.getBundle("Appointment", HelperMethods.getLocale());

        ScheduleTableTitleColumn.setText(resourceBundle.getString("titleText"));
        ScheduleTableDesColumn.setText(resourceBundle.getString("descriptionText"));
        ScheduleTableLocColumn.setText(resourceBundle.getString("locationText"));
        ScheduleTableEditorColumn.setText(resourceBundle.getString("editorText"));
        ScheduleTableTypeColumn.setText(resourceBundle.getString("typeText"));
        ScheduleTableStartColumn.setText(resourceBundle.getString("startText"));
        ScheduleTableEndColumn.setText(resourceBundle.getString("endText"));
        ScheduleTableAuthorColumn.setText(resourceBundle.getString("authorText"));
        ScheduleTableEmployeeColumn.setText(resourceBundle.getString("employeeText"));

        titleLabel.setText(resourceBundle.getString("titleText"));
        descriptionLabel.setText(resourceBundle.getString("descriptionText"));
        locationLabel.setText(resourceBundle.getString("locationText"));
        editorLabel.setText(resourceBundle.getString("editorText"));
        startDateLabel.setText(resourceBundle.getString("startDateText"));
        startTimeLabel.setText(resourceBundle.getString("startTimeText"));
        endDateLabel.setText(resourceBundle.getString("endDateText"));
        endTimeLabel.setText(resourceBundle.getString("endTimeText"));
        employeeLabel.setText(resourceBundle.getString("employeeText"));
        authorLabel.setText(resourceBundle.getString("authorText"));
        typeLabel.setText(resourceBundle.getString("typeText"));
        createDateLabel.setText(resourceBundle.getString("createDateText"));
        createdByLabel.setText(resourceBundle.getString("createdByText"));
        updateDateLabel.setText(resourceBundle.getString("updateDateText"));
        updatedByLabel.setText(resourceBundle.getString("updatedByText"));

        AllViewRadio.setText(resourceBundle.getString("allViewText"));
        MonthlyViewRadio.setText(resourceBundle.getString("monthlyViewText"));
        WeeklyViewRadio.setText(resourceBundle.getString("weeklyViewText"));

        deleteAppConfirmTitle = resourceBundle.getString("deleteAppConfirmTitleText");
        confirmString = resourceBundle.getString("confirmStringText");
        deleteAppConfirmMessage = resourceBundle.getString("deleteAppConfirmMessageText");
        deleteFailedTitle = resourceBundle.getString("deleteFailedTitleText");
        deleteFailedHeader = resourceBundle.getString("deleteFailedHeaderText");
        deleteFailedMessage = resourceBundle.getString("deleteFailedMessageText");
        confirmModAppTitle = resourceBundle.getString("confirmModAppTitleText");
        confirmModAppMessage = resourceBundle.getString("confirmModAppMessageText");
        modAppFailedTitle = resourceBundle.getString("modAppFailedTitleText");
        modAppFailedHeader = resourceBundle.getString("modAppFailedHeaderText");
        appOutsideBusinessHoursString = resourceBundle.getString("appOutsideBusinessHoursStringText");
        appOverlapString = resourceBundle.getString("appOverlapStringText");
        checkInputsString = resourceBundle.getString("checkInputsStringText");
        noAppSelectedString = resourceBundle.getString("noAppSelectedStringText");
        selectAnAppString = resourceBundle.getString("selectAnAppStringText");
        confirmAddAppTitle = resourceBundle.getString("confirmAddAppTitleText");
        confirmAddAppMessage = resourceBundle.getString("confirmAddAppMessageText");
        addAppFailedTitle = resourceBundle.getString("addAppFailedTitleText");
        addAppFailedHeader = resourceBundle.getString("addAppFailedHeaderText");

        UnselectAppButton.setText(resourceBundle.getString("unselectAppText"));
        AddAppButton.setText(resourceBundle.getString("addAppText"));
        ModAppButton.setText(resourceBundle.getString("modAppText"));
        DeleteAppButton.setText(resourceBundle.getString("deleteAppText"));

        try {
            allAppointments = AppointmentDatabaseAccess.getAllAppointments();

            fillInDropDowns();

            ScheduleTableIDColumn.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
            ScheduleTableTitleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
            ScheduleTableDesColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
            ScheduleTableLocColumn.setCellValueFactory(new PropertyValueFactory<>("Location"));
            ScheduleTableEditorColumn.setCellValueFactory(new PropertyValueFactory<>("Editor_Name"));
            ScheduleTableTypeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
            ScheduleTableStartColumn.setCellValueFactory(new PropertyValueFactory<>("Start_Display"));
            ScheduleTableEndColumn.setCellValueFactory(new PropertyValueFactory<>("End_Display"));
            ScheduleTableAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("Author_Name"));
            ScheduleTableEmployeeColumn.setCellValueFactory(new PropertyValueFactory<>("Employee_Name"));

            ScheduleTable.setItems(allAppointments);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method sets the table to display only appointments that are scheduled in the next 7 days.
     */
    private void displayAppointmentsThisMonth() {
        LocalDateTime todayDateTime = LocalDateTime.now();
        ObservableList<Appointment> appointmentsThisMonth = FXCollections.observableArrayList();
        for (Appointment appointment : allAppointments) {
            if (appointment.getStart().getMonth().equals(todayDateTime.getMonth())) {
                appointmentsThisMonth.add(appointment);
            }
        }
        ScheduleTable.setItems(appointmentsThisMonth);
    }

    /**
     * This method sets the table to display appointments that are in the next 7 days
     */
    private void displayAppointmentsThisWeek() {
        LocalDateTime todayDateTime = LocalDateTime.now();
        LocalDateTime inAWeekDateTime = LocalDateTime.now().plusDays(7);
        ObservableList<Appointment> appointmentsThisWeek = FXCollections.observableArrayList();
        for (Appointment appointment : allAppointments) {
            if ((appointment.getStart().isAfter(todayDateTime) || appointment.getStart().isEqual(todayDateTime)) && (appointment.getStart().isBefore(inAWeekDateTime) || (appointment.getStart().isEqual(inAWeekDateTime)))) {
                appointmentsThisWeek.add(appointment);
            }
        }
        ScheduleTable.setItems(appointmentsThisWeek);
    }

    /**
     * This method adds times to the dropdowns for start and end.
     * It also adds all the contact names to that dropdown.
     * Times should be every X minutes between 8am and 10pm.
     *
     * @throws SQLException if it fails to pull all the contacts
     */
    private void fillInDropDowns() throws SQLException {
        ObservableList<Person> allEditors = EditorDatabaseAccess.getAllEditors();
        ObservableList<String> allEditorNames = FXCollections.observableArrayList();
        allEditors.forEach(editor -> allEditorNames.add(editor.getName()));
        EditorDropdown.setItems(allEditorNames);

        ObservableList<Person> allAuthors = AuthorDatabaseAccess.getAllAuthors();
        ObservableList<String> allAuthorNames = FXCollections.observableArrayList();
        allAuthors.forEach(author -> allAuthorNames.add(author.getName()));
        AuthorDropdown.setItems(allAuthorNames);

        ObservableList<Employee> allEmployees = EmployeeDatabaseAccess.getAllEmployees();
        ObservableList<String> allEmployeeNames = FXCollections.observableArrayList();
        allEmployees.forEach(employee -> allEmployeeNames.add(employee.getName()));
        EmployeeDropdown.setItems(allEmployeeNames);
    }


    /**
     * This method generates the next available id for the new appointment.
     *
     * @return int of the next available id
     */
    private int getNewID() {
        int newID = 1;
        for (Appointment appointment : allAppointments) {
            if (appointment.getAppointment_ID() >= newID) {
                newID = appointment.getAppointment_ID() + 1;
            }
        }
        return newID;
    }

    /**
     * This method sets the data fields when the table is clicked on.
     */
    @FXML
    void appointmentSelected() {
        try {
            Appointment selectedAppointment = ScheduleTable.getSelectionModel().getSelectedItem();

            if (selectedAppointment != null) {

                fillInDropDowns();


                ApIdText.setText(String.valueOf(selectedAppointment.getAppointment_ID()));
                TitleText.setText(selectedAppointment.getTitle());
                DescriptionText.setText(selectedAppointment.getDescription());
                LocationText.setText(selectedAppointment.getLocation());
                ApTypeText.setText(selectedAppointment.getType());
                StartDatePicker.setValue(HelperMethods.getDateTimeForLocation(selectedAppointment.getStart()).toLocalDate());
                StartTimeText.setText(String.valueOf(HelperMethods.getDateTimeForLocation(selectedAppointment.getStart()).toLocalTime()));
                EndDatePicker.setValue(HelperMethods.getDateTimeForLocation(selectedAppointment.getEnd()).toLocalDate());
                EndTimeText.setText(String.valueOf(HelperMethods.getDateTimeForLocation(selectedAppointment.getEnd()).toLocalTime()));
                EditorDropdown.setValue(selectedAppointment.getEditor_Name());
                AuthorDropdown.setValue(selectedAppointment.getAuthor_Name());
                EmployeeDropdown.setValue(selectedAppointment.getEmployee_Name());
                UpdatedByText.setText(selectedAppointment.getLast_Updated_By());
                UpdateDateText.setText(selectedAppointment.getLast_Update_Display());
                CreatedByText.setText(selectedAppointment.getCreated_By());
                CreateDateText.setText(selectedAppointment.getCreate_Date_Display());
            } else {
                ApIdText.setText("");
                TitleText.setText("");
                DescriptionText.setText("");
                LocationText.setText("");
                ApTypeText.setText("");
                StartDatePicker.setValue(LocalDateTime.now().toLocalDate());
                EndDatePicker.setValue(LocalDateTime.now().toLocalDate());
                StartTimeText.setText("");
                EndTimeText.setText("");
                UpdatedByText.setText("");
                UpdateDateText.setText("");
                CreatedByText.setText("");
                CreateDateText.setText("");
                EditorDropdown.setValue("");
                AuthorDropdown.setValue("");
                EmployeeDropdown.setValue("");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method unselects anything from the Appointment Table
     */
    @FXML
    public void unselect() {
        ScheduleTable.getSelectionModel().clearSelection();
        appointmentSelected();
    }
}



