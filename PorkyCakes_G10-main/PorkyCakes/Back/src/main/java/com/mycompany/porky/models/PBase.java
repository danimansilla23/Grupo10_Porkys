package com.mycompany.porky.models;

import lombok.Data;

@Data
public class PBase {
    private Integer idProductosBase;
    private String Nombre_Base; // Cambiar a Nombre_Base
    private String Descripcion;

    public PBase(Integer idProductosBase, String Nombre_Base, String descripcion) {
        this.idProductosBase = idProductosBase;
        this.Nombre_Base = Nombre_Base; // Cambiar a Nombre_Base
        this.Descripcion = descripcion;
    }
}

