package fr.m2i.cda.ecfspring.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@Transactional
public class ProjectControllerTest {

    @Autowired
    private MockMvc mvc;


    @Test
    void testGetProjectFound() throws Exception {
        mvc.perform(get("/api/projects/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.title").value("SuperFin"));
    }

    @Test
    void testGetProjectNotFound() throws Exception {
        mvc.perform(get("/api/projects/999"))
        .andExpect(status().isNotFound());
    }

    @Test
    void testSearchProjectsByThemeAndBudget() throws Exception {
    mvc.perform(get("/api/projects")
            .param("theme", "Data")
            .param("budgetMin", "20000")
            .param("budgetMax", "25000"))
       .andExpect(status().isOk())
       .andExpect(jsonPath("$[0].theme").value("Data"))
       .andExpect(jsonPath("$[0].budget").value(22000.0));
}

}
