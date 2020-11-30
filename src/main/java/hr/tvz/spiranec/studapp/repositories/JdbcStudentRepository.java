package hr.tvz.spiranec.studapp.student;


import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Primary
@Repository
public class JdbcStudentRepository implements StudentRepository {
    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert studentInserter;

    public JdbcStudentRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.studentInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("student")
                .usingGeneratedKeyColumns("id");

    }

    @Override
    public List<Student> findAll() {
        String SQL = "SELECT jmbag, first_name, last_name, date_of_birth, number_of_ects FROM student";
        return jdbc.query(SQL, this::mapRowToStudent);

    }

    @Override
    public Optional<Student> findStudentByJmbag(String jmbag) {
        String SQL = "SELECT * FROM student WHERE jmbag = ?";
        return Optional.of(jdbc.queryForObject(SQL, new Object[]{jmbag}, this::mapRowToStudent));
    }

    @Override
    public Optional<Student> addStudent(StudentCommand command) {
        List<Student> students = findAll();
        String SQL;
        if(students.stream().filter(s -> Objects.equals(s.getJmbag(), command.getJmbag())).findFirst().isEmpty()){
             SQL = "INSERT INTO student ( jmbag, first_name, last_name, date_of_birth, number_of_ects ) VALUES (?, ?, ?, ?, ? )";
            jdbc.update(SQL, command.getJmbag(), command.getFirstName(), command.getLastName(), command.getDateOfBirth(), command.getNumberOfEcts());
        }
        else{
            SQL = "UPDATE student SET jmbag = ?, first_name = ?, last_name = ?, date_of_birth = ?, number_of_ects = ? WHERE jmbag = ?";
            jdbc.update(SQL, command.getJmbag(), command.getFirstName(), command.getLastName(), command.getDateOfBirth(), command.getNumberOfEcts(), command.getJmbag());
        }



        return Optional.of(mapCommandToStudent(command));
    }

    @Override
    public boolean deleteByJmbag(String jmbag) {
        String SQL = "DELETE FROM student WHERE jmbag = ?";
        int result = jdbc.update(SQL, new Object[]{jmbag});

        if (result > 0) {
            //(System.out.println("Insert Successfully!");
            return true;
        }
        return false;
    }

    private Student mapRowToStudent(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setJmbag(rs.getString("jmbag"));
        student.setFirstName(rs.getNString("first_name"));
        student.setLastName(rs.getNString("last_name"));
        student.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
        student.setNumberOfEcts(rs.getInt("number_of_ects"));

        return student;
    }

    private Student mapCommandToStudent(StudentCommand command) {
        return new Student(command.getJmbag(), command.getFirstName(), command.getLastName(), command.getDateOfBirth(), command.getNumberOfEcts());
    }
}