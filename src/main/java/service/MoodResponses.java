package service;

import java.util.HashMap;
import java.util.Map;

public class MoodResponses {

    private final Map<String, Map<com.example.helloMood.Emotions, String>> responses = new HashMap<>();

    public MoodResponses() {
        buildResponses();
    }

    private void buildResponses() {
        // Morning
        Map<com.example.helloMood.Emotions, String> morning = new HashMap<>();
        morning.put(com.example.helloMood.Emotions.HAPPY, "Good morning! What a bright start! 🌞");
        morning.put(com.example.helloMood.Emotions.CALM, "Morning calm sets a peaceful tone. 🕊️");
        morning.put(com.example.helloMood.Emotions.MOTIVATED, "Morning motivation is the best motivation! 💪");
        morning.put(com.example.helloMood.Emotions.HOPEFUL, "Morning hope can guide your day! 🌟");
        morning.put(com.example.helloMood.Emotions.SAD, "Good morning. Take it slow and sip some coffee. ☕");
        morning.put(com.example.helloMood.Emotions.ANXIOUS, "Take a deep breath and start gently. 🌬️");
        morning.put(com.example.helloMood.Emotions.IRRITABLE, "Try a short walk to clear your mind. 🚶");
        morning.put(com.example.helloMood.Emotions.TIRED, "Coffee or a stretch might help wake you up. ☕");
        morning.put(com.example.helloMood.Emotions.INDIFFERENT, "Neutral mornings happen. Take it easy. 😐");
        morning.put(com.example.helloMood.Emotions.OVERWHELMED, "Focus on one task at a time. ✅");
        responses.put("morning", morning);

        // Afternoon
        Map<com.example.helloMood.Emotions, String> afternoon = new HashMap<>();
        afternoon.put(com.example.helloMood.Emotions.HAPPY, "Good afternoon! Keep the energy going! ☀️");
        afternoon.put(com.example.helloMood.Emotions.CALM, "Afternoon calm can help you finish strong. 🕊️");
        afternoon.put(com.example.helloMood.Emotions.MOTIVATED, "Afternoon motivation is powerful! 💪");
        afternoon.put(com.example.helloMood.Emotions.HOPEFUL, "Stay hopeful through the rest of your day! 🌟");
        afternoon.put(com.example.helloMood.Emotions.SAD, "Afternoon slump? Take a short break. ☕");
        afternoon.put(com.example.helloMood.Emotions.ANXIOUS, "Pause for a moment and breathe. 🌬️");
        afternoon.put(com.example.helloMood.Emotions.IRRITABLE, "Step outside for fresh air. 🚶");
        afternoon.put(com.example.helloMood.Emotions.TIRED, "A quick stretch can re-energize you. 🧘");
        afternoon.put(com.example.helloMood.Emotions.INDIFFERENT, "Neutral is fine — pace yourself. 😐");
        afternoon.put(com.example.helloMood.Emotions.OVERWHELMED, "Break big tasks into smaller steps. ✅");
        responses.put("afternoon", afternoon);

        // Evening
        Map<com.example.helloMood.Emotions, String> evening = new HashMap<>();
        evening.put(com.example.helloMood.Emotions.HAPPY, "Oh definitely had an amazing day. Enjoy your evening! 🌇");
        evening.put(com.example.helloMood.Emotions.CALM, "A calm evening eases your mind. 🕊️");
        evening.put(com.example.helloMood.Emotions.MOTIVATED, "Use evening motivation to plan tomorrow! 💪");
        evening.put(com.example.helloMood.Emotions.HOPEFUL, "Evening hope prepares for a better tomorrow. 🌟");
        evening.put(com.example.helloMood.Emotions.SAD, "Evening is perfect to relax and reset. 🛋️");
        evening.put(com.example.helloMood.Emotions.ANXIOUS, "Try meditation or deep breathing. 🌬️");
        evening.put(com.example.helloMood.Emotions.IRRITABLE, "Wind down with something you enjoy. 📖");
        evening.put(com.example.helloMood.Emotions.TIRED, "Evening rest is coming soon. 💤");
        evening.put(com.example.helloMood.Emotions.INDIFFERENT, "Neutral evening moments are okay. 😐");
        evening.put(com.example.helloMood.Emotions.OVERWHELMED, "Reflect and sort your thoughts. 📝");
        responses.put("evening", evening);

        // Night
        Map<com.example.helloMood.Emotions, String> night = new HashMap<>();
        night.put(com.example.helloMood.Emotions.HAPPY, "That's good to hear. Have a good night! Rest well and recharge! 🌙");
        night.put(com.example.helloMood.Emotions.CALM, "Night calm can reset your mind. 🕊️");
        night.put(com.example.helloMood.Emotions.MOTIVATED, "Plan your goals for tomorrow. 💪");
        night.put(com.example.helloMood.Emotions.HOPEFUL, "Night hope prepares a fresh start. 🌟");
        night.put(com.example.helloMood.Emotions.SAD, "Night is time to relax. Tomorrow is a new day. 🌙");
        night.put(com.example.helloMood.Emotions.ANXIOUS, "Try a warm drink and deep breathing. ☕");
        night.put(com.example.helloMood.Emotions.IRRITABLE, "Let go of the day and relax. 🛋️");
        night.put(com.example.helloMood.Emotions.TIRED, "Sleep well and recharge. 💤");
        night.put(com.example.helloMood.Emotions.INDIFFERENT, "Night is neutral — perfect to rest. 😐");
        night.put(com.example.helloMood.Emotions.OVERWHELMED, "Write down your thoughts to clear your mind. 📝");
        responses.put("night", night);
    }

    public String get(String timeOfDay, com.example.helloMood.Emotions emotion) {
        return responses.getOrDefault(timeOfDay, Map.of()).get(emotion);
    }
}
