package com.example.mahd1_emotilog;

public class EmojiEvent {
    private final String emoji;
    private final long timestamp;

    public EmojiEvent(String emoji) {
        this.emoji = emoji;
        this.timestamp = System.currentTimeMillis();
    }

    public String getEmoji() {
        return emoji;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
