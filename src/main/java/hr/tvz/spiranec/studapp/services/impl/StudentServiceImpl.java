package hr.tvz.spiranec.studapp.services.impl;

import hr.tvz.spiranec.studapp.commands.StudentCommand;
import hr.tvz.spiranec.studapp.dto.StudentDTO;
import hr.tvz.spiranec.studapp.entities.Student;
import hr.tvz.spiranec.studapp.repositories.StudentRepository;
import hr.tvz.spiranec.studapp.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream().map(this::mapStudentToDTO).collect(Collectors.toList());
    }

    @Override
    public StudentDTO findByJmbag(final String jmbag) {
        return mapStudentToDTO(studentRepository.findByJmbag(jmbag));
    }

    @Override
    public StudentDTO addStudent(final StudentCommand command) {
        return mapStudentToDTO(studentRepository.save(command));
    }

    @Override
    public boolean deleteByJmbag(final String jmbag) {
        return studentRepository.deleteByJmbag(jmbag);
    }

    private StudentDTO mapStudentToDTO(final Student student){
        return new StudentDTO(student.getFirstName(), student.getLastName(), student.getJmbag(), student.getNumberOfEcts(), shouldTuitionBePayed(student.getDateOfBirth()));
    }

    private boolean shouldTuitionBePayed(final LocalDate dateOfBirth){
        int YEAR_AFTER_WHICH_TUITION_SHOULD_BE_PAYED = 26;
        return dateOfBirth.plusYears(YEAR_AFTER_WHICH_TUITION_SHOULD_BE_PAYED).isBefore(LocalDate.now());
    }

}
