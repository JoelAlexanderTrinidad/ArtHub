package com.mindhub.merchshop.repositories;

import com.mindhub.merchshop.models.Ilustracion;
import com.mindhub.merchshop.models.Ilustrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface IlustracionRepository extends JpaRepository<Ilustracion, Long> {

    List<Ilustracion> findByIlustrador(Ilustrador ilustrador);
}
