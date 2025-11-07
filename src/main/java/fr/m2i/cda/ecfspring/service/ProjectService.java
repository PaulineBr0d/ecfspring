package fr.m2i.cda.ecfspring.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import fr.m2i.cda.ecfspring.controller.dto.CreateProjectDTO;
import fr.m2i.cda.ecfspring.controller.dto.ProjectDTO;
import fr.m2i.cda.ecfspring.controller.dto.mapper.ProjectMapper;
import fr.m2i.cda.ecfspring.entity.Project;
import fr.m2i.cda.ecfspring.entity.ProjectOwner;
import fr.m2i.cda.ecfspring.repository.ProjectOwnerRepository;
import fr.m2i.cda.ecfspring.repository.ProjectRepository;


@Service
public class ProjectService {
    
    private final ProjectRepository projectRepository;
    private final ProjectOwnerRepository projectOwnerRepository;
    private final ProjectMapper projectMapper;

    public ProjectService(ProjectRepository projectRepository, ProjectOwnerRepository projectOwnerRepository,
            ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectOwnerRepository = projectOwnerRepository;
        this.projectMapper = projectMapper;
    }


    // Créer un projet
    public Project createProject(Project project) {
    return projectRepository.save(project);
    }

     // Proposer un projet
    public ProjectDTO proposeProject(Integer ownerId, CreateProjectDTO dto) {
    ProjectOwner owner = projectOwnerRepository.findById(ownerId)
            .orElseThrow(() -> new RuntimeException("Project Owner not found"));

    Project project = projectMapper.toEntity(dto);
    project.setProjectOwner(owner);

    Project savedProject = createProject(project);

    return projectMapper.toDTO(savedProject);
    }

    // Rechercher des projets avec filtres
    public List<Project> searchProjects(String theme,
    Double budgetMin,
    Double budgetMax,
    LocalDate deliveryDate,
    LocalDate deliveryDateAfter,
    LocalDate deliveryDateBefore) {
        return projectRepository.findByFilters(theme, budgetMin, budgetMax, deliveryDate, deliveryDateAfter, deliveryDateBefore);
    }

    // Récupérer un projet par ID
    public Optional<Project> getProjectById(Integer id) {
        return projectRepository.findById(id);
    }

    // Récupérer un ProjectOwner par son nom
   public ProjectOwner getProjectOwnerByName(String name) {
    return projectOwnerRepository.findByName(name)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Project Owner not found with name: " + name));
    }
}
