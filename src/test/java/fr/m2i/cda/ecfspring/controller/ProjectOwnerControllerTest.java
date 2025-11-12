package fr.m2i.cda.ecfspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@Transactional
public class ProjectOwnerControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @Test
    void testListProjects() throws Exception {
        mvc.perform(get("/api/projectowner/1/projects"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$[0].title").value("SuperFin"));
    }
   

    @Test
    void testListApplications() throws Exception {
        mvc.perform(get("/api/projectowner/1/applications"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$[0].status").value("Pending"));
    }

    @Test
    void testAcceptApplication() throws Exception {
        mvc.perform(post("/api/projectowner/1/applications/1/accept"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.status").value("Accepted"));
    }

    @Test
    void testRejectApplication() throws Exception {
        mvc.perform(post("/api/projectowner/1/applications/4/reject"))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.status").value("Rejected"));
    }
    
    @Test
    void testProposeProjectSimple() throws Exception {
        String newProjectJson = """
            {
                "title": "Test Project",
                "description": "Description test",
                "budget": 10000.0,
                "theme": "Test",
                "deliveryDate": "2025-12-31"
            }
            """;

        mvc.perform(post("/api/projectowner/1/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newProjectJson))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.title").value("Test Project"))
        .andExpect(jsonPath("$.budget").value(10000.0));
    }
   
   
}   
