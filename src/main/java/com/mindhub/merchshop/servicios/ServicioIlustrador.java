package com.mindhub.merchshop.servicios;

import com.mindhub.merchshop.models.Ilustrador;

import java.util.List;

public interface ServicioIlustrador {
    Ilustrador findByEmail(String email);
    List<Ilustrador> findAll();
    public void save(Ilustrador ilustrador);
    Ilustrador findByNick(String nick);
}
