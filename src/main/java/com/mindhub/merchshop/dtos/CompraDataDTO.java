package com.mindhub.merchshop.dtos;

import java.util.List;

public class CompraDataDTO {

  //  private TransaccionDTO transaccionDTO;
  //  private List<PaqueteDeProductosDTO> paqueteDeProductosDTOS;

    //public CompraDataDTO(TransaccionDTO transaccionDTO, List<PaqueteDeProductosDTO> paqueteDeProductosDTOS) {
     //   this.transaccionDTO = transaccionDTO;
       // this.paqueteDeProductosDTOS = paqueteDeProductosDTOS;
   // }

    //public TransaccionDTO getTransaccionDTO() {
      //  return transaccionDTO;
    //}


    private List<PaqueteDeProductosDTO> paqueteDeProductosDTOS;

    public CompraDataDTO( List<PaqueteDeProductosDTO> paqueteDeProductosDTOS) {

        this.paqueteDeProductosDTOS = paqueteDeProductosDTOS;
    }



    public List<PaqueteDeProductosDTO> getPaqueteDeProductosDTOS() {
        return paqueteDeProductosDTOS;
    }
}
