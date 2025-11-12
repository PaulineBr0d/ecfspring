package fr.m2i.cda.ecfspring.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import jakarta.transaction.Transactional;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@Transactional
public class DeveloperControllerTest {
  

    @Autowired
    private MockMvc mvc;

    @Test
    void testGetProfile_shouldReturnDeveloperFromDatabase() throws Exception {
        // Ici on sait que developer 1 existe (Jean Dupont)
        mvc.perform(get("/api/developer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jean Dupont"))
                .andExpect(jsonPath("$.technologies").value("Java, Spring"));
    }

    @Test
    void testUpdateProfile() throws Exception {
        String json = """
            {
                "name": "Jean Dupont Updated",
                "description": "Backend Dev Updated",
                "technologies": "Java, Spring Boot",
                "experience": 6
            }
        """;

        mvc.perform(put("/api/developer/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Jean Dupont Updated"))
        .andExpect(jsonPath("$.experience").value(6));
    }

    @Test
    void testListApplications() throws Exception {
        mvc.perform(get("/api/developer/1/applications"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].status").value("Pending"));
    }

    @Test
    void testApplyToProject() throws Exception {
        mvc.perform(post("/api/developer/1/apply/2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.developerId").value(1))
        .andExpect(jsonPath("$.projectId").value(2))
        .andExpect(jsonPath("$.status").value("Pending"));
    }

    @Test
    void testValidateApplication() throws Exception {
        mvc.perform(post("/api/developer/5/applications/5/validate"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("Validated")); 
    }    

    @Test
    void testDeleteProfile() throws Exception {
        mvc.perform(delete("/api/developer/1"))
        .andExpect(status().isNoContent());

        mvc.perform(get("/api/developer/1"))
        .andExpect(status().is4xxClientError()); 
    }
}

