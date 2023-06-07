package com.csc3402.project.fittrackr.model;

import jakarta.persistence.*;

@Entity
public class UserWorkout {

    @EmbeddedId
    private UserWorkoutId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("workoutId")
    @JoinColumn(name = "workout_id")
    private Workout workout;

    @Column(name = "date")
    private String date;

    @Column(name = "weight")
    private double weight;

    @Column(name = "duration")
    private int duration;

    @Column(name = "calories_burned")
    private double caloriesBurned;

    public UserWorkout() {
    }

    public UserWorkout(User user, Workout workout, String date, double weight, int duration, double caloriesBurned) {
        this.id = new UserWorkoutId(user.getUserId(), workout.getWorkoutId());
        this.user = user;
        this.workout = workout;
        this.date = date;
        this.weight = weight;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
    }

    public UserWorkoutId getId() {
        return id;
    }

    public void setId(UserWorkoutId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(double caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    @Override
    public String toString() {
        return "UserWorkout{" +
                "id=" + id +
                ", user=" + user +
                ", workout=" + workout +
                ", date='" + date + '\'' +
                ", weight=" + weight +
                ", duration=" + duration +
                ", caloriesBurned=" + caloriesBurned +
                '}';
    }
}