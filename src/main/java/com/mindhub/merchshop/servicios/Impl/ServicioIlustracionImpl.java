package com.mindhub.merchshop.servicios.Impl;

import com.mindhub.merchshop.dtos.IlustracionDTO;
import com.mindhub.merchshop.dtos.PaqueteDeProductosDTO;
import com.mindhub.merchshop.models.Ilustracion;
import com.mindhub.merchshop.models.Ilustrador;
import com.mindhub.merchshop.repositories.IlustracionRepository;
import com.mindhub.merchshop.servicios.ServicioIlustracion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicioIlustracionImpl implements ServicioIlustracion {

    @Autowired
    IlustracionRepository ilustracionRepository;
    @Override
    public Ilustracion findById(Long id) {
        return ilustracionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Ilustracion> findByIlustrador(Ilustrador ilustrador) {
        List<Ilustracion> ilustracionesDelMismoAutor = ilustracionRepository.findByIlustrador(ilustrador);
        return ilustracionesDelMismoAutor;
    }
    @Override
    public List<Ilustracion> findAll() {
        return ilustracionRepository.findAll();
    }
}
