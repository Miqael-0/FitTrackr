package com.csc3402.project.fittrackr.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserWorkoutId implements Serializable {

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "workout_id")
    private Integer workoutId;

    public UserWorkoutId() {
    }

    public UserWorkoutId(Integer userId, Integer workoutId) {
        this.userId = userId;
        this.workoutId = workoutId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        UserWorkoutId that = (UserWorkoutId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(workoutId, that.workoutId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, workoutId);
    }
}
