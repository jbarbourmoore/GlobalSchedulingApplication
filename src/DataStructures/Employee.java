package DataStructures;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created class User.java
 * <p>
 * This class is the data structure for the user information from the database.
 *
 * @author Jamie Barbour-Moore
 */
public class Employee extends Person {


    /**
     * A String containing the username.
     * It is unique.
     */
    private final String Username;
    private final String saltedHash;
    private final String salt;
    /**
     * A String containing the User's password.
     */
   // private final String Password;

    /**
     * This method instantiates the employee object by using the superclass' initiation method.
     *
     * @param ID              the person's id number
     * @param name            the person's name
     * @param address         the person's address
     * @param postal_Code     the person's postal code
     * @param phone           the person's phone number
     * @param email           the person's email address
     * @param create_Date     the date the person was created
     * @param created_By      the name of whoever created the person
     * @param last_Update     the date the person was last updated
     * @param last_Updated_By the name of whoever last updated the person
     * @param region_ID       the person's region's ID
     * @param username        the employee's username
     * @param saltedHash      the sha256 hash value of the employee's password with salt
     * @param salt            the salt for use with sha256 hash for this employee
     */
    public Employee(int ID, String name, String address, String postal_Code, String phone, String email, LocalDateTime create_Date, String created_By, Timestamp last_Update, String last_Updated_By, int region_ID, String username, String saltedHash, String salt) {
        super(ID, name, address, postal_Code, phone, email, create_Date, created_By, last_Update, last_Updated_By, region_ID);
        Username = username;
        this.salt = salt;
        this.saltedHash = saltedHash;
      //  Password = password;
    }

    public String getUsername() {
        return Username;
    }
    public String getSaltedHash() {
        return saltedHash;
    }
    public String getSalt() {
        return salt;
    }

//    public String getPassword() {
//        return Password;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Username.equals(employee.Username);
    }
}
