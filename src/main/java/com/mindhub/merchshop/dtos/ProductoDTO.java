package com.mindhub.merchshop.dtos;

import com.mindhub.merchshop.models.*;

import java.util.List;
import java.util.stream.Collectors;


public class ProductoDTO {
    private Long id;
    private String nombre;
    private Integer stock;
    private String descripcion;
    private Double precio;
    private TipoProducto tipoProducto;
    private TallaProducto talla;
    private ColorProducto color;
    private TamañoProducto tamaño;

    private List<PaqueteDeProductosDTO> paqueteDeProductos;
    public ProductoDTO(){}
    //Remera
    public ProductoDTO(Producto producto) {
        this.id = producto.getId();
        this.nombre = producto.getNombre();
        this.stock = producto.getStock();
        this.descripcion = producto.getDescripcion();
        this.precio = producto.getPrecio();
        this.tipoProducto = producto.getTipoProducto();
        this.talla = producto.getTalla();
        this.color = producto.getColor();
        this.tamaño = producto.getTamaño();
        this.paqueteDeProductos = producto.getPaqueteDeProductos().stream().map(PaqueteDeProductosDTO::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getStock() {
        return stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getPrecio() {
        return precio;
    }


    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public TallaProducto getTalla() {
        return talla;
    }

    public ColorProducto getColor() {
        return color;
    }

    public TamañoProducto getTamaño() {
        return tamaño;
    }

    public List<PaqueteDeProductosDTO> getPaqueteDeProductos() {
        return paqueteDeProductos;
    }
}
