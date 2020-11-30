package hr.tvz.spiranec.studapp.student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private StudentJpaRepository studentJpaRepository;


    public StudentServiceImpl(StudentRepository studentRepository, StudentJpaRepository studentJpaRepository) {
        this.studentRepository = studentRepository;
        this.studentJpaRepository = studentJpaRepository;
    }

    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream().map(this::mapStudentToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<StudentDTO> findStudentByJmbag(String jmbag) {
        return studentJpaRepository.findStudentByJmbag(jmbag).map(this::mapStudentToDTO);
    }

    @Override
    public Optional<StudentDTO> addStudent(StudentCommand command) {
        return studentRepository.addStudent(command).map(this::mapStudentToDTO);
    }

    @Override
    public boolean deleteByJmbag(String jmbag) {
        return studentRepository.deleteByJmbag(jmbag);
    }

    private StudentDTO mapStudentToDTO(Student student){
        return new StudentDTO(student.getFirstName(), student.getLastName(), student.getJmbag(), student.getNumberOfEcts(), shouldTuitionBePayed(student.getDateOfBirth()));
    }

    private boolean shouldTuitionBePayed(LocalDate dateOfBirth){
        int YEAR_AFTER_WHICH_TUITION_SHOULD_BE_PAYED = 26;
        return dateOfBirth.plusYears(YEAR_AFTER_WHICH_TUITION_SHOULD_BE_PAYED).isBefore(LocalDate.now());
    }

}
