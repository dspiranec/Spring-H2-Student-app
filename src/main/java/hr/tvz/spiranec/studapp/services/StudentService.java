package hr.tvz.spiranec.studapp.services;

import hr.tvz.spiranec.studapp.commands.StudentCommand;
import hr.tvz.spiranec.studapp.dto.StudentDTO;

import java.util.List;


public interface StudentService {
    List<StudentDTO> findAll();
    StudentDTO findByJmbag(String jmbag);
    StudentDTO addStudent(StudentCommand command);
    boolean deleteByJmbag(String jmbag);
}