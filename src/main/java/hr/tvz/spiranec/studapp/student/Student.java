package hr.tvz.spiranec.studapp.student;

import hr.tvz.spiranec.studapp.course.Course;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
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


    public Student(String jmbag, String firstName, String lastName, LocalDate dateOfBirth, Integer numberOfEcts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.jmbag = jmbag;
        this.numberOfEcts = numberOfEcts;
    }

    public Student() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getJmbag() {
        return jmbag;
    }

    public void setJmbag(String jmbag) {
        this.jmbag = jmbag;
    }

    public Integer getNumberOfEcts() {
        return numberOfEcts;
    }

    public void setNumberOfEcts(Integer numberOfEcts) {
        this.numberOfEcts = numberOfEcts;
    }
}
