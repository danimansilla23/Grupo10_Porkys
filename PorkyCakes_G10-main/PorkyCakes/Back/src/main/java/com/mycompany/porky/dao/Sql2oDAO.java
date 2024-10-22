package com.mycompany.porky.dao;

import org.sql2o.Sql2o;

public class Sql2oDAO {

    protected static Sql2o sql2o;

    public static Sql2o getSql2o() {
        if (sql2o == null) {
            sql2o = new Sql2o("jdbc:mysql://localhost:3306/Porky", "root",
                    "");
        }
        return sql2o;
    }

}
