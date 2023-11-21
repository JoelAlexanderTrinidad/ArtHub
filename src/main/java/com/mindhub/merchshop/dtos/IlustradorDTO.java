package com.mindhub.merchshop.dtos;

import com.mindhub.merchshop.models.Direccion;
import com.mindhub.merchshop.models.Ilustracion;
import com.mindhub.merchshop.models.Ilustrador;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class IlustradorDTO {
    private String email;
    private String nombre;
    private String nick;
    private String ciudad;
    private String avatarURL;
    private String contrasenia;
//    private Direccion direccion;
    private Set<String> redesSociales;
    private List<IlustracionDTO> ilustraciones;
    public IlustradorDTO(){}
    public IlustradorDTO(Ilustrador ilustrador){
        this.email = ilustrador.getEmail();
        this.nombre = ilustrador.getNombre();
        this.nick = ilustrador.getNick();
        this.ciudad = ilustrador.getCiudad();
        this.avatarURL = ilustrador.getAvatarURL();
        this.contrasenia = ilustrador.getContrasenia();
//        this.direccion = ilustrador.getDireccion();
        this.redesSociales = ilustrador.getRedesSociales();
        this.ilustraciones = ilustrador.getIlustraciones().stream().map(IlustracionDTO::new).collect(Collectors.toList());
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNick() {
        return nick;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public String getContrasenia() {
        return contrasenia;
    }

//    public Direccion getDireccion() {
//        return direccion;
//    }

    public List<IlustracionDTO> getIlustraciones() {
        return ilustraciones;
    }

    public Set<String> getRedesSociales() {
        return redesSociales;
    }
}
