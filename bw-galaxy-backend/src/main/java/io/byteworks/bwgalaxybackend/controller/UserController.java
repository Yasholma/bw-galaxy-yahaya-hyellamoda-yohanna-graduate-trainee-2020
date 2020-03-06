package io.byteworks.bwgalaxybackend.controller;

import io.byteworks.bwgalaxybackend.exception.ResourceNotFoundException;
import io.byteworks.bwgalaxybackend.model.Task;
import io.byteworks.bwgalaxybackend.model.User;
import io.byteworks.bwgalaxybackend.payload.UserIdentityAvailability;
import io.byteworks.bwgalaxybackend.payload.UserSummary;
import io.byteworks.bwgalaxybackend.repository.TaskRepository;
import io.byteworks.bwgalaxybackend.repository.UserRepository;
import io.byteworks.bwgalaxybackend.security.CurrentUser;
import io.byteworks.bwgalaxybackend.security.UserPrincipal;
import io.byteworks.bwgalaxybackend.util.ApiResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ApiResponse<User> getUser(@PathVariable("userId") Long userId) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User Info", userRepository.findUserInfoById(userId));
    }

    @GetMapping("/user/me")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName(), currentUser.getAuthorities().stream().map(Object::toString).collect(Collectors.joining("")));
    }

    @GetMapping("/users/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<User>> fetchAllUsers() {
        return new ApiResponse<>(HttpStatus.OK.value(), "All Users", userRepository.findAll());
    }

    // Assign Tasks to Users
    @PutMapping("/assign/{userId}/{taskId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Task> assignTask(@PathVariable("userId") Long userId, @PathVariable("taskId") Long taskId) {
        if (!taskRepository.existsById(taskId)) throw new ResourceNotFoundException("Task", "", "");

        if (check(taskId)) {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Task has already been assigned to another user.", null);
        }

        return taskRepository.findById(taskId).map(task -> {
            taskRepository.assignTaskToUser(userId, taskId);
            return new ApiResponse<Task>(HttpStatus.OK.value(), "Task has been successfully assigned to user.", null);
        }).orElseThrow(() -> new ResourceNotFoundException("Error assigning task to user.", "", ""));
    }

    // Get task assigned to a user
    @GetMapping("/task/{userId}/{taskId}")
    @PreAuthorize("hasRole('USER')")
    public ApiResponse<Task> getUserTask(@PathVariable(value = "userId") Long userId, @PathVariable("taskId") Long taskId) {
        Optional<Task> maybeTask = taskRepository.findTaskByIdAndUserId(taskId, userId);
        if (!maybeTask.isPresent()) return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "No data", null);

        return new ApiResponse<>(HttpStatus.OK.value(), "Single Task", taskRepository.findTaskByIdAndUserId(taskId, userId));
    }

    // Get all tasks assigned to a user
    @GetMapping("/tasks/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<Task> getUserTasks(@PathVariable(value = "userId") Long userId) {
        return taskRepository.findByUserId(userId);
    }

    // Checking if task has already been assigned to another user
    private boolean check(Long taskId) {
        return taskRepository.check(taskId);
    }

    // Checking username availability helper method
    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }
}