package hr.tvz.spiranec.studapp.student;

import java.util.List;
import java.util.Optional;


public interface StudentService {
    List<StudentDTO> findAll();
    Optional<StudentDTO> findStudentByJmbag(String jmbag);
    Optional<StudentDTO> addStudent(StudentCommand command);
    boolean deleteByJmbag(String jmbag);
}