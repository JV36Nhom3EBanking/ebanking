/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.entity;

/**
 *
 * @author Huy
 */
public class ChangePasswordModel {
    private String password;
    private String newPassword;
    private String reNewPassword;
    private String captcha;

    public ChangePasswordModel() {
    }

    public ChangePasswordModel(String password, String newPassword, String reNewPassword, String captcha) {
        this.password = password;
        this.newPassword = newPassword;
        this.reNewPassword = reNewPassword;
        this.captcha = captcha;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getReNewPassword() {
        return reNewPassword;
    }

    public void setReNewPassword(String reNewPassword) {
        this.reNewPassword = reNewPassword;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
