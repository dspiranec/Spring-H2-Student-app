package hr.tvz.spiranec.studapp.student;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Primary
@Repository
public interface StudentJpaRepository extends JpaRepository<Student, String> {

    List<Student> findAll();

    Optional<Student> findStudentByJmbag(String jmbag);

}