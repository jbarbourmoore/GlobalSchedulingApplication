package DataStructures;

import DatabaseAccess.CountryDatabaseAccess;
import DatabaseAccess.RegionDatabaseAccess;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Person extends DatabaseObjectClass {


    /**
     * A String containing the person's name.
     */
    private final String Name;

    /**
     * A String containing the person's address.
     */
    private final String Address;

    /**
     * A String containing the person's Postal Code.
     */
    private final String Postal_Code;

    /**
     * A String containing the person's phone number.
     */
    private final String Phone;

    /**
     * A String containing the person's email
     */
    private final String Email;

    /**
     * The Region for the person.
     */
    private Region Region;

    /**
     * The country associated with the person's first Level division
     */
    private Country Country;

    /**
     * This method instantiates the Person object
     *
     * @param ID          the person's id number
     * @param name        the person's name
     * @param address     the person's address
     * @param postal_Code the person's postal code
     * @param phone       the person's phone number
     * @param email       the person's email address
     * @param region_ID   the person's region's ID
     */
    public Person(int ID, String name, String address, String postal_Code, String phone, String email, int region_ID) {
        super(ID);
        Name = name;
        Address = address;
        Postal_Code = postal_Code;
        Phone = phone;
        Email = email;

        createDivisionFromID(region_ID);
    }

    /**
     * This method instantiates the Person object
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
     */
    public Person(int ID, String name, String address, String postal_Code, String phone, String email, LocalDateTime create_Date, String created_By, Timestamp last_Update, String last_Updated_By, int region_ID) {
        super(ID, create_Date, created_By, last_Update, last_Updated_By);
        this.ID = ID;
        Name = name;
        Address = address;
        Postal_Code = postal_Code;
        Phone = phone;
        Email = email;

        createDivisionFromID(region_ID);
    }


    /**
     * This method created the Division and Country objects used by this person.
     */
    private void createDivisionFromID(int region_ID) {
        try {
            Region = RegionDatabaseAccess.getRegion(region_ID);
            Country = CountryDatabaseAccess.getCountry(Region.getCountry_ID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method gets the person's ID.
     *
     * @return the person's id
     */
    public int getID() {
        return ID;
    }

    /**
     * This method gets the person's name.
     *
     * @return the person's name
     */
    public String getName() {
        return Name;
    }

    /**
     * This method gets the person's address.
     *
     * @return the person's address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * This method gets the person's postal code.
     *
     * @return the person's postal code
     */
    public String getPostal_Code() {
        return Postal_Code;
    }

    /**
     * This method gets the person's phone number.
     *
     * @return the person's phone number
     */
    public String getPhone() {
        return Phone;
    }

    /**
     * This method gets the person's email address.
     *
     * @return the person's email address
     */
    public String getEmail() {
        return Email;
    }

    /**
     * This method gets the person's region.
     *
     * @return the person's id
     */
    public DataStructures.Region getRegion() {
        return Region;
    }

    /**
     * This method gets the person's country.
     *
     * @return the person's country
     */
    public DataStructures.Country getCountry() {
        return Country;
    }

    /**
     * This method gets the person's region name as a string to display.
     *
     * @return the person's region name as a string to display
     */
    public String getRegion_Name() {
        return Region.getRegion_Name();
    }

    /**
     * This method gets the person's country name as a string to display.
     *
     * @return the person's country name as a string to display
     */
    public String getCountry_Name() {
        return Country.getCountry();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return Name.equals(person.Name) && Address.equals(person.Address) && Postal_Code.equals(person.Postal_Code) && Phone.equals(person.Phone) && Email.equals(person.Email) && Region.equals(person.Region) && Country.equals(person.Country);
    }
}
