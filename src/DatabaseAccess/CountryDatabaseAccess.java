package DatabaseAccess;

import DataStructures.Country;
import Main.DatabaseConnectionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created class CountryDatabaseAccess.java
 * <p>
 * This class controls the database access for the country information in the database.
 *
 * @author Jamie Barbour-Moore
 */
public class CountryDatabaseAccess {

    /**
     * This method pulls all the countries from the database.
     *
     * @return ObservableList of countries
     * @throws SQLException if database use fails
     */
    public static ObservableList<Country> getCountries() throws SQLException {
        ObservableList<Country> countriesObservableList = FXCollections.observableArrayList();
        String queryString = "SELECT * from countries";
        PreparedStatement preparedStatement = DatabaseConnectionManager.getConnection().prepareStatement(queryString);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int country_ID = resultSet.getInt("Country_ID");
            String country = resultSet.getString("Country");
            LocalDateTime create_Date = resultSet.getTimestamp("Create_Date").toLocalDateTime();
            String created_By = resultSet.getString("Created_By");
            Timestamp last_Update = resultSet.getTimestamp("Last_Update");
            String last_Updated_By = resultSet.getString("last_Updated_By");

            Country newCountry = new Country(country_ID, country, create_Date, created_By, last_Update, last_Updated_By);
            countriesObservableList.add(newCountry);
        }
        return countriesObservableList;
    }

    /**
     * This method pulls all the first level divisions from the database.
     *
     * @param countryID the id of the country to retrieve
     * @return ObservableList of first level divisions
     * @throws SQLException if database use fails
     */
    public static Country getCountry(int countryID) throws SQLException {
        Country country = null;
        String queryString = "SELECT * from countries WHERE Country_ID = ?";
        PreparedStatement preparedStatement = DatabaseConnectionManager.getConnection().prepareStatement(queryString);
        preparedStatement.setInt(1, countryID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String countryName = resultSet.getString("Country");
            LocalDateTime create_Date = resultSet.getTimestamp("Create_Date").toLocalDateTime();
            String created_By = resultSet.getString("Created_By");
            Timestamp last_Update = resultSet.getTimestamp("Last_Update");
            String last_Updated_By = resultSet.getString("last_Updated_By");

            country = new Country(countryID, countryName, create_Date, created_By, last_Update, last_Updated_By);

        }
        return country;
    }

}
