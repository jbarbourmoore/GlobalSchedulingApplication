package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created class GSAStart.java
 * <p>
 * This class is the main class for the application.
 *
 * @author Jamie Barbour-Moore
 */
public class GSAStart extends Application {

    /**
     * The main method for the application
     *
     * @param args the system inputs which are not used in this application
     */
    public static void main(String[] args) {
        DatabaseConnectionManager.makeConnection();
        launch();
    }

    /**
     * This method launches the application to the login screen.
     *
     * @param stage the stage
     * @throws IOException if the load fails
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GSAStart.class.getResource("/Resources/GSA_LoginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setTitle("Global Scheduling Application - Login View");
        stage.setScene(scene);
        stage.show();
    }
}