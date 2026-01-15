package moodchecker;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class MyMoodApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        StackPane root = new StackPane();   // blank root
        Scene scene = new Scene(root, 600, 400);

        stage.setTitle("Welcome to MoodApp 🤝");
        stage.setScene(scene);
        stage.show();
    }
}
