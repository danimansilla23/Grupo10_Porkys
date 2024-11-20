package com.mycompany.porky.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.mycompany.porky.dao.CarritoDaoImpl;
import com.mycompany.porky.dao.PDAOImpl;
import com.mycompany.porky.dao.PDao;
import com.mycompany.porky.dao.ProductoCarritoDaoImpl;
import com.mycompany.porky.models.Carrito;
import com.mycompany.porky.models.ProductoCarrito;
import com.mycompany.porky.models.Product;

import spark.Request;
import spark.Response;
import spark.Route;

public class CarritoController {
    private final static CarritoDaoImpl CarritoDao = new CarritoDaoImpl();
    private final static ProductoCarritoDaoImpl ProductoCarritoDao = new ProductoCarritoDaoImpl();
    private final static PDao PDao = new PDAOImpl();

    public static Route view = (Request request, Response response) -> {
        response.type("application/json");

        try {
            Integer idCliente = Integer.valueOf(request.params(":idCliente"));

            Carrito carrito = CarritoDao.buscarCarrito(idCliente);
            if (carrito == null) {
                response.status(404);
                return new Gson().toJson("El cliente no cuenta con productos en el carrito");
            }

            Integer idCarrito = carrito.getIdCarrito();
            List<ProductoCarrito> productosCarrito = ProductoCarritoDao.getProducts(idCarrito);

            double total = 0.0;
            List<Map<String, Object>> productos = new ArrayList<>();

            for (ProductoCarrito productoCarrito : productosCarrito) {
                
                Product product = PDao.getProductById(productoCarrito.getIdProducto());
                if (product == null) {
                    System.out.println("Producto no encontrado con ID: " + productoCarrito.getIdProducto());
                }

                Map<String, Object> productoInfo = new HashMap<>();
                productoInfo.put("idProducto", product.getIdproduct());
                productoInfo.put("nombreProducto", product.getNombre_product());
                productoInfo.put("precio", product.getPrecio_vta());
                productoInfo.put("cantidad", productoCarrito.getCantidad());
                productoInfo.put("tamaño", product.getTamaño() + " cm");

                double subtotal = product.getPrecio_vta() * productoCarrito.getCantidad();
                productoInfo.put("subtotal", subtotal);

                productos.add(productoInfo);

                total += subtotal;
            }

            Map<String, Object> responseData = new HashMap<>();
            responseData.put("idCarrito", carrito.getIdCarrito());
            responseData.put("productos", productos);
            responseData.put("total", total);

            return new Gson().toJson(responseData);

        } catch (NumberFormatException e) {
            response.status(400);
            return new Gson().toJson("Error: formato de número inválido");
        } catch (Exception e) {
            response.status(500);
            return new Gson().toJson("Error controlador: " + e.getMessage());
        }
    };

    public static Route add = (Request request, Response response) -> {
        response.type("application/json");

        try {
            Integer idCliente = Integer.valueOf(request.params(":idCliente"));
            String observacion = request.queryParams("observacion");
            String idProductoparam = request.queryParams("idProducto");
            String cantidadparam = request.queryParams("cantidad");
            String precioparam = request.queryParams("precio");

            if (idProductoparam == null || cantidadparam == null || precioparam == null) {
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

            ProductoCarrito pd = new ProductoCarrito(null, carrito.getIdCarrito(), precio, observacion, idProducto,
                    cantidad);
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
