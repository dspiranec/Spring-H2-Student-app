package hr.tvz.spiranec.studapp.security;

import hr.tvz.spiranec.studapp.authority.Authority;
import hr.tvz.spiranec.studapp.user.User;
import hr.tvz.spiranec.studapp.user.UserDTO;
import hr.tvz.spiranec.studapp.user.UserRepository;
import org.apache.catalina.mapper.Mapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserDTO> getUserDtoByUsername(String username) {
        return userRepository
                .findByUsernameEquals(username)
                .map(this::mapUserToDTO);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) {

        return userRepository
                .findByUsernameEquals(username)
                .map(this::createSpringSecurityUser)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " was not found in the database"));
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(User user) {
        List<GrantedAuthority> grantedAuthorities = user
                .getAuthorities()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    public UserDTO mapUserToDTO(User user){
        return new UserDTO(user.getId(), user.getUsername(), user.getFirstname(), user.getLastname(),
                user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet()));
    }
}
