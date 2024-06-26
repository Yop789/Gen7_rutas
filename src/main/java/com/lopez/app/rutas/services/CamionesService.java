package com.lopez.app.rutas.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.lopez.app.rutas.models.Camion;
import com.lopez.app.rutas.repositories.CamionesRepository;
import com.lopez.app.rutas.repositories.IRepository;

public class CamionesService implements IService<Camion> {
    private IRepository<Camion> camionesRepo;

    public CamionesService(Connection conn) {
        camionesRepo = new CamionesRepository(conn);
    }

    @Override
    public void eliminar(Long id) {
        try {
            camionesRepo.eliminar(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }

    }

    @Override
    public void guardar(Camion t) {
        try {
            camionesRepo.guardar(t);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Camion> lista() {
        try {
            return camionesRepo.lista();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Camion> getByID(Long id) {
        try {
            return Optional.ofNullable(camionesRepo.get(id));
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

}
