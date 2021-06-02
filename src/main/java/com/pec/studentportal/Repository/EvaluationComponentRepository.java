package com.pec.studentportal.Repository;

import com.pec.studentportal.Entity.EvaluationComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EvaluationComponentRepository extends JpaRepository<EvaluationComponent, Integer> {

    Optional<EvaluationComponent> findById(Integer id);

}
