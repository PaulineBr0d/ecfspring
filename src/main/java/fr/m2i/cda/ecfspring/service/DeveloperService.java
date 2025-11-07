package fr.m2i.cda.ecfspring.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.m2i.cda.ecfspring.controller.dto.ApplicationDTO;
import fr.m2i.cda.ecfspring.controller.dto.DeveloperUpdateDTO;
import fr.m2i.cda.ecfspring.controller.dto.mapper.ApplicationMapper;
import fr.m2i.cda.ecfspring.controller.dto.mapper.DeveloperMapper;
import fr.m2i.cda.ecfspring.entity.Application;
import fr.m2i.cda.ecfspring.entity.Developer;
import fr.m2i.cda.ecfspring.entity.Project;
import fr.m2i.cda.ecfspring.repository.ApplicationRepository;
import fr.m2i.cda.ecfspring.repository.DeveloperRepository;
import fr.m2i.cda.ecfspring.repository.ProjectRepository;

@Service
public class DeveloperService {

    private final DeveloperRepository developerRepository;
    private final ProjectRepository projectRepository;
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final DeveloperMapper developerMapper;

    public DeveloperService(DeveloperRepository developerRepository,
                            ProjectRepository projectRepository,
                            ApplicationRepository applicationRepository, 
                            ApplicationMapper applicationMapper, 
                            DeveloperMapper developerMapper) {
        this.developerRepository = developerRepository;
        this.projectRepository = projectRepository;
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
        this.developerMapper = developerMapper;
    }

    // Trouver un développeur par ID
    public Optional<Developer> findById(Integer developerId) {
        if (developerId == null) throw new IllegalArgumentException("developerId cannot be null");
        return developerRepository.findById(developerId);
    }

    // Lister les candidatures d’un développeur
    public List<ApplicationDTO> listApplications(Integer developerId) {
        Developer developer = developerRepository.findById(developerId)
                .orElseThrow(() -> new RuntimeException("Developer not found"));

        List<Application> applications = applicationRepository.findByDeveloper_Id(developerId);

        return applications.stream()
                .map(applicationMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Candidater à un projet
    public ApplicationDTO applyToProject(Integer developerId, Integer projectId) {
        if (developerId == null || projectId == null)
            throw new IllegalArgumentException("developerId and projectId cannot be null");

        Developer developer = developerRepository.findById(developerId)
                .orElseThrow(() -> new RuntimeException("Developer not found"));

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Application application = new Application();
        application.setDeveloper(developer);
        application.setProject(project);
        application.setStatus("Pending");
        application.setSubmissionDate(LocalDate.now());

        applicationRepository.save(application);

        return applicationMapper.toDTO(application);
    }

    // Valider une candidature acceptée par le développeur
    public ApplicationDTO validateApplication(Integer developerId, Integer applicationId) {
        if (developerId == null || applicationId == null)
            throw new IllegalArgumentException("developerId and applicationId cannot be null");

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        if (!application.getDeveloper().getId().equals(developerId)) {
            throw new RuntimeException("This application does not belong to this developer");
        }

        if (!"Accepted".equalsIgnoreCase(application.getStatus())) {
            throw new RuntimeException("Only accepted applications can be validated");
        }

        application.setStatus("Validated");
        applicationRepository.save(application);

        return applicationMapper.toDTO(application);
    }

    // Mettre à jour le profil du développeur
    public Developer updateProfile(Integer developerId, DeveloperUpdateDTO dto) {
        if (developerId == null) throw new IllegalArgumentException("developerId cannot be null");
        if (dto == null) throw new IllegalArgumentException("DeveloperUpdateDTO cannot be null");

        Developer developer = developerRepository.findById(developerId)
                .orElseThrow(() -> new RuntimeException("Developer not found"));

        // MapStruct met à jour directement les champs
        developerMapper.updateFromDTO(dto, developer);

        return developerRepository.save(developer);
    }

    // Supprimer un développeur
    public void deleteDeveloper(Integer developerId) {
        if (developerId == null) throw new IllegalArgumentException("developerId cannot be null");

        if (!developerRepository.existsById(developerId)) {
            throw new RuntimeException("Developer not found");
        }

        developerRepository.deleteById(developerId);
    }
}
