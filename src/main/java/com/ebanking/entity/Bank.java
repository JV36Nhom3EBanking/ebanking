/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Huy Hoang
 */
@Entity
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue()
    int id;
    
    
}
