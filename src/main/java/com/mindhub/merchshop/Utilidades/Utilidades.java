
package com.mindhub.merchshop.Utilidades;

import com.mindhub.merchshop.models.Compra;
import com.mindhub.merchshop.servicios.ServicioCompra;
import com.mindhub.merchshop.servicios.ServicioEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Utilidades {
    @Autowired
    static
    ServicioEmail servicioEmail;
    @Autowired
    static
    ServicioCompra servicioCompra;
    public  static String generarNumeroCompra() {
        Random rand = new Random();
        int num = rand.nextInt(900000000) + 100000000;
        return "AH-" + String.valueOf(num);
    }

//    public static void generarPDF(Authentication authentication, HttpServletResponse response, Compra compra) throws IOException {
//
//        response.setContentType("application/pdf");
//        DateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
//        String horaActual = formatoDeFecha.format(new Date());
//
//        String headerKey = "content-Disposition";
//        String headerValue = "attachment; filename=pdf_" + horaActual + ".pdf";
//        response.setHeader(headerKey, headerValue);
//
//        servicioEmail.exportar(response, compra);
//
//    }
}

