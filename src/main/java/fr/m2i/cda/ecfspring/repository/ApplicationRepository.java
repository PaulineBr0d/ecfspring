package fr.m2i.cda.ecfspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.m2i.cda.ecfspring.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer>{

}
