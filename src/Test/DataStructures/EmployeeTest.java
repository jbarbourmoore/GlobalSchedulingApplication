package DataStructures;

import Main.DatabaseConnectionManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeTest {

    private static final String username = "username";
    private static final String password = "testpass";
    private static final String salt = "227a142b";
    private static final String saltedHash = "18827e010f4df2f7940cefe8f3cbfc07f00cd8350f48ca2c0c1ba161e244b72b";
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
    private static Employee testEmployee;

    @BeforeAll
    static void beforeAll() {
        DatabaseConnectionManager.makeConnection();
        testEmployee = new Employee(ID, name, address, postal_code, phone, email, create_Date, created_By, last_Update, last_Updated_By, region_ID, username);
    }

    @Test
    void getUsername() {
        assertEquals(username, testEmployee.getUsername());
    }
}