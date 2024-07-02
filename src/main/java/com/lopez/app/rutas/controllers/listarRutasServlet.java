package com.lopez.app.rutas.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.lopez.app.rutas.models.Camion;
import com.lopez.app.rutas.models.Chofer;
import com.lopez.app.rutas.models.Direccion;
import com.lopez.app.rutas.models.Ruta;
import com.lopez.app.rutas.models.RutaCompleta;
import com.lopez.app.rutas.services.CamionesService;
import com.lopez.app.rutas.services.ChoferesService;
import com.lopez.app.rutas.services.DireccionService;
import com.lopez.app.rutas.services.IRutaService;
import com.lopez.app.rutas.services.IService;
import com.lopez.app.rutas.services.RutaService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/rutas/listar")
public class listarRutasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IRutaService serviceR = new RutaService(conn);
        List<Ruta> rutas = serviceR.lista();
        IService<Chofer> serviceCh = new ChoferesService(conn);
        List<Chofer> choferes = serviceCh.lista();
        IService<Direccion> serviceDir = new DireccionService(conn);
        List<Direccion> direcciones = serviceDir.lista();
        IService<Camion> serviceCam = new CamionesService(conn);

        List<RutaCompleta> rutasCompleta = new ArrayList<>();

        for (Ruta ruta : rutas) {
            RutaCompleta r = new RutaCompleta();
            r.setRuta(ruta);
            rutasCompleta.add(r);
            Optional<Camion> camiones = serviceCam.getByID(ruta.getCamionId());
            Optional<Chofer> choferesOpt = serviceCh.getByID(ruta.getChoferId());
            Optional<Direccion> direccionesOpt = serviceDir.getByID(ruta.getDireccionOriginalId());
            Optional<Direccion> direccionesOpt2 = serviceDir.getByID(ruta.getDireccionDestinoId());
            if (camiones.isPresent() && choferesOpt.isPresent() && direccionesOpt.isPresent()
                    && direccionesOpt2.isPresent()) {
                r.setCamion(camiones.get());
                r.setChofer(choferesOpt.get());
                r.setDireccionOrigen(direccionesOpt.get());
                r.setDireccionDestino(direccionesOpt2.get());
            }

        }

        req.setAttribute("rutasCompleta", rutasCompleta);

        req.setAttribute("rutas", rutas);

        getServletContext().getRequestDispatcher("/ListarRutas.jsp").forward(req,
                resp);
    }

}
