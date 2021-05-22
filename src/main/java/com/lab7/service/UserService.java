package com.lab7.service;

import com.lab7.message.User;
import com.lab7.dao.UserDao;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Stack;

@AllArgsConstructor
public class UserService implements GenericService<User, String> {

    private final UserDao userDao;

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public List<User> saveAll(List<User> entities) {
        return userDao.saveAll(entities);
    }

    @Override
    public Stack<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(String name) {
        return userDao.findById(name);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(User user) {
        return userDao.delete(user);
    }

    @Override
    public boolean deleteById(String name) {
        return userDao.deleteById(name);
    }

    @Override
    public boolean deleteAll() {
        return userDao.deleteAll();
    }
}
