/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.service;

import com.ebanking.entity.Teller;
import java.util.List;

/**
 *
 * @author Huy Hoang
 */
public interface TellerServiceIF {
    public List<Teller> getTellers();
    public boolean login(String username, String password);
}
