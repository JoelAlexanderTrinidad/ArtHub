package com.mindhub.merchshop.dtos;

import com.mindhub.merchshop.models.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class RegisterDTO {


    private String email;
    private String nombre;
    private String nick;
    private String pais;
    private String password;
    private String ciudad;
    private String direccion;
    private String zipCode;
    private String descripcion;

    public RegisterDTO (){}

    public RegisterDTO(Usuario usuario){
        this.email = usuario.getEmail();
        this.nombre = usuario.getNombre();
        this.nick = usuario.getNick();
        this.pais = usuario.getDireccion().getPais();
        this.ciudad = usuario.getDireccion().getCiudad();
        this.direccion = usuario.getDireccion().getDireccion();
        this.zipCode = usuario.getDireccion().getZipCode();
        this.descripcion = usuario.getDireccion().getDescripcion();
        this.password = usuario.getPassword();
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNick() {
        return nick;
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
