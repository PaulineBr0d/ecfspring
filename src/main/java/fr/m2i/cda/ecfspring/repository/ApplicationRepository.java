package fr.m2i.cda.ecfspring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.m2i.cda.ecfspring.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer>{
      List<Application> findByProject_ProjectOwner_Id(Integer ownerId);

      List<Application> findByDeveloper_Id(Integer developerId);
}
