package analytics;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import repository.MoodHistory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoodAnalytics {

    /**
     * Opens a new window displaying a bar chart of moods in history.
     */
    public static void showMoodTrends() {
        // Load history
        List<String> history = MoodHistory.load();
        if (history.isEmpty()) {
            System.out.println("No history to display.");
            return;
        }

        // Count occurrences of each mood
        Map<String, Integer> moodCounts = new HashMap<>();
        for (model.Emotions e : model.Emotions.values()) {
            moodCounts.put(e.name(), 0);
        }

        for (String line : history) {
            for (model.Emotions e : model.Emotions.values()) {
                if (line.contains(e.getEmoji())) {
                    moodCounts.put(e.name(), moodCounts.get(e.name()) + 1);
                    break;
                }
            }
        }

        // Prepare chart data
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Mood");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Count");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Mood Trends");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Mood Count");

        ObservableList<XYChart.Data<String, Number>> data = FXCollections.observableArrayList();
        for (Map.Entry<String, Integer> entry : moodCounts.entrySet()) {
            data.add(new XYChart.Data<>(capitalize(entry.getKey()), entry.getValue()));
        }
        series.setData(data);
        barChart.getData().add(series);

        // Layout
        VBox root = new VBox(barChart);
        root.setSpacing(10);

        Stage stage = new Stage();
        stage.setTitle("Mood Trends");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    private static String capitalize(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }
}

