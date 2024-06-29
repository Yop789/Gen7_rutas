package com.lopez.app.rutas.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.lopez.app.rutas.models.Cargamento;
import com.lopez.app.rutas.repositories.CargamentoRepository;
import com.lopez.app.rutas.repositories.IRepository;

public class CargamentoService implements IService<Cargamento> {

    IRepository<Cargamento> repository;

    public CargamentoService(Connection conn) {
        this.repository = new CargamentoRepository(conn);
    }

    @Override
    public List<Cargamento> lista() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lista'");
    }

    @Override
    public Optional<Cargamento> getByID(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByID'");
    }

    @Override
    public void guardar(Cargamento t) {
        try {
            repository.guardar(t);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

}
