package hr.tvz.spiranec.studapp.course;

import hr.tvz.spiranec.studapp.student.Student;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Course {

    @Id
    private Integer id;
    private String name;
    private Integer numberOfEcts;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "student_course",
            joinColumns = { @JoinColumn(name = "student_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    List<Student> students = new ArrayList<>();

    public String getName() {
        return name;
    }

    public Integer getNumberOfEcts() {
        return numberOfEcts;
    }
}
