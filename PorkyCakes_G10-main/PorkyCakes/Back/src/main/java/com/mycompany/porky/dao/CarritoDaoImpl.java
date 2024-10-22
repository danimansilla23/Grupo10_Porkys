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
        String insertSQL = "INSERT INTO Carritos (estado, Fecha_creacion, Clientes_idCliente) VALUES (:estado, :fecha_creacion, :idCliente)";
        try (Connection con = sql2o.open()) {
            con.createQuery(insertSQL)
                .addParameter("estado", carrito.getEstado())
                .addParameter("fecha_creacion", new java.sql.Date(carrito.getFecha_creacion().getTime())) // Convierte a Date SQL
                .addParameter("idCliente", carrito.getClientes_idCliente())
                .executeUpdate();
        }

    }

    @Override
    public Carrito buscarCarrito(int id_cliente) {
        String selectSQL = "SELECT idCarrito, Estado, DATE(Fecha_creacion) as Fecha_creacion, Clientes_idCliente " +
                   "FROM carritos WHERE Clientes_idCliente = :id_cliente AND estado = 1";
        try (Connection con = sql2o.open()) {
            return  con.createQuery(selectSQL)
                .addParameter("id_cliente", id_cliente)
                .executeAndFetchFirst(Carrito.class); 
        } 
    }


 

    
}

