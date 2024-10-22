package com.mycompany.porky.dao;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.mycompany.porky.models.ProductoCarrito;

public class ProductoCarritoDaoImpl implements ProductoCarritoDao {
    private final Sql2o sql2o;

    public ProductoCarritoDaoImpl() {
        this.sql2o = Sql2oDAO.getSql2o(); 
    }


    @Override
    public void addProduct(ProductoCarrito producto) {
        String insertSQL = "INSERT INTO carritos_x_productos (Carritos_idCarrito, Productos_idProductos, cantidad, precio, observacion) " +
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

