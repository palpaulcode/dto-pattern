package dev.paul.dtopattern.api;

import dev.paul.dtopattern.model.Role;
import dev.paul.dtopattern.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class Mapper {
    public UserDTO toDto(User user) {
        String name = user.getName();
        List<String> roles = user.getRoles().stream().map(Role::getName).collect(toList());

        return new UserDTO(name, roles);
    }

    public User toUser(UserCreationDTO userDTO) {
        return new User(userDTO.getName(), userDTO.getPassword(), new ArrayList<>());
    }
}
