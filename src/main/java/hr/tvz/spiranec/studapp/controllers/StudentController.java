package hr.tvz.spiranec.studapp.student;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("students")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> getAllStudents(){
        return studentService.findAll();
    }

    @GetMapping("/StudentByJmbag/{jmbag}")
    public ResponseEntity<StudentDTO> getStudentByJmbag(@PathVariable String jmbag){
        return studentService.findStudentByJmbag(jmbag)
                .map(
                        studentDTO -> ResponseEntity
                                .status(HttpStatus.OK)
                                .body(studentDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.NOT_FOUND)
                                .build()
                );
    }


    @PostMapping
    public ResponseEntity<StudentDTO> save(@Valid @RequestBody StudentCommand command){
        return studentService.addStudent(command)
                .map(
                        studentDTO -> ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(studentDTO)
                )
                .orElseGet(
                        () -> ResponseEntity
                                .status(HttpStatus.CONFLICT)
                                .build()
                );
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
