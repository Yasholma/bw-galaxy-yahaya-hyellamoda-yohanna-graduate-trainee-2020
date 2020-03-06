package io.byteworks.bwgalaxybackend.controller;

import io.byteworks.bwgalaxybackend.exception.ResourceNotFoundException;
import io.byteworks.bwgalaxybackend.model.Task;
import io.byteworks.bwgalaxybackend.repository.TaskRepository;
import io.byteworks.bwgalaxybackend.repository.UserRepository;
import io.byteworks.bwgalaxybackend.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Yasholma on 27-Feb-20.
 */
@RestController
@RequestMapping("/api/task/")
public class TaskController {


    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    // Administrator gets all task
    @GetMapping("all")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<Task>> getTasks() {
        return new ApiResponse<>(HttpStatus.OK.value(), "All Tasks", taskRepository.findAll(Sort.by(Sort.Direction.DESC, "id")));
    }

    // Administrator get completed tasks
    @GetMapping("completed")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<Task>> getCompletedTasks() {
        return new ApiResponse<>(HttpStatus.OK.value(), "All Completed Tasks", taskRepository.getAllCompletedTasks());
    }

    // Administrator gets pending task
    @GetMapping("pending")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<Task>> getUnCompletedTasks() {
        return new ApiResponse<>(HttpStatus.OK.value(), "All Pending Tasks", taskRepository.getAllUnCompletedTasks());
    }

    // Administrator get single task
    @GetMapping("{taskId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ApiResponse<Task> getTask(@PathVariable("taskId") Long id) {
        Optional<Task> maybeTask = taskRepository.findById(id);
        if (!maybeTask.isPresent()) return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "No data", null);

        return new ApiResponse<>(HttpStatus.OK.value(), "Single Task", taskRepository.findById(id));
    }

    // Administrator create task
    @PostMapping("create")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Task> createTask(@Valid @RequestBody Task task, BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error: result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Error saving task", errors);
        }

        task.setStatus(false);
        return new ApiResponse<>(HttpStatus.OK.value(), "Task created successfully.", taskRepository.save(task));
    }

    // Administrator update tasks
    @PutMapping("{taskId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<?> updateTask(@PathVariable("taskId") Long taskId, @Valid @RequestBody Task updated) {
        return taskRepository.findById(taskId).map(task -> {
            task.setDescription(updated.getDescription());
            task.setDetails(updated.getDetails());
            task.setDeadline(updated.getDeadline());
            task.setStatus(updated.isStatus());
            return new ApiResponse(HttpStatus.OK.value(), "Task updated successfully.", taskRepository.save(task));
        }).orElseThrow(() -> new ResourceNotFoundException("Task", taskId.toString(), null));
    }

    // Get User ID for task if assigned
    @GetMapping("/user/{taskId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Void> getUserIdForTask(@PathVariable("taskId") Long taskId) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User Id Fetched", taskRepository.checkIfAvailable(taskId));
    }

    // Administrator deletes task
    @DeleteMapping("{taskId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<Task> deleteTask(@PathVariable("taskId") Long id) {
        Optional<Task> maybeTask = taskRepository.findById(id);
        if (maybeTask.isPresent()) {
            taskRepository.delete(maybeTask.get());
            return new ApiResponse<>(HttpStatus.OK.value(), "Task delete.", null);
        }
        return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "An error occurred", null);
    }

    // User Completes Task by updating task status
    @PutMapping("complete/{taskId}")
    @PreAuthorize("hasRole('USER')")
    public ApiResponse<Task> updateTaskStatusByMember( @PathVariable("taskId") Long taskId) {
        int res = taskRepository.findById(taskId).map(task -> taskRepository.updateTaskByMember(task.getId())).orElseThrow(() -> new ResourceNotFoundException("Task", "", ""));
        if (res == 1) {
            return new ApiResponse<>(HttpStatus.OK.value(), "Successfully updated", taskRepository.findById(taskId));
        }
        return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Server error", null);
    }
}