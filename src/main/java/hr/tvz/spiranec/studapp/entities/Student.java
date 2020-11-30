package hr.tvz.spiranec.studapp.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student {

    @Id
    private Integer id;

    private String jmbag;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private Integer numberOfEcts;

    @ManyToMany(mappedBy = "students")
    private List<Course> courses = new ArrayList<>();

}
