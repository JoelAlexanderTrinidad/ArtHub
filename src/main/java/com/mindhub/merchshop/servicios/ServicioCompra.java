package com.mindhub.merchshop.servicios;

import com.mindhub.merchshop.models.Compra;

public interface ServicioCompra {

   Compra getReferenceById(Long id);

   public void save(Compra compra);
}
