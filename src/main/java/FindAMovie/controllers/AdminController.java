package FindAMovie.controllers;

import FindAMovie.DTO.AdminUsersDTO;
import FindAMovie.DTO.UserDTO;
import FindAMovie.reporsitories.UserRepository;
import FindAMovie.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class AdminController {

    UserService userService;
    UserRepository userRepository;

    public AdminController(UserService userService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/admin")
    public List<UserDTO> showUsers() {
        return userService.getUsers();
    }
}
