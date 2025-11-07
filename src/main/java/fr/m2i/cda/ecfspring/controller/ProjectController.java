package fr.m2i.cda.ecfspring.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.m2i.cda.ecfspring.controller.dto.mapper.ProjectMapper;
import fr.m2i.cda.ecfspring.controller.dto.ProjectDTO;
import fr.m2i.cda.ecfspring.entity.Project;
import fr.m2i.cda.ecfspring.service.ProjectService;


@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService service;
    private final ProjectMapper mapper;

    public ProjectController(ProjectService service, ProjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    // Rechercher des projets avec filtre
    @GetMapping
    public List<ProjectDTO> searchProjects(
        @RequestParam(required = false) String theme,
        @RequestParam(required = false) Double budgetMin,
        @RequestParam(required = false) Double budgetMax,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deliveryDate,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deliveryDateAfter,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate deliveryDateBefore
    ) {
        List<Project> projects = service.searchProjects(theme, budgetMin, budgetMax, deliveryDate, deliveryDateAfter, deliveryDateBefore);
        return projects.stream()
                       .map(mapper::toDTO)
                       .collect(Collectors.toList());
    }

    // Détails d’un projet
    @GetMapping("/{id}")
    public ProjectDTO getProject(@PathVariable Integer id) {
        return service.getProjectById(id)
                             .map(mapper::toDTO)
                             .orElse(null); 
    }
}

