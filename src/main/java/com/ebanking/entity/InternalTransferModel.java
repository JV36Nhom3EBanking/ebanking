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
public class InternalTransferModel {
    
    private Account accountFrom;
    private Account accountTo;
    private int accountFromNo;
    private int accountToNo;
    private String amountFormat;
    private String amountByText;
    private int amount;
    private String message;
    private String feeCarier;
    private int fee;
    private String captcha;
    private String otp;

    public InternalTransferModel() {
    }

    public InternalTransferModel(Account accountFrom, Account accountTo, int accountFromNo, int accountToNo, String amountFormat, String amountByText, int amount, String message, String feeCarier, int fee, String captcha, String otp) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.accountFromNo = accountFromNo;
        this.accountToNo = accountToNo;
        this.amountFormat = amountFormat;
        this.amountByText = amountByText;
        this.amount = amount;
        this.message = message;
        this.feeCarier = feeCarier;
        this.fee = fee;
        this.captcha = captcha;
        this.otp = otp;
    }

    public int getAccountFromNo() {
        return accountFromNo;
    }

    public void setAccountFromNo(int accountFromNo) {
        this.accountFromNo = accountFromNo;
    }

    public Account getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Account accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Account accountTo) {
        this.accountTo = accountTo;
    }

    public int getAccountToNo() {
        return accountToNo;
    }

    public void setAccountToNo(int accountToNo) {
        this.accountToNo = accountToNo;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFeeCarier() {
        return feeCarier;
    }

    public void setFeeCarier(String feeCarier) {
        this.feeCarier = feeCarier;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getAmountFormat() {
        return amountFormat;
    }

    public void setAmountFormat(String amountFormat) {
        this.amountFormat = amountFormat;
    }

    public String getAmountByText() {
        return amountByText;
    }

    public void setAmountByText(String amountByText) {
        this.amountByText = amountByText;
    }
    
    
    
}
