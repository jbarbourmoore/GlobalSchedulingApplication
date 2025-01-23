package DatabaseAccess;

import Main.DatabaseConnectionManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RegionDatabaseAccessTest {
    @BeforeAll
    static void beforeAll() {
        DatabaseConnectionManager.makeConnection();
    }

    @Test
    void getAllRegions() throws SQLException {
        assertNotNull(RegionDatabaseAccess.getAllRegions());
    }
}