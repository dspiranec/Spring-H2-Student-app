package hr.tvz.spiranec.studapp.course;

import lombok.ToString;

@ToString
public class CourseDTO {

    private String name;
    private Integer numberOfEcts;

    public CourseDTO(String name, Integer numberOfEcts) {
        this.name = name;
        this.numberOfEcts = numberOfEcts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfEcts() {
        return numberOfEcts;
    }

    public void setNumberOfEcts(Integer numberOfEcts) {
        this.numberOfEcts = numberOfEcts;
    }
}
