package fr.m2i.cda.ecfspring.controller.dto;

public class ProjectOwnerDTO {
    private Integer id;
    private String name;

    public ProjectOwnerDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters et setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
