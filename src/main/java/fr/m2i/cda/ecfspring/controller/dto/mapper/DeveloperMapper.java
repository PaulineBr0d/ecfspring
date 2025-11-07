package fr.m2i.cda.ecfspring.controller.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import fr.m2i.cda.ecfspring.controller.dto.DeveloperDTO;
import fr.m2i.cda.ecfspring.controller.dto.DeveloperUpdateDTO;
import fr.m2i.cda.ecfspring.entity.Developer;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeveloperMapper {
    // Convertir Developer -> DeveloperDTO
    DeveloperDTO toDTO(Developer developer);

    // Mettre à jour un Developer existant avec un DeveloperUpdateDTO
    void updateFromDTO(DeveloperUpdateDTO dto, @MappingTarget Developer developer);
}
