package hr.tvz.spiranec.studapp.repositories;


import hr.tvz.spiranec.studapp.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseJpaRepository extends JpaRepository<Course, Long> {
    List<Course> findAll();
    List<Course> findByStudents_jmbag(String jmbag);
}
