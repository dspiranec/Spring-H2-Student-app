package hr.tvz.spiranec.studapp.authority;

import hr.tvz.spiranec.studapp.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Table(name = "authority")
public class Authority {


    @Id
    private Integer id;
    private String name;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")}
    )
    List<User> users = new ArrayList<>();

    public String getName() {
        return name;
    }
}