package com.csc3402.project.fittrackr.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.csc3402.project.fittrackr.model.Goal;

@Repository
public interface GoalRepository extends JpaRepository <Goal,Integer> {
}
