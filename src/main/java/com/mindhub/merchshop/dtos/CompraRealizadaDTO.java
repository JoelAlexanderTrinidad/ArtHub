package com.mindhub.merchshop.dtos;

public class CompraRealizadaDTO {

        private Long productoId;
        private Long ilustracionId;
        private byte cantidad;

        public CompraRealizadaDTO(Long productoId, Long ilustracionId, byte cantidad) {
            this.productoId = productoId;
            this.ilustracionId = ilustracionId;
            this.cantidad = cantidad;
        }

        public Long getProductoId() {
            return productoId;
        }

        public void setProductoId(Long productoId) {
            this.productoId = productoId;
        }

        public Long getIlustracionId() {
            return ilustracionId;
        }

        public void setIlustracionId(Long ilustracionId) {
            this.ilustracionId = ilustracionId;
        }

        public byte getCantidad() {
            return cantidad;
        }

        public void setCantidad(byte cantidad) {
            this.cantidad = cantidad;
        }
    }
