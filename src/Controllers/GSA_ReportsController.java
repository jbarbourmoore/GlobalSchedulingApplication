package Controllers;

import DataStructures.Appointment;
import DataStructures.Person;
import DataStructures.ReportCountByCategory;
import DatabaseAccess.AppointmentDatabaseAccess;
import DatabaseAccess.AuthorDatabaseAccess;
import DatabaseAccess.EditorDatabaseAccess;
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
import java.util.ResourceBundle;


/**
 * Created class GSA_ReportsController.java
 * <p>
 * This class controls the reports screen for the application.
 *
 * @author Jamie Barbour-Moore
 */
public class GSA_ReportsController implements Initializable {
    String selectedAuthor = "";
    String selectedEditor = "";
    /**
     * The rasior button to allow the user to display the count of authors by country.
     */
    @FXML
    private RadioButton AuthorsByCountry;
    /**
     * The rasior button to allow the user to display the count of editos by country.
     */
    @FXML
    private RadioButton EditorsByCountry;
    /**
     * The button to allow the user to export a csv file with the selected author's appointments.
     */
    @FXML
    private Button exportAuthorAppsButton;
    /**
     * The button to allow the user to export a csv file with the selected editor's appointments.
     */
    @FXML
    private Button exportEditorsAppsButton;
    /**
     * The label to display the application's localization data.
     */
    @FXML
    private Label LocalizationLabel;
    /**
     * The button to allow the user to exit the application.
     */
    @FXML
    private Button ExitButton;
    /**
     * The button to allow the user to go back to the main screen.
     */
    @FXML
    private Button BackButton;
    /**
     * The label to display the screen's title.
     */
    @FXML
    private Label GSALabel;
    /**
     * The label for the dropdown to allow the user to select an editor.
     */
    @FXML
    private Label WhichEditorLabel;
    /**
     * The button to submit the editor selection.
     */
    @FXML
    private Button SubmitEditorButton;
    /**
     * The label for the dropdown to allow the user to select an author.
     */
    @FXML
    private Label WhichAuthorLabel;
    /**
     * The button to submit the author selection.
     */
    @FXML
    private Button SubmitAuthorButton;
    /**
     * The tab for the total authors filtered by location
     */
    @FXML
    private Tab peopleByCountryTab;
    /**
     * The tab for the total appointment counts.
     */
    @FXML
    private Tab appointmentCountsTab;
    /**
     * The tab for the appointments filtered by editor.
     */
    @FXML
    private Tab appointmentsByEditorTab;
    /**
     * The tab for the appointments filtered by author.
     */
    @FXML
    private Tab appointmentsByAuthorTab;
    /**
     * The table view for the author's appointments.
     */
    @FXML
    private TableView<Appointment> ScheduleTable;
    /**
     * The table column for the author's appointments' id.
     */
    @FXML
    private TableColumn<?, ?> ScheduleTableIDColumn;
    /**
     * The table column for the author's appointments' title.
     */
    @FXML
    private TableColumn<?, ?> ScheduleTableTitleColumn;
    /**
     * The table column for the author's appointments' description.
     */
    @FXML
    private TableColumn<?, ?> ScheduleTableDesColumn;
    /**
     * The table column for the author's appointments' location.
     */
    @FXML
    private TableColumn<?, ?> ScheduleTableLocColumn;
    /**
     * The table column for the author's appointments' contact.
     */
    @FXML
    private TableColumn<?, ?> ScheduleTableEditorColumn;
    /**
     * The table column for the author's appointments' type.
     */
    @FXML
    private TableColumn<?, ?> ScheduleTableTypeColumn;
    /**
     * The table column for the author's appointments' start date and time.
     */
    @FXML
    private TableColumn<?, ?> ScheduleTableStartColumn;
    /**
     * The table column for the author's appointments' end date and time.
     */
    @FXML
    private TableColumn<?, ?> ScheduleTableEndColumn;
    /**
     * The table column for the author's appointments' customer id.
     */
    @FXML
    private TableColumn<?, ?> ScheduleTableAuthorColumn;
    /**
     * The table column for the author's appointments' user id.
     */
    @FXML
    private TableColumn<?, ?> ScheduleTableEmployeeColumn;
    /**
     * The choice box to select which author's appointments you are viewing.
     */
    @FXML
    private ChoiceBox<String> AuthorsDropdown;
    /**
     * The table view for the editor's appointments.
     */
    @FXML
    private TableView<Appointment> Editor_ScheduleTable;
    /**
     * The table column for the editor's appointments' id.
     */
    @FXML
    private TableColumn<?, ?> Editor_ScheduleTableIDColumn;
    /**
     * The table column for the editor's appointments' title.
     */
    @FXML
    private TableColumn<?, ?> Editor_ScheduleTableTitleColumn;
    /**
     * The table column for the editor's appointments' description.
     */
    @FXML
    private TableColumn<?, ?> Editor_ScheduleTableDesColumn;
    /**
     * The table column for the editor's appointments' location.
     */
    @FXML
    private TableColumn<?, ?> Editor_ScheduleTableLocColumn;
    /**
     * The table column for the editor's appointments' contact.
     */
    @FXML
    private TableColumn<?, ?> Editor_ScheduleTableEditorColumn;
    /**
     * The table column for the editor's appointments' type.
     */
    @FXML
    private TableColumn<?, ?> Editor_ScheduleTableTypeColumn;
    /**
     * The table column for the editor's appointments' start date and time.
     */
    @FXML
    private TableColumn<?, ?> Editor_ScheduleTableStartColumn;
    /**
     * The table column for the editor's appointments' end date and time.
     */
    @FXML
    private TableColumn<?, ?> Editor_ScheduleTableEndColumn;
    /**
     * The table column for the editor's appointments' customer id.
     */
    @FXML
    private TableColumn<?, ?> Editor_ScheduleTableAuthorColumn;
    /**
     * The table column for the editor's appointments' user id.
     */
    @FXML
    private TableColumn<?, ?> Editor_ScheduleTableEmployeeColumn;
    /**
     * The choice box to select which editor's appointments you are viewing.
     */
    @FXML
    private ChoiceBox<String> EditorDropDown;
    /**
     * The table view for the counting of appointments.
     */
    @FXML
    private TableView<ReportCountByCategory> AppointmentCountTable;
    /**
     * The table column for the appointments' category.
     */
    @FXML
    private TableColumn<?, ?> AppointmentCategoryColumn;
    /**
     * The table column for the count value for each appointment category.
     */
    @FXML
    private TableColumn<?, ?> AppointmentCountColumn;
    /**
     * The table view for the counting of authors by location.
     */
    @FXML
    private TableView<ReportCountByCategory> LocationTable;
    /**
     * The table column for the authors' location.
     */
    @FXML
    private TableColumn<?, ?> LocationColumn;
    /**
     * The table column for the count value for each author location.
     */
    @FXML
    private TableColumn<?, ?> PeopleCountColumn;
    /**
     * Radio Button to show appointment count by month.
     */
    @FXML
    private RadioButton CountByMonthRadio;
    /**
     * Radio Button to show appointment count by type.
     */
    @FXML
    private RadioButton CountByTypeRadio;
    /**
     * Radio Button to show appointment count by editor.
     */
    @FXML
    private RadioButton CountByEditorRadio;
    /**
     * Radio Button to show appointment count by author.
     */
    @FXML
    private RadioButton CountByAuthorRadio;
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String exportAppsButtonText = "";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String failedExportTitle = "";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String failedExportHeader = "";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String failedExportMessage = "";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String successfulExportTitle = "";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String successfulExportHeader = "";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String successfulExportMessage = "";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String byEditorColumn = "";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String byAuthorColumn = "";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String byTypeColumn = "";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String byMonthColumn = "";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String editorsCountriesColumnLabel = "";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String authorsCountriesColumnLabel = "";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String editorsCountriesCountColumnLabel = "";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String authorsCountriesCountColumnLabel = "";
    /**
     * The observable list containing all the appointments.
     */
    private ObservableList<Appointment> allAppointments;
    /**
     * The observable list containing all the authors.
     */
    private ObservableList<Person> allAuthors;
    /**
     * The observable list containing all the editors.
     */
    private ObservableList<Person> allEditors;

    /**
     * This method exits the application if the user clicks to confirm.
     *
     * @param event the action that triggered the method
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
     * This method goes back to the home screen if the user clicks to confirm.
     *
     * @param event the action that triggered the method
     * @throws IOException if the load fails
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
     * This method initializes the reports screen.
     *
     * @param location  the url
     * @param resources the resource bundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalizationLabel.setText(HelperMethods.getLocalizationText());
        ExitButton.setText(HelperMethods.getExitButtonText());
        GSALabel.setText(HelperMethods.getHeaderText() + " - " + HelperMethods.getReportScreenText());
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

        Editor_ScheduleTableTitleColumn.setText(resourceBundle.getString("titleText"));
        Editor_ScheduleTableDesColumn.setText(resourceBundle.getString("descriptionText"));
        Editor_ScheduleTableLocColumn.setText(resourceBundle.getString("locationText"));
        Editor_ScheduleTableEditorColumn.setText(resourceBundle.getString("editorText"));
        Editor_ScheduleTableTypeColumn.setText(resourceBundle.getString("typeText"));
        Editor_ScheduleTableStartColumn.setText(resourceBundle.getString("startText"));
        Editor_ScheduleTableEndColumn.setText(resourceBundle.getString("endText"));
        Editor_ScheduleTableAuthorColumn.setText(resourceBundle.getString("authorText"));
        Editor_ScheduleTableEmployeeColumn.setText(resourceBundle.getString("employeeText"));

        resourceBundle = ResourceBundle.getBundle("Reports", HelperMethods.getLocale());

        WhichEditorLabel.setText(resourceBundle.getString("WhichEditorLabelText"));
        SubmitEditorButton.setText(resourceBundle.getString("SubmitEditorButtonText"));
        WhichAuthorLabel.setText(resourceBundle.getString("WhichAuthorLabelText"));
        SubmitAuthorButton.setText(resourceBundle.getString("SubmitAuthorButtonText"));
        CountByMonthRadio.setText(resourceBundle.getString("CountByMonthRadioText"));
        CountByTypeRadio.setText(resourceBundle.getString("CountByTypeRadioText"));
        CountByEditorRadio.setText(resourceBundle.getString("CountByEditorRadioText"));
        CountByAuthorRadio.setText(resourceBundle.getString("CountByAuthorRadioText"));
        exportAppsButtonText = resourceBundle.getString("exportAppsButtonText");
        failedExportTitle = resourceBundle.getString("failedExportTitleText");
        failedExportHeader = resourceBundle.getString("failedExportHeaderText");
        failedExportMessage = resourceBundle.getString("failedExportMessageText");
        successfulExportTitle = resourceBundle.getString("successfulExportTitleText");
        successfulExportHeader = resourceBundle.getString("successfulExportHeaderText");
        successfulExportMessage = resourceBundle.getString("successfulExportMessageText");
        byEditorColumn = resourceBundle.getString("byEditorColumnText");
        byAuthorColumn = resourceBundle.getString("byAuthorColumnText");
        byTypeColumn = resourceBundle.getString("byTypeColumnText");
        byMonthColumn = resourceBundle.getString("byMonthColumnText");
        peopleByCountryTab.setText(resourceBundle.getString("authorsByCountryText"));
        appointmentCountsTab.setText(resourceBundle.getString("appointmentCountsText"));
        appointmentsByEditorTab.setText(resourceBundle.getString("appointmentsByEditorText"));
        appointmentsByAuthorTab.setText(resourceBundle.getString("appointmentsByAuthorText"));
        AppointmentCountColumn.setText(resourceBundle.getString("AppointmentCountColumnText"));
        LocationColumn.setText(resourceBundle.getString("LocationColumnText"));
        PeopleCountColumn.setText(resourceBundle.getString("AuthorsCountColumnText"));
        editorsCountriesColumnLabel = resourceBundle.getString("editorsCountriesColumnLabelText");
        authorsCountriesColumnLabel = resourceBundle.getString("authorsCountriesColumnLabelText");
        editorsCountriesCountColumnLabel = resourceBundle.getString("editorsCountriesCountColumnLabelText");
        authorsCountriesCountColumnLabel = resourceBundle.getString("authorsCountriesCountColumnLabelText");

        try {
            allAppointments = AppointmentDatabaseAccess.getAllAppointments();
            allAuthors = AuthorDatabaseAccess.getAllAuthors();
            allEditors = EditorDatabaseAccess.getAllEditors();

            initializeAppointmentsByEditorTab();
            initializeAppointmentsByAuthorTab();
            CountAppointmentsRadioPressed();
            CountPeopleRadioPressed();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method loads the total appointments by month tab.
     */
    private void initializeAppointmentsByEditor() {
        AppointmentCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));
        AppointmentCountColumn.setCellValueFactory(new PropertyValueFactory<>("Count"));

        ObservableList<ReportCountByCategory> allAppointmentCountsByEditor = FXCollections.observableArrayList();

        for (Appointment appointment : allAppointments) {
            boolean editorCreated = false;
            for (ReportCountByCategory reportCountByCategory : allAppointmentCountsByEditor) {
                if (reportCountByCategory.getCategory().equalsIgnoreCase(appointment.getEditor_Name())) {
                    reportCountByCategory.incrementCount();
                    editorCreated = true;
                    break;
                }
            }

            if (!editorCreated) {
                allAppointmentCountsByEditor.add(new ReportCountByCategory(appointment.getEditor_Name()));
            }
        }

        AppointmentCountTable.setItems(allAppointmentCountsByEditor);
    }

    /**
     * This method loads the total appointments by month tab.
     */
    private void initializeAppointmentsByAuthor() {
        AppointmentCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));
        AppointmentCountColumn.setCellValueFactory(new PropertyValueFactory<>("Count"));

        ObservableList<ReportCountByCategory> allAppointmentCountsByAuthor = FXCollections.observableArrayList();

        for (Appointment appointment : allAppointments) {
            boolean authorCreated = false;
            for (ReportCountByCategory reportCountByCategory : allAppointmentCountsByAuthor) {
                if (reportCountByCategory.getCategory().equalsIgnoreCase(appointment.getAuthor_Name())) {
                    reportCountByCategory.incrementCount();
                    authorCreated = true;
                    break;
                }
            }
            if (!authorCreated) {
                allAppointmentCountsByAuthor.add(new ReportCountByCategory(appointment.getAuthor_Name()));
            }
        }
        AppointmentCountTable.setItems(allAppointmentCountsByAuthor);
    }

    /**
     * This method loads the total appointments by month tab.
     */
    private void initializeAppointmentsByMonth() {
        AppointmentCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));
        AppointmentCountColumn.setCellValueFactory(new PropertyValueFactory<>("Count"));

        ObservableList<ReportCountByCategory> allAppointmentCountsByMonth = FXCollections.observableArrayList();

        for (Appointment appointment : allAppointments) {
            boolean monthCreated = false;
            for (ReportCountByCategory reportCountByCategory : allAppointmentCountsByMonth) {
                if (reportCountByCategory.getCategory().equalsIgnoreCase(appointment.getStart().getMonth().toString())) {
                    reportCountByCategory.incrementCount();
                    monthCreated = true;
                }
            }
            if (!monthCreated) {
                allAppointmentCountsByMonth.add(new ReportCountByCategory(appointment.getStart().getMonth().toString()));
            }
        }
        AppointmentCountTable.setItems(allAppointmentCountsByMonth);
    }

    /**
     * This method loads the total appointments by type tab.
     */
    private void initializeAppointmentsByType() {
        AppointmentCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));
        AppointmentCountColumn.setCellValueFactory(new PropertyValueFactory<>("Count"));

        ObservableList<ReportCountByCategory> allAppointmentCountsByType = FXCollections.observableArrayList();

        for (Appointment appointment : allAppointments) {
            boolean typeCreated = false;
            for (ReportCountByCategory reportCountByCategory : allAppointmentCountsByType) {
                if (reportCountByCategory.getCategory().equalsIgnoreCase(appointment.getType())) {
                    reportCountByCategory.incrementCount();
                    typeCreated = true;
                }
            }
            if (!typeCreated) {
                allAppointmentCountsByType.add(new ReportCountByCategory(appointment.getType()));
            }
        }
        AppointmentCountTable.setItems(allAppointmentCountsByType);
    }

    /**
     * This method loads the appointments by contact tab.
     */
    private void initializeAppointmentsByEditorTab() {
        Editor_ScheduleTableIDColumn.setCellValueFactory(new PropertyValueFactory<>("Appointment_ID"));
        Editor_ScheduleTableTitleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Editor_ScheduleTableDesColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        Editor_ScheduleTableLocColumn.setCellValueFactory(new PropertyValueFactory<>("Location"));
        Editor_ScheduleTableEditorColumn.setCellValueFactory(new PropertyValueFactory<>("Editor_Name"));
        Editor_ScheduleTableTypeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Editor_ScheduleTableStartColumn.setCellValueFactory(new PropertyValueFactory<>("Start_Display"));
        Editor_ScheduleTableEndColumn.setCellValueFactory(new PropertyValueFactory<>("End_Display"));
        Editor_ScheduleTableAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("Author_Name"));
        Editor_ScheduleTableEmployeeColumn.setCellValueFactory(new PropertyValueFactory<>("Employee_Name"));

        ObservableList<String> allEditorNames = FXCollections.observableArrayList();
        allEditors.forEach(editor -> allEditorNames.add(editor.getName()));
        EditorDropDown.setItems(allEditorNames);

        EditorDropDown.setValue(allEditorNames.get(0));

        editorSelected();
    }

    /**
     * This method loads the appointments by customer tab.
     */
    private void initializeAppointmentsByAuthorTab() {
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

        ObservableList<String> allAuthorNames = FXCollections.observableArrayList();
        allAuthors.forEach(author -> allAuthorNames.add(author.getName()));
        AuthorsDropdown.setItems(allAuthorNames);
        AuthorsDropdown.setValue(allAuthorNames.get(0));

        authorSelected();
    }

    /**
     * This method gets the editor's name from the dropdown.
     * Then it finds the editor's id.
     * If creates a list of all appointments with that editor's id and adds it to the table.
     */
    @FXML
    protected void editorSelected() {
        selectedEditor = EditorDropDown.getValue();
        exportEditorsAppsButton.setText(String.format(exportAppsButtonText, selectedEditor));

        int editorID = 0;
        for (Person editor : allEditors) {
            if (selectedEditor.equalsIgnoreCase(editor.getName())) {
                editorID = editor.getID();
            }
        }

        ObservableList<Appointment> allAppointmentsWithEditor = FXCollections.observableArrayList();
        for (Appointment appointment : allAppointments) {
            if (editorID == appointment.getEditor_ID()) {
                allAppointmentsWithEditor.add(appointment);
            }
        }

        Editor_ScheduleTable.setItems(allAppointmentsWithEditor);
    }

    /**
     * This method gets the author's name from the dropdown.
     * Then it finds the author's id.
     * If creates a list of all appointments with that customer's id and adds it to the table.
     */
    @FXML
    protected void authorSelected() {
        selectedEditor = AuthorsDropdown.getValue();
        exportAuthorAppsButton.setText(String.format(exportAppsButtonText, selectedEditor));
        int authorID = 0;
        for (Person author : allAuthors) {
            if (selectedEditor.equalsIgnoreCase(author.getName())) {
                authorID = author.getID();
            }
        }
        ObservableList<Appointment> allAppointmentsWithAuthor = FXCollections.observableArrayList();
        for (Appointment appointment : allAppointments) {
            if (authorID == appointment.getAuthor_ID()) {
                allAppointmentsWithAuthor.add(appointment);
            }
        }
        ScheduleTable.setItems(allAppointmentsWithAuthor);
    }

    /**
     * This method loads the total authors count by location.
     */
    private void initializeAuthorsByCountry() {
        LocationColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));
        PeopleCountColumn.setCellValueFactory(new PropertyValueFactory<>("Count"));

        ObservableList<ReportCountByCategory> allAuthorsByCountry = FXCollections.observableArrayList();

        for (Person author : allAuthors) {
            boolean typeCreated = false;
            for (ReportCountByCategory authorsByCountry : allAuthorsByCountry) {
                if (authorsByCountry.getCategory().equalsIgnoreCase(author.getCountry_Name())) {
                    authorsByCountry.incrementCount();
                    typeCreated = true;
                }
            }
            if (!typeCreated) {
                allAuthorsByCountry.add(new ReportCountByCategory(author.getCountry_Name()));
            }
        }
        LocationTable.setItems(allAuthorsByCountry);
    }

    /**
     * This method loads the total editors count by location.
     */
    private void initializeEditorsByCountry() {
        LocationColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));
        PeopleCountColumn.setCellValueFactory(new PropertyValueFactory<>("Count"));

        ObservableList<ReportCountByCategory> allEditorsByCountry = FXCollections.observableArrayList();

        for (Person editor : allEditors) {
            boolean typeCreated = false;
            for (ReportCountByCategory editorsByCountry : allEditorsByCountry) {
                if (editorsByCountry.getCategory().equalsIgnoreCase(editor.getCountry_Name())) {
                    editorsByCountry.incrementCount();
                    typeCreated = true;
                }
            }
            if (!typeCreated) {
                allEditorsByCountry.add(new ReportCountByCategory(editor.getCountry_Name()));
            }
        }
        LocationTable.setItems(allEditorsByCountry);
    }

    /**
     * This method updates the appointment count table based on the filter radio button the user has selected.
     */
    @FXML
    private void CountAppointmentsRadioPressed() {
        if (CountByEditorRadio.isSelected()) {
            AppointmentCategoryColumn.setText(byEditorColumn);
            initializeAppointmentsByEditor();
        } else if (CountByAuthorRadio.isSelected()) {
            AppointmentCategoryColumn.setText(byAuthorColumn);
            initializeAppointmentsByAuthor();
        } else if (CountByMonthRadio.isSelected()) {
            AppointmentCategoryColumn.setText(byMonthColumn);
            initializeAppointmentsByMonth();
        } else {
            AppointmentCategoryColumn.setText(byTypeColumn);
            initializeAppointmentsByType();
        }
    }

    /**
     * This method exports the selected author's appointments.
     */
    @FXML
    private void exportAuthorAppsClicked() {
        int authorID = 0;
        for (Person author : allAuthors) {
            if (selectedAuthor.equalsIgnoreCase(author.getName())) {
                authorID = author.getID();
                break;
            }
        }
        ObservableList<Appointment> allAppointmentsWithAuthor = FXCollections.observableArrayList();
        for (Appointment appointment : allAppointments) {
            if (authorID == appointment.getAuthor_ID()) {
                allAppointmentsWithAuthor.add(appointment);
            }
        }
        String fileLocation = String.format("AppointmentsForAuthorID%d.csv", authorID);
        if (HelperMethods.exportAppointmentListToCSV(allAppointmentsWithAuthor, fileLocation)) {
            HelperMethods.infoDialog(successfulExportTitle, String.format(successfulExportHeader, selectedAuthor), String.format(successfulExportMessage, fileLocation));
        } else {
            HelperMethods.infoDialog(failedExportTitle, String.format(failedExportHeader, selectedAuthor), failedExportMessage);
        }
    }

    /**
     * This method exports the selected editor's appointments.
     */
    @FXML
    private void exportEditorsAppointments() {

        int editorID = 0;
        for (Person editor : allEditors) {
            if (selectedEditor.equalsIgnoreCase(editor.getName())) {
                editorID = editor.getID();
                break;
            }
        }
        ObservableList<Appointment> allAppointmentsWithEditor = FXCollections.observableArrayList();
        for (Appointment appointment : allAppointments) {
            if (editorID == appointment.getEditor_ID()) {
                allAppointmentsWithEditor.add(appointment);
            }
        }
        String fileLocation = String.format("AppointmentsForEditorID%d.csv", editorID);
        if (HelperMethods.exportAppointmentListToCSV(allAppointmentsWithEditor, fileLocation)) {
            HelperMethods.infoDialog(successfulExportTitle, String.format(successfulExportHeader, selectedEditor), String.format(successfulExportMessage, fileLocation));
        } else {
            HelperMethods.infoDialog(failedExportTitle, String.format(failedExportHeader, selectedEditor), failedExportMessage);
        }
    }

    /**
     * This method updates the People count by country table to display authors or editors based on the radio button the user has selected.
     *
     * @throws SQLException if the database access fails.
     */
    @FXML
    private void CountPeopleRadioPressed() throws SQLException {
        if (EditorsByCountry.isSelected()) {
            LocationColumn.setText(editorsCountriesColumnLabel);
            PeopleCountColumn.setText(editorsCountriesCountColumnLabel);
            initializeEditorsByCountry();
        } else if (AuthorsByCountry.isSelected()) {
            LocationColumn.setText(authorsCountriesColumnLabel);
            PeopleCountColumn.setText(authorsCountriesCountColumnLabel);
            initializeAuthorsByCountry();
        }

    }
}
