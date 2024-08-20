package app.services;

import app.dao.UserDao;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public boolean saveUser(User user) {
        if (user.getName() != null && !user.getName().isEmpty()
                && user.getSurname() != null && !user.getSurname().isEmpty()
                && user.getAge() > 0) {
            userDao.saveUser(user);
            return true;
        }
        return false;
    }

    @Transactional
    public void removeUserById(int id) {
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    public boolean updateUserById(int id, User user) {
        if (user.getName() != null && !user.getName().isEmpty()
                && user.getSurname() != null && !user.getSurname().isEmpty()
                && user.getAge() > 0) {
            userDao.updateUserById(id, user);
            return true;
        }
        return false;
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }
}