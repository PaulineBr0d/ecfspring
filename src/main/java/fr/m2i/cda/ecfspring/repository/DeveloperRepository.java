package fr.m2i.cda.ecfspring.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import fr.m2i.cda.ecfspring.entity.Developer;

public interface DeveloperRepository extends JpaRepository<Developer, Integer> {

}
