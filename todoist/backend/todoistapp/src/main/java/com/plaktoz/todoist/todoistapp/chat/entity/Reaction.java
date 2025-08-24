package com.plaktoz.todoist.todoistapp.chat.entity;

import java.time.Instant;

public class Reaction {
    private String userId;
    private String emoji;
    private Instant at;

    public Reaction() {
    }

    public Reaction(String userId, String emoji, Instant at) {
        this.userId = userId;
        this.emoji = emoji;
        this.at = at;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public Instant getAt() {
        return at;
    }

    public void setAt(Instant at) {
        this.at = at;
    }
}