package com.mindhub.merchshop.servicios.Impl;

import com.mindhub.merchshop.models.Direccion;
import com.mindhub.merchshop.models.Usuario;
import com.mindhub.merchshop.repositories.DireccionRepository;
import com.mindhub.merchshop.servicios.ServicioDireccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioDireccionImpl implements ServicioDireccion {

    @Autowired
    DireccionRepository direccionRepository;

    @Override
    public Direccion save(Direccion direccion) {
        return direccionRepository.save(direccion);
    }
}
