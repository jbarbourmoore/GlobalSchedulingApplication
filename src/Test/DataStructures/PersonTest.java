package DataStructures;

import DatabaseAccess.CountryDatabaseAccess;
import DatabaseAccess.RegionDatabaseAccess;
import Main.DatabaseConnectionManager;
import Main.HelperMethods;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PersonTest {

    static private final int ID = 1;
    private static final String name = "Name";
    private static final String address = "address";
    private static final String postal_code = "postal code";
    private static final String phone = "phone";
    private static final String email = "email";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final LocalDateTime create_Date = LocalDateTime.parse("2023-01-01 00:00:00", formatter);
    private static final String created_By = "Created By";
    private static final Timestamp last_Update = Timestamp.valueOf(LocalDateTime.parse("2023-01-01 01:00:00", formatter));
    private static final String last_Updated_By = "Last Updated By";
    private static final int region_ID = 1;

    private static Person testPerson;
    private static Person testPersonComplete;

    @BeforeAll
    static void beforeAll() {
        DatabaseConnectionManager.makeConnection();
        testPerson = new Person(ID, name, address, postal_code, phone, email, region_ID);
        testPersonComplete = new Person(ID, name, address, postal_code, phone, email, create_Date, created_By, last_Update, last_Updated_By, region_ID);

    }

    @Test
    void getID() {
        assertEquals(ID, testPerson.getID());
    }

    @Test
    void getName() {
        assertEquals(name, testPerson.getName());
    }

    @Test
    void getAddress() {
        assertEquals(address, testPerson.getAddress());
    }

    @Test
    void getPostal_Code() {
        assertEquals(postal_code, testPerson.getPostal_Code());
    }

    @Test
    void getPhone() {
        assertEquals(phone, testPerson.getPhone());
    }

    @Test
    void getEmail() {
        assertEquals(email, testPerson.getEmail());
    }

    @Test
    void getCreate_Date() {
        assertEquals(create_Date, testPersonComplete.getCreate_Date());
        assertNull(testPerson.getCreate_Date());
    }

    @Test
    void getCreate_Date_Display() {
        assertEquals(HelperMethods.formatDateTimeDisplay(create_Date), testPersonComplete.getCreate_Date_Display());
    }

    @Test
    void getCreated_By() {
        assertEquals(created_By, testPersonComplete.getCreated_By());
    }

    @Test
    void getLast_Update() {
        assertEquals(last_Update, testPersonComplete.getLast_Update());
    }

    @Test
    void getLast_Update_Display() {
        assertEquals(HelperMethods.formatDateTimeDisplay(last_Update), testPersonComplete.getLast_Update_Display());
    }

    @Test
    void getLast_Updated_By() {
        assertEquals(last_Updated_By, testPersonComplete.getLast_Updated_By());
    }

    @Test
    void getRegion() throws SQLException {
        Region region = RegionDatabaseAccess.getRegion(region_ID);
        assertEquals(region, testPersonComplete.getRegion());
    }

    @Test
    void getCountry() throws SQLException {
        Region region = RegionDatabaseAccess.getRegion(region_ID);
        Country country = CountryDatabaseAccess.getCountry(region.getCountry_ID());
        assertEquals(country, testPersonComplete.getCountry());
    }

    @Test
    void getRegion_Name() throws SQLException {
        Region region = RegionDatabaseAccess.getRegion(region_ID);
        assertEquals(region.getRegion_Name(), testPersonComplete.getRegion_Name());
    }

    @Test
    void getCountry_Name() throws SQLException {
        Region region = RegionDatabaseAccess.getRegion(region_ID);
        Country country = CountryDatabaseAccess.getCountry(region.getCountry_ID());
        assertEquals(country.getCountry(), testPersonComplete.getCountry_Name());
    }


}