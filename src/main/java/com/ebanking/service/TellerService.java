/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.service;

import com.ebanking.dao.TellerDaoIF;
import com.ebanking.entity.Teller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Huy
 */
@Service
public class TellerService implements TellerServiceIF {

    @Autowired
    TellerDaoIF tellerDao;
    
    @Override
    public List<Teller> getTellers() {
       return (List<Teller>) tellerDao.findAll();
    }

    @Override
    public boolean login(String username, String password) {
        for (Teller teller : getTellers()) {
            if (teller.getUsername().equals(username)) {
                if (teller.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }
    
}
