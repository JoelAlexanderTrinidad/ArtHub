package com.mindhub.merchshop.servicios.Impl;

import com.mindhub.merchshop.models.Ilustrador;
import com.mindhub.merchshop.repositories.IlustradorRepository;
import com.mindhub.merchshop.servicios.ServicioIlustrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioIlustradorImpl implements ServicioIlustrador {
    @Autowired
    IlustradorRepository ilustradorRepository;
    @Override
    public Ilustrador findByEmail(String email) {
        return ilustradorRepository.findByEmail(email);
    }

    @Override
    public List<Ilustrador> findAll() {
        return ilustradorRepository.findAll();
    }

    @Override
    public void save(Ilustrador ilustrador) {
        ilustradorRepository.save(ilustrador);
    }

    @Override
    public Ilustrador findByNick(String nick) {
        return ilustradorRepository.findByNick(nick);
    }

}
