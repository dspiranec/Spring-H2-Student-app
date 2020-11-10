package hr.tvz.spiranec.studapp.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.security.test.web.servlet.request.
        SecurityMockMvcRequestPostProcessors.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Test
    void getAllStudents() throws Exception {
        this.mockMvc
                .perform(get("/students")
                    .with(user("admin")
                        .password("admin")
                        .roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getStudentByJmbag() throws Exception {

/*
        //ok
        this.mockMvc
                .perform(get("/students/StudentByJmbag/1234567892")
                        .with(user("admin")
                                .password("admin")
                                .roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        */

        //notFound
        this.mockMvc
                .perform(get("/students/StudentByJmbag/1234567899")
                        .with(user("admin")
                                .password("admin")
                                .roles("ADMIN")))
                .andExpect(status().isNotFound());

    }

    @Test
    void save() throws Exception {
        /*
        StudentCommand studentCommand = new StudentCommand("Ivo", "Ivic", "1234567895",
                                        LocalDate.of(1998, 3, 10), 110);

        studentCommand.setFirstName("Ivo");
        studentCommand.setLastName("Ivic");
        studentCommand.setJmbag("1234567894");
        studentCommand.setDateOfBirth(LocalDate.of(1998, 3, 10));
        studentCommand.setNumberOfECTS(210);
        */
        String studentCommandJson = "{\n"+
                "   \"firstName\": \"Ivo\",\n" +
                "   \"lastName\": \"Ivic\",\n" +
                "   \"jmbag\": \"1234567896\",\n" +
                "   \"dateOfBirth\": \"03.03.2000.\",\n" +
                "   \"numberOfEcts\": \"12\"\n" +
                "}";
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/students")
                        .with(user("admin")
                        .password("admin")
                        .roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON)
                        //.with(csrf())
                .content(studentCommandJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    //@DirtiesContext ERROR - Ne prolazi nista poslije delete()
    void delete() throws Exception {
        //ok
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/students/1234567890")
                .with(user("admin")
                        .password("admin")
                        .roles("ADMIN")))
                .andExpect(status().isOk());

        //notFound
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/students/1234561111")
                .with(user("admin")
                        .password("admin")
                        .roles("ADMIN")))
                .andExpect(status().isNotFound());
    }
}
