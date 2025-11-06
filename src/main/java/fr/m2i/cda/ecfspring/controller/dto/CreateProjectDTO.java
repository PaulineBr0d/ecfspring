package fr.m2i.cda.ecfspring.controller.dto;

import java.time.LocalDate;

public class CreateProjectDTO {
    private String title;
    private String description;
    private LocalDate deliveryDate;
    private Double budget;
    private String theme;
    private String projectOwnerName; 

    // Constructeur
    public CreateProjectDTO() {}

    public CreateProjectDTO(String title, String description, LocalDate deliveryDate, Double budget, String theme, String projectOwnerName) {
        this.title = title;
        this.description = description;
        this.deliveryDate = deliveryDate;
        this.budget = budget;
        this.theme = theme;
        this.projectOwnerName = projectOwnerName;
    }

    // Getters et setters
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

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getProjectOwnerName() {
        return projectOwnerName;
    }

    public void setProjectOwnerName(String projectOwnerName) {
        this.projectOwnerName = projectOwnerName;
    }
}
