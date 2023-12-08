package fr.polytech.cours.repository;

import fr.polytech.cours.entity.EvaluationFinaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationFinaleRepository extends JpaRepository<EvaluationFinaleEntity, Integer> {
}
