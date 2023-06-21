package com.csc3402.project.fittrackr.model;

import jakarta.persistence.*;

@Entity
public class TraineeWorkout {

    @EmbeddedId
    private TraineeWorkoutId id;

    @ManyToOne
    @MapsId("traineeId")
    @JoinColumn(name = "trainee_id")
    private Trainee trainee;

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

    public TraineeWorkout() {
    }

    public TraineeWorkout(Trainee trainee, Workout workout, String date, double weight, int duration, double caloriesBurned) {
        this.id = new TraineeWorkoutId(trainee.getTraineeId(), workout.getWorkoutId());
        this.trainee = trainee;
        this.workout = workout;
        this.date = date;
        this.weight = weight;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
    }

    public TraineeWorkoutId getId() {
        return id;
    }

    public void setId() {
        this.id = new TraineeWorkoutId(trainee.getTraineeId(),workout.getWorkoutId());;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
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
        return "TraineeWorkout{" +
                "id=" + id +
                ", trainee=" + trainee +
                ", workout=" + workout +
                ", date='" + date + '\'' +
                ", weight=" + weight +
                ", duration=" + duration +
                ", caloriesBurned=" + caloriesBurned +
                '}';
    }
}