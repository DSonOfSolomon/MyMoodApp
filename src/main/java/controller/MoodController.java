package controller;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import service.MoodService;
import model.Emotions;

import java.util.List;


public class MoodController {

    private final ComboBox<Emotions> dropdown;
    private final Label resultLabel;
    private final VBox historyContent;
    private final Label greetingLabel;
    private final VBox root;

    private final MoodService service;

    public MoodController(ComboBox<Emotions> dropdown,
                          Label resultLabel,
                          VBox historyContent,
                          Label greetingLabel,
                          VBox root,
                          MoodService service) {
        this.dropdown = dropdown;
        this.resultLabel = resultLabel;
        this.historyContent = historyContent;
        this.greetingLabel = greetingLabel;
        this.root = root;
        this.service = service;
    }

    /** Handle submit button click */
    public void handleSubmit() {
        Emotions emotion = dropdown.getValue();
        if (emotion == null) {
            resultLabel.setText("Please select an emotion.");
            resultLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        // Delegate business logic to service
        String response = service.processMood(emotion);
        resultLabel.setText(response);
        resultLabel.setStyle("-fx-text-fill: " + toHex(service.getEmotionColor(emotion)) + ";");

        // Refresh history display
        refreshHistory();

        // Update greeting & background dynamically
        greetingLabel.setText(service.getGreeting());
        root.setBackground(service.getBackground());
    }

    /** Handle clear button click */
    public void handleClear() {
        service.clearHistory();
        historyContent.getChildren().clear();
    }

    /** Refresh history VBox */
    public void refreshHistory() {
        List<Label> labels = service.getHistoryLabels();
        historyContent.getChildren().setAll(labels);
    }

    /** Convert JavaFX Color to CSS hex string for inline styling */
    private String toHex(javafx.scene.paint.Color color) {
        int r = (int) (color.getRed() * 255);
        int g = (int) (color.getGreen() * 255);
        int b = (int) (color.getBlue() * 255);
        return String.format("#%02x%02x%02x", r, g, b);
    }
}
