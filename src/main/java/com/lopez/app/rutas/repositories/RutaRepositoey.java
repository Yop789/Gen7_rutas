package com.lopez.app.rutas.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.lopez.app.rutas.models.Ruta;

public class RutaRepositoey implements IRutasRepository {
    private Connection conn;

    public RutaRepositoey(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Ruta> lista() throws SQLException {
        String sql = "";
        List<Ruta> rutas = new ArrayList<>();
        sql = "SELECT * FROM RUTAS";
        try (Statement stm = this.conn.createStatement();
                ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                Ruta a = this.getRuta(rs);
                rutas.add(a);
            }
        }

        return rutas;

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
        sql = "INSERT INTO RUTAS (ID_RUTA,ID_CAMION,ID_DIRECCION_ORIGEN," +
                "ID_DIRECCION_DESTINO,ID_CHOFER,DISTANCIA,FECHA_SALIDA, " +
                "FECHA_LLEGADA_ESTIMADA,FECHA_LLEGADA_REAL,A_TIEMPO) " +
                "VALUES (SEQUENCE_4.NEXTVAL,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement stm = conn.prepareStatement(sql, new String[] { "ID_RUTA" })) {

            stm.setLong(1, ruta.getCamionId());

            stm.setLong(2, ruta.getDireccionOriginalId());
            stm.setLong(3, ruta.getDireccionDestinoId());
            stm.setLong(4, ruta.getChoferId());
            stm.setFloat(5, ruta.getDiatancio());
            stm.setTimestamp(6, Timestamp.valueOf(ruta.getFechaSalida()));
            stm.setTimestamp(7, Timestamp.valueOf(ruta.getFechaLlegadaEstimada()));
            stm.setTimestamp(8, Timestamp.valueOf(ruta.getFechaLlegadaEstimada()));
            stm.setInt(9, ruta.getaTiempo());

            int executeUpdate = stm.executeUpdate();

            if (executeUpdate > 0) {
                try (ResultSet rs = stm.getGeneratedKeys()) {
                    if (rs.next()) {
                        resultado = rs.getLong(1);
                    }

                }
            }

            return resultado;

        }

    }

    private Ruta getRuta(ResultSet rs) throws SQLException {
        Ruta a = new Ruta();
        a.setId(rs.getLong("ID_RUTA"));
        a.setCamionId(rs.getLong("ID_CAMION"));
        a.setDireccionOriginalId(rs.getLong("ID_DIRECCION_ORIGEN"));
        a.setDireccionDestinoId(rs.getLong("ID_DIRECCION_DESTINO"));
        a.setChoferId(rs.getLong("ID_CHOFER"));
        a.setDiatancio(rs.getFloat("DISTANCIA"));
        a.setFechaSalida(rs.getTimestamp("FECHA_SALIDA").toLocalDateTime());
        a.setFechaLlegadaEstimada(rs.getTimestamp("FECHA_LLEGADA_ESTIMADA").toLocalDateTime());
        a.setFechaLlegadaReal(rs.getTimestamp("FECHA_LLEGADA_REAL").toLocalDateTime());
        a.setaTiempo(rs.getInt("A_TIEMPO"));
        return a;
    }

}
