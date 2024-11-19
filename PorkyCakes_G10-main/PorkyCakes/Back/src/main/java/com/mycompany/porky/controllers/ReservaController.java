package com.mycompany.porky.controllers;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.mycompany.porky.dao.*;
import com.mycompany.porky.models.*;

import spark.Request;
import spark.Response;
import spark.Route;

public class ReservaController {
    private final static CarritoDaoImpl CarritoDao = new CarritoDaoImpl();
    private final static ReservaDaoImpl ReservaDao = new ReservaDaoImpl();


    public static Route add = (Request request, Response response) -> {
        response.type("application/json");

        try {
            Integer idClliente = Integer.valueOf(request.params(":idCliente"));
            String forma_de_pagoparam = request.queryParams("forma_de_pago");
            String fecha_entregaparam = request.queryParams("fecha_entrega");

            if (forma_de_pagoparam == null || fecha_entregaparam == null) {
                response.status(400);
                return new Gson().toJson("Error: Todos los parámetros son requeridos.");
            }

            Integer forma_de_pago = Integer.valueOf(forma_de_pagoparam);

            //String withTime = "2014-01-01 12:30";
            DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime fecha_entrega = LocalDateTime.parse(fecha_entregaparam, formatterWithTime);


            Carrito carrito = CarritoDao.buscarCarrito(idClliente);
            if (carrito == null) {
                response.status(404);
                return new Gson().toJson("Carrito no encontrado o vacio");
            }

            Reserva r = new Reserva(null,fecha_entrega,forma_de_pago);
            Integer idReserva = ReservaDao.add(r);

            carrito.setReservas_idReservas(idReserva);
            CarritoDao.update(carrito);
            
            System.out.println(r.getEstados_idEstados());
            response.status(201);
            return new Gson().toJson("Reserva agregada exitosamente.");
        } catch (NumberFormatException e) {
            response.status(400);
            return new Gson().toJson("Error");
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson("Error controlador: " + e.getMessage());
        }
    };

}
