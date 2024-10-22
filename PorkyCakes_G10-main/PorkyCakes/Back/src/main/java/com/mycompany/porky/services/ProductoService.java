/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

 package com.mycompany.porky.services;

 import com.mycompany.porky.controllers.PController;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;
 
 public class ProductoService {
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
 
         post("/addProduct", PController.add);
         get("/getPBase", PController.getPBase); // Ruta para obtener productos base
 
     }
 }

 //http://localhost:4567/addProduct?nombre_product=Chesscake&precio_vta=3000&tamaño=18&descripcion_product=Sin tac&imagen_url=/torta_frutilla.jpg