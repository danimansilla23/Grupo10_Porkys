package com.mycompany.porky.controllers;

import com.google.gson.Gson;
import com.mycompany.porky.dao.PDAOImpl;
import com.mycompany.porky.dao.PDao;
import com.mycompany.porky.models.Product;

import spark.Request;
import spark.Response;
import spark.Route;

public class PController {
    private final static PDao PDao = new PDAOImpl(); 

    public static Route add = (Request request, Response response) -> {
        response.type("application/json");
    
        try {
            String nombre_product = request.queryParams("nombre_product");
            String precio_vtaParam = request.queryParams("precio_vta");
            String tamañoParam = request.queryParams("tamaño");
            String descripcion_product = request.queryParams("descripcion_product");
            String id_pbaseParam = request.queryParams("id_pbase"); 
            String imagen_url = request.queryParams("imagen_url"); 
    
            // Verificar que los parámetros requeridos estén presentes
            if (nombre_product == null || precio_vtaParam == null || tamañoParam == null || 
                descripcion_product == null || imagen_url == null) {
                response.status(400); 
                return new Gson().toJson("Error: Todos los parámetros son requeridos.");
            }
    
            Integer precio_vta = Integer.valueOf(precio_vtaParam);
            Integer tamaño = Integer.valueOf(tamañoParam);
            Integer id_pbase = id_pbaseParam != null ? Integer.valueOf(id_pbaseParam) : null; 
    
            Product product = new Product(null, nombre_product, precio_vta, tamaño, descripcion_product, imagen_url); 
    
            if (id_pbase != null) {
                product.setId_pbase(id_pbase);
            }

            PDao.add(product); 
    
            response.status(201); 
            return new Gson().toJson("Producto agregado exitosamente.");
        } catch (NumberFormatException e) {
            response.status(400); 
            return new Gson().toJson("Error: Precio de venta y tamaño deben ser números.");
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson("Error controlador: " + e.getMessage());
        }
    };
    


}
