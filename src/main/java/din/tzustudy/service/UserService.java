package din.tzustudy.service;

import din.tzustudy.model.entyti.Role;
import din.tzustudy.model.entyti.User;
import din.tzustudy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public User findByUsername(String username){
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user with %s not found".formatted(username)));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = findByUsername(username);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(Role::getName)
                        .map(SimpleGrantedAuthority::new).
                        toList()

        );
    }

    public void createNewUser(User user) throws RoleNotFoundException {
        user.setRoles(List.of(roleService.findByName("USER")));
        userRepository.save(user);
    }
    public boolean userWithUsernameExist(String username){
        return userRepository.existsUserByUsername(username);
    }
}
