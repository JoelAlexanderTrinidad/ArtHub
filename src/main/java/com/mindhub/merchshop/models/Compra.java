package com.mindhub.merchshop.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Long id;

    private String numeroDeCompra;
    private LocalDateTime fecha;
    private Double montoTotal;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @OneToMany(mappedBy = "compra", fetch = FetchType.EAGER)
    private List<PaqueteDeProductos> productos = new ArrayList<>();
    public Compra(){};

    public Compra(Usuario usuario, LocalDateTime fecha,  String numeroDeCompra) {
        this.usuario = usuario;
        this.productos = productos;
        this.fecha = fecha;
        for (PaqueteDeProductos producto : this.productos){ this.montoTotal += producto.getMontoTotal(); }
        this.numeroDeCompra = numeroDeCompra;
    }

    public Long getId() {
        return id;
    }
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<PaqueteDeProductos> getProductos() {
        return productos;
    }

    public void setProductos(List<PaqueteDeProductos> productos) {
        this.productos = productos;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getNumeroDeCompra() {
        return numeroDeCompra;
    }

    public void setNumeroDeCompra(String numeroDeCompra) {
        this.numeroDeCompra = numeroDeCompra;
    }

    public void addPaqueteDeProductos(PaqueteDeProductos paqueteDeProductos) {
        paqueteDeProductos.setCompra(this);
        productos.add(paqueteDeProductos);
    }

}
