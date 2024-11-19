 package com.mycompany.porky.services;

 import static spark.Spark.*;

import com.mycompany.porky.controllers.CarritoController;
import com.mycompany.porky.controllers.ReservaController;
 
 public class CarritoService {
     public static void main(String[] args) {
 
         before((request, response) -> {
             response.header("Access-Control-Allow-Origin", "*"); // Permitir todas las fuentes
             response.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS"); // Métodos permitidos
             response.header("Access-Control-Allow-Headers", "Content-Type, Authorization"); // Encabezados permitidos
         });
 
         // Responder a cualquier solicitud OPTIONS con un código de éxito 200
         options("/*", (request, response) -> {
             String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
             if (accessControlRequestHeaders != null) {
                 response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
             }
 
             String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
             if (accessControlRequestMethod != null) {
                 response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
             }
 
             response.status(200); // Respuesta exitosa para las solicitudes preflight
             return "OK";
         });
 
         post("/carrito/:idCliente/",CarritoController.add);
         get("/getcarrito/:idCliente/",CarritoController.view);

         post("/reserva/:idCliente/",ReservaController.add);
 
     }
 }

 //http://localhost:4567/carrito/4/?idProducto=4&cantidad=3&precio=300
 //http://localhost:4567/getcarrito/1/
 //http://localhost:4567/reserva/11/?fecha_entrega=2024-11-18 15:30&forma_de_pago=1 