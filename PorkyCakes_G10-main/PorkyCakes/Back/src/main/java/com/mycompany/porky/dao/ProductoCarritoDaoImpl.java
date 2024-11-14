package com.mycompany.porky.dao;

import java.util.ArrayList;
import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.mycompany.porky.models.ProductoCarrito;

public class ProductoCarritoDaoImpl implements ProductoCarritoDao {
    private final Sql2o sql2o;

    public ProductoCarritoDaoImpl() {
        this.sql2o = Sql2oDAO.getSql2o();
    }

    @Override
    public List<ProductoCarrito> getProducts(Integer idCarrito) {
        String selectSQL = "SELECT Productos_idProductos AS idProducto, cantidad, precio, observacion " +
                "FROM carritos_x_productos " +
                "WHERE Carritos_idCarrito = :idCarrito";

        try (Connection con = sql2o.open()) {
            return con.createQuery(selectSQL)
                    .addParameter("idCarrito", idCarrito)
                    .executeAndFetch(ProductoCarrito.class);
        } catch (Exception e) {
            System.err.println("Error al obtener productos del carrito: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void addProduct(ProductoCarrito producto) {
        String insertSQL = "INSERT INTO carritos_x_productos (Carritos_idCarrito, Productos_idProductos, cantidad, precio, observacion) "
                +
                "VALUES (:idCarrito, :idProducto, :cantidad, :precio, :observacion)";

        try (Connection con = sql2o.open()) {
            con.createQuery(insertSQL)
                    .addParameter("idCarrito", producto.getIdCarrito())
                    .addParameter("idProducto", producto.getIdProducto())
                    .addParameter("cantidad", producto.getCantidad())
                    .addParameter("precio", producto.getPrecio())
                    .addParameter("observacion", producto.getObservacion()) // Puede ser null
                    .executeUpdate();
        } catch (Exception e) {

        }
    }

}
