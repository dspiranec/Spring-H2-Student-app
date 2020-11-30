package hr.tvz.spiranec.studapp.controllers;

import hr.tvz.spiranec.studapp.commands.StudentCommand;
import hr.tvz.spiranec.studapp.dto.StudentDTO;
import hr.tvz.spiranec.studapp.exceptions.NotFoundException;
import hr.tvz.spiranec.studapp.services.StudentService;
import hr.tvz.spiranec.studapp.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("students")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<StudentDTO> getAllStudents(){
        return studentService.findAll();
    }


    @GetMapping("/StudentByJmbag/{jmbag}")
    public ResponseEntity<ApiResponse> getStudentByJmbag(@PathVariable String jmbag){
        try {
            return new ResponseEntity<>(new ApiResponse(studentService.findByJmbag(jmbag)), HttpStatus.OK);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<ApiResponse> save(@Valid @RequestBody StudentCommand command){
        try {
            return new ResponseEntity<>(new ApiResponse(studentService.addStudent(command)), HttpStatus.CREATED);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{jmbag}")
    public ResponseEntity delete(@PathVariable String jmbag){
        boolean ifDone = studentService.deleteByJmbag(jmbag);

        if(ifDone){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
