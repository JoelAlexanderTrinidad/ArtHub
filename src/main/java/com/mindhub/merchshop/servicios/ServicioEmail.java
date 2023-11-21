

package com.mindhub.merchshop.servicios;

import com.lowagie.text.pdf.PdfPTable;
import com.mindhub.merchshop.models.Compra;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ServicioEmail {
    public void EnviarEmail(String emailA, Compra compra);

    public void exportar(HttpServletResponse response, Compra compra ) throws IOException;

    public void cuerpoTabla(PdfPTable tabla, Compra compra);

    public void cabeceraTabla(PdfPTable tabla);

    public void pieTabla(PdfPTable tabla, Compra compra);
}

