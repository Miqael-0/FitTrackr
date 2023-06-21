package com.csc3402.project.fittrackr.repositories;

import com.csc3402.project.fittrackr.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Integer> {
    @Query(value = "SELECT * FROM trainee where trainee_id = :id", nativeQuery = true)
    Trainee findTraineeById(@Param("id") int id);
}
