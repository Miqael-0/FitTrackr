package com.csc3402.project.fittrackr.controller;

        import com.csc3402.project.fittrackr.repositories.UserRepository;
        import com.csc3402.project.fittrackr.model.User;
        import com.csc3402.project.fittrackr.repositories.GoalRepository;
        import com.csc3402.project.fittrackr.model.Goal;
        import com.csc3402.project.fittrackr.repositories.WorkoutRepository;
        import com.csc3402.project.fittrackr.model.Workout;
        import com.csc3402.project.fittrackr.repositories.UserWorkoutRepository;
        import com.csc3402.project.fittrackr.model.UserWorkout;
        import jakarta.validation.Valid;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.validation.BindingResult;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    private  GoalRepository goalRepository;

    @Autowired
    private Workout Repository workoutRepository;

    @Autowired
    private UserWorkoutRepository userWorkoutRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "list-user";
    }

    @GetMapping("signup")
    public String showSignUpForm(User user,Model model){
        model.addAttribute("goals", goalRepository.findAll());
        return "add-user;
    }

    @PostMapping("add")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
       userRepository.save(user);
        return "redirect:list";
    }

    // UPDATE user
    @GetMapping("update")
    public String showUpdateMainForm(Model model) {
        model.addAttribute("users", userRepository.findAll());
        //nama html yang kita nak mapping
        return "choose-user-to-update";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        model.addAttribute("goals", goalRepository.findAll());
        return "update-user";
    }

    @PostMapping("update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setUserId((int) id);
            //return html index
            return "index";
        }

        model.addAttribute("users, userRepository.findAll());
        userRepository.save(user);
        return "list-user";
    }


    // DELETE user
    @GetMapping("delete")
    public String showDeleteMainForm(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "choose-user-to-delete";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
       User user= userRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        return "list-user";
    }

    // Assign workout to user
    @GetMapping("assign")
    public String assignuserProject(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "choose-workout-to-assign";
    }

    @GetMapping("assign/{id}")
    public String showAssignWorkout(@PathVariable("id") long id, @Valid UserWorkout
            userWorkout, Model model) {
        User user = userRepository.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        model.addAttribute("workouts", workoutRepository.findAll());

        return "assign-workout";
    }
// yang ni tak sure
    @PostMapping("userWorkout/{userid}")
    public String updateUserWorkout(@PathVariable("userid") long id1, @Valid Workout work, @RequestParam String date,
                                     @RequestParam double weight, @RequestParam int duration,@RequestParam double caloriesBurned,
                                     @Valid UserWorkout userWorkout,
                                     BindingResult result, Model model) {
        if (result.hasErrors()) {
            userWorkout.setId();
            return "index";
        }

//yang ini pun tak sure
       User user= userRepository.findStaffById((int) id1);
        Workout workout = workoutRepository.findWorkoutById(proj.getWorkoutId());

       UserWorkout userWorkout1= new UserWorkout(user,workout,date,weight,duration,caloriesBurned);
        userWorkoutRepository.save(userWorkout1);

        return "index";
    }
                           
    @GetMapping("display")
    public String displayUserWorkout(Model model) {
        model.addAttribute("users",userRepository.findAll());
        return "choose-workout-to-display";
    }

    @GetMapping("display/{userid}")
    public String showDisplayWorkoutForm(@PathVariable("userid") long id, Model model) {
        List<UserWorkout> userWorkout = (List<UserWorkout>) userWorkoutRepository.findUserWorkoutByUserId((int) id);
        // .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user",userRepository.findUseById((int)id));
        model.addAttribute("userWorkout", userWorkout);
        return "display-workout";
    }

}
