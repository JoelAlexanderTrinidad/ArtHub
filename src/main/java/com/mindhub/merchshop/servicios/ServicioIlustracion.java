package com.mindhub.merchshop.servicios;

import com.mindhub.merchshop.models.Ilustracion;
import com.mindhub.merchshop.models.Ilustrador;
import com.mindhub.merchshop.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface ServicioIlustracion {

    Ilustracion findById(Long id);

    List<Ilustracion> findByIlustrador (Ilustrador ilustrador);

    List<Ilustracion> findAll ();

}
