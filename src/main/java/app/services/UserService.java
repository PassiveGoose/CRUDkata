package app.services;

import app.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    @Transactional
    boolean saveUser(User user);

    @Transactional
    void removeUserById(int id);

    List<User> getAllUsers();

    @Transactional
    boolean updateUserById(int id, User user);

    User getUserById(int id);
}
