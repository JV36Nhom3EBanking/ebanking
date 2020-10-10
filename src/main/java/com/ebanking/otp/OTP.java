/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.otp;

import java.util.Random;

/**
 *
 * @author Huy Hoang
 */
public class OTP {
    public static String createOTP() {
        Random otp = new Random();
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 5; i++) {
            sb.append(otp.nextInt(10));
        }
        
        return sb.toString();
    }
}
