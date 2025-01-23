package DataStructures;

import DatabaseAccess.AuthorDatabaseAccess;
import DatabaseAccess.EditorDatabaseAccess;
import DatabaseAccess.EmployeeDatabaseAccess;
import Main.DatabaseConnectionManager;
import Main.HelperMethods;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AppointmentTest {
    private static final int id = 12;
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

    private static final int author_id = 1;
    private static final int editor_id = 2;
    private static final int employee_id = 3;


    private Appointment testAppointment;
    private Appointment testAppointmentComplete;

    @BeforeAll
    static void beforeAll() {
        DatabaseConnectionManager.makeConnection();
    }

    @BeforeEach
    void setUp() {
        testAppointment = new Appointment(id, title, description, location, type, start, end, author_id, employee_id, editor_id);
        testAppointmentComplete = new Appointment(id, title, description, location, type, start, end, create_Date, created_By, last_Update, last_Updated_By, author_id, employee_id, editor_id);
    }

    @Test
    void equals() {
        Appointment mewAppointment = new Appointment(id, title, description, location, type, start, end, create_Date, created_By, last_Update, last_Updated_By, author_id, employee_id, editor_id);
        assertNotEquals(testAppointment, mewAppointment);
        assertEquals(testAppointmentComplete, mewAppointment);
    }

    @Test
    void getAppointment_ID() {
        assertEquals(id, testAppointment.getAppointment_ID());
    }

    @Test
    void getTitle() {
        assertEquals(title, testAppointment.getTitle());

    }

    @Test
    void setTitle() {
        assertEquals(title, testAppointment.getTitle());
        testAppointment.setTitle("New Title");
        assertEquals("New Title", testAppointment.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals(description, testAppointment.getDescription());
    }

    @Test
    void setDescription() {
        assertEquals(description, testAppointment.getDescription());
        testAppointment.setDescription("New Description");
        assertEquals("New Description", testAppointment.getDescription());
    }

    @Test
    void getLocation() {
        assertEquals(location, testAppointment.getLocation());
    }

    @Test
    void setLocation() {
        assertEquals(location, testAppointment.getLocation());
        testAppointment.setLocation("New Location");
        assertEquals("New Location", testAppointment.getLocation());
    }

    @Test
    void getType() {
        assertEquals(type, testAppointment.getType());
    }

    @Test
    void setType() {
        assertEquals(type, testAppointment.getType());
        testAppointment.setType("New Type");
        assertEquals("New Type", testAppointment.getType());
    }

    @Test
    void getEnd() {
        assertEquals(end, testAppointment.getEnd());
    }

    @Test
    void setEnd() {
        LocalDateTime comparisonNew = LocalDateTime.parse("2023-01-01 13:00:00", formatter);
        assertEquals(end, testAppointment.getEnd());
        testAppointment.setEnd(comparisonNew);
        assertEquals(comparisonNew, testAppointment.getEnd());
    }

    @Test
    void getEnd_Display() {
        assertEquals(HelperMethods.formatDateTimeDisplay(end), testAppointment.getEnd_Display());
    }

    @Test
    void getStart() {
        assertEquals(start, testAppointment.getStart());
    }

    @Test
    void setStart() {
        LocalDateTime comparisonNew = LocalDateTime.parse("2023-01-01 12:00:00", formatter);
        assertEquals(start, testAppointment.getStart());
        testAppointment.setStart(comparisonNew);
        assertEquals(comparisonNew, testAppointment.getStart());
    }

    @Test
    void getStart_Display() {
        assertEquals(HelperMethods.formatDateTimeDisplay(start), testAppointment.getStart_Display());
    }

    @Test
    void getCreate_Date() {
        assertEquals(create_Date, testAppointmentComplete.getCreate_Date());
    }

    @Test
    void setCreate_Date() {
        LocalDateTime comparisonNew = LocalDateTime.parse("2023-01-01 00:30:00", formatter);
        assertEquals(create_Date, testAppointmentComplete.getCreate_Date());
        testAppointmentComplete.setCreate_Date(comparisonNew);
        assertEquals(comparisonNew, testAppointmentComplete.getCreate_Date());
    }

    @Test
    void getCreate_Date_Display() {
        assertEquals(HelperMethods.formatDateTimeDisplay(create_Date), testAppointmentComplete.getCreate_Date_Display());
    }

    @Test
    void getCreated_By() {
        assertEquals(created_By, testAppointmentComplete.getCreated_By());
    }

    @Test
    void getLast_Update() {
        assertEquals(last_Update, testAppointmentComplete.getLast_Update());
    }

    @Test
    void getLast_Update_Display() {
        assertEquals(testAppointmentComplete.getLast_Update_Display(), HelperMethods.formatDateTimeDisplay(last_Update));
    }

    @Test
    void getLast_Updated_By() {
        assertEquals(last_Updated_By, testAppointmentComplete.getLast_Updated_By());
    }

    @Test
    void getAuthor_ID() {
        assertEquals(author_id, testAppointment.getAuthor_ID());
    }

    @Test
    void getEmployee_ID() {
        assertEquals(employee_id, testAppointment.getEmployee_ID());
    }

    @Test
    void getEditor_ID() {
        assertEquals(editor_id, testAppointment.getEditor_ID());

    }

    @Test
    void getAuthor() throws SQLException {
        Person author = AuthorDatabaseAccess.getAuthor(author_id);
        assertEquals(author, testAppointment.getAuthor());
    }

    @Test
    void getAuthor_Name() throws SQLException {
        Person author = AuthorDatabaseAccess.getAuthor(author_id);
        assertEquals(author, testAppointment.getAuthor());
        assertEquals(author.getName(), testAppointment.getAuthor_Name());
    }

    @Test
    void getEmployee() throws SQLException {
        Employee employee = EmployeeDatabaseAccess.getEmployee(employee_id);
        assertEquals(employee, testAppointment.getEmployee());
    }

    @Test
    void getEmployee_Name() throws SQLException {
        Employee employee = EmployeeDatabaseAccess.getEmployee(employee_id);
        assertEquals(employee, testAppointment.getEmployee());
        assertEquals(employee.getName(), testAppointment.getEmployee_Name());
    }

    @Test
    void getEditor() throws SQLException {
        Person editor = EditorDatabaseAccess.getEditor(editor_id);
        assertEquals(editor, testAppointment.getEditor());
    }

    @Test
    void getEditor_Name() throws SQLException {
        Person editor = EditorDatabaseAccess.getEditor(editor_id);
        assertEquals(editor, testAppointment.getEditor());
        assertEquals(editor.getName(), testAppointment.getEditor_Name());
    }
}