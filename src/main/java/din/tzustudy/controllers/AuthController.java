package din.tzustudy.controllers;

import din.tzustudy.exceptions.AuthError;
import din.tzustudy.model.dto.JwtRequest;
import din.tzustudy.model.dto.JwtResponse;
import din.tzustudy.model.dto.RegistrationUserDto;
import din.tzustudy.model.dto.UserDto;
import din.tzustudy.service.RegistrationService;
import din.tzustudy.service.UserService;
import din.tzustudy.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final RegistrationService registrationService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthError(401, "incorrect password or username"));
        }
        log.debug("username is authenticated");
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/registration")
     public ResponseEntity<?> registration(@RequestBody RegistrationUserDto registrationUserDto) throws RoleNotFoundException {
        UserDto user = registrationService.registration(registrationUserDto);
        return ResponseEntity.ok(user);
    }
}
