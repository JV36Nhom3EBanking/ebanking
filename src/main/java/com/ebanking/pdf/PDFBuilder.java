/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebanking.pdf;

import com.ebanking.entity.Transaction;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Huy
 */
public class PDFBuilder extends AbstractITextPdfView {

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
        Transaction transaction = (Transaction) model.get("transaction");

        
        addMetaData(doc);
        addTitlePage(doc);
        addContent(doc, transaction);
    }

    private void addMetaData(Document document) throws DocumentException {
        document.addTitle("Thông tin giao dịch");
        document.addSubject("Giao dịch - EBanking");
        document.addKeywords("Transaction, Giao dịch");
        document.addAuthor("EBanking");
        document.addCreator("EBanking");
    }
    
    private void addTitlePage(Document document) throws DocumentException {
        Paragraph preface = new Paragraph();
        
        addEmptyLine(preface, 1);
        Paragraph title = new Paragraph("Thông tin chi tiết giao dịch" , catFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        addEmptyLine(preface, 1);
        
        preface.add(new Paragraph("Biên lai được in bởi : " + System.getProperty("user.name") + ", " + new Date(), smallBold));
        
        document.add(preface);
    }
    
    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    
    private void addContent(Document document, Transaction transaction) throws DocumentException {
        Paragraph content = new Paragraph();
        
        addEmptyLine(content, 3);
        
        content.add(new Paragraph("Mã giao dịch             : " + transaction.getId()));
        content.add(new Paragraph("Loại giao dịch           : " + transaction.getType()));
        content.add(new Paragraph("Số tài khoản chuyển tiền : " + transaction.getAccount1().getId()));
        content.add(new Paragraph("Số tài khoản thụ hưởng   : " + transaction.getAccount2().getId()));
        content.add(new Paragraph("Số tiền giao dịch        : " + transaction.getAmount()));
        content.add(new Paragraph("Tin nhắn                 : " + transaction.getMessage()));
        content.add(new Paragraph("Ngày thực hiện giao dịch : " + transaction.getTransactionDate()));
        
        content.setAlignment(Element.ALIGN_CENTER);
        
        document.add(content);
    }
}
