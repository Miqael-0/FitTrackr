package com.csc3402.project.fittrackr.controller;

import com.csc3402.project.fittrackr.model.Trainee;
import com.csc3402.project.fittrackr.model.TraineeWorkout;
import com.csc3402.project.fittrackr.model.Workout;
import com.csc3402.project.fittrackr.repositories.GoalRepository;
import com.csc3402.project.fittrackr.repositories.TraineeRepository;
import com.csc3402.project.fittrackr.repositories.TraineeWorkoutRepository;
import com.csc3402.project.fittrackr.repositories.WorkoutRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/workouts")
public class WorkoutController {

    private final WorkoutRepository workoutRepository;

    @Autowired
    private TraineeRepository traineeRepository;

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private TraineeWorkoutRepository traineeWorkoutRepository;

    public WorkoutController(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }


    // Assign workout to trainee
    @GetMapping("assign")
    public String assignTraineeWorkout(Model model) {
        model.addAttribute("trainees", traineeRepository.findAll());
        return "choose-workout-to-assign";
    }

    @GetMapping("assign/{id}")
    public String showAssignWorkoutForm(@PathVariable("id") long id, @Valid TraineeWorkout
            traineeWorkout, Model model) {
        Trainee trainee = traineeRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid trainee Id:" + id));
        model.addAttribute("trainee", trainee);
        model.addAttribute("workouts", workoutRepository.findAll());

        return "assign-workout";
    }

    @PostMapping("traineeworkout/{traineeid}")
    public String updateStaffProject(@PathVariable("traineeid") long id1, @Valid Workout work, @RequestParam String date,
                                     @RequestParam double weight, @RequestParam int duration, @RequestParam double caloriesBurned,
                                     @Valid TraineeWorkout traineeWorkout,
                                     BindingResult result, Model model) {
        if (result.hasErrors()) {
            traineeWorkout.setId();
            return "redirect:/workouts/assign";
        }

        Trainee trainee = traineeRepository.findTraineeById((int) id1);
        Workout workout = workoutRepository.findWorkoutById(work.getWorkoutId());

        TraineeWorkout traineeWorkout1 = new TraineeWorkout(trainee, workout, date, weight, duration, caloriesBurned);
        traineeWorkoutRepository.save(traineeWorkout1);

        return "redirect:/workouts/assign";
    }

    @GetMapping("display")
    public String displayTraineeWorkout(Model model) {
        model.addAttribute("trainees",traineeRepository.findAll());
        return "choose-workout-to-display";
    }

    @GetMapping("display/{traineeid}")
    public String showDisplayWorkoutForm(@PathVariable("traineeid") long id, Model model) {
        List<TraineeWorkout> traineeWorkout = (List<TraineeWorkout>) traineeWorkoutRepository.findTraineeWorkoutByTraineeId((int) id);
        // .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("trainee",traineeRepository.findTraineeById((int)id));
        model.addAttribute("traineeWorkouts", traineeWorkout);
        return "display-workout";
    }
}