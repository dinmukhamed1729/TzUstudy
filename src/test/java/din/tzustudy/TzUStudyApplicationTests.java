package din.tzustudy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootTest
class TzUStudyApplicationTests {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    void contextLoads() {
        System.out.println(passwordEncoder.encode("pass"));
    }
}
