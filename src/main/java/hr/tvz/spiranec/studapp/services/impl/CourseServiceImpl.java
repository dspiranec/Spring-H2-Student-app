package hr.tvz.spiranec.studapp.services.impl;


import hr.tvz.spiranec.studapp.dto.CourseDTO;
import hr.tvz.spiranec.studapp.entities.Course;
import hr.tvz.spiranec.studapp.repositories.CourseJpaRepository;
import hr.tvz.spiranec.studapp.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseJpaRepository courseRepository;

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
