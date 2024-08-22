package app.dao;

import app.model.User;

import java.util.List;

public interface UserDao {

    void saveUser(User user);

    void removeUserById(int id);

    List<User> getAllUsers();

    void updateUserById(int id, User user);

    User getUserById(int id);
}
