package com.lopez.app.rutas.repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.lopez.app.rutas.models.Chofer;

public class CoferesRepository implements IRepository<Chofer> {
    private Connection conn;

    public CoferesRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Chofer> lista() throws SQLException {
        List<Chofer> choferes = new ArrayList<>();
        try (Statement stm = this.conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM CHOFERES")) {
            while (rs.next()) {
                Chofer a = this.getChofer(rs);
                choferes.add(a);

            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }

        return choferes;
    }

    @Override
    public Chofer get(Long id) throws SQLException {
        Chofer chofer = null;
        try (PreparedStatement stm = conn.prepareStatement("SELECT * FROM CHOFERES WHERE ID_CHOFER=?")) {
            stm.setLong(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    chofer = this.getChofer(rs);

                }
            }

        } catch (SQLException e) {
            throw new SQLException(e);
        }

        return chofer;
    }

    @Override
    public void guardar(Chofer chofer) throws SQLException {
        String sql = "";
        if (chofer.getId() != null && chofer.getId() > 0) {
            sql = "UPDATE CHOFERES SET NOMBRE=?, APATERNO=?, " +
                    "AMATERNO=?, LICENCIAS=?, TELEFONO=?, " +
                    "FECHA_NACIMIENTO=?, DISPONIBILIDAD=? " +
                    "WHERE ID_CHOFER=?";

        } else {
            sql = "INSERT INTO CHOFERES(ID_CHOFER, NOMBRE, " +
                    "APATERNO, AMATERNO, LICENCIAS, TELEFONO," +
                    "FECHA_NACIMIENTO, DISPONIBILIDAD )" +
                    "VALUES (-1,?,?,?,?,?,?,?)";
        }

        try (PreparedStatement stm = conn.prepareStatement(sql)) {
            if (chofer.getId() != null && chofer.getId() > 0) {
                stm.setString(1, chofer.getNombre());
                stm.setString(2, chofer.getApMaternos());
                stm.setString(3, chofer.getApMaternos());
                stm.setString(4, chofer.getLicencias());
                stm.setString(5, chofer.getTelefono());
                stm.setDate(6, Date.valueOf(chofer.getFechaNacimiento()));
                stm.setInt(7, chofer.getDiponivilidad() ? 1 : 0);
                stm.setLong(8, chofer.getId());

            } else {

                stm.setString(1, chofer.getNombre());
                stm.setString(2, chofer.getApPaternos());
                stm.setString(3, chofer.getApMaternos());
                stm.setString(4, chofer.getLicencias());
                stm.setString(5, chofer.getTelefono());
                stm.setDate(6, Date.valueOf(chofer.getFechaNacimiento()));
                stm.setInt(7, chofer.getDiponivilidad() ? 1 : 0);
            }

            stm.executeUpdate();

        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM CHOFERES WHERE ID_CHOFER=?";
        try (PreparedStatement stm = conn.prepareStatement(sql)) {
            stm.setLong(1, id);
            stm.executeUpdate();
        }

    }

    private Chofer getChofer(ResultSet rs) throws SQLException {
        Chofer chofer = new Chofer();
        chofer.setId(rs.getLong("ID_CHOFER"));
        chofer.setNombre(rs.getString("NOMBRE"));
        chofer.setApPaternos(rs.getString("APATERNO"));
        chofer.setApMaternos(rs.getString("AMATERNO"));
        chofer.setLicencias(rs.getString("LICENCIAS"));
        chofer.setTelefono(rs.getString("TELEFONO"));
        chofer.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO").toLocalDate());
        chofer.setDiponivilidad(rs.getBoolean("DISPONIBILIDAD"));
        return chofer;
    }

}
