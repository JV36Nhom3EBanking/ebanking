/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.dao;

import com.ebanking.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Huy
 */
public interface UserDaoIF extends CrudRepository<User, Integer> {
    public User findByUsername(String username);
    
    @Query(value = "SELECT username FROM user", nativeQuery = true)
    public List<String> getListEmail();
}
