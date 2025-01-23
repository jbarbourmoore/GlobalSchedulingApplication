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
 * Created class AuthorDatabaseAccess.java
 * <p>
 * This class controls the database access for the author information in the database.
 *
 * @author Jamie Barbour-Moore
 */
public class AuthorDatabaseAccess {

    /**
     * This method pulls all the authors from the database.
     *
     * @return ObservableList of authors
     * @throws SQLException if database use fails
     */
    public static ObservableList<Person> getAllAuthors() throws SQLException {
        String queryString = "SELECT * from authors";

        PreparedStatement preparedStatement = DatabaseConnectionManager.getConnection().prepareStatement(queryString);
        ResultSet resultSet = preparedStatement.executeQuery();

        ObservableList<Person> authorsObservableList = FXCollections.observableArrayList();

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
            Person author = new Person(ID, Name, address, postal_Code, phone, email, create_Date, created_By, last_Update, last_Updated_By, divisionID);
            authorsObservableList.add(author);
        }
        return authorsObservableList;
    }

    /**
     * This method retrieves an author with a given id from the database.
     *
     * @param author_ID the  id for the author to be retrieved
     * @return the author with the given id
     * @throws SQLException if the database connection fails
     */
    public static Person getAuthor(int author_ID) throws SQLException {
        String queryString = "SELECT * from authors WHERE ID = ?";

        PreparedStatement preparedStatement = DatabaseConnectionManager.getConnection().prepareStatement(queryString);
        preparedStatement.setInt(1, author_ID);
        ResultSet resultSet = preparedStatement.executeQuery();

        Person author = null;
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
            author = new Person(author_ID, Name, address, postal_Code, phone, email, create_Date, created_By, last_Update, last_Updated_By, divisionID);
        }
        return author;
    }

    /**
     * This method deletes an author with a given id.
     * It also deletes all appointments with that author.
     *
     * @param authorToDelete_id The id of the author to be deleted
     * @throws SQLException if the database access fails
     */
    public static void deleteAuthorWithID(int authorToDelete_id) throws SQLException {
        AppointmentDatabaseAccess.deleteAppointmentsWithThisAuthor(authorToDelete_id);

        String queryString = "DELETE FROM authors WHERE ID = ?";
        DatabaseConnectionManager.makePreparedStatement(queryString, DatabaseConnectionManager.getConnection());

        PreparedStatement preparedStatement = DatabaseConnectionManager.getPreparedStatement();
        preparedStatement.setInt(1, authorToDelete_id);
        preparedStatement.execute();

    }

    /**
     * This method saves modified data for the author.
     *
     * @param modifiedCAuthorData a person object with the new data
     * @throws SQLException if the database access fails
     */
    public static void modifyAuthor(Person modifiedCAuthorData) throws SQLException {
        String queryString = "UPDATE authors SET Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Region_ID = ?, Last_Update = ?, Last_Updated_By = ?, Email = ? WHERE ID = ?";

        DatabaseConnectionManager.makePreparedStatement(queryString, DatabaseConnectionManager.getConnection());

        PreparedStatement preparedStatement = DatabaseConnectionManager.getPreparedStatement();

        preparedStatement.setString(1, modifiedCAuthorData.getName());
        preparedStatement.setString(2, modifiedCAuthorData.getAddress());
        preparedStatement.setString(3, modifiedCAuthorData.getPostal_Code());
        preparedStatement.setString(4, modifiedCAuthorData.getPhone());
        preparedStatement.setInt(5, modifiedCAuthorData.getRegion().getRegion_ID());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowUTC = HelperMethods.getDateTimeForUTC(now);

        preparedStatement.setTimestamp(6, Timestamp.valueOf(nowUTC));
        preparedStatement.setString(7, DatabaseConnectionManager.getCurrentUser());
        preparedStatement.setString(8, modifiedCAuthorData.getEmail());

        preparedStatement.setInt(9, modifiedCAuthorData.getID());

        preparedStatement.execute();
    }

    /**
     * This method adds a new author to the database
     *
     * @param addedAuthorData a person object with the new data
     * @throws SQLException if the database access fails
     */
    public static void addAuthor(Person addedAuthorData) throws SQLException {

        String queryString = "INSERT INTO authors (ID, Name, Address, Postal_Code, Phone, Email, Create_Date, Created_By, Last_Update, Last_Updated_By, Region_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        DatabaseConnectionManager.makePreparedStatement(queryString, DatabaseConnectionManager.getConnection());

        PreparedStatement preparedStatement = DatabaseConnectionManager.getPreparedStatement();

        preparedStatement.setInt(1, addedAuthorData.getID());
        preparedStatement.setString(2, addedAuthorData.getName());
        preparedStatement.setString(3, addedAuthorData.getAddress());
        preparedStatement.setString(4, addedAuthorData.getPostal_Code());
        preparedStatement.setString(5, addedAuthorData.getPhone());
        preparedStatement.setString(6, addedAuthorData.getEmail());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowUTC = HelperMethods.getDateTimeForUTC(now);

        preparedStatement.setTimestamp(7, Timestamp.valueOf(nowUTC));
        preparedStatement.setString(8, DatabaseConnectionManager.getCurrentUser());
        preparedStatement.setTimestamp(9, Timestamp.valueOf(nowUTC));
        preparedStatement.setString(10, DatabaseConnectionManager.getCurrentUser());
        preparedStatement.setInt(11, addedAuthorData.getRegion().getRegion_ID());
        preparedStatement.execute();
    }

}
