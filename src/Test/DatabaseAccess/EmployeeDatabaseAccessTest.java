package DatabaseAccess;

import Main.DatabaseConnectionManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDatabaseAccessTest {

    @BeforeAll
    static void beforeAll() {
        DatabaseConnectionManager.makeConnection();
    }

    @Test
    void getAllEmployees() throws SQLException {
        assertNotNull(EmployeeDatabaseAccess.getAllEmployees());
    }

    @Test
    void checkUserAndPassword() {
        assertFalse(EmployeeDatabaseAccess.checkUserAndPassword("a", "a"));
        assertTrue(EmployeeDatabaseAccess.checkUserAndPassword("admin", "admin"));

    }

}