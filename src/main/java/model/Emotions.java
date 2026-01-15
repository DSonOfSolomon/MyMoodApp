package model;

public enum Emotions{
    HAPPY("😊"),
    CALM("😌"),
    MOTIVATED("💪"),
    HOPEFUL("🌟"),
    SAD("😢"),
    ANXIOUS("😰"),
    IRRITABLE("😠"),
    TIRED("😴"),
    INDIFFERENT("😐"),
    OVERWHELMED("😵");

    private final String emoji;

    Emotions(String emoji) {
        this.emoji = emoji;
    }

    public String getEmoji() {
        return emoji;
    }

    @Override
    public String toString() {
        String lower = name().toLowerCase();
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1) + " " + emoji;
    }
}



