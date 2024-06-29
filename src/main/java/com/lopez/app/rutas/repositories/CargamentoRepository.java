package com.lopez.app.rutas.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.lopez.app.rutas.models.Cargamento;

public class CargamentoRepository implements IRepository<Cargamento> {
    Connection conn;

    public CargamentoRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Cargamento> lista() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lista'");
    }

    @Override
    public Cargamento get(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public void guardar(Cargamento t) throws SQLException {
        String sql = "";
        if (t.getId() != null && t.getId() > 0) {
            sql = "UPDATE CARGAMENTOS SET ID_RUTA=?, DESCRIPCION=?, " +
                    "PESO=?, ESTATUS=? " +
                    "WHERE ID_CARGAMENTO=?";
        } else {
            sql = "INSERT INTO CARGAMENTOS (ID_CARGA,ID_RUTA, " +
                    "DESCRIPCION, PESO,ESTATUS) VALUES (SEQUENCE_5.NEXTVAL,?, ?, ?, ?)";
        }

        try (PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setLong(1, t.getIdRuta());
            stm.setString(2, t.getDescripcion());
            stm.setFloat(3, t.getPeso());
            stm.setInt(4, t.getEstatus());
            if (t.getId() != null && t.getId() > 0) {
                stm.setLong(5, t.getId());
            }
            stm.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

}
