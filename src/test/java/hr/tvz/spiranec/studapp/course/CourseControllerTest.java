package hr.tvz.spiranec.studapp.course;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void ShouldGetAllCoursesAndReturnOk() throws Exception {

        this.mockMvc
                .perform(get("/courses")
                .with(user("admin")
                    .password("admin")
                    .roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void ShouldGetCourseByJmbagAndReturnOk() throws Exception {

        this.mockMvc
                .perform(get("/courses/courseByJmbag/1234567890")
                        .with(user("admin")
                                .password("admin")
                                .roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void ShouldGetCourseByJmbagAndReturnNotFound() throws Exception {

        this.mockMvc
                .perform(get("/courses/courseByJmbag/1234561111")
                        .with(user("admin")
                                .password("admin")
                                .roles("ADMIN")))
                .andExpect(status().isNotFound());

    }


}









