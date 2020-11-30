package hr.tvz.spiranec.studapp.course;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseJpaRepository courseRepository;

    public CourseServiceImpl(CourseJpaRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseDTO> findAll() {
        return courseRepository.findAll().stream().map(this::mapCourseToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> findCourseByJmbag(String jmbag) {
        return courseRepository.findByStudents_jmbag(jmbag).stream().map(this::mapCourseToDTO).collect(Collectors.toList());
    }

    private CourseDTO mapCourseToDTO(Course course){
        return new CourseDTO(course.getName(), course.getNumberOfEcts());
    }
}
