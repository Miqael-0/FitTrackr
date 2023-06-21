package com.csc3402.project.fittrackr.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workout_id")
    private Integer workoutId;

    @Column(name = "workout_name")
    private String workoutName;

    @OneToMany(mappedBy = "workout")
    private Set<TraineeWorkout> traineeWorkouts = new HashSet<>();

    public Workout() {
    }

    public Workout(String workoutName) {
        this.workoutName = workoutName;
    }

    public Integer getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Integer workoutId) {
        this.workoutId = workoutId;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "workoutId=" + workoutId +
                ", workoutName='" + workoutName +
                '}';
    }
}
