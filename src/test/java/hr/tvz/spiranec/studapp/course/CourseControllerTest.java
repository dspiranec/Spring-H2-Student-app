package hr.tvz.spiranec.studapp.course;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import static org.springframework.security.test.web.servlet.request.
        SecurityMockMvcRequestPostProcessors.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

/*
        CourseJpaRepository courseJpaRepository;


        @Test
        void findAll() {
            List<Course> courses = courseJpaRepository.findAll();
            assertNotNull(courses);
        }

        @Test
        void findByStudents_jmbag() {
            List<Course> courses = courseJpaRepository.findByStudents_jmbag("1444576890");
            assertNull(courses);
        }
*/

    @Test
    void getAllCourses() throws Exception {
        this.mockMvc
                .perform(get("/courses")
                .with(user("admin")
                    .password("admin")
                    .roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getCourseByJmbag() throws Exception {
        //ok
        this.mockMvc
                .perform(get("/courses/courseByJmbag/1234567890")
                        .with(user("admin")
                                .password("admin")
                                .roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        /*
        //notFound
        this.mockMvc
                .perform(get("/courses/courseByJmbag/1234561111")
                        .with(user("admin")
                                .password("admin")
                                .roles("ADMIN")))
                .andExpect(status().isNotFound());
        */
    }


}









