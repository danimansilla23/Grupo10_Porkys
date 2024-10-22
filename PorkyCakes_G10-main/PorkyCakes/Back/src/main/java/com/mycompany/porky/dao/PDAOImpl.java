package com.mycompany.porky.dao;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.mycompany.porky.models.Product;

public class PDAOImpl implements PDao {
    private final Sql2o sql2o;

    public PDAOImpl() {
        this.sql2o = Sql2oDAO.getSql2o(); 
    }

    @Override
    public void add(Product product) {
        String insertSQL = "INSERT INTO productos (Nombre_Producto, Precio_vta, tamaño, descripcion_producto, ProductosBase_idProductosBase, imagen_url) VALUES (:nombre_product, :precio_vta, :tamaño, :descripcion_product, :id_pbase, :imagen_url)";
        try (Connection con = sql2o.open()) {
            con.createQuery(insertSQL)
                .addParameter("nombre_product", product.getNombre_product())
                .addParameter("precio_vta", product.getPrecio_vta())
                .addParameter("tamaño", product.getTamaño())
                .addParameter("descripcion_product", product.getDescripcion_product())
                .addParameter("id_pbase", product.getId_pbase()) 
                .addParameter("imagen_url", product.getImagen_url())
                .executeUpdate();
        }
    }

    
}

