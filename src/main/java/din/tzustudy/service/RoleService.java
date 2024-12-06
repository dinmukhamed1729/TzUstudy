package din.tzustudy.service;

import din.tzustudy.model.entyti.Role;
import din.tzustudy.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findByName(String name) throws RoleNotFoundException {
        return roleRepository.findByName(name).orElseThrow(() -> new RoleNotFoundException(String.format("role %s not fount", "USER")));
    }
}
