package com.lopez.app.rutas.services;

import com.lopez.app.rutas.models.Camion;
import com.lopez.app.rutas.models.Chofer;
import com.lopez.app.rutas.models.Ruta;

import java.util.List;

public interface IRutaService extends IService<Ruta> {

    List<Camion> listarCamiones();

    List<Chofer> listarChoferes();

    Long guardarReturId(Ruta ruta);
}
