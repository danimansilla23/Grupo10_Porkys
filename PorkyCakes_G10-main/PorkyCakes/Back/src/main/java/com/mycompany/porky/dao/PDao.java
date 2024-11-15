package com.mycompany.porky.dao;

import com.mycompany.porky.models.Product;

public interface PDao {
    void add(Product product);
    String getInfoProductById(Integer idProducto);
}
