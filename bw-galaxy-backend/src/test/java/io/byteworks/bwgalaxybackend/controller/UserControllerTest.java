package io.byteworks.bwgalaxybackend.controller;

import io.byteworks.bwgalaxybackend.exception.AppException;
import io.byteworks.bwgalaxybackend.model.Role;
import io.byteworks.bwgalaxybackend.model.RoleName;
import io.byteworks.bwgalaxybackend.model.Task;
import io.byteworks.bwgalaxybackend.model.User;
import io.byteworks.bwgalaxybackend.repository.RoleRepository;
import io.byteworks.bwgalaxybackend.repository.TaskRepository;
import io.byteworks.bwgalaxybackend.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskRepository taskRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @Before
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void fetchAllUsers() throws Exception {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Mike Jones");
        user1.setUsername("mikejones");
        user1.setEmail("mikejones@bw.com");
        user1.setPassword("password");

        User user2 = new User();
        user2.setId(1L);
        user2.setName("Mike Jones");
        user2.setUsername("mikejones");
        user2.setEmail("mikejones@bw.com");
        user2.setPassword("password");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/api/users/all")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void assignTask() throws Exception {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Mike Jones");
        user1.setUsername("mikejones");
        user1.setEmail("mikejones@bw.com");
        user1.setPassword("password");

        String str = "2009-12-02T11:25:25";
        LocalDateTime dateTime = LocalDateTime.parse(str);
        Task newTask = new Task();
        newTask.setId(1L);
        newTask.setDescription("Bla bla bla bla bla bla bla");
        newTask.setDetails("Bla bla bla bla bla bla blaBla bla bla bla bla bla blaBla bla bla bla bla bla bla");
        newTask.setDeadline(dateTime);
        newTask.setStatus(false);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(newTask));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));

        mockMvc.perform(put("/api/assign/{uid}/{tid}", 1L, 1L)).andExpect(status().isNotFound());

    }

//    @WithMockUser(roles = {"ADMIN"})
    @Test
    @WithMockUser
    void getUserTask() throws Exception {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Mike Jones");
        user1.setUsername("mikejones");
        user1.setEmail("mikejones@bw.com");
        user1.setPassword("password");

        String str = "2009-12-02T11:25:25";
        LocalDateTime dateTime = LocalDateTime.parse(str);
        Task newTask = new Task();
        newTask.setId(1L);
        newTask.setDescription("Bla bla bla bla bla bla bla");
        newTask.setDetails("Bla bla bla bla bla bla blaBla bla bla bla bla bla blaBla bla bla bla bla bla bla");
        newTask.setDeadline(dateTime);
        newTask.setStatus(false);
        newTask.setUser(user1);

        when(taskRepository.findTaskByIdAndUserId(1L, 1L)).thenReturn(Optional.of(newTask));

        mockMvc.perform(get("/api/task/1/1")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getUserTasks() throws Exception {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Mike Jones");
        user1.setUsername("mikejones");
        user1.setEmail("mikejones@bw.com");
        user1.setPassword("password");

        String str = "2009-12-02T11:25:25";
        LocalDateTime dateTime = LocalDateTime.parse(str);
        Task newTask = new Task();
        newTask.setId(1L);
        newTask.setDescription("Bla bla bla bla bla bla bla");
        newTask.setDetails("Bla bla bla bla bla bla blaBla bla bla bla bla bla blaBla bla bla bla bla bla bla");
        newTask.setDeadline(dateTime);
        newTask.setStatus(false);
        newTask.setUser(user1);

        when(taskRepository.findByUserId(1L)).thenReturn(Arrays.asList(newTask));

        mockMvc.perform(get("/api/tasks/1")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}