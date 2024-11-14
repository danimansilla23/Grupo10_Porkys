package com.mycompany.porky.dao;

import java.util.List;

import com.mycompany.porky.models.ProductoCarrito;

public interface ProductoCarritoDao {
    void addProduct(ProductoCarrito producto);
    List<ProductoCarrito> getProducts(Integer idCarrito);

}
