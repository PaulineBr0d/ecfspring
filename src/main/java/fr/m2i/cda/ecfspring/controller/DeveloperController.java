package fr.m2i.cda.ecfspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.m2i.cda.ecfspring.controller.dto.ApplicationDTO;
import fr.m2i.cda.ecfspring.controller.dto.DeveloperDTO;
import fr.m2i.cda.ecfspring.controller.dto.DeveloperUpdateDTO;
import fr.m2i.cda.ecfspring.controller.dto.mapper.DeveloperMapper;
import fr.m2i.cda.ecfspring.entity.Developer;
import fr.m2i.cda.ecfspring.service.DeveloperService;

@RestController
@RequestMapping("/api/developer")
public class DeveloperController {

    private final DeveloperService developerService;
    private final DeveloperMapper developerMapper;
    
    public DeveloperController(DeveloperService developerService, 
                                DeveloperMapper developerMapper) {
        this.developerService = developerService;
        this.developerMapper = developerMapper;
    }

    // lister les candidatures du développeur
    @GetMapping("/{developerId}/applications")
    public List<ApplicationDTO> listApplications(@PathVariable Integer developerId) {
        return developerService.listApplications(developerId);
    }

     // candidater à un projet
    @PostMapping("/{developerId}/apply/{projectId}")
    public ApplicationDTO applyToProject(@PathVariable Integer developerId,
                                         @PathVariable Integer projectId) {
        return developerService.applyToProject(developerId, projectId);
    }

    // valider une candidature acceptée
    @PostMapping("/{developerId}/applications/{applicationId}/validate")
    public ApplicationDTO validateApplication(@PathVariable Integer developerId,
                                               @PathVariable Integer applicationId) {
        return developerService.validateApplication(developerId, applicationId);
    }
    
    //mise à jour profile
    @PutMapping("/{developerId}")
    public DeveloperDTO updateProfile(@PathVariable Integer developerId,
                                    @RequestBody DeveloperUpdateDTO dto) {
        Developer updatedDeveloper = developerService.updateProfile(developerId, dto);
        return developerMapper.toDTO(updatedDeveloper);
}

    // Consulter son profil
    @GetMapping("/{developerId}")
    public DeveloperDTO getProfile(@PathVariable Integer developerId) {
        Developer developer = developerService.findById(developerId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Developer not found"));
        return developerMapper.toDTO(developer);
    }

    // Supprimer son profil
    @DeleteMapping("/{developerId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Integer developerId) {
        try {
            developerService.deleteDeveloper(developerId);
            return ResponseEntity.noContent().build(); 
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
        }
    }

}
