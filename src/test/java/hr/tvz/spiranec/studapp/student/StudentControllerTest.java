package hr.tvz.spiranec.studapp.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetAllStudentsAndReturnOk() throws Exception {

        this.mockMvc
                .perform(get("/students")
                    .with(user("admin")
                        .password("admin")
                        .roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldFailToGetStudentByJmbagAndReturnNotFound() throws Exception {

        this.mockMvc
                .perform(get("/students/StudentByJmbag/1234567899")
                        .with(user("admin")
                                .password("admin")
                                .roles("ADMIN")))
                .andExpect(status().isNotFound());

    }

    @Test
    void shouldSaveStudentAndReturnCreated() throws Exception {

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
                .content(studentCommandJson)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DirtiesContext
    void shouldDeleteStudentAndReturnOk() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/students/1234567890")
                .with(user("admin")
                        .password("admin")
                        .roles("ADMIN")))
                .andExpect(status().isOk());

    }

    @Test
    @DirtiesContext
    void shouldDeleteStudentAndReturnNotFound() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/students/1111111111")
                .with(user("admin")
                        .password("admin")
                        .roles("ADMIN")))
                .andExpect(status().isNotFound());
    }
}
