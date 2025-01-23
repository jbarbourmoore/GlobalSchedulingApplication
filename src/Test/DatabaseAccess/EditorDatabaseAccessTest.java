package DatabaseAccess;

import DataStructures.Person;
import Main.DatabaseConnectionManager;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class EditorDatabaseAccessTest {
    private static final String name = "Name";
    private static final String address = "address";
    private static final String postal_code = "postalcode";
    private static final String phone = "phone";
    private static final String email = "email";
    private static final int region_ID = 1;
    private static int editor_id = 10;

    @BeforeAll
    static void beforeAll() {
        DatabaseConnectionManager.makeConnection();
    }

    @Test
    void modifyEditor() throws SQLException {
        editor_id = getNewEditorID();
        Person testEditor = new Person(editor_id, name, address, postal_code, phone, email, region_ID);
        assertNull(EditorDatabaseAccess.getEditor(editor_id));
        EditorDatabaseAccess.addEditor(testEditor);
        assertEquals(name, EditorDatabaseAccess.getEditor(editor_id).getName());
        Person modifiedEditor = new Person(editor_id, "Modified", address, postal_code, phone, email, region_ID);
        EditorDatabaseAccess.modifyEditor(modifiedEditor);
        assertEquals("Modified", EditorDatabaseAccess.getEditor(editor_id).getName());
        EditorDatabaseAccess.deleteEditorWithID(editor_id);
    }

    private int getNewEditorID() throws SQLException {

        ObservableList<Person> allAuthors = EditorDatabaseAccess.getAllEditors();
        int newID = 1;
        for (Person author : allAuthors) {
            if (author.getID() >= newID) {
                newID = author.getID() + 1;
            }
        }
        return newID;
    }
}