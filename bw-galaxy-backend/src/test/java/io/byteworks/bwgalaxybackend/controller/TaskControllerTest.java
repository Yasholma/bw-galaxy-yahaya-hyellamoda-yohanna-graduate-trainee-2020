package io.byteworks.bwgalaxybackend.controller;


import io.byteworks.bwgalaxybackend.model.Task;
import io.byteworks.bwgalaxybackend.model.User;
import io.byteworks.bwgalaxybackend.repository.TaskRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskRepository taskRepository;

    @Before
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getTasks() throws Exception {
        String str = "2009-12-02T11:25:25";
        LocalDateTime dateTime = LocalDateTime.parse(str);

        Task t1 = new Task();
        t1.setId(1L);
        t1.setDescription("Lorem ipsum dolor sit amet.");
        t1.setDetails("Lorem ipsum dolor sit amet, consectetur");
        t1.setDeadline(dateTime);
        t1.setStatus(false);

        Task t2 = new Task();
        t2.setId(2L);
        t2.setDescription("Lorem ipsum dolor sit amet.");
        t2.setDetails("Lorem ipsum dolor sit amet, consectetur");
        t2.setDeadline(dateTime);
        t2.setStatus(false);

        when(taskRepository.findAll()).thenReturn(Arrays.asList(t1, t2));
        List<Task> tasks = taskRepository.findAll();
        assertThat(tasks.size()).isEqualTo(2);
        assertThat(tasks.get(0).getDescription()).isEqualTo("Lorem ipsum dolor sit amet.");

        mockMvc.perform(get("/api/task/all")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void getTask() throws Exception {
        String str = "2009-12-02T11:25:25";
        LocalDateTime dateTime = LocalDateTime.parse(str);

        Task t = new Task();
        t.setId(1L);
        t.setDescription("Lorem ipsum dolor sit amet.");
        t.setDetails("Lorem ipsum dolor sit amet, consectetur");
        t.setDeadline(dateTime);
        t.setStatus(false);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(t));

        mockMvc.perform(get("/api/task/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"status\":200,\"message\":\"Single Task\",\"result\":{\"createdAt\":null,\"updatedAt\":null,\"id\":1,\"description\":\"Lorem ipsum dolor sit amet.\",\"details\":\"Lorem ipsum dolor sit amet, consectetur\",\"deadline\":\"2009-12-02 11:25:25\",\"status\":false}}"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getCompletedTasks() throws Exception {
        String str = "2009-12-02T11:25:25";
        LocalDateTime dateTime = LocalDateTime.parse(str);

        Task t1 = new Task();
        t1.setId(1L);
        t1.setDescription("Lorem ipsum dolor sit amet.");
        t1.setDetails("Lorem ipsum dolor sit amet, consectetur");
        t1.setDeadline(dateTime);
        t1.setStatus(true);

        Task t2 = new Task();
        t2.setId(2L);
        t2.setDescription("Lorem ipsum dolor sit amet.");
        t2.setDetails("Lorem ipsum dolor sit amet, consectetur");
        t2.setDeadline(dateTime);
        t2.setStatus(false);

        when(taskRepository.getAllCompletedTasks()).thenReturn(Collections.singletonList(t1));
        List<Task> tasks = taskRepository.getAllCompletedTasks();

        assertThat(tasks.size()).isEqualTo(1);
        mockMvc.perform(get("/api/task/completed")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getUnCompletedTasks() throws Exception {
        String str = "2009-12-02T11:25:25";
        LocalDateTime dateTime = LocalDateTime.parse(str);

        Task t1 = new Task();
        t1.setId(1L);
        t1.setDescription("Lorem ipsum dolor sit amet.");
        t1.setDetails("Lorem ipsum dolor sit amet, consectetur");
        t1.setDeadline(dateTime);
        t1.setStatus(false);

        Task t2 = new Task();
        t2.setId(2L);
        t2.setDescription("Lorem ipsum dolor sit amet.");
        t2.setDetails("Lorem ipsum dolor sit amet, consectetur");
        t2.setDeadline(dateTime);
        t2.setStatus(false);

        when(taskRepository.getAllUnCompletedTasks()).thenReturn(Arrays.asList(t1, t2));
        List<Task> tasks = taskRepository.getAllUnCompletedTasks();

        assertThat(tasks.size()).isEqualTo(2);
        mockMvc.perform(get("/api/task/pending")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void createTask() throws Exception {
        String str = "2009-12-02T11:25:25";
        LocalDateTime dateTime = LocalDateTime.parse(str);
        Task newTask = new Task();
        newTask.setId(1L);
        newTask.setDescription("Lorem ipsum dolor sit amet.");
        newTask.setDetails("Lorem ipsum dolor sit amet, consectetur");
        newTask.setDeadline(dateTime);
        newTask.setStatus(false);

        when(taskRepository.save(any(Task.class))).thenReturn(newTask);

        String body = "{\"id\":1,\"description\":\"Lorem ipsum dolor sit amet.\",\"details\":\"Lorem ipsum dolor sit amet, consectetur\",\"deadline\":\"2009-12-02 11:25:25\",\"status\":0}";

        mockMvc.perform(post("/api/task/create").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void updateTask() throws Exception {
        String str = "2009-12-02T11:25:25";
        LocalDateTime dateTime = LocalDateTime.parse(str);
        Task toUpdate = new Task();
        toUpdate.setId(1L);
        toUpdate.setDescription("Lorem ipsum dolor sit amet");
        toUpdate.setDetails("Lorem ipsum dolor sit amet, consectetur");
        toUpdate.setDeadline(dateTime);
        toUpdate.setStatus(false);

        when(taskRepository.save(any(Task.class))).thenReturn(toUpdate);
        when(taskRepository.findById(1L)).thenReturn(Optional.of(toUpdate));

        String body = "{\"id\":1,\"description\":\"Lorem ipsum dolor sit amet to be updated\",\"details\":\"Lorem ipsum dolor sit amet, consectetur to be updated\",\"deadline\":\"2009-12-02 11:25:25\"}";

        mockMvc.perform(put("/api/task/{id}", 1).contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void deleteTask() throws Exception {
        mockMvc.perform(delete("/api/task/{id}", 1)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void updateTaskStatusByMember() throws Exception {
        String str = "2009-12-02T11:25:25";
        LocalDateTime dateTime = LocalDateTime.parse(str);
        Task toComplete = new Task();
        toComplete.setId(2L);
        toComplete.setDescription("Lorem ipsum dolor sit amet");
        toComplete.setDetails("Lorem ipsum dolor sit amet, consectetur");
        toComplete.setDeadline(dateTime);
        toComplete.setStatus(false);

        when(taskRepository.findById(2L)).thenReturn(Optional.of(toComplete));

        String body = "{\"status\": 1}";
        mockMvc.perform(put("/api/task/complete/{id}", 2).content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getUserIdForTask() throws Exception {
        String str = "2009-12-02T11:25:25";
        LocalDateTime dateTime = LocalDateTime.parse(str);

        User user = new User("John Doe", "johndoe", "johndoe@bw.com", "user@12");
        user.setId(1L);

        Task t1 = new Task();
        t1.setId(1L);
        t1.setDescription("Lorem ipsum dolor sit amet.");
        t1.setDetails("Lorem ipsum dolor sit amet, consectetur");
        t1.setDeadline(dateTime);
        t1.setStatus(false);
        t1.setUser(user);

        Task t2 = new Task();
        t2.setId(2L);
        t2.setDescription("Lorem ipsum dolor sit amet.");
        t2.setDetails("Lorem ipsum dolor sit amet, consectetur");
        t2.setDeadline(dateTime);
        t2.setStatus(false);

        when(taskRepository.checkIfAvailable(1L)).thenReturn(1);
        Integer userId = taskRepository.checkIfAvailable(1L);

        assertThat(userId).isEqualTo(1);
        mockMvc.perform(get("/api/task/user/1")).andExpect(status().isOk());
    }
}