package com.plaktoz.todoist.todoistapp.chat.entity;

import java.time.Instant;

public class Participant {
    private String userId;
    private String role;     // "owner", "admin", "member"
    private Instant joinedAt;

    public Participant() {
    }

    public Participant(String userId, String role, Instant joinedAt) {
        this.userId = userId;
        this.role = role;
        this.joinedAt = joinedAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Instant getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Instant joinedAt) {
        this.joinedAt = joinedAt;
    }
}
