package din.tzustudy.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class MainController {
    @GetMapping("/unsecured")
    public String unsecured() {
        return "unsecured Data";
    }

    @GetMapping("/secured")
    public String secured() {
        return "secured Data";
    }

    @GetMapping("/info")
    public String userData(Principal principal){
        return principal.getName();
    }
}
