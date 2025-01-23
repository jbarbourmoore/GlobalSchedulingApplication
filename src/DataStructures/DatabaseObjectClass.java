package DataStructures;

import Main.HelperMethods;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class DatabaseObjectClass {

    int ID;
    /**
     * A LocalDateTime containing when the Appointment was created.
     */
    LocalDateTime Create_Date;
    /**
     * A String containing who created the Appointment.
     */
    String Created_By;
    /**
     * A Timestamp containing when the object was last updated.
     */
    Timestamp Last_Update;
    /**
     * A String containing who last updated the object.
     */
    String Last_Updated_By;

    public DatabaseObjectClass(int id, LocalDateTime create_Date, String created_By, Timestamp last_Update, String last_Updated_By) {
        ID = id;
        Create_Date = create_Date;
        Created_By = created_By;
        Last_Update = last_Update;
        Last_Updated_By = last_Updated_By;
    }

    public DatabaseObjectClass(int id) {
        ID = id;
    }

    /**
     * This method gets the  date and time for when the object was created.
     *
     * @return the object's start date and time
     */
    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    /**
     * This method sets the date and time for when the object was created.
     *
     * @param create_Date the object's new created date and time
     */
    public void setCreate_Date(LocalDateTime create_Date) {
        Create_Date = create_Date;
    }

    /**
     * This method gets the  date and time for when the object was created.
     *
     * @return the object's start date and time
     */
    public String getCreate_Date_Display() {
        return HelperMethods.formatDateTimeDisplay(Create_Date);
    }

    /**
     * This method gets who created the object.
     *
     * @return who created the object
     */
    public String getCreated_By() {
        return Created_By;
    }

    /**
     * This method gets the  date and time for when the object was last updated.
     *
     * @return the object's last update date and time
     */
    public Timestamp getLast_Update() {
        return Last_Update;
    }


    /**
     * This method gets the  date and time for when the object was last updated.
     *
     * @return the object's last update date and time
     */
    public String getLast_Update_Display() {
        return HelperMethods.formatDateTimeDisplay(Last_Update);
    }

    /**
     * This method gets who last updated the object.
     *
     * @return who last updated the object
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DatabaseObjectClass)) return false;
        DatabaseObjectClass that = (DatabaseObjectClass) o;
        return ID == that.ID && Objects.equals(Create_Date, that.Create_Date) && Objects.equals(Created_By, that.Created_By) && Objects.equals(Last_Update, that.Last_Update) && Objects.equals(Last_Updated_By, that.Last_Updated_By);
    }
}
