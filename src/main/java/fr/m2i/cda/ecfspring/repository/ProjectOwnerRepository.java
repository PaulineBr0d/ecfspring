package fr.m2i.cda.ecfspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.m2i.cda.ecfspring.entity.ProjectOwner;

public interface ProjectOwnerRepository extends JpaRepository<ProjectOwner, Integer>{
    Optional<ProjectOwner> findByName(String name);
}
