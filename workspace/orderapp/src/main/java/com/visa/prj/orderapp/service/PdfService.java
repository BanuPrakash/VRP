package com.visa.prj.orderapp.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.visa.prj.orderapp.entity.Order;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfService {

    public byte[] generateOrderPdf(List<Order> orders) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        document.add(new Paragraph("Order List"));

        for (Order order : orders) {
            document.add(new Paragraph("Order ID: " + order.getOid()));
            document.add(new Paragraph("Customer Name: " + order.getCustomer().getFirstName()));
            document.add(new Paragraph("Order Total: $" + order.getTotal()));
            document.add(new Paragraph(" "));
        }

        document.close();
        return byteArrayOutputStream.toByteArray();
    }
}