package com.mycompany.porky.models;
import java.sql.Date;

import lombok.Data;



@Data
public class Carrito {
    private Integer idCarrito;
    private Date Fecha_creacion;
    private Integer Clientes_idCliente;
    private Integer Reservas_idReservas;

   public Carrito(Integer idCarrito, Integer IdCliente){
    this.idCarrito = idCarrito;
    this.Fecha_creacion = new Date(System.currentTimeMillis()); // Fecha actual
    this.Clientes_idCliente = IdCliente;
   }

}
