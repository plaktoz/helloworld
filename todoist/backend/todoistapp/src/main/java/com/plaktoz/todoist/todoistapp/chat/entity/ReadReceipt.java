package com.plaktoz.todoist.todoistapp.chat.entity;

import java.time.Instant;

public class ReadReceipt {
    private String userId;
    private Instant at;

    public ReadReceipt() {
    }

    public ReadReceipt(String userId, Instant at) {
        this.userId = userId;
        this.at = at;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Instant getAt() {
        return at;
    }

    public void setAt(Instant at) {
        this.at = at;
    }
}