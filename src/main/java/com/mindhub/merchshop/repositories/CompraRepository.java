package com.mindhub.merchshop.repositories;

import com.mindhub.merchshop.models.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CompraRepository extends JpaRepository<Compra, Long> {
}
