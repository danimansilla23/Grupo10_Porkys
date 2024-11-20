package com.mycompany.porky.dao;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.mycompany.porky.models.Carrito;

public class CarritoDaoImpl implements CarritoDao {
    private final Sql2o sql2o;

    public CarritoDaoImpl() {
        this.sql2o = Sql2oDAO.getSql2o();
    }

    @Override
    public void add(Carrito carrito) {
        String insertSQL = "INSERT INTO Carritos (Fecha_creacion, Clientes_idCliente) VALUES (:fecha_creacion, :idCliente)";
        try (Connection con = sql2o.open()) {
            con.createQuery(insertSQL)
                    .addParameter("fecha_creacion", new java.sql.Date(carrito.getFecha_creacion().getTime())) // Convierte
                                                                                                              // a Date
                                                                                                              // SQL
                    .addParameter("idCliente", carrito.getClientes_idCliente())
                    .executeUpdate();
        }

    }

    @Override
    public void update(Carrito carrito) {
        String updateSQL = "UPDATE Carritos SET Reserva_idReservas = :idReserva WHERE idCarrito = :idCarrito";

        try (Connection con = sql2o.open()) {
            con.createQuery(updateSQL)
                    .addParameter("idReserva", carrito.getReservas_idReservas()) 
                    .addParameter("idCarrito", carrito.getIdCarrito()) 
                    .executeUpdate(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Carrito buscarCarrito(int id_cliente) {
        String selectSQL = "SELECT idCarrito, DATE(Fecha_creacion) as Fecha_creacion, Clientes_idCliente " +
                           "FROM carritos WHERE Clientes_idCliente = :id_cliente AND Reserva_idReservas IS NULL";
        try (Connection con = sql2o.open()) {
            return con.createQuery(selectSQL)
                      .addParameter("id_cliente", id_cliente)
                      .executeAndFetchFirst(Carrito.class);
        }
    }
    

}
