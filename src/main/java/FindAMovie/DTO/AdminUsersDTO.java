package FindAMovie.DTO;

import java.util.List;

public class AdminUsersDTO {
    private UserDTO currentUser;
    private UserDTO[] users;

    public AdminUsersDTO () {}

    public AdminUsersDTO(UserDTO currentUser, UserDTO[] users) {
        this.currentUser = currentUser;
        this.users = users;
    }

    public UserDTO getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserDTO currentUser) {
        this.currentUser = currentUser;
    }

    public UserDTO[] getUsers() {
        return users;
    }

    public void setUsers(UserDTO[] users) {
        this.users = users;
    }
}
