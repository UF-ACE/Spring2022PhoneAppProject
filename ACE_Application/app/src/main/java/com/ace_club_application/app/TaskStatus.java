package com.ace_club_application.app;

public enum TaskStatus {
    Open("Open"),
    InProgress("In Progress"),
    Complete("Complete");
    String displayName;
    TaskStatus(String displayName) {
        this.displayName = displayName;
    }
}