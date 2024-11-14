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

    @Override
    public Product getProductById(Integer idProducto) {
        String selectSQL = "SELECT idProductos, Nombre_Producto, Precio_vta, tamaño, descripcion_producto, ProductosBase_idProductosBase, imagen_url " +
                           "FROM productos WHERE idProductos = :idProducto";
    
        try (Connection con = sql2o.open()) {
            return con.createQuery(selectSQL)
                    .addParameter("idProducto", idProducto)
                    .addColumnMapping("idProductos", "idproduct")  // Mapeo explícito para el ID
                    .addColumnMapping("Nombre_Producto", "nombre_product")  // Mapeo explícito para el nombre
                    .addColumnMapping("Precio_vta", "precio_vta")  // Mapeo explícito para el precio
                    .addColumnMapping("tamaño", "tamaño")  // Mapeo explícito para el tamaño
                    .addColumnMapping("descripcion_producto", "descripcion_product")  // Mapeo explícito para la descripción
                    .addColumnMapping("ProductosBase_idProductosBase", "id_pbase")  // Mapeo explícito para el id_pbase
                    .addColumnMapping("imagen_url", "imagen_url")  // Mapeo explícito para la URL de la imagen
                    .executeAndFetchFirst(Product.class);  // Mapea a la clase Product
        } catch (Exception e) {
            System.err.println("Error al obtener producto: " + e.getMessage());
            return null;
        }
    }
    


    
}

