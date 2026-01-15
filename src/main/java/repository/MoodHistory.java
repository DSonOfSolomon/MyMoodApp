package repository;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MoodHistory {

    private static final String HISTORY_FILE = "mood_history.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void save(String timeOfDay, model.Emotions emotion) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String entry = timestamp + " - " + timeOfDay + " - " + emotion.getEmoji() + " " + capitalize(emotion.name());

        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(HISTORY_FILE, true)))) {
            out.println(entry);
        } catch (IOException e) {
            showError("Could not save mood history:\n" + e.getMessage());
        }
    }

    public static List<String> load() {
        List<String> history = new ArrayList<>();
        File file = new File(HISTORY_FILE);
        if (!file.exists()) return history;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                history.add(line);
            }
        } catch (IOException e) {
            showError("Could not load mood history:\n" + e.getMessage());
        }
        return history;
    }

    public static void clear() {
        try (PrintWriter out = new PrintWriter(HISTORY_FILE)) {
            showInfo("Mood history cleared.");
        } catch (IOException e) {
            showError("Could not clear mood history:\n" + e.getMessage());
        }
    }

    public static void exportCSV() {
        List<String> history = load();
        if (history.isEmpty()) {
            showInfo("No history to export.");
            return;
        }

        String csvFile = "mood_history.csv";

        try (PrintWriter out = new PrintWriter(new FileWriter(csvFile))) {
            out.println("Timestamp,TimeOfDay,Mood,Emoji");
            for (String line : history) {
                String[] parts = line.split(" - ");
                if (parts.length == 3) {
                    String timestamp = parts[0];
                    String timeOfDay = parts[1];
                    String moodEmoji = parts[2].trim();
                    String emoji = moodEmoji.substring(0, 2);
                    String mood = moodEmoji.substring(2).trim();
                    out.println(timestamp + "," + timeOfDay + "," + mood + "," + emoji);
                }
            }
            showInfo("History exported to " + csvFile);
        } catch (Exception e) {
            showError("Could not export CSV:\n" + e.getMessage());
        }
    }

    private static String capitalize(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

    private static void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("MoodChecker Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static void showInfo(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("MoodChecker Info");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
