package io.byteworks.bwgalaxybackend.repository;

import io.byteworks.bwgalaxybackend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by Yasholma on 27-Feb-20.
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Fetch A Single Task
    Optional<Task> findById(Long taskId);

    // Fetch User Tasks
    List<Task> findByUserId(@Param("userId") Long userId);

    // Fetch Single Task for a user
    Optional<Task> findTaskByIdAndUserId(Long user_id, Long taskId);

    // Assign Task to User by Administrator
    @Transactional
    @Modifying
    @Query(value = "UPDATE Tasks t SET t.user_id = :userId WHERE t.id = :taskId", nativeQuery = true)
    void assignTaskToUser(@Param("userId") Long userId, @Param("taskId") Long taskId);

    // User update status of task after completion
    @Transactional
    @Modifying
    @Query(value = "UPDATE Task t SET t.status = 1 WHERE t.id = :id")
    int updateTaskByMember(@Param("id") Long id);

    // Administrator all pending tasks
    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM Tasks t WHERE t.status = 0", nativeQuery = true)
    List<Task> getAllUnCompletedTasks();

    // Administrator all completed tasks
    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM Tasks t WHERE t.status = 1", nativeQuery = true)
    List<Task> getAllCompletedTasks();

    // Check if Task Already Assigned
    @Query(value = "SELECT user_id FROM Tasks WHERE id = :taskId", nativeQuery = true)
    Integer checkIfAvailable(@Param("taskId") Long taskId);

    default boolean check(Long taskId) throws NullPointerException {
        String res = String.valueOf(checkIfAvailable(taskId));
        return !res.equals("null");
    }
}