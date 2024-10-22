package com.mycompany.porky.models;

import lombok.Data;



@Data
public class ProductoCarrito {
    private Integer idProductoCarrito;
    private Integer idCarrito;
    private Integer precio;
    private String observacion;
    private Integer idProducto;
    private Integer cantidad;

   public ProductoCarrito(Integer idProductoCarrito,Integer idCarrito, Integer precio,String observacion, Integer idProducto, Integer cantidad){
     this.idProductoCarrito = idProductoCarrito;
    this.idCarrito = idCarrito;
     this.cantidad = cantidad;
     this.precio = precio;
     this.idProducto = idProducto;
     this.observacion = observacion;
   }

}
