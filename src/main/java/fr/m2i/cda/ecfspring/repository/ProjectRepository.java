package fr.m2i.cda.ecfspring.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.m2i.cda.ecfspring.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findByProjectOwner_Id(Integer ownerId);

    @Query("""
        SELECT p FROM Project p
        WHERE (:theme IS NULL OR p.theme = :theme)
          AND (:budgetMin IS NULL OR p.budget >= :budgetMin)
          AND (:budgetMax IS NULL OR p.budget <= :budgetMax)
          AND (:deliveryDate IS NULL OR p.deliveryDate = :deliveryDate)
          AND (:deliveryDateAfter IS NULL OR p.deliveryDate > :deliveryDateAfter)
          AND (:deliveryDateBefore IS NULL OR p.deliveryDate < :deliveryDateBefore)
    """)
    List<Project> findByFilters(
        @Param("theme") String theme,
        @Param("budgetMin") Double budgetMin,
        @Param("budgetMax") Double budgetMax,
        @Param("deliveryDate") LocalDate deliveryDate,
        @Param("deliveryDateAfter") LocalDate deliveryDateAfter,
        @Param("deliveryDateBefore") LocalDate deliveryDateBefore
    );
}

