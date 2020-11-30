package hr.tvz.spiranec.studapp.dto;

import lombok.*;

import java.util.Set;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private Set<String> authorities;

}
