package fr.m2i.cda.ecfspring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.m2i.cda.ecfspring.controller.dto.ApplicationDTO;
import fr.m2i.cda.ecfspring.controller.dto.mapper.ApplicationMapper;
import fr.m2i.cda.ecfspring.repository.ApplicationRepository;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;

    public ApplicationService(ApplicationRepository applicationRepository,
                              ApplicationMapper applicationMapper) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
    }

    public List<ApplicationDTO> listApplicationsByOwner(Integer ownerId) {
        return applicationRepository.findByProjectOwner_Id(ownerId)
                                    .stream()
                                    .map(applicationMapper::toDTO)
                                    .collect(Collectors.toList());
    }
}
