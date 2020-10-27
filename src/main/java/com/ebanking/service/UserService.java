/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.service;

import com.ebanking.dao.UserDaoIF;
import com.ebanking.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Huy
 */
@Service
public class UserService implements UserServiceIF {
    
    @Autowired
    UserDaoIF userDao;
    
    @Override
    public User getUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.save(user);
    }

    @Override
    public void changePassword(User user, String password) {
        user.setPassword(password);
        userDao.save(user);
    }

    @Override
    public User getUserById(int id) {
        Optional<User> user = userDao.findById(id);
	return user.isPresent() ? user.get() : null;
    }

    @Override
    public boolean existEmail(String email) {
        List<String> emails = userDao.getListEmail();
        if(emails.contains(email)) {
            return true;
        }
        return false;
    }
    
    
}
