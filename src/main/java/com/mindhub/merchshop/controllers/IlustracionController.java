package com.mindhub.merchshop.controllers;

import com.mindhub.merchshop.dtos.IlustracionDTO;
import com.mindhub.merchshop.models.Ilustracion;
import com.mindhub.merchshop.models.Ilustrador;
import com.mindhub.merchshop.repositories.IlustracionRepository;
import com.mindhub.merchshop.servicios.ServicioIlustracion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class IlustracionController {


    @Autowired
    private ServicioIlustracion servicioIlustracion;


    @GetMapping("/ilustraciones")
    public List<IlustracionDTO> getClients() {
        return servicioIlustracion.findAll().stream().map(IlustracionDTO::new).collect(toList());
    }
    @GetMapping("/ilustracion/{id}")
    public List<IlustracionDTO> getIlustracionesDelMismoAutor(@PathVariable Long id) {
        Ilustracion ilustracion1 = servicioIlustracion.findById(id);
        Ilustrador ilustrador = ilustracion1.getIllustrator();
        List<Ilustracion> ilustracionesDelMismoAutor = servicioIlustracion.findByIlustrador(ilustrador);

        List<IlustracionDTO> ilustracionesDTO = new ArrayList<>();
        for (Ilustracion ilustracion : ilustracionesDelMismoAutor) {
            IlustracionDTO ilustracionDTO = new IlustracionDTO(ilustracion);
            ilustracionesDTO.add(ilustracionDTO);
        }

        return ilustracionesDTO;
    }

}
