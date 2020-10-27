/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.service;

import com.ebanking.entity.User;

/**
 *
 * @author Huy
 */
public interface UserServiceIF {
    public User getUserById(int id);
    public User getUserByUsername(String username);
    public void saveUser(User user);
    public void updateUser(User user);
    public void changePassword(User user, String password);
    public boolean existEmail(String email);
}
