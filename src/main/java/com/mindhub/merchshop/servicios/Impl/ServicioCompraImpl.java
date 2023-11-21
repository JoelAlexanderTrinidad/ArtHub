package com.mindhub.merchshop.servicios.Impl;

import com.mindhub.merchshop.models.Compra;
import com.mindhub.merchshop.repositories.CompraRepository;
import com.mindhub.merchshop.servicios.ServicioCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioCompraImpl implements ServicioCompra {

    @Autowired
    CompraRepository compraRepository;

    @Override
    public Compra getReferenceById(Long id) {
        return compraRepository.getReferenceById(id);
    }

    @Override
    public void save(Compra compra) {
        compraRepository.save(compra);
    }
}
