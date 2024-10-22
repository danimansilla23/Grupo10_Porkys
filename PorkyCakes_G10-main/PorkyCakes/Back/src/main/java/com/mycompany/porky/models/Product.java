package com.mycompany.porky.models;
import lombok.Data;



@Data
public class Product {
    private Integer idproduct;
    private String nombre_product;
    private Integer precio_vta;
    private Integer tamaño;
    private String descripcion_product;
    private Integer id_pbase;
    private String imagen_url;

    public Product(Integer idproduct, String nombre_product, Integer precio_vta, Integer tamaño,
                   String descripcion_product, String imagen_url) {
        this.idproduct = idproduct;
        this.nombre_product = nombre_product;
        this.precio_vta = precio_vta;
        this.tamaño = tamaño;
        this.descripcion_product = descripcion_product;
        this.imagen_url = imagen_url;
        this.id_pbase = null; 
    }    

    public void setId_pbase(Integer id_pbase) {
        this.id_pbase = id_pbase;
    }

}
