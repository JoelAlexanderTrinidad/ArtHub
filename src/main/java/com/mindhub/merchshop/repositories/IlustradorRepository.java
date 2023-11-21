package com.mindhub.merchshop.repositories;

import com.mindhub.merchshop.models.Ilustrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface IlustradorRepository extends JpaRepository<Ilustrador, String> {

    Ilustrador findByEmail(String email);
    Ilustrador findByNick(String nick);
}
