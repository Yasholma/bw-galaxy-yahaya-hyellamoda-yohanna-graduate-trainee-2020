package io.byteworks.bwgalaxybackend.controller;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Before
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser
    void existentUserCanGetTokenAndAuthentication() throws Exception {
        String body = "{\"usernameOrEmail\":\"superadmin\"," + "\"password\":\"admin@12\"}";

        mockMvc.perform(post("/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void nonexistentUserCannotGetToken() throws Exception {
        String body = "{\"usernameOrEmail\":\"\"," + "\"password\":\"\"}";

        mockMvc.perform(post("/api/auth/signin").contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void onlyAdminCanRegister() throws Exception {
        String body = "{\"name\":\"Steve Bruce\"," + "\"username\":\"stevebruce\"," + "\"email\":\"stevebruce@bw.com\"," + "\"password\":\"user@12\"}";
        mockMvc.perform(post("/api/auth/register").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isCreated()).andExpect(content().json("{\"success\":true,\"message\":\"User registered successfully.\"}"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void usernameAlreadyExist() throws Exception {
        String body = "{\"name\":\"Sarah Lance\"," + "\"username\":\"sarahlance\"," + "\"email\":\"sarahlance@bw.com\"," + "\"password\":\"user@12\"}";
        mockMvc.perform(post("/api/auth/register").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isBadRequest()).andExpect(content().json("{\"success\":false,\"message\":\"Username is already taken\"}"));
    }
}