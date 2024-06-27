package com.lopez.app.rutas.repositories;

import java.sql.SQLException;

import com.lopez.app.rutas.models.Ruta;

public interface IRutasRepository extends IRepository<Ruta> {
    Long guardarReturID(Ruta ruta) throws SQLException;
}
