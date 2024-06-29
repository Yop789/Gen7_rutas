package com.lopez.app.rutas.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lopez.app.rutas.models.Direccion;

public class DireccionRepository implements IRepository<Direccion> {
    private Connection conn;

    public DireccionRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Direccion> lista() throws SQLException {
        List<Direccion> direcciones = new ArrayList<>();
        String sql = "SELECT * FROM DIRECCIONES";
        try (Statement stm = this.conn.createStatement();
                ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                Direccion d = this.getDireccion(rs);
                direcciones.add(d);
            }
        }
        return direcciones;
    }

    @Override
    public Direccion get(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public void guardar(Direccion t) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

    private Direccion getDireccion(ResultSet rs) throws SQLException {
        Direccion d = new Direccion();
        d.setId(rs.getLong("ID_DIRECCION"));
        d.setCalle(rs.getString("CALLE"));
        d.setNumero(rs.getString("NUMERO"));
        d.setColonia(rs.getString("COLONIA"));
        d.setCp(rs.getString("CP"));
        d.setCiudad(rs.getString("CIUDAD"));
        d.setEstado(rs.getString("ESTADO"));
        return d;
    }

}
