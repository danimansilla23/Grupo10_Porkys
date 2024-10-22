package com.mycompany.porky.dao;

import com.mycompany.porky.models.PBase;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;

public class PBaseDAOImpl implements PBaseDao {
    private final Sql2o sql2o;

    public PBaseDAOImpl() {
        this.sql2o = Sql2oDAO.getSql2o();
    }

    @Override
    public List<PBase> getAll() {
        String selectSQL = "SELECT idProductosBase, Nombre_Base, Descripcion FROM productosbase";
        try (Connection con = sql2o.open()) {
            return con.createQuery(selectSQL)
                    .executeAndFetch(PBase.class);
        }
    }
}

