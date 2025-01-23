package Controllers;

import DataStructures.Appointment;
import DataStructures.Country;
import DataStructures.Person;
import DataStructures.Region;
import DatabaseAccess.AppointmentDatabaseAccess;
import DatabaseAccess.AuthorDatabaseAccess;
import DatabaseAccess.CountryDatabaseAccess;
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
public class GSA_AuthorController implements Initializable {

    /**
     * The observable list for all the authors in the database
     */
    ObservableList<Person> allAuthors;
    /**
     * The label for the author's screen.
     */
    @FXML
    private Label GSALabel;
    /**
     * The back button for the author's screen.
     */
    @FXML
    private Button BackButton;
    /**
     * The exit button for the author's screen.
     */
    @FXML
    private Button ExitButton;
    /**
     * The label for the author's screen's localization information.
     */
    @FXML
    private Label LocalizationLabel;
    /**
     * The label for the author's name.
     */
    @FXML
    private Label NameLabel;
    /**
     * The label for the author's address.
     */
    @FXML
    private Label AddressLabel;
    /**
     * The label for the author's postal code.
     */
    @FXML
    private Label PostalCodeLabel;
    /**
     * The label for the author's country.
     */
    @FXML
    private Label CountryLabel;
    /**
     * The label for the author's phone.
     */
    @FXML
    private Label PhoneLabel;
    /**
     * The label for the author's date created.
     */
    @FXML
    private Label DateCreatedLabel;
    /**
     * The label for the author's created by.
     */
    @FXML
    private Label CreatedByLabel;
    /**
     * The label for the author's last updated.
     */
    @FXML
    private Label LastUpdatedLabel;
    /**
     * The label for the author's updated by.
     */
    @FXML
    private Label UpdatedByLabel;
    /**
     * The label for the author's region.
     */
    @FXML
    private Label RegionLabel;
    /**
     * The label for the author's email.
     */
    @FXML
    private Label EmailLabel;
    /**
     * The button to add an author to the database.
     */
    @FXML
    private Button AddButton;
    /**
     * The button to modify an author.
     */
    @FXML
    private Button ModButton;
    /**
     * The button to delete an author from the database.
     */
    @FXML
    private Button DeleteButton;
    /**
     * The button to unselect an author.
     */
    @FXML
    private Button UnselectButton;
    /**
     * The text field for the author's email.
     */
    @FXML
    private TextField EmailText;
    /**
     * The label for the author's id.
     */
    @FXML
    private TextField IDText;
    /**
     * The button for the author's screen's search.
     */
    @FXML
    private Button SearchButton;
    /**
     * The text field for the author's screen search.
     */
    @FXML
    private TextField SearchField;
    /**
     * The table view for the author information
     */
    @FXML
    private TableView<Person> PersonalInformationTable;
    /**
     * The table column for the author's ID.
     */
    @FXML
    private TableColumn<?, ?> IDColumn;
    /**
     * The table column for the author's name.
     */
    @FXML
    private TableColumn<?, ?> NameColumn;
    /**
     * The table column for the author's address.
     */
    @FXML
    private TableColumn<?, ?> AddressColumn;
    /**
     * The table column for the author's postal code.
     */
    @FXML
    private TableColumn<?, ?> PostalCodeColumn;
    /**
     * The table column for the author's phone.
     */
    @FXML
    private TableColumn<?, ?> PhoneColumn;
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
     * The input field at the bottom of the screen for the selected author's name.
     */
    @FXML
    private TextField NameText;
    /**
     * The input field at the bottom of the screen for the selected author's address.
     */
    @FXML
    private TextField AddressText;
    /**
     * The input field at the bottom of the screen for the selected author's postal code.
     */
    @FXML
    private TextField PostalCodeText;
    /**
     * The input field at the bottom of the screen for the selected author's phone.
     */
    @FXML
    private TextField PhoneText;
    /**
     * The display field for who last updated the author.
     */
    @FXML
    private TextField UpdatedByText;
    /**
     * The display field for when the author was last updated.
     */
    @FXML
    private TextField LastUpdatedText;
    /**
     * The display field for who created the author.
     */
    @FXML
    private TextField CreatedByText;
    /**
     * The display field for when the author was created.
     */
    @FXML
    private TextField DateCreatedText;
    /**
     * The table column for the author's region.
     */
    @FXML
    private TableColumn<?, ?> RegionColumn;
    /**
     * The table column for the author's country.
     */
    @FXML
    private TableColumn<?, ?> CountryColumn;
    /**
     * The table column for the author's email.
     */
    @FXML
    private TableColumn<?, ?> EmailColumn;

    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String deleteConfirmTitle = "Delete Author?";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String confirmString = "Confirm";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String deleteConfirmMessage = "Do you want to delete this author?";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String hasAssociatedAppsTitle = "This customer has associated appointments";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String hasAssociatedAppsMessage = "Do you want to delete this customer AND the associated appointments?";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String delFailedTitle = "Delete Customer Failed";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String delFailedHeader = "Unable to Delete Customer";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String delFailedMessage = "Please try again.";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String delFailedUnselectedMessage = "Please select the customer you want to delete";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String modConfirmTitle = "Modify Author?";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String modConfirmMessage = "Do you want to modify this author?";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String modFailedTitle = "Modify Customer Failed";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String modFailedHeader = "Unable to Modify Customer";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String wrongInputsString = "Please check all your inputs and try again.";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String modFailedUnselectedMessage = "Please select the customer you want to modify";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String addConfirmTitle = "Add Author?";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String addConfirmMessage = "Do you want to add this author?";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String addFailedTitle = "Add Customer Failed";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String addFailedHeader = "Unable to Add Customer";
    /**
     * String variable to allow app to load in French or English using resource bundle.
     */
    private String addFailedSelectedString = "Please unselect the customer in order to add a new one";

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
     * This method saves a new author if the user clicks to confirm.
     **/
    @FXML
    protected void AddAuthorClicked() {
        Person authorToAdd = PersonalInformationTable.getSelectionModel().getSelectedItem();
        if (authorToAdd == null) {
            if (HelperMethods.confirmDialog(addConfirmTitle, confirmString, addConfirmMessage)) {
                try {
                    if (isDataValid()) {
                        int addedAuthorID = getNewID();
                        Person addedAuthor = getAuthorFromForm(addedAuthorID);
                        AuthorDatabaseAccess.addAuthor(addedAuthor);
                        allAuthors = AuthorDatabaseAccess.getAllAuthors();
                        PersonalInformationTable.setItems(allAuthors);
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
     * This method modifies an author if the user clicks to confirm.
     */
    @FXML
    protected void ModifyAuthorClicked() {
        Person authorToModify = PersonalInformationTable.getSelectionModel().getSelectedItem();
        if (authorToModify != null) {
            if (HelperMethods.confirmDialog(modConfirmTitle, confirmString, modConfirmMessage)) {
                try {
                    if (isDataValid()) {
                        int modifiedAuthorID = authorToModify.getID();
                        Person modifiedAuthor = getAuthorFromForm(modifiedAuthorID);
                        AuthorDatabaseAccess.modifyAuthor(modifiedAuthor);
                        allAuthors = AuthorDatabaseAccess.getAllAuthors();
                        PersonalInformationTable.setItems(allAuthors);
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
     * This method deletes the selected author if the user clicks to confirm.
     *
     * @throws SQLException if the database access fails
     */
    @FXML
    protected void DeleteAuthorClicked() throws SQLException {
        Person authorToDelete = PersonalInformationTable.getSelectionModel().getSelectedItem();
        if (authorToDelete != null) {
            if (HelperMethods.confirmDialog(deleteConfirmTitle, confirmString, deleteConfirmMessage)) {
                int authorToDeleteID = authorToDelete.getID();
                ObservableList<Appointment> allAppointments = AppointmentDatabaseAccess.getAllAppointments();
                boolean hasAssociatedAppointment = false;
                for (Appointment appointment : allAppointments) {
                    if (appointment.getAuthor_ID() == authorToDeleteID) {
                        hasAssociatedAppointment = true;
                        break;
                    }
                }
                if (hasAssociatedAppointment) {
                    if (HelperMethods.confirmDialog(hasAssociatedAppsTitle, confirmString, hasAssociatedAppsMessage)) {
                        try {
                            AuthorDatabaseAccess.deleteAuthorWithID(authorToDeleteID);
                            allAuthors = AuthorDatabaseAccess.getAllAuthors();
                            PersonalInformationTable.setItems(allAuthors);
                            SearchField.setText("");
                            unselect();
                        } catch (Exception e) {
                            e.printStackTrace();
                            HelperMethods.infoDialog(delFailedTitle, delFailedHeader, delFailedMessage);
                        }
                    }
                } else {
                    try {
                        AuthorDatabaseAccess.deleteAuthorWithID(authorToDeleteID);
                        allAuthors = AuthorDatabaseAccess.getAllAuthors();
                        PersonalInformationTable.setItems(allAuthors);
                        SearchField.setText("");
                        unselect();
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
     * This method initializes the authors screen.
     * It adds all the authors in the system to the display table.
     *
     * @param location  the url
     * @param resources the resource bundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LocalizationLabel.setText(HelperMethods.getLocalizationText());
        ExitButton.setText(HelperMethods.getExitButtonText());
        GSALabel.setText(HelperMethods.getHeaderText() + " - " + HelperMethods.getAuthorScreenText());
        BackButton.setText(HelperMethods.getBackButtonText());

        ResourceBundle resourceBundle = ResourceBundle.getBundle("Author", HelperMethods.getLocale());

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

            allAuthors = AuthorDatabaseAccess.getAllAuthors();

            IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
            NameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
            AddressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
            PostalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("Postal_Code"));
            PhoneColumn.setCellValueFactory(new PropertyValueFactory<>("Phone"));
            RegionColumn.setCellValueFactory(new PropertyValueFactory<>("Region_Name"));
            CountryColumn.setCellValueFactory(new PropertyValueFactory<>("Country_Name"));
            EmailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));

            PersonalInformationTable.setItems(allAuthors);

            fillInDropDowns();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method fills in the information for the author dropdown.
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
     * This method generates the next available id for the new author.
     *
     * @return int of the next available id
     */
    private int getNewID() {
        int newID = 1;
        for (Person author : allAuthors) {
            if (author.getID() >= newID) {
                newID = author.getID() + 1;
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
            Person selectedAuthor = PersonalInformationTable.getSelectionModel().getSelectedItem();

            if (selectedAuthor != null) {

                fillInDropDowns();

                NameText.setText(selectedAuthor.getName());
                IDText.setText(String.valueOf(selectedAuthor.getID()));
                AddressText.setText(selectedAuthor.getAddress());
                PostalCodeText.setText(selectedAuthor.getPostal_Code());
                PhoneText.setText(selectedAuthor.getPhone());
                CountryDropDown.setValue(selectedAuthor.getCountry_Name());
                UpdatedByText.setText(selectedAuthor.getLast_Updated_By());
                LastUpdatedText.setText(selectedAuthor.getLast_Update_Display());
                CreatedByText.setText(selectedAuthor.getCreated_By());
                DateCreatedText.setText(selectedAuthor.getCreate_Date_Display());
                updateDivisionsByCountry();
                RegionDropDown.setValue(selectedAuthor.getRegion_Name());
                EmailText.setText(selectedAuthor.getEmail());

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

        if (NameText.getText().equalsIgnoreCase("") || AddressText.getText().equalsIgnoreCase("") || AddressText.getText().equalsIgnoreCase("") || PostalCodeText.getText().equalsIgnoreCase("") || PhoneText.getText().equalsIgnoreCase("") || EmailText.getText().equalsIgnoreCase("")) {
            dataValid = false;
        } else if (RegionDropDown.getSelectionModel().getSelectedItem() == null) {
            dataValid = false;
        }

        return dataValid;
    }

    /**
     * This method creates an author object from the data currently in the form.
     *
     * @param authorID The id of the author.
     * @return the author object
     * @throws SQLException if the database access fails
     */
    private Person getAuthorFromForm(int authorID) throws SQLException {
        Person author;
        String name = NameText.getText();
        String address = AddressText.getText();
        String postalCode = PostalCodeText.getText();
        String phone = PhoneText.getText();
        String email = EmailText.getText();
        String authorRegionName = RegionDropDown.getSelectionModel().getSelectedItem();
        int authorRegionID = 0;
        ObservableList<Region> allRegions = RegionDatabaseAccess.getAllRegions();
        for (Region division : allRegions) {
            if (division.getRegion_Name().equalsIgnoreCase(authorRegionName)) {
                authorRegionID = division.getRegion_ID();
            }
        }
        author = new Person(authorID, name, address, postalCode, phone, email, authorRegionID);
        return author;
    }

    /**
     * This method unselects anything from the author Table
     **/
    @FXML
    public void unselect() {
        PersonalInformationTable.getSelectionModel().clearSelection();
        personSelected();
    }

    /**
     * This method searches the author table.
     * It displays anything that matches in the ID or the name.
     *
     * @throws SQLException if the database access fails
     */
    public void searchClicked() throws SQLException {
        String searchString = SearchField.getText();
        ObservableList<Person> allAuthors = AuthorDatabaseAccess.getAllAuthors();
        ObservableList<Person> allAuthorsFound = FXCollections.observableArrayList();
        try {
            int idNumberSearch = Integer.parseInt(searchString);
            for (Person author : allAuthors) {
                if (author.getID() == idNumberSearch) {
                    allAuthorsFound.add(author);
                }
            }
        } catch (Exception ignored) {

        }
        for (Person author : allAuthors) {
            if (author.getName().contains(searchString)) {
                allAuthorsFound.add(author);
            }
        }

        if (!allAuthorsFound.isEmpty()) {
            PersonalInformationTable.setItems(allAuthorsFound);
        } else {
            SearchField.setText("");
            PersonalInformationTable.setItems(allAuthors);
            HelperMethods.infoDialog("Nothing Found", "There were no search results for "+searchString, "Please try again");
        }
    }
}
