package com.lopez.app.rutas.services;

import com.lopez.app.rutas.models.Chofer;
import com.lopez.app.rutas.repositories.CoferesRepository;
import com.lopez.app.rutas.repositories.IRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ChoferesService implements IService<Chofer> {

    private IRepository<Chofer> choferesRepo;

    public ChoferesService(Connection conn) {
        choferesRepo = new CoferesRepository(conn);
    }

    @Override
    public List<Chofer> lista() {
        try {
            return choferesRepo.lista();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Chofer> getByID(Long id) {
        try {
            return choferesRepo.get(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Chofer chofer) {
        try {
            choferesRepo.guardar(chofer);

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {

    }
}
