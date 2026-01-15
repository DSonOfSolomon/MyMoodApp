package controller;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import moodchecker.MoodApp;
import service.MoodResponses;

import java.util.List;

public class MoodController {

    private final ComboBox<com.example.helloMood.Emotions> dropdown;
    private final Label resultLabel;
    private final VBox historyContent; // VBox for color-coded history
    private final Label greetingLabel;
    private final VBox root;
    private final MoodResponses moodResponses;

    public MoodController(ComboBox<com.example.helloMood.Emotions> dropdown,
                          Label resultLabel,
                          VBox historyContent,
                          Label greetingLabel,
                          VBox root) {
        this.dropdown = dropdown;
        this.resultLabel = resultLabel;
        this.historyContent = historyContent;
        this.greetingLabel = greetingLabel;
        this.root = root;
        this.moodResponses = new MoodResponses();
    }

    public void handleSubmit() {
        com.example.helloMood.Emotions emotion = dropdown.getValue();
        if (emotion == null) {
            resultLabel.setText("Please select an emotion.");
            resultLabel.setTextFill(Color.RED);
            return;
        }

        String timeOfDay = MoodApp.getTimeOfDay();
        String response = moodResponses.get(timeOfDay, emotion);

        if (response == null) {
            resultLabel.setText("No response found.");
            resultLabel.setTextFill(Color.RED);
            return;
        }

        resultLabel.setText(response);
        resultLabel.setTextFill(getColor(emotion));

        // Save mood with timestamp
        MoodHistory.save(timeOfDay, emotion);

        // Refresh history display
        refreshHistory();

        // Update greeting & background dynamically
        greetingLabel.setText(MoodApp.getGreeting(timeOfDay));
        root.setBackground(MoodApp.getBackground(timeOfDay));
    }

    public void handleClear() {
        MoodHistory.clear();
        historyContent.getChildren().clear();
    }

    // Refresh history with color-coded lines
    public void refreshHistory() {
        List<String> historyList = MoodHistory.load();
        historyContent.getChildren().clear(); // clear previous entries

        for (String line : historyList) {
            Label entryLabel = new Label("• " + line);
            entryLabel.setWrapText(true);
            entryLabel.setMaxWidth(400);

            // Extract the mood emoji from the line
            String[] parts = line.split(" - ");
            if (parts.length == 3) {
                String moodPart = parts[2].trim();
                for (com.example.helloMood.Emotions e : com.example.helloMood.Emotions.values()) {
                    if (moodPart.startsWith(e.getEmoji())) {
                        if (e == com.example.helloMood.Emotions.HAPPY || e == com.example.helloMood.Emotions.CALM || e == com.example.helloMood.Emotions.MOTIVATED || e == com.example.helloMood.Emotions.HOPEFUL) {
                            entryLabel.setTextFill(Color.LIGHTGREEN);
                        } else if (e == com.example.helloMood.Emotions.INDIFFERENT) {
                            entryLabel.setTextFill(Color.ORANGE);
                        } else {
                            entryLabel.setTextFill(Color.RED);
                        }
                        break;
                    }
                }
            }

            historyContent.getChildren().add(entryLabel);
        }
    }

    private Color getColor(com.example.helloMood.Emotions emotion) {
        return switch (emotion) {
            case HAPPY, CALM, MOTIVATED, HOPEFUL -> Color.LIGHTGREEN;
            case INDIFFERENT -> Color.ORANGE;
            default -> Color.RED;
        };
    }
}
