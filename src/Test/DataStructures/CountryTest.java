package DataStructures;

import Main.DatabaseConnectionManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountryTest {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final int id = 1;
    private static final String name = "name";
    private static final LocalDateTime create_Date = LocalDateTime.parse("2023-01-01 00:00:00", formatter);
    private static final LocalDateTime new_create_date = LocalDateTime.parse("2023-01-01 00:30:00", formatter);
    private static final String created_By = "Created By";
    private static final Timestamp last_Update = Timestamp.valueOf(LocalDateTime.parse("2023-01-01 01:00:00", formatter));
    private static final String last_Updated_By = "Last Updated By";
    private static Country testCountry;

    @BeforeAll
    static void beforeAll() {
        DatabaseConnectionManager.makeConnection();
        testCountry = new Country(id, name, create_Date, created_By, last_Update, last_Updated_By);
        Country testCountryother = new Country(id, name);
    }

    @Test
    void getCountry_ID() {
        assertEquals(id, testCountry.getCountry_ID());
    }

    @Test
    void getCountry() {
        assertEquals(name, testCountry.getCountry());
    }

    @Test
    void getCreate_Date() {
        assertEquals(create_Date, testCountry.getCreate_Date());
    }

    @Test
    void getCreated_By() {
        assertEquals(created_By, testCountry.getCreated_By());
    }

    @Test
    void getLast_Update() {
        assertEquals(last_Update, testCountry.getLast_Update());
    }

    @Test
    void getLast_Updated_By() {
        assertEquals(last_Updated_By, testCountry.getLast_Updated_By());
    }
}