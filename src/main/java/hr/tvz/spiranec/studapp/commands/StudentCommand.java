package hr.tvz.spiranec.studapp.commands;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCommand {

    @NotBlank(message = "First name must not be empty")
    private String firstName;

    @NotBlank(message = "Last name must not be empty")
    private String lastName;

    @NotBlank(message = "JMBAG must not be empty")
    @Pattern(message = "JMBAG must have 10 digits", regexp="[\\d]{10}")
    private String jmbag;

    @JsonFormat(pattern = "dd.MM.yyyy.")
    @NotNull(message = "Date of birth must be entered")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotNull(message = "Number of ECTS points must be entered")
    @PositiveOrZero(message = "Number of ECTS must be entered as a positive integer")
    @Max(message = "Number of ECTS can not be higher than 480", value = 480)
    private Integer numberOfEcts;


    public StudentCommand(@NotBlank(message = "First name must not be empty") String firstName, @NotBlank(message = "Last name must not be empty") String lastName, @NotBlank(message = "JMBAG must not be empty") @Pattern(message = "JMBAG must have 10 digits", regexp = "[\\d]{10}") String jmbag, @NotNull(message = "Date of birth must be entered") @Past(message = "Date of birth must be in the past") String dateOfBirth, @NotNull(message = "Number of ECTS points must be entered") @PositiveOrZero(message = "Number of ECTS must be entered as a positive integer") @Max(message = "Number of ECTS can not be higher than 480", value = 480) Integer numberOfEcts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbag = jmbag;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        this.dateOfBirth = LocalDate.parse(dateOfBirth, dateFormatter);
        this.numberOfEcts = numberOfEcts;
    }
}
