package com.mycompany.porky.models;
import java.time.LocalDateTime;

import lombok.Data;



@Data
public class Reserva {
    private Integer idReserva;
    private LocalDateTime  fecha_entrega;
    private Integer forma_de_pago;
    private Integer Estados_idEstados;

   public Reserva(Integer idReserva,LocalDateTime  fecha_entrega, Integer forma_de_pago){
    this.idReserva = idReserva;
    this.Estados_idEstados = 1; 
    this.fecha_entrega = fecha_entrega; 
    this.forma_de_pago = forma_de_pago;
   }

}
