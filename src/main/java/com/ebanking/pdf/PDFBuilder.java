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
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
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
        Paragraph title = new Paragraph("Transaction Information", catFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        addEmptyLine(preface, 1);

        preface.add(new Paragraph("Receipt was printed by : " + System.getProperty("user.name") + ", " + new Date(), smallBold));

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


        PdfPTable table = new PdfPTable(2);

        PdfPCell c1 = new PdfPCell(new Phrase("Transaction ID"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        PdfPCell c2 = new PdfPCell(new Phrase(transaction.getId() + ""));
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c2);

        c1 = new PdfPCell(new Phrase("Transaction Type"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c2 = new PdfPCell(new Phrase(transaction.getType()));
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c2);

        c1 = new PdfPCell(new Phrase("Account Number From"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c2 = new PdfPCell(new Phrase(transaction.getAccount1().getId() + ""));
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c2);

        c1 = new PdfPCell(new Phrase("Account Number To"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c2 = new PdfPCell(new Phrase(transaction.getAccount2().getId() + ""));
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c2);

        c1 = new PdfPCell(new Phrase("Amount"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c2 = new PdfPCell(new Phrase(formatCurrency(transaction.getAmount()) + " VND"));
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c2);

        c1 = new PdfPCell(new Phrase("Date Transfer"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c2 = new PdfPCell(new Phrase(transaction.getTransactionDate() + ""));
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c2);
        
        c1 = new PdfPCell(new Phrase("Message"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c2 = new PdfPCell(new Phrase(transaction.getMessage()));
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c2);

        content.add(table);

        document.add(content);
    }

    private String formatCurrency(int n) {
        NumberFormat currentLocale = NumberFormat.getInstance();
        Locale localeVI = new Locale("vi", "VI");
        NumberFormat vi = NumberFormat.getInstance(localeVI);
        
        return vi.format(n);
    }
}
