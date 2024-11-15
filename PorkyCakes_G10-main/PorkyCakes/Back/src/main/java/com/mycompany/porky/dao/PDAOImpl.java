package com.mycompany.porky.dao;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
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
public String getInfoProductById(Integer idProducto) {
    String selectSQL = "SELECT idProductos, Nombre_Producto, Precio_vta, tamaño " +
                       "FROM productos WHERE idProductos = :idProducto";

    try (Connection con = sql2o.open()) {
        Product product = con.createQuery(selectSQL)
                .addParameter("idProducto", idProducto)
                .addColumnMapping("idProductos", "idproduct")  
                .addColumnMapping("Nombre_Producto", "nombre_product")
                .addColumnMapping("Precio_vta", "precio_vta")
                .addColumnMapping("tamaño", "tamaño")
                .executeAndFetchFirst(Product.class);

        if (product != null) {
            
            Map<String, Object> productoInfo = new LinkedHashMap<>();
            productoInfo.put("precio", product.getPrecio_vta());
            productoInfo.put("tamaño", product.getTamaño() + " cm");
            productoInfo.put("subtotal", 0); 
            productoInfo.put("idProducto", product.getIdproduct());
            productoInfo.put("cantidad", 0); // La cantidad se agregará luego
            productoInfo.put("nombreProducto", product.getNombre_product());

            Gson gson = new Gson();
            return gson.toJson(productoInfo);
        }
    } catch (Exception e) {
        System.err.println("Error al obtener la información del producto: " + e.getMessage());
    }

    return null;
}

}
