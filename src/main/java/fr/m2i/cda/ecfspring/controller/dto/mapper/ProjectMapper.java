package fr.m2i.cda.ecfspring.controller.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import fr.m2i.cda.ecfspring.controller.dto.CreateProjectDTO;
import fr.m2i.cda.ecfspring.controller.dto.ProjectDTO;
import fr.m2i.cda.ecfspring.controller.dto.ProjectOwnerDTO;
import fr.m2i.cda.ecfspring.entity.Project;
import fr.m2i.cda.ecfspring.entity.ProjectOwner;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {

    Project toEntity(CreateProjectDTO dto); 
    
    ProjectDTO toDTO(Project project); 

    ProjectOwnerDTO toProjectOwnerDTO(ProjectOwner projectOwner); 
}