
package com.mindhub.merchshop.servicios.Impl;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.*;
import com.mindhub.merchshop.Utilidades.Utilidades;
import com.mindhub.merchshop.models.Compra;
import com.mindhub.merchshop.models.PaqueteDeProductos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
@Transactional
public class ServicioEmail implements com.mindhub.merchshop.servicios.ServicioEmail {
    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String email;

    @Override
    public void EnviarEmail(String emailA, Compra compra) {
        MimeMessage mensaje = javaMailSender.createMimeMessage();

        try {


            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document documento = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(documento, baos);


            writer.setPageEvent(new FondoEmail());




            documento.open();

            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));
            documento.add(new Paragraph(" "));


            BaseFont font = BaseFont.createFont("src/main/resources/static/web/assets/font/Bangers.ttf",
                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fontPersonalizada = new Font(font, 12, Font.NORMAL, Color.white);

            BaseFont font1 = BaseFont.createFont("src/main/resources/static/web/assets/font/Bangers.ttf",
                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fontPersonalizada3 = new Font(font1, 17, Font.NORMAL);


            BaseFont fontt = BaseFont.createFont("src/main/resources/static/web/assets/font/MouseMemoirs-Regular.ttf",
                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fontPersonalizada2 = new Font(fontt, 15, Font.NORMAL);



            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime fechaCompra = compra.getFecha();
            String fechaFormateada = fechaCompra.format(formatoFecha);

            PdfPTable infoTable = new PdfPTable(1);
            infoTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            infoTable.addCell(new Phrase("Numero de compra: " + "A-H 435346161", fontPersonalizada2));
            infoTable.addCell(new Phrase("ID de compra: " + compra.getId(), fontPersonalizada2));
            infoTable.addCell(new Phrase("Fecha de compra: " + fechaFormateada, fontPersonalizada2));
            infoTable.addCell(new Phrase("Nombre de usuario: " + compra.getUsuario().getNombre(), fontPersonalizada2));



            documento.add(infoTable);


            documento.add(new Paragraph(" "));



            PdfPTable tabla = new PdfPTable(5);
            tabla.setWidthPercentage(100f);
            tabla.setSpacingBefore(10);


            PdfPTable table = new PdfPTable(new float[]{3, 5, 2, 3});


            Color headerBackgroundColor = new Color(124, 75, 177);
            com.lowagie.text.Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, Color.white);




            PdfPCell cell1 = new PdfPCell(new Phrase("Nombre", fontPersonalizada));
            PdfPCell cell2 = new PdfPCell(new Phrase("Descripción", fontPersonalizada));
            PdfPCell cell3 = new PdfPCell(new Phrase("Cantidad", fontPersonalizada));
            PdfPCell cell4 = new PdfPCell(new Phrase("Precio", fontPersonalizada));

            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setBackgroundColor(headerBackgroundColor);

            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setBackgroundColor(headerBackgroundColor);

            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setBackgroundColor(headerBackgroundColor);

            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell4.setBackgroundColor(headerBackgroundColor);

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            
            for (PaqueteDeProductos paquete : compra.getProductos()) {
                table.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(new Phrase(paquete.getProducto().getNombre(),fontPersonalizada2));
                table.addCell(new Phrase(paquete.getProducto().getDescripcion(),fontPersonalizada2));
                table.addCell(new Phrase(paquete.getCantidad().toString(),fontPersonalizada2));
                table.addCell(new Phrase("$"+ paquete.getProducto().getPrecio().toString(),fontPersonalizada2));

            }

            double total = 0;
            for (PaqueteDeProductos paquete : compra.getProductos()) {
                total += paquete.getCantidad() * paquete.getProducto().getPrecio();
            }


            PdfPCell totalLabelCell = new PdfPCell(new Phrase("Monto total", fontPersonalizada2));
            PdfPCell totalValueCell = new PdfPCell(new Phrase("$" + total , fontPersonalizada2));

            totalLabelCell.setColspan(3);
            totalValueCell.setHorizontalAlignment(Element.ALIGN_LEFT);

            table.addCell(totalLabelCell);
            table.addCell(totalValueCell);

            pieTabla(table, compra);

            documento.add(table);

            Paragraph titulo = new Paragraph("Gracias de nuevo por confiar en Arthub para su compra de arte en línea. Esperamos tener la oportunidad de atenderlo/a nuevamente en el futuro cercano.\n" +
                    "\n" +
                    "Atentamente,\n" +
                    "El equipo de Arthub", fontPersonalizada2);
            titulo.setSpacingAfter(10);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);


            documento.close();



            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
            helper.setFrom(email);
            helper.setTo(emailA);
            helper.setSubject("Ticket de Compra");
            helper.setText("Estimado cliente adjuntamos el ticket de compra");

            ByteArrayDataSource fuente = new ByteArrayDataSource(baos.toByteArray(), "application/pdf");
            helper.addAttachment("TicketCompra.pdf", fuente);

            javaMailSender.send(mensaje);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

}

    @Override
    public void exportar(HttpServletResponse response, Compra compra) throws IOException {

    }

    @Override
    public void cuerpoTabla(PdfPTable tabla, Compra compra) {

    }

    @Override
    public void cabeceraTabla(PdfPTable tabla) {

    }

    @Override
    public void pieTabla(PdfPTable tabla, Compra compra) {

    }
}
