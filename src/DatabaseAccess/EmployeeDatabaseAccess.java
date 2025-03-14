package DatabaseAccess;

import DataStructures.Employee;
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
 * Created class EmployeeDatabaseAccess.java
 * <p>
 * This class controls the database access for the employee information in the database.
 *
 * @author Jamie Barbour-Moore
 */
public class EmployeeDatabaseAccess {

    /**
     * This method pulls all the employee from the database.
     *
     * @return ObservableList of employee
     * @throws SQLException if database use fails
     */
    public static ObservableList<Employee> getAllEmployees() throws SQLException {
        ObservableList<Employee> usersObservableList = FXCollections.observableArrayList();
        String queryString = "SELECT * from employees";
        PreparedStatement preparedStatement = DatabaseConnectionManager.getConnection().prepareStatement(queryString);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            LocalDateTime create_Date = resultSet.getTimestamp("Create_Date").toLocalDateTime();
            String created_By = resultSet.getString("Created_By");
            Timestamp last_Update = resultSet.getTimestamp("Last_Update");
            String last_Updated_By = resultSet.getString("Last_Updated_By");
            int region_ID = resultSet.getInt("Region_ID");
            Employee employee = new Employee(resultSet.getInt("ID"), resultSet.getString("Name"), resultSet.getString("Address"), resultSet.getString("Postal_Code"), resultSet.getString("Phone"), resultSet.getString("Email"), create_Date, created_By, last_Update, last_Updated_By, region_ID, resultSet.getString("User_Name"));
            usersObservableList.add(employee);
        }
        return usersObservableList;
    }

    /**
     * This method gets the User with specified id from the employees database.
     *
     * @param employee_ID the employee's id
     * @return the employee object with the id
     * @throws SQLException if the database access fails
     */
    public static Employee getEmployee(int employee_ID) throws SQLException {
        Employee employee = null;
        String queryString = "SELECT * from employees WHERE ID = ?";
        PreparedStatement preparedStatement = DatabaseConnectionManager.getConnection().prepareStatement(queryString);
        preparedStatement.setInt(1, employee_ID);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            LocalDateTime create_Date = resultSet.getTimestamp("Create_Date").toLocalDateTime();
            String created_By = resultSet.getString("Created_By");
            Timestamp last_Update = resultSet.getTimestamp("Last_Update");
            String last_Updated_By = resultSet.getString("Last_Updated_By");
            int region_ID = resultSet.getInt("Region_ID");
            employee = new Employee(employee_ID, resultSet.getString("Name"), resultSet.getString("Address"), resultSet.getString("Postal_Code"), resultSet.getString("Phone"), resultSet.getString("Email"), create_Date, created_By, last_Update, last_Updated_By, region_ID, resultSet.getString("User_Name"));
        }
        return employee;
    }

    /**
     * This method checks if the username and password entered are valid.
     *
     * It fetches the salt value from the database and uses that to generate the sha-256 has for the password.
     * This generated hash is then compared with the value in the database to determine if the password is correct.
     *
     * @param username the inputted username
     * @param password the inputted password
     * @return boolean that is true if the credentials are valid
     */
    public static boolean checkUserAndPassword(String username, String password) {
        String stringQuery = "SELECT User_Name, Salt FROM employees WHERE User_Name = '" + username +"'"           ;
        try {
            PreparedStatement preparedStatement = DatabaseConnectionManager.getConnection().prepareStatement(stringQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String salt = resultSet.getString("Salt");
                String generatedSaltedHash = HelperMethods.generateHash(password, salt);
                String checkHashQuery = "SELECT User_Name FROM employees WHERE User_Name = '" + username +"' AND Salted_Hash = '" + generatedSaltedHash + "'";           ;
                PreparedStatement preparedCheckHashStatement = DatabaseConnectionManager.getConnection().prepareStatement(checkHashQuery);
                ResultSet resultCheckHashSet = preparedCheckHashStatement.executeQuery();
                if (resultCheckHashSet.next()) {
                    if(resultCheckHashSet.getString("User_Name").equals(username)){
                        return true;
                    }
                }
            }
        } catch (SQLException ignored) {
        }
        return false;
    }

}
