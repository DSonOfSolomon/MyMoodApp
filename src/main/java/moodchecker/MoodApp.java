package moodchecker;

import analytics.MoodAnalytics;
import controller.MoodController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Emotions;
import repository.MoodHistory;

import java.time.LocalTime;
import java.util.List;

public class MoodApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    // Determine current time of day
    public static String getTimeOfDay() {
        int hour = LocalTime.now().getHour();
        if (hour >= 5 && hour < 12) return "morning";
        else if (hour >= 12 && hour < 17) return "afternoon";
        else if (hour >= 17 && hour < 21) return "evening";
        else return "night";
    }

    public static String getGreeting(String timeOfDay) {
        return timeOfDay.equals("night") ? "How are you tonight?" : "How are you feeling this " + timeOfDay + "?";
    }

    // Gradient background based on time of day
    public static Background getBackground(String timeOfDay) {
        Stop[] stops;
        switch (timeOfDay) {
            case "morning" -> stops = new Stop[]{new Stop(0, Color.GREY), new Stop(1, Color.DARKGREY)};
            case "afternoon" -> stops = new Stop[]{new Stop(0, Color.LIGHTBLUE), new Stop(1, Color.SKYBLUE)};
            case "evening" -> stops = new Stop[]{new Stop(0, Color.LIGHTSALMON), new Stop(1, Color.ORANGE)};
            default -> stops = new Stop[]{new Stop(0, Color.DARKBLUE), new Stop(1, Color.MIDNIGHTBLUE)};
        }
        return new Background(new BackgroundFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops), CornerRadii.EMPTY, Insets.EMPTY));
    }

    @Override
    public void start(Stage stage) {

        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        // Greeting label
        Label greetingLabel = new Label();
        greetingLabel.setFont(Font.font(24));
        greetingLabel.setTextFill(Color.WHITE);

        // Dropdown for emotions (from your enum with emojis)
        ComboBox<Emotions> dropdown = new ComboBox<>();
        dropdown.getItems().addAll(Emotions.values());
        dropdown.setPrefWidth(220);

        // Result label
        Label resultLabel = new Label();
        resultLabel.setWrapText(true);
        resultLabel.setFont(Font.font(16));
        resultLabel.setTextFill(Color.WHITE);
        resultLabel.setPadding(new Insets(10));
        resultLabel.setBackground(new Background(new BackgroundFill(Color.rgb(0,0,0,0.5), new CornerRadii(10), Insets.EMPTY)));
        resultLabel.setEffect(new DropShadow(5, Color.BLACK));
        resultLabel.setMaxWidth(400);

        // History VBox (color-coded)
        VBox historyContent = new VBox(5);
        historyContent.setFillWidth(true);

        ScrollPane historyScroll = new ScrollPane(historyContent);
        historyScroll.setPrefSize(400, 150);
        historyScroll.setFitToWidth(true);
        historyScroll.setStyle("-fx-background-color: transparent; -fx-background: RGBA(0,0,0,0.3);");
        historyScroll.setVisible(false); // hidden by default

        // Load existing history
        //List<String> loadedHistory = MoodHistory.load();
        // Will be displayed via refreshHistory

        // Toggle history button
        Button toggleHistoryButton = new Button("📜 Show History");
        toggleHistoryButton.setStyle("-fx-background-color: #555555; -fx-text-fill: white;");
        toggleHistoryButton.setOnAction(e -> {
            boolean visible = historyScroll.isVisible();
            historyScroll.setVisible(!visible);
            toggleHistoryButton.setText(visible ? "📜 Show History" : "📜 Hide History");
        });

        // Mood Trends button
        Button trendsButton = new Button("📊 Show Mood Trends");
        trendsButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        trendsButton.setOnAction(e -> MoodAnalytics.showMoodTrends());

        // Submit, Clear, Export buttons
        Button submitButton = new Button("Submit");
        Button clearButton = new Button("Clear History");
        Button exportButton = new Button("💾 Export History");
        submitButton.setPrefWidth(120);
        clearButton.setPrefWidth(120);
        exportButton.setPrefWidth(120);
        submitButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        clearButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
        exportButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");
        HBox buttonBox = new HBox(15, submitButton, clearButton, exportButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Controller
        MoodController controller = new MoodController(dropdown, resultLabel, historyContent, greetingLabel, root);
        submitButton.setOnAction(e -> controller.handleSubmit());
        clearButton.setOnAction(e -> controller.handleClear());
        exportButton.setOnAction(e -> MoodHistory.exportCSV());

        // Initial greeting & background
        String initialTime = getTimeOfDay();
        greetingLabel.setText(getGreeting(initialTime));
        root.setBackground(getBackground(initialTime));

        // Add nodes to root
        root.getChildren().addAll(
                greetingLabel,
                dropdown,
                submitButton,
                resultLabel,
                toggleHistoryButton,
                trendsButton,
                historyScroll,
                buttonBox
        );

        // Refresh history on start
        controller.refreshHistory();

        Scene scene = new Scene(root, 600, 500);
        stage.setScene(scene);
        stage.setTitle("Mood Checker");
        stage.show();
    }
}
