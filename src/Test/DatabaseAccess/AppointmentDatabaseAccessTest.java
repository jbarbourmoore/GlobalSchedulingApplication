package DatabaseAccess;

import DataStructures.Appointment;
import DataStructures.Person;
import Main.DatabaseConnectionManager;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentDatabaseAccessTest {
    private static final int id = 999;
    private static final String title = "title";
    private static final String description = "description";
    private static final String location = "location";
    private static final String type = "type";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final LocalDateTime start = LocalDateTime.parse("2023-01-01 11:00:00", formatter);
    private static final LocalDateTime end = LocalDateTime.parse("2023-01-01 12:00:00", formatter);
    private static final LocalDateTime create_Date = LocalDateTime.parse("2023-01-01 00:00:00", formatter);
    private static final String created_By = "Created By";
    private static final Timestamp last_Update = Timestamp.valueOf(LocalDateTime.parse("2023-01-01 01:00:00", formatter));
    private static final String last_Updated_By = "Last Updated By";
    private static final String name = "Name";
    private static final String address = "address";
    private static final String postal_code = "postalcode";
    private static final String phone = "phone";
    private static final String email = "email";
    private static final int region_ID = 1;
    private static final int emp_id = 1;
    private static int test_author_id = 10;
    private static int test_editor_id = 10;
    private static Appointment testAppointment;

    @BeforeAll
    static void beforeAll() {
        DatabaseConnectionManager.makeConnection();
    }
    @BeforeEach
    void setUp() throws SQLException {
        test_author_id = getNewAuthorID();
        test_editor_id = getNewEditorID();
        testAppointment = new Appointment(id, title, description, location, type, start, end, create_Date,
                        created_By, last_Update, last_Updated_By, test_author_id, emp_id, test_editor_id);
        Person testAuthor = new Person(test_author_id, name, address, postal_code, phone, email, region_ID);
        Person testEditor = new Person(test_editor_id, name, address, postal_code, phone, email, region_ID);
        try {
            AuthorDatabaseAccess.addAuthor(testAuthor);
        } catch (Exception ignored) {
        }
        try {
            EditorDatabaseAccess.addEditor(testEditor);
        } catch (Exception ignored) {

        }
    }

    @Test
    void getAllAppointments() throws SQLException {
        AppointmentDatabaseAccess.addAppointment(testAppointment);
        ObservableList<Appointment> appointments = AppointmentDatabaseAccess.getAllAppointments();
        assertNotNull(appointments);
        boolean foundAppointment =false;
        for(Appointment appointment:appointments){
            if(testAppointment.getAppointment_ID() == appointment.getAppointment_ID()){
                foundAppointment = true;
            }
        }
        assertTrue(foundAppointment);
        AppointmentDatabaseAccess.deleteAppointment(testAppointment.getAppointment_ID());
    }

    @Test
    void deleteAppointment() throws SQLException {
        AppointmentDatabaseAccess.addAppointment(testAppointment);
        assertNotNull(AppointmentDatabaseAccess.getAppointment(id));
        AppointmentDatabaseAccess.deleteAppointment(id);
        assertNull(AppointmentDatabaseAccess.getAppointment(id));
    }

    @Test
    void deleteAppointmentsWithThisAuthor() throws SQLException {
        AppointmentDatabaseAccess.addAppointment(testAppointment);
        assertNotNull(AppointmentDatabaseAccess.getAppointment(id));
        AppointmentDatabaseAccess.deleteAppointmentsWithThisAuthor(test_author_id);
        assertNull(AppointmentDatabaseAccess.getAppointment(id));
    }

    @Test
    void deleteAppointmentsWithThisEditor() throws SQLException {
        AppointmentDatabaseAccess.addAppointment(testAppointment);
        assertNotNull(AppointmentDatabaseAccess.getAppointment(id));
        AppointmentDatabaseAccess.deleteAppointmentsWithThisEditor(test_editor_id);
        assertNull(AppointmentDatabaseAccess.getAppointment(id));
    }

    @Test
    void modifyAppointment() throws SQLException {
        AppointmentDatabaseAccess.addAppointment(testAppointment);
        assertEquals(AppointmentDatabaseAccess.getAppointment(id).getTitle(), testAppointment.getTitle());
        Appointment modifiedAppointment = new Appointment(id, "A New Title", description, location, type,
                start, end, create_Date, created_By, last_Update, last_Updated_By, test_author_id, emp_id, test_editor_id);
        AppointmentDatabaseAccess.modifyAppointment(modifiedAppointment);
        assertEquals(AppointmentDatabaseAccess.getAppointment(id).getTitle(), modifiedAppointment.getTitle());
    }

    @Test
    void addAppointment() throws SQLException {
        assertNull(AppointmentDatabaseAccess.getAppointment(id));
        AppointmentDatabaseAccess.addAppointment(testAppointment);
        assertNotNull(AppointmentDatabaseAccess.getAppointment(id));
    }

    private int getNewAuthorID() throws SQLException {
        ObservableList<Person> allAuthors = AuthorDatabaseAccess.getAllAuthors();
        int newID = 1;
        for (Person author : allAuthors) {
            if (author.getID() >= newID) {
                newID = author.getID() + 1;
            }
        }
        return newID;
    }

    private int getNewEditorID() throws SQLException {

        ObservableList<Person> allAuthors = EditorDatabaseAccess.getAllEditors();
        int newID = 1;
        for (Person author : allAuthors) {
            if (author.getID() >= newID) {
                newID = author.getID() + 1;
            }
        }
        return newID;
    }

    @AfterEach
    void tearDown() {
        try {
            AuthorDatabaseAccess.deleteAuthorWithID(test_author_id);
        } catch (Exception ignored) {
        }
        try {
            EditorDatabaseAccess.deleteEditorWithID(test_editor_id);
        } catch (Exception ignored) {
        }
        try {
            AppointmentDatabaseAccess.deleteAppointment(id);
        } catch (Exception ignored) {
        }
    }
}