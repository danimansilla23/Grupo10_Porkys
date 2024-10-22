package com.mycompany.porky.controllers;

import com.google.gson.Gson;
import com.mycompany.porky.dao.CarritoDaoImpl;
import com.mycompany.porky.dao.ProductoCarritoDaoImpl;
import com.mycompany.porky.models.Carrito;
import com.mycompany.porky.models.ProductoCarrito;

import spark.Request;
import spark.Response;
import spark.Route;

public class CarritoController {
    private final static CarritoDaoImpl CarritoDao = new CarritoDaoImpl(); 
    private final static ProductoCarritoDaoImpl ProductoCarritoDao = new ProductoCarritoDaoImpl(); 

    public static Route add = (Request request, Response response) -> {
        response.type("application/json");
    
        try {
            Integer idCliente = Integer.valueOf(request.params(":idCliente"));
            String observacion = request.queryParams("observacion");
            String idProductoparam = request.queryParams("idProducto"); 
            String cantidadparam = request.queryParams("cantidad"); 
            String precioparam = request.queryParams("precio"); 

            if (idProductoparam == null || cantidadparam == null || precioparam == null ) {
                response.status(400); 
                return new Gson().toJson("Error: Todos los parámetros son requeridos.");
            }

            Integer idProducto = Integer.valueOf(idProductoparam);
            Integer cantidad = Integer.valueOf(cantidadparam);
            Integer precio = Integer.valueOf(precioparam);
           
            Carrito carrito = CarritoDao.buscarCarrito(idCliente);
            if (carrito == null) {
                carrito = new Carrito(null, idCliente); 
                CarritoDao.add(carrito); 
                carrito = CarritoDao.buscarCarrito(idCliente);
            }
            
            
            ProductoCarrito pd = new ProductoCarrito(null,carrito.getIdCarrito(), precio,observacion,idProducto,cantidad);
            ProductoCarritoDao.addProduct(pd); 
    
            response.status(201); 
            return new Gson().toJson("Producto agregado exitosamente.");
        } catch (NumberFormatException e) {
            response.status(400); 
            return new Gson().toJson("Error");
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson("Error controlador: " + e.getMessage());
        }
    };
    


}
