package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created class DatabaseConnectionManager.java
 * <p>
 * This class manages the connections to the database.
 * It also stores the user's username.
 *
 * @author Jamie Barbour-Moore
 */
public class DatabaseConnectionManager {
    /**
     * A string containing the value for protocol.
     */
    private static final String protocol = "jdbc";

    /**
     * A string containing the value for vendor.
     */
    private static final String vendor = ":mysql:";

    /**
     * A string containing the value for the url.
     */
    private static final String location = "//localhost/";

    /**
     * A string containing the value for database name.
     */
    private static final String databaseName = "globalschedulingapplication";

    /**
     * A string containing the value for the full url to the database.
     */
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL

    /**
     * A string containing the value for driver.
     */
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference

    /**
     * A string containing the value for username.
     */
    private static final String userName = ""; // Username

    /**
     * A string containing the value for password.
     */
    private static final String password = ""; // Password

    /**
     * A string containing the value for the current username.
     */
    private static String currentUser = "";

    /**
     * A Connection containing the current database connection.
     */
    private static Connection connection = null;  // Connection Interface

    /**
     * A PreparedStatement containing the current prepared statement.
     */
    private static PreparedStatement preparedStatement;

    /**
     * This method meakes a connection with the database.
     */
    public static void makeConnection() {

        try {
            Class.forName(driver); // Locate Driver
            //password = Details.getPassword(); // Assign password
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // reference Connection object
            System.out.println("Connection successful!");
        } catch (ClassNotFoundException e) {
            System.out.println("Error:" + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * This method gets the connection.
     *
     * @return the connection with the database
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * This method closes the connection with the database.
     */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method creates a prepared statement given sql and connection.
     *
     * @param sqlStatement The sql for the statement
     * @param conn         The connection
     * @throws SQLException if the database access fails
     */
    public static void makePreparedStatement(String sqlStatement, Connection conn) throws SQLException {
        if (conn != null)
            preparedStatement = conn.prepareStatement(sqlStatement);
        else
            System.out.println("Prepared Statement Creation Failed!");
    }

    /**
     * This method gets the prepared statement.
     * If there is not a prepared statement it returns null.
     *
     * @return the prepared statment
     * @throws SQLException if the database connection fails
     */
    public static PreparedStatement getPreparedStatement() throws SQLException {
        if (preparedStatement != null)
            return preparedStatement;
        else System.out.println("Null reference to Prepared Statement");
        return null;
    }

    /**
     * This method gets the current user.
     *
     * @return the current user
     */
    public static String getCurrentUser() {
        return currentUser;
    }

    /**
     * This method sets the current user.
     *
     * @param currentUser the new current user
     */
    public static void setCurrentUser(String currentUser) {
        DatabaseConnectionManager.currentUser = currentUser;
    }
}
