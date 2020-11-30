package hr.tvz.spiranec.studapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private String name;
    private Integer numberOfEcts;

}
