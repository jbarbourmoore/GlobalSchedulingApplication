package DatabaseAccess;

import DataStructures.Appointment;
import Main.DatabaseConnectionManager;
import Main.HelperMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;


/**
 * Created class AppointmentDatabaseAccess.java
 * <p>
 * This class controls the database access for the appointment information in the database.
 *
 * @author Jamie Barbour-Moore
 */
public class AppointmentDatabaseAccess {

    /**
     * This method pulls all the appointments from the database.
     *
     * @return ObservableList of appointments
     * @throws SQLException if database use fails
     */
    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        ObservableList<Appointment> appointmentsObservableList = FXCollections.observableArrayList();
        String queryString = "SELECT * from appointments";
        PreparedStatement preparedStatement = DatabaseConnectionManager.getConnection().prepareStatement(queryString);
        ResultSet allAppointmentsResultSet = preparedStatement.executeQuery();
        while (allAppointmentsResultSet.next()) {
            int appointment_ID = allAppointmentsResultSet.getInt("Appointment_ID");
            String title = allAppointmentsResultSet.getString("Title");
            String description = allAppointmentsResultSet.getString("Description");
            String location = allAppointmentsResultSet.getString("Location");
            String type = allAppointmentsResultSet.getString("Type");
            LocalDateTime start = allAppointmentsResultSet.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = allAppointmentsResultSet.getTimestamp("End").toLocalDateTime();
            int author_ID = allAppointmentsResultSet.getInt("Author_ID");
            int employee_ID = allAppointmentsResultSet.getInt("Employee_ID");
            int editor_ID = allAppointmentsResultSet.getInt("Editor_ID");

            LocalDateTime create_Date = allAppointmentsResultSet.getTimestamp("Create_Date").toLocalDateTime();
            String created_By = allAppointmentsResultSet.getString("Created_By");
            Timestamp last_Update = allAppointmentsResultSet.getTimestamp("Last_Update");
            String last_Updated_By = allAppointmentsResultSet.getString("Last_Updated_By");

            Appointment appointment = new Appointment(appointment_ID, title, description, location, type, start, end, create_Date, created_By, last_Update, last_Updated_By, author_ID, employee_ID, editor_ID);
            appointmentsObservableList.add(appointment);
        }
        return appointmentsObservableList;
    }

    /**
     * This method pulls all the appointments from the database.
     *
     * @return ObservableList of appointments
     * @throws SQLException if database use fails
     */
    public static Appointment getAppointment(int id) throws SQLException {
        Appointment appointment = null;
        String queryString = "SELECT * from appointments WHERE Appointment_ID=?";
        Connection connection = DatabaseConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(queryString);
        preparedStatement.setInt(1, id);
        ResultSet allAppointmentsResultSet = preparedStatement.executeQuery();
        if (allAppointmentsResultSet.next()) {
            int appointment_ID = allAppointmentsResultSet.getInt("Appointment_ID");
            String title = allAppointmentsResultSet.getString("Title");
            String description = allAppointmentsResultSet.getString("Description");
            String location = allAppointmentsResultSet.getString("Location");
            String type = allAppointmentsResultSet.getString("Type");
            LocalDateTime start = allAppointmentsResultSet.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = allAppointmentsResultSet.getTimestamp("End").toLocalDateTime();
            int author_ID = allAppointmentsResultSet.getInt("Author_ID");
            int employee_ID = allAppointmentsResultSet.getInt("Employee_ID");
            int editor_ID = allAppointmentsResultSet.getInt("Editor_ID");

            LocalDateTime create_Date = allAppointmentsResultSet.getTimestamp("Create_Date").toLocalDateTime();
            String created_By = allAppointmentsResultSet.getString("Created_By");
            Timestamp last_Update = allAppointmentsResultSet.getTimestamp("Last_Update");
            String last_Updated_By = allAppointmentsResultSet.getString("Last_Updated_By");

            appointment = new Appointment(appointment_ID, title, description, location, type, start, end, create_Date, created_By, last_Update, last_Updated_By, author_ID, employee_ID, editor_ID);
        }

        return appointment;
    }

    /**
     * This method deletes an appointment with a specified appointment id.
     *
     * @param appointmentID the id of the appointment to be deleted
     * @return an int holding the result of delete
     * @throws SQLException if the database access fails
     */
    public static int deleteAppointment(int appointmentID) throws SQLException {
        Connection connection = DatabaseConnectionManager.getConnection();
        String queryString = "DELETE FROM appointments WHERE Appointment_ID=?";
        PreparedStatement preparedStatement = connection.prepareStatement(queryString);
        preparedStatement.setInt(1, appointmentID);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    /**
     * This method deletes all appointments with a specific author id.
     *
     * @param authorToDelete_id The author id that is being removed
     * @throws SQLException if the database access fails
     */
    public static void deleteAppointmentsWithThisAuthor(int authorToDelete_id) throws SQLException {
        ObservableList<Appointment> getAllAppointmentsList = AppointmentDatabaseAccess.getAllAppointments();
        for (Appointment appointment : getAllAppointmentsList) {
            if (authorToDelete_id == appointment.getAuthor_ID()) {
                AppointmentDatabaseAccess.deleteAppointment(appointment.getAppointment_ID());
            }
        }
    }

    /**
     * This method deletes all appointments with a specific editor id.
     *
     * @param editorToDelete_id The editor id that is being removed
     * @throws SQLException if the database access fails
     */
    public static void deleteAppointmentsWithThisEditor(int editorToDelete_id) throws SQLException {
        ObservableList<Appointment> getAllAppointmentsList = AppointmentDatabaseAccess.getAllAppointments();
        for (Appointment appointment : getAllAppointmentsList) {
            if (editorToDelete_id == appointment.getEditor_ID()) {
                AppointmentDatabaseAccess.deleteAppointment(appointment.getAppointment_ID());
            }
        }
    }

    /**
     * This method saves modified data for the appointment.
     *
     * @param modifiedAppointmentData an appointment object with the new data
     * @throws SQLException if the database access fails
     */
    public static void modifyAppointment(Appointment modifiedAppointmentData) throws SQLException {
        String queryString = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Author_ID = ?, Employee_ID = ?, Editor_ID = ?, Last_Update = ?, Last_Updated_By = ?  WHERE Appointment_ID = ?";

        DatabaseConnectionManager.makePreparedStatement(queryString, DatabaseConnectionManager.getConnection());

        PreparedStatement preparedStatement = DatabaseConnectionManager.getPreparedStatement();

        preparedStatement.setString(1, modifiedAppointmentData.getTitle());
        preparedStatement.setString(2, modifiedAppointmentData.getDescription());
        preparedStatement.setString(3, modifiedAppointmentData.getLocation());
        preparedStatement.setString(4, modifiedAppointmentData.getType());

        LocalDateTime startDateTime = modifiedAppointmentData.getStart();
        LocalDateTime startDateTimeUTC = HelperMethods.getDateTimeForUTC(startDateTime);


        LocalDateTime endDateTime = modifiedAppointmentData.getEnd();
        LocalDateTime endDateTimeUTC = HelperMethods.getDateTimeForUTC(endDateTime);

        preparedStatement.setString(5, startDateTimeUTC.toString());
        preparedStatement.setString(6, endDateTimeUTC.toString());

        preparedStatement.setInt(7, modifiedAppointmentData.getAuthor_ID());
        preparedStatement.setInt(8, modifiedAppointmentData.getEmployee_ID());
        preparedStatement.setInt(9, modifiedAppointmentData.getEditor_ID());

        preparedStatement.setTimestamp(10, Timestamp.valueOf(HelperMethods.getDateTimeForUTC(LocalDateTime.now())));
        preparedStatement.setString(11, DatabaseConnectionManager.getCurrentUser());

        preparedStatement.setInt(12, modifiedAppointmentData.getAppointment_ID());

        preparedStatement.execute();
    }

    /**
     * This method saves a new appointment to the database.
     *
     * @param addedAppointmentData a customer object with the new data
     * @throws SQLException if the database access fails
     */
    public static void addAppointment(Appointment addedAppointmentData) throws SQLException {

        String queryString = "INSERT INTO appointments (Appointment_ID, Title, Description, Location, Type, Start, End, Author_ID, Employee_ID, Editor_ID, Create_Date, Created_By, Last_Update, Last_Updated_By) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        DatabaseConnectionManager.makePreparedStatement(queryString, DatabaseConnectionManager.getConnection());

        PreparedStatement preparedStatement = DatabaseConnectionManager.getPreparedStatement();

        preparedStatement.setInt(1, addedAppointmentData.getAppointment_ID());
        preparedStatement.setString(2, addedAppointmentData.getTitle());
        preparedStatement.setString(3, addedAppointmentData.getDescription());
        preparedStatement.setString(4, addedAppointmentData.getLocation());
        preparedStatement.setString(5, addedAppointmentData.getType());


        LocalDateTime startDateTime = addedAppointmentData.getStart();
        LocalDateTime startDateTimeUTC = HelperMethods.getDateTimeForUTC(startDateTime);


        LocalDateTime endDateTime = addedAppointmentData.getEnd();
        LocalDateTime endDateTimeUTC = HelperMethods.getDateTimeForUTC(endDateTime);

        preparedStatement.setString(6, startDateTimeUTC.toString());
        preparedStatement.setString(7, endDateTimeUTC.toString());

        preparedStatement.setInt(8, addedAppointmentData.getAuthor_ID());
        preparedStatement.setInt(9, addedAppointmentData.getEmployee_ID());
        preparedStatement.setInt(10, addedAppointmentData.getEditor_ID());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowUTC = HelperMethods.getDateTimeForUTC(now);
        preparedStatement.setTimestamp(11, Timestamp.valueOf(now));
        preparedStatement.setString(12, DatabaseConnectionManager.getCurrentUser());
        preparedStatement.setTimestamp(13, Timestamp.valueOf(now));
        preparedStatement.setString(14, DatabaseConnectionManager.getCurrentUser());

        preparedStatement.execute();
    }
}
