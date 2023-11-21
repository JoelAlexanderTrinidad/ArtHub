package com.mindhub.merchshop.servicios.Impl;

import com.mindhub.merchshop.models.Producto;
import com.mindhub.merchshop.repositories.ProductoRepository;
import com.mindhub.merchshop.servicios.ServicioProducto;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ServicioProductoImpl implements ServicioProducto {
    @Autowired
    ProductoRepository productoRepository;
    @Override
    public Producto findById(Long id) {
        return productoRepository.findById(id).get();
    }


}
