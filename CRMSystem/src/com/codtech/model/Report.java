package com.codtech.model;

import java.time.LocalDateTime;

public class Report {
    private int id;
    private String title;
    private String content;
    private LocalDateTime generatedAt;

    public Report() {
    }

    // Constructor for creating a report (without ID and generatedAt)
    public Report(String title, String content) {
        this.title = title;
        this.content = content;
        this.generatedAt = LocalDateTime.now(); // Automatically set current timestamp
    }

    // Constructor for creating a report with ID, title, content, and generatedAt
    public Report(int id, String title, String content, LocalDateTime generatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.generatedAt = generatedAt;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
}
