package DatabaseAccess;

import DataStructures.Person;
import Main.DatabaseConnectionManager;
import Main.HelperMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created class EditorDatabaseAccess.java
 * <p>
 * This class controls the database access for the editor information in the database.
 *
 * @author Jamie Barbour-Moore
 */
public class EditorDatabaseAccess {

    /**
     * This method pulls all the editors from the database.
     *
     * @return ObservableList of editors
     * @throws SQLException if database use fails
     */
    public static ObservableList<Person> getAllEditors() throws SQLException {
        String queryString = "SELECT * from editors";

        PreparedStatement preparedStatement = DatabaseConnectionManager.getConnection().prepareStatement(queryString);
        ResultSet resultSet = preparedStatement.executeQuery();

        ObservableList<Person> allEditors = FXCollections.observableArrayList();

        while (resultSet.next()) {
            int ID = resultSet.getInt("ID");
            String Name = resultSet.getString("Name");
            String address = resultSet.getString("Address");
            String postal_Code = resultSet.getString("Postal_Code");
            String phone = resultSet.getString("Phone");
            String email = resultSet.getString("Email");
            LocalDateTime create_Date = resultSet.getTimestamp("Create_Date").toLocalDateTime();
            String created_By = resultSet.getString("Created_By");
            Timestamp last_Update = resultSet.getTimestamp("Last_Update");
            String last_Updated_By = resultSet.getString("Last_Updated_By");
            int divisionID = resultSet.getInt("Region_ID");
            Person editor = new Person(ID, Name, address, postal_Code, phone, email, create_Date, created_By, last_Update, last_Updated_By, divisionID);
            allEditors.add(editor);
        }
        return allEditors;
    }

    /**
     * This method retrieves the editor with a specified id from the database.
     *
     * @param editor_ID the id for the editor to be found
     * @return the editor with the id
     * @throws SQLException if the database access fails
     */
    public static Person getEditor(int editor_ID) throws SQLException {
        String queryString = "SELECT * from editors WHERE ID = ?";

        PreparedStatement preparedStatement = DatabaseConnectionManager.getConnection().prepareStatement(queryString);
        preparedStatement.setInt(1, editor_ID);
        ResultSet resultSet = preparedStatement.executeQuery();

        Person editor = null;
        while (resultSet.next()) {
            String Name = resultSet.getString("Name");
            String address = resultSet.getString("Address");
            String postal_Code = resultSet.getString("Postal_Code");
            String phone = resultSet.getString("Phone");
            String email = resultSet.getString("Email");
            LocalDateTime create_Date = resultSet.getTimestamp("Create_Date").toLocalDateTime();
            String created_By = resultSet.getString("Created_By");
            Timestamp last_Update = resultSet.getTimestamp("Last_Update");
            String last_Updated_By = resultSet.getString("Last_Updated_By");
            int divisionID = resultSet.getInt("Region_ID");
            editor = new Person(editor_ID, Name, address, postal_Code, phone, email, create_Date, created_By, last_Update, last_Updated_By, divisionID);
        }
        return editor;
    }


    /**
     * This method deletes an editor with a given id.
     * It also deletes all appointments with that editor.
     *
     * @param editorToDelete_id The id of the author to be deleted
     * @throws SQLException if the database access fails
     */
    public static void deleteEditorWithID(int editorToDelete_id) throws SQLException {
        AppointmentDatabaseAccess.deleteAppointmentsWithThisEditor(editorToDelete_id);

        String queryString = "DELETE FROM editors WHERE ID = ?";
        DatabaseConnectionManager.makePreparedStatement(queryString, DatabaseConnectionManager.getConnection());

        PreparedStatement preparedStatement = DatabaseConnectionManager.getPreparedStatement();
        preparedStatement.setInt(1, editorToDelete_id);
        preparedStatement.execute();

    }

    /**
     * This method saves modified data for the editor.
     *
     * @param modifiedEditor a person object with the new data
     * @throws SQLException if the database access fails
     */
    public static void modifyEditor(Person modifiedEditor) throws SQLException {
        String queryString = "UPDATE editors SET Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Region_ID = ?, Last_Update = ?, Last_Updated_By = ?, Email = ? WHERE ID = ?";

        DatabaseConnectionManager.makePreparedStatement(queryString, DatabaseConnectionManager.getConnection());

        PreparedStatement preparedStatement = DatabaseConnectionManager.getPreparedStatement();

        preparedStatement.setString(1, modifiedEditor.getName());
        preparedStatement.setString(2, modifiedEditor.getAddress());
        preparedStatement.setString(3, modifiedEditor.getPostal_Code());
        preparedStatement.setString(4, modifiedEditor.getPhone());
        preparedStatement.setInt(5, modifiedEditor.getRegion().getRegion_ID());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowUTC = HelperMethods.getDateTimeForUTC(now);

        preparedStatement.setTimestamp(6, Timestamp.valueOf(nowUTC));
        preparedStatement.setString(7, DatabaseConnectionManager.getCurrentUser());
        preparedStatement.setString(8, modifiedEditor.getEmail());

        preparedStatement.setInt(9, modifiedEditor.getID());

        preparedStatement.execute();
    }

    /**
     * This method adds a new editor to the database
     *
     * @param addedEditor a person object with the new data
     * @throws SQLException if the database access fails
     */
    public static void addEditor(Person addedEditor) throws SQLException {

        String queryString = "INSERT INTO editors (ID, Name, Address, Postal_Code, Phone, Email, Create_Date, Created_By, Last_Update, Last_Updated_By, Region_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        DatabaseConnectionManager.makePreparedStatement(queryString, DatabaseConnectionManager.getConnection());

        PreparedStatement preparedStatement = DatabaseConnectionManager.getPreparedStatement();

        preparedStatement.setInt(1, addedEditor.getID());
        preparedStatement.setString(2, addedEditor.getName());
        preparedStatement.setString(3, addedEditor.getAddress());
        preparedStatement.setString(4, addedEditor.getPostal_Code());
        preparedStatement.setString(5, addedEditor.getPhone());
        preparedStatement.setString(6, addedEditor.getEmail());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowUTC = HelperMethods.getDateTimeForUTC(now);

        preparedStatement.setTimestamp(7, Timestamp.valueOf(nowUTC));
        preparedStatement.setString(8, DatabaseConnectionManager.getCurrentUser());
        preparedStatement.setTimestamp(9, Timestamp.valueOf(nowUTC));
        preparedStatement.setString(10, DatabaseConnectionManager.getCurrentUser());

        preparedStatement.setInt(11, addedEditor.getRegion().getRegion_ID());

        preparedStatement.execute();
    }

}
