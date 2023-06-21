package com.csc3402.project.fittrackr.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Goal {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="goal_id")
    private Integer goalId;

    @Column(name="target_goal")
    private String targetGoal;

    @Column(name="start_date")
    private String startDate;

    @Column(name="end_date")
    private String endDate;

    @OneToMany(mappedBy= "goal", cascade=CascadeType.ALL)
    private Set<Trainee> trainees;
   
    public Goal() {
    }

    public Goal(String targetGoal, String startDate, String endDate) {
        this.targetGoal = targetGoal;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getGoalId() {
        return goalId;
    }

    public void setGoalId(Integer goalId) {
        this.goalId = goalId;
    }

    public String getTargetGoal() {
        return targetGoal;
    }

    public void setTargetGoal(String targetGoal) {
        this.targetGoal = targetGoal;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Set<Trainee> getTrainees() {
        return trainees;
    }

    public void setTrainees(Set<Trainee> trainees) {
        this.trainees = trainees;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "goalId=" + goalId +
                ", targetGoal='" + targetGoal + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
