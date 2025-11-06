package fr.m2i.cda.ecfspring.controller.dto;

import java.time.LocalDate;

public class ProjectDTO {
    private Integer id;
    private String title;
    private String description;
    private LocalDate deliveryDate;
    private Double budget;
    private String theme;

    private ProjectOwnerDTO projectOwner; // Nouveau champ pour le propriétaire du projet

    public ProjectDTO(Integer id, String title, String description, LocalDate deliveryDate, Double budget, 
                      String theme, ProjectOwnerDTO projectOwner) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deliveryDate = deliveryDate;
        this.budget = budget;
        this.theme = theme;
        this.projectOwner = projectOwner;
    }

    // Getters et setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public ProjectOwnerDTO getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(ProjectOwnerDTO projectOwner) {
        this.projectOwner = projectOwner;
    }
}
