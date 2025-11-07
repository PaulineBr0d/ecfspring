package fr.m2i.cda.ecfspring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.m2i.cda.ecfspring.controller.dto.ApplicationDTO;
import fr.m2i.cda.ecfspring.controller.dto.ProjectDTO;
import fr.m2i.cda.ecfspring.controller.dto.mapper.ApplicationMapper;
import fr.m2i.cda.ecfspring.controller.dto.mapper.ProjectMapper;
import fr.m2i.cda.ecfspring.entity.Application;
import fr.m2i.cda.ecfspring.entity.Project;
import fr.m2i.cda.ecfspring.repository.ApplicationRepository;
import fr.m2i.cda.ecfspring.repository.ProjectRepository;
@Service
public class ProjectOwnerService {
    private final ApplicationRepository applicationRepository;
    private final ProjectRepository projectRepository;
    private final ApplicationMapper applicationMapper;
    private final ProjectMapper projectMapper;



    public ProjectOwnerService(ApplicationRepository applicationRepository, 
                                ProjectRepository projectRepository,
                                ApplicationMapper applicationMapper, 
                                ProjectMapper projectMapper) {
        this.applicationRepository = applicationRepository;
        this.projectRepository = projectRepository;
        this.applicationMapper = applicationMapper;
        this.projectMapper = projectMapper;
    }

    public List<ProjectDTO> listProjects(Integer ownerId) {
        List<Project> projects = projectRepository.findByProjectOwner_Id(ownerId);
        return projects.stream()
                .map(projectMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<ApplicationDTO> listApplications(Integer ownerId) {
        List<Application> applications = applicationRepository.findByProjectOwner_Id(ownerId);
        return applications.stream()
                .map(applicationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ApplicationDTO acceptApplication(Integer applicationId) {
        Application app = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        app.setStatus("Accepted");
        applicationRepository.save(app);
        return applicationMapper.toDTO(app);
    }

    public ApplicationDTO rejectApplication(Integer applicationId) {
        Application app = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        app.setStatus("Rejected");
        applicationRepository.save(app);
        return applicationMapper.toDTO(app);
    }
}
