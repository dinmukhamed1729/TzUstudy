package din.tzustudy.service;

import din.tzustudy.model.dto.RegistrationUserDto;
import din.tzustudy.model.dto.UserDto;
import din.tzustudy.model.entyti.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.management.relation.RoleNotFoundException;

@RequiredArgsConstructor
@Service
public class RegistrationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserDto registration(RegistrationUserDto registrationUserDto) throws RoleNotFoundException {
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwords do not match");
        }
        if (userService.userWithUsernameExist(registrationUserDto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A user with this username already exists");
        }
        User user = new User(registrationUserDto.getUsername(),
                registrationUserDto.getEmail(),
                passwordEncoder.encode(registrationUserDto.getPassword()));
        userService.createNewUser(user);
        return new UserDto(user.getUsername(), user.getEmail());
    }
}
