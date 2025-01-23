package DataStructures;

import DatabaseAccess.AuthorDatabaseAccess;
import DatabaseAccess.EditorDatabaseAccess;
import DatabaseAccess.EmployeeDatabaseAccess;
import Main.HelperMethods;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 * Created class Appointment.java
 * <p>
 * This class is the data structure for the appointment information from the database.
 *
 * @author Jamie Barbour-Moore
 */
public class Appointment extends DatabaseObjectClass {

    /**
     * an int containing the appointment's author id.
     */
    private final int Author_ID;
    /**
     * an int containing the appointment's employee id.
     */
    private final int Employee_ID;
    /**
     * an int containing the appointment's editor id.
     */
    private final int Editor_ID;
    /**
     * A String containing the Appointment's title.
     */
    private String Title;
    /**
     * A String containing the Appointment's description.
     */
    private String Description;
    /**
     * A String containing the Appointment's location.
     */
    private String Location;
    /**
     * A String containing the Appointment's type.
     */
    private String Type;
    /**
     * A LocalDateTime containing the Appointment's start date and time.
     */
    private LocalDateTime Start;
    /**
     * A LocalDateTime containing the Appointment's end date and time.
     */
    private LocalDateTime End;

    /**
     * The author associated with the appointment's author id.
     */
    private Person Author;

    /**
     * The employee associated with the appointment's employee id.
     */
    private Employee Employee;

    /**
     * The editor associated with the appointment's editor id.
     */
    private Person Editor;

    /**
     * This method is a constructor for appointment.
     *
     * @param appointment_ID the id for the appointment
     * @param title          the title for the appointment
     * @param description    the description for the appointment
     * @param location       the location for the appointment
     * @param type           the type of the appointment
     * @param start          the start date and time for the appointment
     * @param end            the end date and time for the appointment
     * @param author_ID      the id for the author
     * @param employee_ID    the id for the employee
     * @param editor_ID      the id for the editor
     */
    public Appointment(int appointment_ID, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int author_ID, int employee_ID, int editor_ID) {
        super(appointment_ID);
        Title = title;
        Description = description;
        Location = location;
        Type = type;
        Start = start;
        End = end;
        Author_ID = author_ID;
        Employee_ID = employee_ID;
        Editor_ID = editor_ID;

        createConnectedObjects();
    }

    /**
     * This method is a constructor for appointment.
     *
     * @param appointment_ID  the id for the appointment
     * @param title           the title for the appointment
     * @param description     the description for the appointment
     * @param location        the location for the appointment
     * @param type            the type of the appointment
     * @param start           the start date and time for the appointment
     * @param end             the end date and time for the appointment
     * @param create_Date     the date and time when the appointment was created
     * @param created_By      the person who created the appointment
     * @param last_Update     the date and time when the appointment was last updated
     * @param last_Updated_By the person who last updated the appointment
     * @param author_ID       the id for the author
     * @param employee_ID     the id for the employee
     * @param editor_ID       the id for the editor
     */
    public Appointment(int appointment_ID, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, LocalDateTime create_Date, String created_By, Timestamp last_Update, String last_Updated_By, int author_ID, int employee_ID, int editor_ID) {
        super(appointment_ID, create_Date, created_By, last_Update, last_Updated_By);
        Title = title;
        Description = description;
        Location = location;
        Type = type;
        Start = start;
        End = end;
        Author_ID = author_ID;
        Employee_ID = employee_ID;
        Editor_ID = editor_ID;
        createConnectedObjects();
    }

    /**
     * This method retrieves the customer user and contact associated with this appointment.
     */
    private void createConnectedObjects() {
        try {
            Author = AuthorDatabaseAccess.getAuthor(Author_ID);
            Employee = EmployeeDatabaseAccess.getEmployee(Employee_ID);
            Editor = EditorDatabaseAccess.getEditor(Editor_ID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method gets the id for the appointment.
     *
     * @return the appointment's ID
     */
    public int getAppointment_ID() {
        return ID;
    }

    /**
     * This method gets the title for the appointment.
     *
     * @return the appointment's title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * This method sets the title for the appointment.
     *
     * @param title the new title for the appointment
     */
    public void setTitle(String title) {
        Title = title;
    }

    /**
     * This method gets the description for the appointment.
     *
     * @return the appointment's description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * This method sets the description for the appointment.
     *
     * @param description the new description for the appointment
     */
    public void setDescription(String description) {
        Description = description;
    }

    /**
     * This method gets the location for the appointment.
     *
     * @return the appointment's location
     */
    public String getLocation() {
        return Location;
    }

    /**
     * This method sets the location for the appointment.
     *
     * @param location the new location for the appointment
     */
    public void setLocation(String location) {
        Location = location;
    }

    /**
     * This method gets the type of the appointment.
     *
     * @return the appointment's type
     */
    public String getType() {
        return Type;
    }

    /**
     * This method sets the type of the appointment.
     *
     * @param type the new type for the appointment
     */
    public void setType(String type) {
        Type = type;
    }

    /**
     * This method gets the end date and time for the appointment.
     *
     * @return the appointment's end date and time
     */
    public LocalDateTime getEnd() {
        return End;
    }

    /**
     * This method sets the end date and time for the appointment.
     *
     * @param end the appointment's new end date and time
     */
    public void setEnd(LocalDateTime end) {
        End = end;
    }

    /**
     * This method gets the end date and time for the appointment.
     *
     * @return the appointment's end date and time
     */
    public String getEnd_Display() {
        return HelperMethods.formatDateTimeDisplay(End);
    }

    /**
     * This method gets the start date and time for the appointment.
     *
     * @return the appointment's start date and time
     */
    public LocalDateTime getStart() {
        return Start;
    }

    /**
     * This method sets the start date and time for the appointment.
     *
     * @param start the appointment's new start date and time
     */
    public void setStart(LocalDateTime start) {
        Start = start;
    }

    /**
     * This method gets the start date and time for the appointment.
     *
     * @return the appointment's start date and time
     */
    public String getStart_Display() {
        return HelperMethods.formatDateTimeDisplay(Start);
    }


    /**
     * This method gets the author ID.
     *
     * @return the author's ID
     */
    public int getAuthor_ID() {
        return Author_ID;
    }

    /**
     * This method gets the employee's ID.
     *
     * @return the employee's ID
     */
    public int getEmployee_ID() {
        return Employee_ID;
    }


    /**
     * This method gets the editor's ID.
     *
     * @return the editor's ID
     */
    public int getEditor_ID() {
        return Editor_ID;
    }

    /**
     * This method gets the author associated with this appointment.
     *
     * @return the author object
     */
    public Person getAuthor() {
        return Author;
    }

    /**
     * This method gets the author associated with this appointment.
     *
     * @return the String with the author name
     */
    public String getAuthor_Name() {
        return Author.getName();
    }

    /**
     * This method gets the employee associated with this appointment.
     *
     * @return the employee object
     */
    public Employee getEmployee() {
        return Employee;
    }

    /**
     * This method gets the employee name associated with this appointment.
     *
     * @return the employee's name
     */
    public String getEmployee_Name() {
        return Employee.getName();
    }

    /**
     * This method gets the editor associated with this appointment.
     *
     * @return the editor object
     */
    public Person getEditor() {
        return Editor;
    }

    /**
     * This method gets the editor name associated with this appointment.
     *
     * @return the editor's name
     */
    public String getEditor_Name() {
        return Editor.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment)) return false;
        if (!super.equals(o)) return false;
        Appointment that = (Appointment) o;
        return Author_ID == that.Author_ID && Employee_ID == that.Employee_ID && Editor_ID == that.Editor_ID && Title.equals(that.Title) && Description.equals(that.Description) && Location.equals(that.Location) && Type.equals(that.Type) && Start.equals(that.Start) && End.equals(that.End) && Author.equals(that.Author) && Employee.equals(that.Employee) && Editor.equals(that.Editor);
    }
}
