package com.csc3402.project.fittrackr.repositories;

import com.csc3402.project.fittrackr.model.TraineeWorkout;
import com.csc3402.project.fittrackr.model.TraineeWorkoutId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TraineeWorkoutRepository extends JpaRepository<TraineeWorkout, TraineeWorkoutId> {
    @Query(value = "SELECT * FROM trainee_workout where trainee_id = :id", nativeQuery = true)
    List<TraineeWorkout> findTraineeWorkoutByTraineeId(@Param("id")int id);
}
