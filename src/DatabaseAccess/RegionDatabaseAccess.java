package DatabaseAccess;

import DataStructures.Region;
import Main.DatabaseConnectionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created class RegionDatabaseAccess.java
 * <p>
 * This class controls the database access for the first level division information in the database.
 *
 * @author Jamie Barbour-Moore
 */
public class RegionDatabaseAccess {

    /**
     * This method pulls all the regions from the database.
     *
     * @return ObservableList of regions
     * @throws SQLException if database use fails
     */
    public static ObservableList<Region> getAllRegions() throws SQLException {
        ObservableList<Region> firstLevelDivisionsObservableList = FXCollections.observableArrayList();
        String queryString = "SELECT * from regions";
        PreparedStatement preparedStatement = DatabaseConnectionManager.getConnection().prepareStatement(queryString);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int divisionID = resultSet.getInt("Region_ID");
            String divisionName = resultSet.getString("Region_Name");
            int country_ID = resultSet.getInt("Country_ID");
            LocalDateTime create_Date = resultSet.getTimestamp("Create_Date").toLocalDateTime();
            String created_By = resultSet.getString("Created_By");
            Timestamp last_Update = resultSet.getTimestamp("Last_Update");
            String last_Updated_By = resultSet.getString("last_Updated_By");
            Region region = new Region(divisionID, divisionName, create_Date, created_By, last_Update, last_Updated_By, country_ID);
            firstLevelDivisionsObservableList.add(region);
        }
        return firstLevelDivisionsObservableList;
    }

    /**
     * This method pulls all the regions from the database.
     *
     * @param regionID the id of the region to be retrieved
     * @return region with the given id
     * @throws SQLException if database use fails
     */
    public static Region getRegion(int regionID) throws SQLException {
        Region region = null;
        String queryString = "SELECT * from regions WHERE Region_ID = ?";
        PreparedStatement preparedStatement = DatabaseConnectionManager.getConnection().prepareStatement(queryString);
        preparedStatement.setInt(1, regionID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String regionName = resultSet.getString("Region_Name");
            int country_ID = resultSet.getInt("Country_ID");
            LocalDateTime create_Date = resultSet.getTimestamp("Create_Date").toLocalDateTime();
            String created_By = resultSet.getString("Created_By");
            Timestamp last_Update = resultSet.getTimestamp("Last_Update");
            String last_Updated_By = resultSet.getString("last_Updated_By");
            region = new Region(regionID, regionName, create_Date, created_By, last_Update, last_Updated_By, country_ID);
        }
        return region;
    }

}
