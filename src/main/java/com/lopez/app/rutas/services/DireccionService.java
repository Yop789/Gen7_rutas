package com.lopez.app.rutas.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.lopez.app.rutas.models.Direccion;
import com.lopez.app.rutas.repositories.DireccionRepository;
import com.lopez.app.rutas.repositories.IRepository;

public class DireccionService implements IService<Direccion> {
    IRepository<Direccion> direcionRepo;

    public DireccionService(Connection conn) {
        this.direcionRepo = new DireccionRepository(conn);
    }

    @Override
    public List<Direccion> lista() {
        try {
            return direcionRepo.lista();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Direccion> getByID(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByID'");
    }

    @Override
    public void guardar(Direccion t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public void eliminar(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

}
