package model;

public enum Emotions {

    //Positive emotions
    HAPPY("😊"),
    CALM("😌"),
    MOTIVATED("💪"),
    HOPEFUL("🌟"),

    //Neutral emotions
    INDIFFERENT("😐"),

    //Negative emotions
    SAD("😢"),
    ANXIOUS("😰"),
    IRRITABLE("😒"),
    TIRED("😴"),
    OVERWHELMED("");


    //The emoji associated with the emotion
    private final String emoji;

    //Constructor
    Emotions(String emoji) {
        this.emoji = emoji;
    }

    /**
     * @return Emoji string
     */
    public String getEmoji() {
        return emoji;
    }

    @Override
    public String toString() {
        String lower = name().toLowerCase();
        return Character.toUpperCase(lower.charAt(0))
                + lower.substring(1) + " " + emoji;
    }
}


