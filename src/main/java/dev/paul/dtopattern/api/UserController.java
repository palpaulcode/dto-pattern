package dev.paul.dtopattern.api;

import dev.paul.dtopattern.model.RoleService;
import dev.paul.dtopattern.model.User;
import dev.paul.dtopattern.model.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private RoleService roleService;
    private Mapper mapper;

    public UserController(UserService userService, RoleService roleService, Mapper mapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.mapper = mapper;
    }

    @GetMapping
    @ResponseBody
    public List<UserDTO> getUsers() {
        return userService.getAll().stream().map(mapper::toDto).collect(toList());
    }

    @PostMapping
    @ResponseBody
    public UserIdDTO create(@RequestBody UserCreationDTO userDTO) {
        User user = mapper.toUser(userDTO);

        userDTO.getRoles()
                .stream()
                .map(role -> roleService.getOrCreate(role))
                .forEach(user::addRole);

        userService.save(user);

        return new UserIdDTO(user.getId());
    }
}
