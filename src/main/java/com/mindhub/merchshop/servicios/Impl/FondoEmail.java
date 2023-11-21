package com.mindhub.merchshop.servicios.Impl;


import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;



public class FondoEmail extends PdfPageEventHelper {

    public void onEndPage(PdfWriter writer, Document document) {

        try {
            Image fondo = Image.getInstance("https://iili.io/HOBdUve.jpg");

            fondo.setAbsolutePosition(0, 0);
            fondo.scaleAbsolute(document.getPageSize().getWidth(), document.getPageSize().getHeight());

            PdfContentByte canvas = writer.getDirectContentUnder();
            canvas.addImage(fondo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
