package hr.tvz.spiranec.studapp.student;

public class StudentDTO {
    private String firstName;
    private String lastName;
    private String jmbag;
    private Integer numberOfEcts;
    private boolean paidTuition;

    public StudentDTO(String firstName, String lastName, String jmbag, Integer numberOfEcts, boolean paidTuition) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbag = jmbag;
        this.numberOfEcts = numberOfEcts;
        this.paidTuition = paidTuition;
    }


    public String getJmbag() {
        return jmbag;
    }

    public void setJmbag(String jmbag) {
        this.jmbag = jmbag;
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

    @Override
    public String toString() {
        return "StudentDTO{" +
                "jmbag='" + jmbag + '\'' +
                ", numberOfEcts=" + numberOfEcts +
                ", paidTuition=" + paidTuition +
                '}';
    }
}
