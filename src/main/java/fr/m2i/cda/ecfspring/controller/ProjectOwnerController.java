package fr.m2i.cda.ecfspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.m2i.cda.ecfspring.controller.dto.ApplicationDTO;
import fr.m2i.cda.ecfspring.controller.dto.CreateProjectDTO;
import fr.m2i.cda.ecfspring.controller.dto.ProjectDTO;
import fr.m2i.cda.ecfspring.service.ProjectOwnerService;
import fr.m2i.cda.ecfspring.service.ProjectService;

@RestController
@RequestMapping("/api/projectowner/{ownerId}")
public class ProjectOwnerController {

    private final ProjectOwnerService projectOwnerService;
    private final ProjectService projectService;

    public ProjectOwnerController(ProjectOwnerService projectOwnerService, ProjectService projectService) {
        this.projectOwnerService = projectOwnerService;
        this.projectService = projectService;
    }
    @GetMapping("/projects")
    public List<ProjectDTO> listProjects(@PathVariable Integer ownerId) {
        return projectOwnerService.listProjects(ownerId);
    }
    //proposer un projet
    @PostMapping("/projects")
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDTO proposeProject(@PathVariable Integer ownerId,
                                    @RequestBody CreateProjectDTO dto) {
        return projectService.proposeProject(ownerId, dto);
    }

    //lister les candidatures
    @GetMapping("/applications")
    public List<ApplicationDTO> listApplications(@PathVariable Integer ownerId) {
        return projectOwnerService.listApplications(ownerId);
    }
    
    //accepter ou rejeter une application
    @PostMapping("/applications/{applicationId}/accept")
    public ApplicationDTO acceptApplication(@PathVariable Integer applicationId) {
        return projectOwnerService.acceptApplication(applicationId);
    }

    @PostMapping("applications/{applicationId}/reject")
    public ApplicationDTO rejectApplication(@PathVariable Integer applicationId) {
        return projectOwnerService.rejectApplication(applicationId);
    }

}
