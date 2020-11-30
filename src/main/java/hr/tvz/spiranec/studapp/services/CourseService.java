package hr.tvz.spiranec.studapp.course;

import java.util.List;

public interface CourseService {
    List<CourseDTO> findAll();
    List<CourseDTO> findCourseByJmbag(String jmbag);
}
