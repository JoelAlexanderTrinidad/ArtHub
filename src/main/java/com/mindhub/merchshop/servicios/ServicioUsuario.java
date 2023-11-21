package com.mindhub.merchshop.servicios;

import com.mindhub.merchshop.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface ServicioUsuario {

    List<Usuario> findAll();

    Optional<Usuario> findById(String email);

    Usuario save(Usuario usuario);

    Usuario findByNick(String nick);

    Usuario findByEmailOrNick(String email, String nick);
    Usuario findByEmail(String email);

}
