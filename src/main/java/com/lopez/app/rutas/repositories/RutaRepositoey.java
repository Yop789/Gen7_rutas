package com.lopez.app.rutas.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.lopez.app.rutas.models.Ruta;

public class RutaRepositoey implements IRutasRepository {
    private Connection conn;

    public RutaRepositoey(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Ruta> lista() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lista'");
    }

    @Override
    public Ruta get(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public void guardar(Ruta t) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

    @Override
    public Long guardarReturID(Ruta ruta) throws SQLException {
        String sql;
        Long resultado = -1L;
        sql = "INSERT INTO RUTAS (ID_RURA,CAMION_ID,CHOFER_ID,DIRECCION_ORIGINAL_ID," +
                "DIRECCION_DESTINO_ID,DIATANCIO,FECHA_SALIDA,FECHA_LLEGADA_ESTIMADA," +
                "FECHA_LLEGADA_REAL,ATIEMPO) VALUES (SEQUENCE_4.NEXTVAL,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement stm = conn.prepareStatement(sql, new String[] { "ID_RURA" })) {

            stm.setLong(1, ruta.getCamionId());
            stm.setLong(2, ruta.getChoferId());
            stm.setLong(3, ruta.getDireccionOriginalId());
            stm.setLong(4, ruta.getDireccionDestinoId());
            stm.setFloat(5, ruta.getDiatancio());
            stm.setDate(6, Date.valueOf(ruta.getFechaSalida()));
            stm.setDate(7, Date.valueOf(ruta.getFechaLlegadaEstimada()));
            stm.setDate(8, Date.valueOf(ruta.getFechaLlegadaEstimada()));
            stm.setInt(9, ruta.getaTiempo());
            stm.executeUpdate();
            ResultSet rs = stm.getGeneratedKeys();

            if (rs.next()) {
                resultado = rs.getLong(1);
            }

        }
        return resultado;
    }

}
