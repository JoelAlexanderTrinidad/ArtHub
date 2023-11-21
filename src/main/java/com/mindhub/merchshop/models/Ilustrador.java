package com.mindhub.merchshop.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Ilustrador {
    @Id
    private String email;
    private String nombre;
    private String nick;
    private String ciudad;
    private String avatarURL;
    private String contrasenia;

//    private List<Direccion> direcciones;
    @ElementCollection
    @Column(name = "redes_sociales")
    Set<String> redesSociales = new HashSet<>();
    @OneToMany(mappedBy = "ilustrador", fetch = FetchType.EAGER)
    private List<Ilustracion> ilustraciones = new ArrayList<>();

    public Ilustrador(){};
    public Ilustrador(String email, String nombre, String nick, String ciudad,String avatarURL, String contrasenia,  Set<String> redesSociales,List<Ilustracion> ilustraciones) {
        this.email = email;
        this.nombre = nombre;
        this.nick = nick;
        this.ciudad = ciudad;
        this.avatarURL = avatarURL;
        this.contrasenia = contrasenia;
//        this.direccion = direccion;
        this.redesSociales = redesSociales;
        this.ilustraciones = ilustraciones;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

//    public Direccion getDireccion() {
//        return direccion;
//    }
//
//    public void setDireccion(Direccion direccion) {
//        this.direccion = direccion;
//    }

    public List<Ilustracion> getIlustraciones() {
        return ilustraciones;
    }

    public void setIlustraciones(List<Ilustracion> ilustraciones) {
        this.ilustraciones = ilustraciones;
    }
    public void addIlustraciones(Ilustracion ilustracion) {
        ilustracion.setIlustrador(this);
        ilustraciones.add(ilustracion);
    }

    public Set<String> getRedesSociales() {
        return redesSociales;
    }

    public void setRedesSociales(Set<String> redesSociales) {
        this.redesSociales = redesSociales;
    }
}
