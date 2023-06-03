package com.csc3402.project.fittrackr.model;

import jakarta.persistence.*;
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

    public Goal() {
    }

    public Integer getGoalId() {
        return goalId;
    }

    public void setGoalsId(Integer goalId) {
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

    @Override
    public String toString() {
        return "Goal{" +
                "goalId=" + goalId +
                ", target='" + targetGoal + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
