package FindAMovie.services;

import FindAMovie.DTO.UserDTO;
import FindAMovie.models.User;
import FindAMovie.reporsitories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getUsers() {
        List<UserDTO> userdtos = new LinkedList<>();
        List<User> list = userRepository.findAll();
        for(User u : list) {
            UserDTO udto = new UserDTO();
            udto.setId(u.getId());
            udto.setUsername(u.getUsername());
            udto.setPassword(u.getPassword());
            udto.setEmail(u.getEmail());
            udto.setRole(u.getRole());
            userdtos.add(udto);
        }
        return userdtos;
    }

}
