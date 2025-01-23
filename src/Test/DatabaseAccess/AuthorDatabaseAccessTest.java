package DatabaseAccess;

import DataStructures.Person;
import Main.DatabaseConnectionManager;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AuthorDatabaseAccessTest {
    private static final String name = "Name";
    private static final String address = "address";
    private static final String postal_code = "postalcode";
    private static final String phone = "phone";
    private static final String email = "email";
    private static final int region_ID = 1;
    private static int test_author_id = 10;

    @BeforeAll
    static void beforeAll() {
        DatabaseConnectionManager.makeConnection();
    }

    @Test
    void modifyAuthor() throws SQLException {
        test_author_id = getNewAuthorID();
        Person testAuthor = new Person(test_author_id, name, address, postal_code, phone, email, region_ID);
        assertNull(AuthorDatabaseAccess.getAuthor(test_author_id));
        AuthorDatabaseAccess.addAuthor(testAuthor);
        assertEquals(name, AuthorDatabaseAccess.getAuthor(test_author_id).getName());
        Person modifiedAuthor = new Person(test_author_id, "Modified", address, postal_code, phone, email, region_ID);
        AuthorDatabaseAccess.modifyAuthor(modifiedAuthor);
        assertEquals("Modified", AuthorDatabaseAccess.getAuthor(test_author_id).getName());
        AuthorDatabaseAccess.deleteAuthorWithID(test_author_id);
    }

    private int getNewAuthorID() throws SQLException {
        ObservableList<Person> allAuthors = AuthorDatabaseAccess.getAllAuthors();
        int newID = 1;
        for (Person author : allAuthors) {
            if (author.getID() >= newID) {
                newID = author.getID() + 1;
            }
        }
        return newID;
    }
}