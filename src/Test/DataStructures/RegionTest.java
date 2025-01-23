package DataStructures;

import Main.DatabaseConnectionManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegionTest {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final int id = 1;
    private static final String name = "name";
    private static final LocalDateTime create_Date = LocalDateTime.parse("2023-01-01 00:00:00", formatter);
    private static final LocalDateTime new_create_date = LocalDateTime.parse("2023-01-01 00:30:00", formatter);
    private static final String created_By = "Created By";
    private static final Timestamp last_Update = Timestamp.valueOf(LocalDateTime.parse("2023-01-01 01:00:00", formatter));
    private static final String last_Updated_By = "Last Updated By";
    private static final int country_id = 2;

    private static Region testRegion;

    @BeforeAll
    static void beforeAll() {
        DatabaseConnectionManager.makeConnection();
        testRegion = new Region(id, name, create_Date, created_By, last_Update, last_Updated_By, country_id);
        Region region = new Region(id, name, country_id);
    }

    @Test
    void getRegion_ID() {
        assertEquals(id, testRegion.getRegion_ID());
    }

    @Test
    void getRegion_Name() {
        assertEquals(name, testRegion.getRegion_Name());
    }

    @Test
    void getCreate_Date() {
        assertEquals(create_Date, testRegion.getCreate_Date());
    }

    @Test
    void setCreate_Date() {
        assertEquals(create_Date, testRegion.getCreate_Date());
        testRegion.setCreate_Date(new_create_date);
        assertEquals(new_create_date, testRegion.getCreate_Date());
    }

    @Test
    void getCreated_By() {
        assertEquals(created_By, testRegion.getCreated_By());
    }

    @Test
    void getLast_Update() {
        assertEquals(last_Update, testRegion.getLast_Update());
    }

    @Test
    void getLast_Updated_By() {
        assertEquals(last_Updated_By, testRegion.getLast_Updated_By());
    }

    @Test
    void getCountry_ID() {
        assertEquals(country_id, testRegion.getCountry_ID());
    }
}