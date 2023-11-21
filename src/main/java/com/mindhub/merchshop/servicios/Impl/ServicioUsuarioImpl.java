package com.mindhub.merchshop.servicios.Impl;

import com.mindhub.merchshop.models.Usuario;
import com.mindhub.merchshop.repositories.UsuarioRepository;
import com.mindhub.merchshop.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioUsuarioImpl implements ServicioUsuario {


    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> findAll() {

        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(String email) {
        return usuarioRepository.findById(email);
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }


    @Override
    public Usuario save(Usuario client) {
        return usuarioRepository.save(client);
    }

    @Override
    public Usuario findByNick(String nick) {
        return usuarioRepository.findByNick(nick);
    }

    @Override
    public Usuario findByEmailOrNick(String email, String nick) {
        return usuarioRepository.findByEmailOrNick(email, nick);
    }

}
