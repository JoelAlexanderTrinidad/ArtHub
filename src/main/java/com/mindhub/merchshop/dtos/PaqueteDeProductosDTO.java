package com.mindhub.merchshop.dtos;


import com.mindhub.merchshop.models.PaqueteDeProductos;


public class PaqueteDeProductosDTO {
    private Long id;
    private Byte cantidad;
    private Double montoTotal;
    private Long compraId;

    public PaqueteDeProductosDTO(){}
    public PaqueteDeProductosDTO(PaqueteDeProductos paqueteDeProductos){
        this.id = paqueteDeProductos.getId();
        this.cantidad = paqueteDeProductos.getCantidad();
        this.montoTotal = paqueteDeProductos.getMontoTotal();
        this.compraId = paqueteDeProductos.getCompra().getId();
    }

    public Long getId() {
        return id;
    }

    public Byte getCantidad() {
        return cantidad;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public Long getCompraId() {
        return compraId;
    }


}
