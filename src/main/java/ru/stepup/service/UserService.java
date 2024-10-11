package ru.stepup.service;

import org.springframework.stereotype.Service;
import ru.stepup.dao.UserDao;
import ru.stepup.dto.User;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void insert(User user) {
        userDao.insert(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public User findByName(String name) {
        return userDao.findByName(name);
    }

    public void delete(User user) {
        userDao.delete(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }
}
