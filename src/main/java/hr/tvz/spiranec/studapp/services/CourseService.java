package hr.tvz.spiranec.studapp.services;

import hr.tvz.spiranec.studapp.dto.CourseDTO;

import java.util.List;


public interface CourseService {
    List<CourseDTO> findAll();
    List<CourseDTO> findCourseByJmbag(String jmbag);
}
