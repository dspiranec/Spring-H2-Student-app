package hr.tvz.spiranec.studapp.repositories;


import hr.tvz.spiranec.studapp.commands.StudentCommand;
import hr.tvz.spiranec.studapp.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findAll();
    Student findByJmbag(String jmbag);
    Student save(StudentCommand command);
    boolean deleteByJmbag(String jmbag);
}
