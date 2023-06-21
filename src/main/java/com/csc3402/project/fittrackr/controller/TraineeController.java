package com.csc3402.project.fittrackr.controller;

import com.csc3402.project.fittrackr.model.Trainee;
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
            return "list-trainee";
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
}