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
@RequestMapping("/trainees")
public class TraineeController {
    private final TraineeRepository traineeRepository;

    @Autowired
    private  GoalRepository goalRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private TraineeWorkoutRepository traineeWorkoutRepository;

    public TraineeController(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("trainees", traineeRepository.findAll());
        model.addAttribute("goals", goalRepository.findAll());
        return "list-trainee";
    }

    @GetMapping("signup")
    public String showSignUpForm(Trainee trainee, Model model){
        model.addAttribute("goals", goalRepository.findAll());
        return "add-trainee";
    }

    @PostMapping("add")
    public String addTrainee(@Valid Trainee trainee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-trainee";
        }

        traineeRepository.save(trainee);
        return "redirect:list";
    }

    // UPDATE TRAINEE
    @GetMapping("update")
    public String showUpdateMainForm(Model model) {
        model.addAttribute("trainees", traineeRepository.findAll());
        return "choose-trainee-to-update";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Trainee trainee = traineeRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Trainee Id:" + id));
        model.addAttribute("trainee", trainee);
        model.addAttribute("goals", goalRepository.findAll());
        return "update-trainee";
    }

    @PostMapping("update/{id}")
    public String updateTrainee(@PathVariable("id") long id, @Valid Trainee trainee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            trainee.setTraineeId((int) id);
            //return html index
            return "landing-page";
        }

        model.addAttribute("trainees", traineeRepository.findAll());
        model.addAttribute("goals", goalRepository.findAll());
        traineeRepository.save(trainee);
        return "list-trainee";
    }


    // DELETE TRAINEE
    @GetMapping("delete")
    public String showDeleteMainForm(Model model) {
        model.addAttribute("trainees", traineeRepository.findAll());
        return "choose-trainee-to-delete";
    }

    @GetMapping("delete/{id}")
    public String deleteTrainee(@PathVariable("id") long id, Model model) {
       Trainee trainee = traineeRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Trainee Id:" + id));
        traineeRepository.delete(trainee);
        model.addAttribute("trainees", traineeRepository.findAll());
        return "list-trainee";
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
            return "index";
        }

        Trainee trainee = traineeRepository.findTraineeById((int) id1);
        Workout workout = workoutRepository.findWorkoutById(work.getWorkoutId());

        TraineeWorkout traineeWorkout1 = new TraineeWorkout(trainee,workout,date,weight,duration,caloriesBurned);
        traineeWorkoutRepository.save(traineeWorkout1);

        return "index";
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
