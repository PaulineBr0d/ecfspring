package fr.m2i.cda.ecfspring.controller.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fr.m2i.cda.ecfspring.controller.dto.ApplicationDTO;
import fr.m2i.cda.ecfspring.entity.Application;


@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    @Mapping(source = "developer.id", target = "developerId")
    @Mapping(source = "developer.name", target = "developerName")
    @Mapping(source = "project.id", target = "projectId")
    @Mapping(source = "project.title", target = "projectTitle")
     ApplicationDTO toDTO(Application application);
}
