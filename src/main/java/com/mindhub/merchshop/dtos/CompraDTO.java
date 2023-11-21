package com.mindhub.merchshop.dtos;

import com.mindhub.merchshop.models.Compra;
import com.mindhub.merchshop.models.PaqueteDeProductos;

import java.time.LocalDateTime;

import java.util.List;
import java.util.stream.Collectors;

public class CompraDTO {
    private Long id;

    private String numeroDeCompra;
    private LocalDateTime fecha;
    private Double montoTotal;
    private List<PaqueteDeProductosDTO> productos;
    public CompraDTO(){}
    public CompraDTO(Compra compra){
        this.id = compra.getId();
        this.fecha = compra.getFecha();
        this.montoTotal = compra.getMontoTotal();
        this.productos = compra.getProductos().stream().map(PaqueteDeProductosDTO::new).collect(Collectors.toList());
        this.numeroDeCompra = compra.getNumeroDeCompra();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public List<PaqueteDeProductosDTO> getProductos() {
        return productos;
    }

    public String getNumeroDeCompra() {
        return numeroDeCompra;
    }
}
