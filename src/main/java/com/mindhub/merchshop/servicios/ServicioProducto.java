package com.mindhub.merchshop.servicios;

import com.mindhub.merchshop.models.Producto;
import org.hibernate.mapping.List;

public interface ServicioProducto {

    Producto findById(Long id);

}
