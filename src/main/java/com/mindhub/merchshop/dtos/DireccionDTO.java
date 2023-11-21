package com.mindhub.merchshop.dtos;

import com.mindhub.merchshop.models.Direccion;

public class DireccionDTO {
    private Long id;
    private String pais;
    private String ciudad;
    private String direccion;
    private String zipCode;
    private String descripcion;

    public DireccionDTO(){}
    public DireccionDTO(Direccion direccion){
        this.id = direccion.getId();
        this.pais = direccion.getPais();
        this.ciudad = direccion.getCiudad();
        this.direccion = direccion.getDireccion();
        this.zipCode = direccion.getZipCode();
        this.descripcion = direccion.getDescripcion();
    }

    public Long getId() {
        return id;
    }

    public String getPais() {
        return pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
