package com.mycompany.porky.dao;

import com.mycompany.porky.models.Carrito;

public interface CarritoDao {
    void add(Carrito carrito);
    Carrito buscarCarrito(int id_cliente);

}
