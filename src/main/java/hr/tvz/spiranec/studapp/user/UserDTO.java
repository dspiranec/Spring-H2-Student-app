package hr.tvz.spiranec.studapp.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@Getter
@ToString
public class UserDTO {

    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private Set<String> authorities;

    public UserDTO(Integer id, String username, String firstName, String lastName, Set<String> authorities) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.authorities = authorities;
    }
}
