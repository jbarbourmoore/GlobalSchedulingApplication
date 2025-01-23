package DatabaseAccess;

import Main.DatabaseConnectionManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CountryDatabaseAccessTest {


    @BeforeAll
    static void beforeAll() {
        DatabaseConnectionManager.makeConnection();
    }

    @Test
    void getCountries() throws SQLException {
        assertNotNull(CountryDatabaseAccess.getCountries());
    }
}