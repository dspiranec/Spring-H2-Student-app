package hr.tvz.spiranec.studapp.course;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("courses")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseDTO> getAllCourses(){
        return courseService.findAll();
    }

    @GetMapping("/courseByJmbag/{jmbag}")
    public List<CourseDTO> getCourseByJmbag(@PathVariable String jmbag){
        return courseService.findCourseByJmbag(jmbag);
    }
}
