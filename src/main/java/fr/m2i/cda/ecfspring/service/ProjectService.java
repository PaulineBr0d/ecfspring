package fr.m2i.cda.ecfspring.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import fr.m2i.cda.ecfspring.entity.Project;
import fr.m2i.cda.ecfspring.entity.ProjectOwner;
import fr.m2i.cda.ecfspring.repository.ProjectOwnerRepository;
import fr.m2i.cda.ecfspring.repository.ProjectRepository;


@Service
public class ProjectService {
    
    private final ProjectRepository projectRepository;
    private final ProjectOwnerRepository projectOwnerRepository;

    public ProjectService(ProjectRepository projectRepository, ProjectOwnerRepository projectOwnerRepository) {
        this.projectRepository = projectRepository;
        this.projectOwnerRepository = projectOwnerRepository;
    }

    // Créer un projet
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    // Rechercher des projets avec filtres
    public List<Project> searchProjects(String theme, Double budget, LocalDate deliveryDate) {
        return projectRepository.findByFilters(theme, budget, deliveryDate);
    }

    // Récupérer un projet par ID
    public Optional<Project> getProjectById(Integer id) {
        return projectRepository.findById(id);
    }

    // Récupérer un ProjectOwner par son nom
   public ProjectOwner getProjectOwnerByName(String name) {
    return projectOwnerRepository.findByName(name)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "ProjectOwner not found with name: " + name));
    }
}
