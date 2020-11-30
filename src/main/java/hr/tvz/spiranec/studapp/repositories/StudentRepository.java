package hr.tvz.spiranec.studapp.student;
import java.util.List;
import java.util.Optional;


public interface StudentRepository {
    List<Student> findAll();
    Optional<Student> findStudentByJmbag(String jmbag);
    Optional<Student> addStudent(StudentCommand command);
    boolean deleteByJmbag(String jmbag);
}
