package com.mindhub.merchshop.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ilustracion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Long id;
    private String nombre;
    private String imgURL;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ilustrador_id")
    private Ilustrador ilustrador;
    @OneToMany(mappedBy = "ilustracion", fetch = FetchType.EAGER)
    private List<PaqueteDeProductos> paqueteDeProductos = new ArrayList<>();

    public Ilustracion(){}
    public Ilustracion(String nombre, String imgURL, Ilustrador ilustrador, List<PaqueteDeProductos> paqueteDeProductos) {
        this.nombre = nombre;
        this.imgURL = imgURL;
        this.ilustrador = ilustrador;
        this.paqueteDeProductos = paqueteDeProductos;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Ilustrador getIllustrator() {
        return ilustrador;
    }

    public void setIlustrador(Ilustrador ilustrador) {
        this.ilustrador = ilustrador;
    }


    public List<PaqueteDeProductos> getPaqueteDeProductos() {
        return paqueteDeProductos;
    }

    public void setPaqueteDeProductos(List<PaqueteDeProductos> paqueteDeProductos) {
        this.paqueteDeProductos = paqueteDeProductos;
    }

    public void addPaqueteDeProductos(PaqueteDeProductos paqueteDeProductoss) {
        paqueteDeProductoss.setIlustracion(this);
        paqueteDeProductos.add(paqueteDeProductoss);
    }

}
