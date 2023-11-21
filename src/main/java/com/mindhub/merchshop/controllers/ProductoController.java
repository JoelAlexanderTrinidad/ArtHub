package com.mindhub.merchshop.controllers;

import com.mindhub.merchshop.dtos.ProductoDTO;
import com.mindhub.merchshop.models.Producto;
import com.mindhub.merchshop.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ProductoController {
    @Autowired
    ProductoRepository productoRepository;

    @GetMapping("/productos")
    public Set<ProductoDTO> listaProductos(){
        List<Producto> todosLosProductos = productoRepository.findAll();

        return todosLosProductos.stream().map(ProductoDTO::new).collect(Collectors.toSet());
    }

}
