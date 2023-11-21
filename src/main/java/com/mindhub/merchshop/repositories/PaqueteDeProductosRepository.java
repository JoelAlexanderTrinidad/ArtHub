package com.mindhub.merchshop.repositories;

import com.mindhub.merchshop.models.PaqueteDeProductos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PaqueteDeProductosRepository extends JpaRepository<PaqueteDeProductos, Long> {
}
