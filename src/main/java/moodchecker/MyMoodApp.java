package moodchecker;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Main launcher for MoodApp.
 * Starts the JavaFX application.
 */
public class MyMoodApp extends Application {

    public static void main(String[] args) {
        launch(args); // starts JavaFX
    }

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();   // blank root
        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("MoodApp");
        stage.setScene(scene);
        stage.show();  // display window
    }
}
