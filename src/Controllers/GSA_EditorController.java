package Controllers;

import DataStructures.Appointment;
import DataStructures.Country;
import DataStructures.Person;
import DataStructures.Region;
import DatabaseAccess.AppointmentDatabaseAccess;
import DatabaseAccess.CountryDatabaseAccess;
import DatabaseAccess.EditorDatabaseAccess;
import DatabaseAccess.RegionDatabaseAccess;
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
 * Created class GSA_AuthorController.java
 * <p>
 * This class controls the customer screen for the application.
 *
 * @author Jamie Barbour-Moore
 */
public class GSA_EditorController implements Initializable {

    public TextField IDText;
    public TextField SearchField;
    public Button SearchButton;
    /**
     * The table view for the customer information
     */
    @FXML
    private Label GSALabel;
    /**
     * The table view for the customer information
     */
    @FXML
    private Button BackButton;
    /**
     * The table view for the customer information
     */
    @FXML
    private Button ExitButton;
    /**
     * The table view for the customer information
     */
    @FXML
    private Label LocalizationLabel;
    /**
     * The table view for the customer information
     */
    @FXML
    private Label NameLabel;
    /**
     * The table view for the customer information
     */
    @FXML
    private Label AddressLabel;
    /**
     * The table view for the customer information
     */
    @FXML
    private Label PostalCodeLabel;
    /**
     * The table view for the customer information
     */
    @FXML
    private Label CountryLabel;
    /**
     * The table view for the customer information
     */
    @FXML
    private Label PhoneLabel;
    /**
     * The table view for the customer information
     */
    @FXML
    private Label DateCreatedLabel;
    /**
     * The table view for the customer information
     */
    @FXML
    private Label CreatedByLabel;
    /**
     * The table view for the customer information
     */
    @FXML
    private Label LastUpdatedLabel;
    /**
     * The table view for the customer information
     */
    @FXML
    private Label UpdatedByLabel;
    /**
     * The table view for the customer information
     */
    @FXML
    private Label RegionLabel;
    /**
     * The table view for the customer information
     */
    @FXML
    private Label EmailLabel;
    /**
     * The table view for the customer information
     */
    @FXML
    private Button AddButton;
    /**
     * The table view for the customer information
     */
    @FXML
    private Button ModButton;
    /**
     * The table view for the customer information
     */
    @FXML
    private Button DeleteButton;
    /**
     * The table view for the customer information
     */
    @FXML
    private Button UnselectButton;
    /**
     * The table view for the customer information
     */
    @FXML
    private TextField EmailText;


    /**
     * The table view for the customer information
     */
    @FXML
    private TableView<Person> PersonalInformationTable;

    /**
     * The table column for the customer's ID.
     */
    @FXML
    private TableColumn<?, ?> IDColumn;

    /**
     * The table column for the customer's name.
     */
    @FXML
    private TableColumn<?, ?> NameColumn;

    /**
     * The table column for the customer's address.
     */
    @FXML
    private TableColumn<?, ?> AddressColumn;

    /**
     * The table column for the customer's postal code.
     */
    @FXML
    private TableColumn<?, ?> PostalCodeColumn;

    /**
     * The table column for the customer's phone.
     */
    @FXML
    private TableColumn<?, ?> PhoneColumn;

    /**
     * The table column for the customer's first level division.
     */
    @FXML
    private TableColumn<?, ?> RegionColumn;

    @FXML
    private TableColumn<?, ?> CountryColumn;

    @FXML
    private TableColumn<?, ?> EmailColumn;

    /**
     * The choice box for the region options.
     */
    @FXML
    private ChoiceBox<String> RegionDropDown;

    /**
     * The choice box for the country options.
     */
    @FXML
    private ChoiceBox<String> CountryDropDown;

    /**
     * The input field at the bottom of the screen for the selected Customer's name.
     */
    @FXML
    private TextField NameText;

    /**
     * The input field at the bottom of the screen for the selected Customer's address.
     */
    @FXML
    private TextField AddressText;

    /**
     * The input field at the bottom of the screen for the selected Customer's postal code.
     */
    @FXML
    private TextField PostalCodeText;

    /**
     * The input field at the bottom of the screen for the selected Customer's phone.
     */
    @FXML
    private TextField PhoneText;

    /**
     * The display field for who last updated the customer.
     */
    @FXML
    private TextField UpdatedByText;

    /**
     * The display field for when the customer was last updated.
     */
    @FXML
    private TextField LastUpdatedText;

    /**
     * The display field for who created the customer.
     */
    @FXML
    private TextField CreatedByText;

    /**
     * The display field for when the customer was created.
     */
    @FXML
    private TextField DateCreatedText;

    /**
     * The observable list for all the customers in the database
     */
    private ObservableList<Person> allEditors;

    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String deleteConfirmTitle = "Delete Editor?";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String confirmString = "Confirm";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String deleteConfirmMessage = "Do you want to delete this Editor?";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String hasAssociatedAppsTitle = "This Editor has associated appointments";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String hasAssociatedAppsMessage = "Do you want to delete this Editor AND the associated appointments?";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String delFailedTitle = "Delete Editor Failed";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String delFailedHeader = "Unable to Delete Editor";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String delFailedMessage = "Please try again.";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String delFailedUnselectedMessage = "Please select the Editor you want to delete";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String modConfirmTitle = "Modify Editor?";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String modConfirmMessage = "Do you want to modify this Editor?";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String modFailedTitle = "Modify Editor Failed";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String modFailedHeader = "Unable to Modify Editor";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String wrongInputsString = "Please check all your inputs and try again.";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String modFailedUnselectedMessage = "Please select the Editor you want to modify";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String addConfirmTitle = "Add Editor?";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String addConfirmMessage = "Do you want to add this Editor?";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String addFailedTitle = "Add Editor Failed";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String addFailedHeader = "Unable to Add Editor";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String addFailedSelectedString = "Please unselect the Editor in order to add a new one";

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
     * This method saves a new editor if the user clicks to confirm.
     */
    @FXML
    protected void AddEditorClicked() {
        Person authorToAdd = PersonalInformationTable.getSelectionModel().getSelectedItem();
        if (authorToAdd == null) {
            if (HelperMethods.confirmDialog(addConfirmTitle, confirmString, addConfirmMessage)) {
                try {
                    if (isDataValid()) {
                        int addedEditorID = getNewID();
                        Person addedEditor = getEditorFromForm(addedEditorID);
                        EditorDatabaseAccess.addEditor(addedEditor);
                        allEditors = EditorDatabaseAccess.getAllEditors();
                        PersonalInformationTable.setItems(allEditors);
                        unselect();
                        SearchField.setText("");
                    } else {
                        HelperMethods.infoDialog(addFailedTitle, addFailedHeader, wrongInputsString);
                    }
                } catch (Exception e) {
                    HelperMethods.infoDialog(addFailedTitle, addFailedHeader, wrongInputsString);
                    e.printStackTrace();
                }
            }
        } else {
            HelperMethods.infoDialog(addFailedTitle, addFailedHeader, addFailedSelectedString);
        }
    }


    /**
     * This method modifies an editor if the user clicks to confirm.
     */
    @FXML
    protected void ModifyEditorClicked() {
        Person editorToModify = PersonalInformationTable.getSelectionModel().getSelectedItem();
        if (editorToModify != null) {
            if (HelperMethods.confirmDialog(modConfirmTitle, confirmString, modConfirmMessage)) {
                try {
                    if (isDataValid()) {
                        int modifiedEditorID = editorToModify.getID();
                        Person modifiedEditor = getEditorFromForm(modifiedEditorID);
                        EditorDatabaseAccess.modifyEditor(modifiedEditor);
                        allEditors = EditorDatabaseAccess.getAllEditors();
                        PersonalInformationTable.setItems(allEditors);
                        unselect();
                        SearchField.setText("");
                    } else {
                        HelperMethods.infoDialog(modFailedTitle, modFailedHeader, wrongInputsString);
                    }
                } catch (Exception e) {
                    HelperMethods.infoDialog(modFailedTitle, modFailedHeader, wrongInputsString);
                }
            }
        } else {
            HelperMethods.infoDialog(modFailedTitle, modFailedHeader, modFailedUnselectedMessage);
        }
    }


    /**
     * This method deletes the selected customer if the user clicks to confirm.
     *
     * @throws SQLException if the database access fails
     */
    @FXML
    protected void DeleteEditorClicked() throws SQLException {
        Person editorToDelete = PersonalInformationTable.getSelectionModel().getSelectedItem();
        if (editorToDelete != null) {
            if (HelperMethods.confirmDialog(deleteConfirmTitle, confirmString, deleteConfirmMessage)) {
                int editorToDeleteID = editorToDelete.getID();
                ObservableList<Appointment> allAppointments = AppointmentDatabaseAccess.getAllAppointments();
                boolean hasAssociatedAppointment = false;
                for (Appointment appointment : allAppointments) {
                    if (appointment.getEditor_ID() == editorToDeleteID) {
                        hasAssociatedAppointment = true;
                        break;
                    }
                }
                if (hasAssociatedAppointment) {
                    if (HelperMethods.confirmDialog(hasAssociatedAppsTitle, confirmString, hasAssociatedAppsMessage)) {
                        try {
                            EditorDatabaseAccess.deleteEditorWithID(editorToDeleteID);
                            allEditors = EditorDatabaseAccess.getAllEditors();
                            PersonalInformationTable.setItems(allEditors);
                            unselect();
                            SearchField.setText("");
                        } catch (Exception e) {
                            e.printStackTrace();
                            HelperMethods.infoDialog(delFailedTitle, delFailedHeader, delFailedMessage);
                        }
                    }
                } else {
                    try {
                        EditorDatabaseAccess.deleteEditorWithID(editorToDeleteID);
                        allEditors = EditorDatabaseAccess.getAllEditors();
                        PersonalInformationTable.setItems(allEditors);
                        unselect();
                        SearchField.setText("");
                    } catch (Exception e) {
                        HelperMethods.infoDialog(delFailedTitle, delFailedHeader, delFailedMessage);
                    }
                }
            }
        } else {
            HelperMethods.infoDialog(delFailedTitle, delFailedHeader, delFailedUnselectedMessage);
        }
    }

    /**
     * This method goes back to the home screen if the user clicks to confirm.
     *
     * @param event the action that triggered the method
     * @throws IOException if load fails
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
     * This method initializes the customers screen.
     * It adds all the customers in the system to the display table.
     *
     * @param location  the url
     * @param resources the resource bundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LocalizationLabel.setText(HelperMethods.getLocalizationText());
        ExitButton.setText(HelperMethods.getExitButtonText());
        GSALabel.setText(HelperMethods.getHeaderText() + " - " + HelperMethods.getEditorScreenText());
        BackButton.setText(HelperMethods.getBackButtonText());

        ResourceBundle resourceBundle = ResourceBundle.getBundle("Editor", HelperMethods.getLocale());

        NameLabel.setText(resourceBundle.getString("nameText"));
        AddressLabel.setText(resourceBundle.getString("addressText"));
        PostalCodeLabel.setText(resourceBundle.getString("postalCodeText"));
        CountryLabel.setText(resourceBundle.getString("countryText"));
        PhoneLabel.setText(resourceBundle.getString("phoneText"));
        DateCreatedLabel.setText(resourceBundle.getString("createDateText"));
        CreatedByLabel.setText(resourceBundle.getString("createdByText"));
        LastUpdatedLabel.setText(resourceBundle.getString("updateDateText"));
        UpdatedByLabel.setText(resourceBundle.getString("updatedByText"));
        RegionLabel.setText(resourceBundle.getString("regionText"));
        EmailLabel.setText(resourceBundle.getString("emailText"));
        AddButton.setText(resourceBundle.getString("addText"));
        ModButton.setText(resourceBundle.getString("modText"));
        DeleteButton.setText(resourceBundle.getString("deleteText"));
        UnselectButton.setText(resourceBundle.getString("unselectText"));

        NameColumn.setText(resourceBundle.getString("nameText"));
        AddressColumn.setText(resourceBundle.getString("addressText"));
        PostalCodeColumn.setText(resourceBundle.getString("postalCodeText"));
        PhoneColumn.setText(resourceBundle.getString("phoneText"));
        RegionColumn.setText(resourceBundle.getString("regionText"));
        CountryColumn.setText(resourceBundle.getString("countryText"));
        EmailColumn.setText(resourceBundle.getString("emailText"));

        deleteConfirmTitle = resourceBundle.getString("deleteConfirmTitleText");
        confirmString = resourceBundle.getString("confirmStringText");
        deleteConfirmMessage = resourceBundle.getString("deleteConfirmMessageText");
        hasAssociatedAppsTitle = resourceBundle.getString("hasAssociatedAppsTitleText");
        hasAssociatedAppsMessage = resourceBundle.getString("hasAssociatedAppsMessageText");
        delFailedTitle = resourceBundle.getString("delFailedTitleText");
        delFailedHeader = resourceBundle.getString("delFailedHeaderText");
        delFailedMessage = resourceBundle.getString("delFailedMessageText");
        delFailedUnselectedMessage = resourceBundle.getString("delFailedUnselectedMessageText");
        modConfirmTitle = resourceBundle.getString("modConfirmTitleText");
        modConfirmMessage = resourceBundle.getString("modConfirmMessageText");

        modFailedTitle = resourceBundle.getString("modFailedTitleText");
        modFailedHeader = resourceBundle.getString("modFailedHeaderText");
        wrongInputsString = resourceBundle.getString("wrongInputsStringText");
        modFailedUnselectedMessage = resourceBundle.getString("modFailedUnselectedMessageText");

        addConfirmTitle = resourceBundle.getString("addConfirmTitleText");
        addConfirmMessage = resourceBundle.getString("addConfirmMessageText");

        addFailedTitle = resourceBundle.getString("addFailedTitleText");
        addFailedHeader = resourceBundle.getString("addFailedHeaderText");
        addFailedSelectedString = resourceBundle.getString("addFailedSelectedStringText");

        try {
            allEditors = EditorDatabaseAccess.getAllEditors();

            IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
            NameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
            AddressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
            PostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("Postal_Code"));
            PhoneColumn.setCellValueFactory(new PropertyValueFactory<>("Phone"));
            RegionColumn.setCellValueFactory(new PropertyValueFactory<>("Region_Name"));
            EmailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
            CountryColumn.setCellValueFactory(new PropertyValueFactory<>("Country_Name"));

            PersonalInformationTable.setItems(allEditors);

            fillInDropDowns();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method fills in the information for the region and country dropdowns.
     * It adds all the countries and first level divisions to the appropriate dropdowns.
     * This method also uses the 2 lambda expressions.
     * <p>
     * The first lambda replaces a for loop to create a list of all the country names.
     * The second lambda replaces a for loop to create a list of all the first level divisions.
     *
     * @throws SQLException if it fails to get all countries or first level divisions
     */
    private void fillInDropDowns() throws SQLException {
        ObservableList<Country> allCountries = CountryDatabaseAccess.getCountries();
        ObservableList<String> allCountryNames = FXCollections.observableArrayList();
        ObservableList<Region> allRegions = RegionDatabaseAccess.getAllRegions();
        ObservableList<String> allFirstLevelDivNames = FXCollections.observableArrayList();

        // Lambda expressions
        allCountries.forEach(country -> allCountryNames.add(country.getCountry()));
        allRegions.forEach(region -> allFirstLevelDivNames.add(region.getRegion_Name()));

        CountryDropDown.setItems(allCountryNames);
        RegionDropDown.setItems(allFirstLevelDivNames);
    }

    /**
     * This method updates the first level divisions in the dropdown to only be the ones which correspond to the country selected.
     *
     * @throws SQLException if the database access fails
     */
    @FXML
    private void updateDivisionsByCountry() throws SQLException {
        ObservableList<Region> allRegions = RegionDatabaseAccess.getAllRegions();
        String countryName = CountryDropDown.getSelectionModel().getSelectedItem();
        if (countryName != null) {
            ObservableList<String> allFirstLevelDivNamesForCountry = FXCollections.observableArrayList();
            ObservableList<Country> allCountries = CountryDatabaseAccess.getCountries();
            int countryID = 0;
            for (Country country : allCountries) {
                if (country.getCountry().equalsIgnoreCase(countryName)) {
                    countryID = country.getCountry_ID();
                }
            }
            for (Region division : allRegions) {
                if (division.getCountry_ID() == countryID) {
                    allFirstLevelDivNamesForCountry.add(division.getRegion_Name());
                }
            }

            RegionDropDown.setItems(allFirstLevelDivNamesForCountry);

        } else {
            ObservableList<String> allFirstLevelDivNames = FXCollections.observableArrayList();
            allRegions.forEach(region -> allFirstLevelDivNames.add(region.getRegion_Name()));
            RegionDropDown.setItems(allFirstLevelDivNames);
        }
    }

    /**
     * This method generates the next available id for the new editor.
     *
     * @return int of the next available id
     */
    private int getNewID() {
        int newID = 1;
        for (Person editor : allEditors) {
            if (editor.getID() >= newID) {
                newID = editor.getID() + 1;
            }
        }
        return newID;
    }


    /**
     * This method sets the data fields when the table is clicked on.
     */
    @FXML
    void personSelected() {
        try {
            Person selectedEditor = PersonalInformationTable.getSelectionModel().getSelectedItem();
            if (selectedEditor != null) {
                fillInDropDowns();
                IDText.setText(String.valueOf(selectedEditor.getID()));
                NameText.setText(selectedEditor.getName());
                AddressText.setText(selectedEditor.getAddress());
                PostalCodeText.setText(selectedEditor.getPostal_Code());
                PhoneText.setText(selectedEditor.getPhone());
                EmailText.setText(selectedEditor.getEmail());

                UpdatedByText.setText(selectedEditor.getLast_Updated_By());
                LastUpdatedText.setText(selectedEditor.getLast_Update_Display());
                CreatedByText.setText(selectedEditor.getCreated_By());
                DateCreatedText.setText(selectedEditor.getCreate_Date_Display());
                CountryDropDown.setValue(selectedEditor.getCountry_Name());
                updateDivisionsByCountry();
                RegionDropDown.setValue(selectedEditor.getRegion_Name());

            } else {
                IDText.setText("");
                NameText.setText("");
                AddressText.setText("");
                PostalCodeText.setText("");
                PhoneText.setText("");
                UpdatedByText.setText("");
                LastUpdatedText.setText("");
                CreatedByText.setText("");
                DateCreatedText.setText("");
                EmailText.setText("");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method checks if the customer information in the form is valid.
     *
     * @return a boolean which is true if the information is valid
     */
    private boolean isDataValid() {
        boolean dataValid = true;
        if (NameText.getText().equalsIgnoreCase("") || AddressText.getText().equalsIgnoreCase("") || PostalCodeText.getText().equalsIgnoreCase("") || PhoneText.getText().equalsIgnoreCase("") || EmailText.getText().equalsIgnoreCase("")) {
            dataValid = false;
        } else if (RegionDropDown.getSelectionModel().getSelectedItem() == null) {
            dataValid = false;
        }
        return dataValid;
    }

    /**
     * This method creates an editor Person from the data currently in the form.
     *
     * @param editorID The id of the author.
     * @return the author object
     * @throws SQLException if the database access fails
     */
    private Person getEditorFromForm(int editorID) throws SQLException {
        Person editor;
        String name = NameText.getText();
        String address = AddressText.getText();
        String postalCode = PostalCodeText.getText();
        String phone = PhoneText.getText();
        String email = EmailText.getText();

        String regionName = RegionDropDown.getSelectionModel().getSelectedItem();
        int regionID = 0;
        ObservableList<Region> allRegions = RegionDatabaseAccess.getAllRegions();
        for (Region division : allRegions) {
            if (division.getRegion_Name().equalsIgnoreCase(regionName)) {
                regionID = division.getRegion_ID();
                break;
            }
        }
        editor = new Person(editorID, name, address, postalCode, phone, email, regionID);
        return editor;
    }

    /**
     * This method unselects anything from the Customer Table
     **/
    @FXML
    public void unselect() {
        PersonalInformationTable.getSelectionModel().clearSelection();
        personSelected();
    }

    /**
     * This method allows the user to search the author data table by name or id.
     *
     * @throws SQLException
     */
    public void searchClicked() throws SQLException {
        String searchString = SearchField.getText();
        ObservableList<Person> allEditors = EditorDatabaseAccess.getAllEditors();
        ObservableList<Person> allEditorsFound = FXCollections.observableArrayList();
        try {
            int idNumberSearch = Integer.parseInt(searchString);
            for (Person editor : allEditors) {
                if (editor.getID() == idNumberSearch) {
                    allEditorsFound.add(editor);
                }
            }
        } catch (Exception ignored) {

        }
        for (Person editor : allEditors) {
            if (editor.getName().contains(searchString)) {
                allEditorsFound.add(editor);
            }
        }

        if (!allEditorsFound.isEmpty()) {
            PersonalInformationTable.setItems(allEditorsFound);
        } else {
            SearchField.setText("");
            PersonalInformationTable.setItems(allEditors);
            HelperMethods.infoDialog("Nothing Found", "There were no search results for "+searchString, "Please try again");
        }
    }
}
