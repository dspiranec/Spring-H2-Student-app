package hr.tvz.spiranec.studapp.controllers;

import hr.tvz.spiranec.studapp.dto.CourseDTO;
import hr.tvz.spiranec.studapp.services.CourseService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("courses")
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public List<CourseDTO> getAllCourses(){
        return courseService.findAll();
    }

    @GetMapping("/courseByJmbag/{jmbag}")
    public List<CourseDTO> getCourseByJmbag(@PathVariable String jmbag){
        return courseService.findCourseByJmbag(jmbag);
    }
}
