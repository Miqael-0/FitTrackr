package com.csc3402.project.fittrackr.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TraineeWorkoutId implements Serializable {

    @Column(name = "trainee_id")
    private Integer traineeId;

    @Column(name = "workout_id")
    private Integer workoutId;

    public TraineeWorkoutId() {
    }

    public TraineeWorkoutId(Integer traineeId, Integer workoutId) {
        this.traineeId = traineeId;
        this.workoutId = workoutId;
    }

    public Integer getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(Integer traineeId) {
        this.traineeId = traineeId;
    }

    public Integer getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Integer workoutId) {
        this.workoutId = workoutId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TraineeWorkoutId that = (TraineeWorkoutId) o;
        return Objects.equals(traineeId, that.traineeId) && Objects.equals(workoutId, that.workoutId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(traineeId, workoutId);
    }
}
