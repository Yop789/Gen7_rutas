package com.lopez.app.rutas.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lopez.app.rutas.models.Camion;
import com.lopez.app.rutas.models.enums.Marcas;
import com.lopez.app.rutas.models.enums.Tipos;

public class CamionesRepository implements IRepository<Camion> {
    private Connection conn;

    public CamionesRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Camion> lista() throws SQLException {
        List<Camion> camiones = new ArrayList<>();
        try (Statement stm = this.conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM CAMIONES")) {
            while (rs.next()) {
                Camion a = this.getCamion(rs);
                camiones.add(a);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return camiones;

    }

    @Override
    public Camion get(Long id) throws SQLException {
        Camion camion = null;
        try (PreparedStatement stm = conn.prepareStatement("SELECT * FROM CAMIONES WHERE ID_CAMION=?")) {
            stm.setLong(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    camion = this.getCamion(rs);
                }
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return camion;
    }

    @Override
    public void guardar(Camion t) throws SQLException {
        String sql = "";
        if (t.getId() != null && t.getId() > 0) {
            sql = "Update camiones set MATRICULA=?, " +
                    "TIPO_CAMION=?, MODELO=?, MARCA=?, " +
                    "CAPACIDAD=?, KILOMETRAJE=?, DISPONIBILIDAD=? " +
                    "where ID_CAMION=?";
        } else {

            sql = "INSERT INTO CAMIONES(ID_CAMION, MATRICULA, " +
                    "TIPO_CAMION, MODELO, MARCA, " +
                    "CAPACIDAD, KILOMETRAJE, DISPONIBILIDAD )" +
                    "VALUES (SEQUENCE_2.NEXTVAL,?,?,?,?,?,?,?)";
        }
        try (PreparedStatement stm = conn.prepareStatement(sql)) {
            if (t.getId() != null && t.getId() > 0) {
                stm.setInt(1, t.getModelo());
                stm.setString(1, t.getMatricula());
                stm.setString(3, t.getTipoCamion().toString());
                stm.setString(4, t.getMarca().toString());
                stm.setInt(5, t.getCapacidad());
                stm.setDouble(6, t.getKilometros());
                stm.setInt(7, t.getDisponibilidad() ? 1 : 0);
                stm.setLong(8, t.getId());
            } else {
                stm.setString(1, t.getMatricula());
                stm.setString(2, t.getTipoCamion().toString());
                stm.setInt(3, t.getModelo());
                stm.setString(4, t.getMarca().toString());
                stm.setInt(5, t.getCapacidad());
                stm.setDouble(6, t.getKilometros());
                stm.setInt(7, t.getDisponibilidad() ? 1 : 0);

            }
            stm.executeUpdate();
        }

    }

    @Override
    public void eliminar(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

    private Camion getCamion(ResultSet rs) throws SQLException {
        Camion camion = new Camion();
        camion.setId(rs.getLong("ID_CAMION"));
        camion.setModelo(rs.getInt("MODELO"));
        camion.setMatricula(rs.getString("MATRICULA"));
        camion.setTipoCamion(Tipos.valueOf(rs.getString("TIPO_CAMION")));
        camion.setMarca(Marcas.valueOf(rs.getString("MARCA")));
        camion.setCapacidad(rs.getInt("CAPACIDAD"));
        camion.setKilometros(rs.getDouble("KILOMETRAJE"));
        camion.setDisponibilidad(rs.getBoolean("DISPONIBILIDAD"));
        return camion;
    }

}
