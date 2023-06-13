package com.csc3402.project.fittrackr.repositories;

public interface UserWorkoutRepository {
  @Query(value = "SELECT * FROM user_workout where user_id = :id", nativeQuery = true)
    List<UserWorkout> findUserWorkoutByUserId(@Param("id")int id);
}
