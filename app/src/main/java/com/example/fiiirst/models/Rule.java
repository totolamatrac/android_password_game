package com.example.fiiirst.models;

public class Rule {
    private String title;
    private String description;
    private boolean isValide;

    public Rule(String title, String description) {
        this.title = title;
        this.description = description;
        this.isValide = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isValide() {
        return isValide;
    }

    public void setValide(boolean valide) {
        isValide = valide;
    }
}
