
package com.mindhub.merchshop.controllers;

import com.mindhub.merchshop.dtos.CompraRealizadaDTO;
import com.mindhub.merchshop.models.*;
import com.mindhub.merchshop.servicios.*;
//import com.mindhub.merchshop.servicios.ServicioEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.mindhub.merchshop.Utilidades.Utilidades.generarNumeroCompra;


@RestController
@RequestMapping("/api")
public class CompraController {

    @Autowired
    ServicioUsuario servicioUsuario;
    @Autowired
    ServicioEmail servicioEmail;
    @Autowired
    ServicioCompra servicioCompra;
    @Autowired
    ServicioProducto servicioProducto;
    @Autowired
    ServicioIlustracion servicioIlustracion;
    @Autowired
    ServicioPaqueteDeProductos servicioPaqueteDeProductos;


    @PostMapping("/email/pdf")
    public void crearCompra(@RequestBody List<CompraRealizadaDTO> productos, Authentication authentication) {

        Usuario usuario = servicioUsuario.findByEmail(authentication.getName());

        Compra nuevaCompra = new Compra(usuario, LocalDateTime.now(), generarNumeroCompra());

        List<PaqueteDeProductos> paqueteProductos = new ArrayList<>();

        for (CompraRealizadaDTO producto : productos) {

            Producto productoBD = servicioProducto.findById(producto.getProductoId());
            Ilustracion ilustracionBD = servicioIlustracion.findById(producto.getIlustracionId());
            PaqueteDeProductos paqueteProducto = new PaqueteDeProductos(nuevaCompra,productoBD, ilustracionBD, producto.getCantidad());
            paqueteProductos.add(paqueteProducto);
        }

        nuevaCompra.setProductos(paqueteProductos);
        servicioCompra.save(nuevaCompra);
        servicioEmail.EnviarEmail(usuario.getEmail(), nuevaCompra);
    }



}

