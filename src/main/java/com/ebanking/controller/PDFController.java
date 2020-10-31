/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.controller;

import com.ebanking.entity.Transaction;
import com.ebanking.service.TransactionServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Huy
 */
@Controller
public class PDFController {
    
    @Autowired
    TransactionServiceIF transactionService;
    
    @GetMapping("/downloadPDF/{id}")
    public ModelAndView generatePDF(@PathVariable int id) {
        Transaction transaction = transactionService.getTransaction(id);
        
        return new ModelAndView("pdfView", "transaction", transaction);
    }
}
