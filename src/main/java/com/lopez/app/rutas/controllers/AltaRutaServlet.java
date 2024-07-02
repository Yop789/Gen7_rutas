package com.lopez.app.rutas.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.lopez.app.rutas.models.Cargamento;
import com.lopez.app.rutas.models.Direccion;
import com.lopez.app.rutas.models.Ruta;
import com.lopez.app.rutas.services.CargamentoService;
import com.lopez.app.rutas.services.DireccionService;
import com.lopez.app.rutas.services.IRutaService;
import com.lopez.app.rutas.services.IService;
import com.lopez.app.rutas.services.RutaService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/rutas/alta")
public class AltaRutaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        IRutaService service = new RutaService(conn);
        IService<Direccion> direccionesRepo = new DireccionService(conn);

        req.setAttribute("direcciones", direccionesRepo.lista());
        req.setAttribute("camiones", service.listarCamiones());
        req.setAttribute("choferes", service.listarChoferes());
        getServletContext().getRequestDispatcher("/AltaRutas.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        Map<String, String> errores = new HashMap<>();

        IRutaService service = new RutaService(conn);
        IService<Cargamento> cargmento = new CargamentoService(conn);

        Long cliente = Long.parseLong(req.getParameter("cliente"));
        Long idDireOrigen = Long.parseLong(req.getParameter("origen"));
        String fSalida = req.getParameter("fsalida");
        Float distancia = Float.parseFloat(req.getParameter("distancia"));
        Long camion = Long.parseLong(req.getParameter("camion"));
        Long idDirecDestino = Long.parseLong(req.getParameter("destino"));
        String fLlegada = req.getParameter("Fllegada");

        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

            Date dateSalida = inputFormat.parse(fSalida);
            String fechaSalidaFormateada = outputFormat.format(dateSalida);
            LocalDateTime fechaFSalida = LocalDateTime.parse(fechaSalidaFormateada,
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

            Date dateLlegada = inputFormat.parse(fLlegada);
            String fechaLlegadaFormateada = outputFormat.format(dateLlegada);
            LocalDateTime fechaFLlegada = LocalDateTime.parse(fechaLlegadaFormateada,
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

            Ruta ruta = new Ruta();
            ruta.setCamionId(camion);
            ruta.setChoferId(cliente);
            ruta.setDireccionOriginalId(idDireOrigen);
            ruta.setDireccionDestinoId(idDirecDestino);
            ruta.setDiatancio(distancia);
            ruta.setFechaSalida(fechaFSalida);
            ruta.setFechaLlegadaEstimada(fechaFLlegada);
            ruta.setFechaLlegadaReal(fechaFLlegada);
            ruta.setaTiempo(1);
            if (cliente == null || cliente == 0) {
                errores.put("cliente", "Debe seleccionar un cliente");
            }

            if (idDireOrigen == null || idDireOrigen == 0) {
                errores.put("origen", "Debe seleccionar una dirección");
            }

            if (idDirecDestino == null || idDirecDestino == 0) {
                errores.put("destino", "Debe seleccionar una dirección");
            }

            if (camion == null || camion == 0) {
                errores.put("camion", "Debe seleccionar un camion");
            }

            if (req.getParameter("cargamento[0][desc]") == null) {
                errores.put("cargamento", "Debe agregar un cargamento");
            }
            if (req.getParameter("cargamento[0][peso]") == null) {
                errores.put("cargamento", "Debe agregar un cargamento");
            }

            if (errores.isEmpty()) {
                Long id = service.guardarReturId(ruta);

                int i = 0;
                while (req.getParameter("cargamento[" + i + "][desc]") != null) {
                    String descripcion = req.getParameter("cargamento[" + i + "][desc]");
                    Float peso = Float.parseFloat(req.getParameter("cargamento[" + i + "][peso]"));
                    Cargamento cargamento = new Cargamento();
                    cargamento.setIdRuta(id);
                    cargamento.setDescripcion(descripcion);
                    cargamento.setPeso(peso);
                    cargamento.setEstatus(1);
                    cargmento.guardar(cargamento);
                    i++;
                }

            } else {
                req.setAttribute("errores", errores);

            }
        } catch (Exception e) {
            errores.put("error", e.getMessage());
            req.setAttribute("errores", errores);

        }

    }

}
