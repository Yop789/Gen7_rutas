package com.lopez.app.rutas.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.lopez.app.rutas.models.Chofer;
import com.lopez.app.rutas.services.ChoferesService;
import com.lopez.app.rutas.services.IService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/choferes/editar")
public class EdicionChoferServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("conn");
        IService<Chofer> service = new ChoferesService(con);

        String nombre = req.getParameter("nombre");
        String apPaternos = req.getParameter("apPaternos");
        String apMaternos = req.getParameter("apMaternos");
        String licencias = req.getParameter("licencias");
        String telefono = req.getParameter("telefono");
        String fechaNacimiento = req.getParameter("fechaNacimiento");
        LocalDate fecha;
        try {
            fecha = LocalDate.parse(fechaNacimiento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            fecha = null;
        }

        String checkbox[];
        checkbox = req.getParameterValues("diponivilidad");
        Boolean habilitar;
        if (checkbox != null) {
            habilitar = true;
        } else {
            habilitar = false;
        }
        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "El nombre es obligatorio");
        }

        if (apPaternos == null || apPaternos.isBlank()) {
            errores.put("apPaternos", "El apellido paterno es obligatorio");
        }

        if (apMaternos == null || apMaternos.isBlank()) {
            errores.put("apMaternos", "El apellido materno es obligatorio");
        }

        if (licencias == null || licencias.isBlank()) {
            errores.put("licencias", "Las licencias son obligatorias");
        }

        if (telefono == null || telefono.isBlank()) {
            errores.put("telefono", "El telefono es obligatorio");
        }

        if (fechaNacimiento == null || fechaNacimiento.isBlank()) {
            errores.put("fechaNacimiento", "La fecha de nacimiento es obligatoria");
        }

        Long id;
        id = Long.parseLong(req.getParameter("id"));
        Chofer chofer = new Chofer();
        chofer.setId(id);
        chofer.setNombre(nombre);
        chofer.setApPaternos(apPaternos);
        chofer.setApMaternos(apMaternos);
        chofer.setLicencias(licencias);
        chofer.setTelefono(telefono);
        chofer.setFechaNacimiento(fecha);
        chofer.setDiponivilidad(habilitar);
        if (errores.isEmpty()) {
            service.guardar(chofer);
            resp.sendRedirect(req.getContextPath() + "/choferes/listar");
        } else {
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/EdicionChofer.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");

        IService<Chofer> service = new ChoferesService(conn);
        long id;

        try {
            id = Long.parseLong(req.getParameter("id"));

        } catch (Exception e) {
            id = 0L;
        }

        Chofer chofer = new Chofer();

        if (id > 0) {
            Optional<Chofer> optional = service.getByID(id);

            if (optional.isPresent()) {
                chofer = optional.get();
                req.setAttribute("chofer", chofer);
                getServletContext().getRequestDispatcher("/EdicionChofer.jsp").forward(req, resp);

            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encontro el chofer");

            }

        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error es nulo se deve enviar como parametro en la url");
        }

    }
}
