package com.mindhub.merchshop.repositories;

import com.mindhub.merchshop.dtos.UsuarioDTO;
import com.mindhub.merchshop.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Usuario findByEmail(String email);

    Usuario findByNick(String nick);

    Usuario findByEmailOrNick(String email, String nick);




}
