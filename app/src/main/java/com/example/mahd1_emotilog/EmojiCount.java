package com.example.mahd1_emotilog;

public class EmojiCount {
    private final String emoji;
    private final int count;
    public EmojiCount(String emoji, int count) {
        this.emoji = emoji;
        this.count = count;
    }
    public String getEmoji() {
        return emoji;
    }
    public int getCount() {
        return count;
    }
}
