package com.mycompany.porky.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import java.lang.reflect.Field;


import com.mycompany.porky.models.Reserva;

public class ReservaDaoImpl implements ReservaDao {
    private final Sql2o sql2o;

    public ReservaDaoImpl() {
        this.sql2o = Sql2oDAO.getSql2o();
    }

  

    @Override
    public Integer add(Reserva r) {
        Class<?> cls = r.getClass(); // Obtiene la clase de la instancia
        Field[] fields = cls.getDeclaredFields(); // Obtiene todos los atributos declarados en la clase
    
        StringBuilder columnsInsertSQL = new StringBuilder("(");
        StringBuilder valuesInsertSQL = new StringBuilder("(");
    
        try {
            for (Field field : fields) {
                field.setAccessible(true); // obtenemos atributos
                String name = field.getName();
                Object value = field.get(r); // obtenemos valores
    
                if (value == null || name.equals("idReserva")) {
                    continue; // Excluye  `idReserva`
                }
    
                columnsInsertSQL.append(name).append(", ");
                valuesInsertSQL.append(":").append(name).append(", ");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    
        // Elimina la coma final y cierra los paréntesis
        columnsInsertSQL.setLength(columnsInsertSQL.length() - 2);
        columnsInsertSQL.append(")");
        valuesInsertSQL.setLength(valuesInsertSQL.length() - 2);
        valuesInsertSQL.append(")");
    
        String insertSQL = "INSERT INTO Reservas " + columnsInsertSQL + " VALUES " + valuesInsertSQL;
    
        try (Connection con = sql2o.open()) {
            Query query = con.createQuery(insertSQL);
    
            // Mapeo explícito de parámetros
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(r);
                if (value != null) {
                    query.addParameter(field.getName(), value);
                }
            }
    
            query.executeUpdate();
    
            // Obtiene el ID generado
            String idQuery = "SELECT LAST_INSERT_ID()";
            return con.createQuery(idQuery).executeScalar(Integer.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
}
    
