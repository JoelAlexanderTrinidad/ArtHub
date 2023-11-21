package com.mindhub.merchshop.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class PaqueteDeProductos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Long id;
    private Byte cantidad;
    private Double montoTotal;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "compra_id")
    private Compra compra;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id")
    private Producto producto;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ilustracion_id")
    private Ilustracion ilustracion;


    public PaqueteDeProductos(){}
    public PaqueteDeProductos(Compra compra, Producto producto, Ilustracion ilustracion,Byte cantidad) {
        this.compra = compra;
        this.producto = producto;
        this.ilustracion = ilustracion;
        this.cantidad = cantidad;
        this.montoTotal = this.producto.getPrecio() * this.cantidad;
    }

    public Long getId() {
        return id;
    }
    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Byte getCantidad() {
        return cantidad;
    }

    public void setCantidad(Byte cantidad) {
        this.cantidad = cantidad;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Ilustracion getIlustracion() {
        return ilustracion;
    }

    public void setIlustracion(Ilustracion ilustracion) {
        this.ilustracion = ilustracion;
    }
}

