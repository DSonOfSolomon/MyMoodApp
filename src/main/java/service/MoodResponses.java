package service;

import model.Emotions;
import java.util.HashMap;
import java.util.Map;

public class MoodResponses {

    // Stores responses for each time of day and emotion
    private final Map<String, Map<Emotions, String>> responses = new HashMap<>();

    public MoodResponses() {
        buildResponses();
    }

    private void buildResponses() {
        // Morning
        Map<Emotions, String> morning = new HashMap<>();
        morning.put(Emotions.HAPPY, "Good morning! What a bright start! 🌞");
        morning.put(Emotions.CALM, "Morning calm sets a peaceful tone. 🕊️");
        morning.put(Emotions.MOTIVATED, "Morning motivation is the best motivation! 💪");
        morning.put(Emotions.HOPEFUL, "Morning hope can guide your day! 🌟");
        morning.put(Emotions.SAD, "Good morning. Take it slow and sip some coffee. ☕");
        morning.put(Emotions.ANXIOUS, "Take a deep breath and start gently. 🌬️");
        morning.put(Emotions.IRRITABLE, "Try a short walk to clear your mind. 🚶");
        morning.put(Emotions.TIRED, "Coffee or a stretch might help wake you up. ☕");
        morning.put(Emotions.INDIFFERENT, "Neutral mornings happen. Take it easy. 😐");
        morning.put(Emotions.OVERWHELMED, "Focus on one task at a time. ✅");
        responses.put("morning", morning);

        // Afternoon
        Map<Emotions, String> afternoon = new HashMap<>();
        afternoon.put(Emotions.HAPPY, "Good afternoon! Keep the energy going! ☀️");
        afternoon.put(Emotions.CALM, "Afternoon calm can help you finish strong. 🕊️");
        afternoon.put(Emotions.MOTIVATED, "Afternoon motivation is powerful! 💪");
        afternoon.put(Emotions.HOPEFUL, "Stay hopeful through the rest of your day! 🌟");
        afternoon.put(Emotions.SAD, "Afternoon slump? Take a short break. ☕");
        afternoon.put(Emotions.ANXIOUS, "Pause for a moment and breathe. 🌬️");
        afternoon.put(Emotions.IRRITABLE, "Step outside for fresh air. 🚶");
        afternoon.put(Emotions.TIRED, "A quick stretch can re-energize you. 🧘");
        afternoon.put(Emotions.INDIFFERENT, "Neutral is fine — pace yourself. 😐");
        afternoon.put(Emotions.OVERWHELMED, "Break big tasks into smaller steps. ✅");
        responses.put("afternoon", afternoon);

        // Evening
        Map<Emotions, String> evening = new HashMap<>();
        evening.put(Emotions.HAPPY, "Oh definitely had an amazing day. Enjoy your evening! 🌇");
        evening.put(Emotions.CALM, "A calm evening eases your mind. 🕊️");
        evening.put(Emotions.MOTIVATED, "Use evening motivation to plan tomorrow! 💪");
        evening.put(Emotions.HOPEFUL, "Evening hope prepares for a better tomorrow. 🌟");
        evening.put(Emotions.SAD, "Evening is perfect to relax and reset. 🛋️");
        evening.put(Emotions.ANXIOUS, "Try meditation or deep breathing. 🌬️");
        evening.put(Emotions.IRRITABLE, "Wind down with something you enjoy. 📖");
        evening.put(Emotions.TIRED, "Evening rest is coming soon. 💤");
        evening.put(Emotions.INDIFFERENT, "Neutral evening moments are okay. 😐");
        evening.put(Emotions.OVERWHELMED, "Reflect and sort your thoughts. 📝");
        responses.put("evening", evening);

        // Night
        Map<Emotions, String> night = new HashMap<>();
        night.put(Emotions.HAPPY, "That's good to hear. Have a good night! Rest well and recharge! 🌙");
        night.put(Emotions.CALM, "Night calm can reset your mind. 🕊️");
        night.put(Emotions.MOTIVATED, "Plan your goals for tomorrow. 💪");
        night.put(Emotions.HOPEFUL, "Night hope prepares a fresh start. 🌟");
        night.put(Emotions.SAD, "Night is time to relax. Tomorrow is a new day. 🌙");
        night.put(Emotions.ANXIOUS, "Try a warm drink and deep breathing. ☕");
        night.put(Emotions.IRRITABLE, "Let go of the day and relax. 🛋️");
        night.put(Emotions.TIRED, "Sleep well and recharge. 💤");
        night.put(Emotions.INDIFFERENT, "Night is neutral — perfect to rest. 😐");
        night.put(Emotions.OVERWHELMED, "Write down your thoughts to clear your mind. 📝");
        responses.put("night", night);
    }

    /**
     * Get the response for a given time of day and emotion.
     * @param timeOfDay morning, afternoon, evening, night
     * @param emotion the current mood
     * @return corresponding message, or null if not found
     */
    public String get(String timeOfDay, Emotions emotion) {
        return responses.getOrDefault(timeOfDay, Map.of()).get(emotion);
    }
}
