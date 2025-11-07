package fr.m2i.cda.ecfspring.controller.dto;

import java.time.LocalDate;

public class ApplicationDTO {
    private Integer id;
    private LocalDate submissionDate;
    private String status;

    private Integer developerId;
    private String developerName; 

    private Integer projectId;
    private String projectTitle;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDate getSubmissionDate() {
        return submissionDate;
    }
    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getDeveloperId() {
        return developerId;
    }
    public void setDeveloperId(Integer developerId) {
        this.developerId = developerId;
    }
    public String getDeveloperName() {
        return developerName;
    }
    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }
    public Integer getProjectId() {
        return projectId;
    }
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
    public String getProjectTitle() {
        return projectTitle;
    }
    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }  

}
