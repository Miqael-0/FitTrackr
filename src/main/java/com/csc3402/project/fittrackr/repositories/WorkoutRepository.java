package com.csc3402.project.fittrackr.repositories;

import com.csc3402.project.fittrackr.model.Trainee;
import com.csc3402.project.fittrackr.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Integer> {
    @Query(value = "SELECT * FROM workout where workout_id = :id", nativeQuery = true)
    Workout findWorkoutById(@Param("id") int id);
}
