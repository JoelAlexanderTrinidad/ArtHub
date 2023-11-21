package com.mindhub.merchshop.controllers;

import com.mindhub.merchshop.dtos.IlustradorDTO;
import com.mindhub.merchshop.models.Ilustrador;
import com.mindhub.merchshop.repositories.IlustradorRepository;
import com.mindhub.merchshop.servicios.ServicioIlustrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api")
public class IlustradorController {
    @Autowired
    ServicioIlustrador servicioIlustrador;

    @GetMapping("/ilustradores/{nick}")
    public IlustradorDTO getAccount(@PathVariable String nick){
        return new IlustradorDTO(servicioIlustrador.findByNick(nick));
    }

    @GetMapping("/ilustradores")
    public Set<IlustradorDTO> listaIlustradores(){
        return servicioIlustrador.findAll().stream().map(IlustradorDTO::new).collect(Collectors.toSet());
    }
    @PostMapping("/admin/illustradores")
    public ResponseEntity<Object> crearIlustrador (@RequestBody IlustradorDTO ilustradorDTO){

        Ilustrador ilustradorExistente = servicioIlustrador.findByEmail(ilustradorDTO.getEmail());
        String mail = ilustradorDTO.getEmail();
        String nombre = ilustradorDTO.getNombre();
        String nick = ilustradorDTO.getNick();
        String ciudad = ilustradorDTO.getCiudad();
        String avatar = ilustradorDTO.getAvatarURL();
        String contrasenia = ilustradorDTO.getContrasenia();
        Set<String> redesSociales = ilustradorDTO.getRedesSociales();


        if(mail.isEmpty()){
            return new ResponseEntity<>("Este campo no puede estar vacío", HttpStatus.BAD_REQUEST);}
        if(ilustradorExistente != null){
            return new ResponseEntity<>("Este correo ya está en uso", HttpStatus.BAD_REQUEST);}
        if(nombre.isEmpty() ){
            return new ResponseEntity<>("Este campo no puede estar vacío", HttpStatus.BAD_REQUEST);}
        if(nick.isEmpty()){
            return new ResponseEntity<>("Este campo no puede estar vacío", HttpStatus.BAD_REQUEST);}
        if(avatar.isEmpty()){
            return new ResponseEntity<>("Este campo no puede estar vacío", HttpStatus.BAD_REQUEST);}
        if(contrasenia.isEmpty() ){
            return new ResponseEntity<>("Este campo no puede estar vacío", HttpStatus.BAD_REQUEST);}

        Ilustrador nuevoIlustrador = new Ilustrador(mail, nombre, nick, ciudad,avatar, contrasenia, redesSociales ,List.of());
        servicioIlustrador.save(nuevoIlustrador);

        return new ResponseEntity<>("Ilustrador creado!", HttpStatus.CREATED);
    }
}
