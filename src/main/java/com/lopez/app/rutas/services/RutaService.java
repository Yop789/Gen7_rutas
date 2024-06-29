package com.lopez.app.rutas.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.lopez.app.rutas.models.Camion;
import com.lopez.app.rutas.models.Chofer;
import com.lopez.app.rutas.models.Ruta;
import com.lopez.app.rutas.repositories.CamionesRepository;
import com.lopez.app.rutas.repositories.CoferesRepository;
import com.lopez.app.rutas.repositories.IRepository;
import com.lopez.app.rutas.repositories.IRutasRepository;
import com.lopez.app.rutas.repositories.RutaRepositoey;

public class RutaService implements IRutaService {
    private IRepository<Chofer> choferesRepo;
    private IRepository<Camion> camionesRepo;
    private IRutasRepository rutasRepo;

    public RutaService(Connection conn) {
        this.choferesRepo = new CoferesRepository(conn);
        this.camionesRepo = new CamionesRepository(conn);
        this.rutasRepo = new RutaRepositoey(conn);

    }

    @Override
    public List<Ruta> lista() {
        try {
            return rutasRepo.lista();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }

    }

    @Override
    public Optional<Ruta> getByID(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByID'");
    }

    @Override
    public void guardar(Ruta t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardar'");
    }

    @Override
    public void eliminar(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

    @Override
    public List<Camion> listarCamiones() {
        try {
            return camionesRepo.lista();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Chofer> listarChoferes() {
        try {
            return choferesRepo.lista();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Long guardarReturId(Ruta ruta) {
        try {
            return rutasRepo.guardarReturID(ruta);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

}
