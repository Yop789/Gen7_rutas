package com.lopez.app.rutas.controllers;

import com.lopez.app.rutas.models.Chofer;
import com.lopez.app.rutas.services.ChoferesService;
import com.lopez.app.rutas.services.IService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/choferes/alta")
public class AltaChoferServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/AltaChofer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("conn");
        IService<Chofer> service = new ChoferesService(con);

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

        String nombre = req.getParameter("nombre");
        String apPaternos = req.getParameter("apPaternos");
        String apMaternos = req.getParameter("apMaternos");
        String licencias = req.getParameter("licencias");
        String telefono = req.getParameter("telefono");
        String fechaNacimiento = req.getParameter("fechaNacimiento");
        LocalDate fecha;
        try {
            Date fechaInput = inputFormat.parse(fechaNacimiento);
            String formato = outputFormat.format(fechaInput);
            fecha = LocalDate.parse(formato, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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

        if (errores.isEmpty()) {
            Chofer chofer = new Chofer();
            chofer.setNombre(nombre);
            chofer.setApPaternos(apPaternos);
            chofer.setApMaternos(apMaternos);
            chofer.setLicencias(licencias);
            chofer.setTelefono(telefono);
            chofer.setFechaNacimiento(fecha);
            chofer.setDiponivilidad(habilitar);
            service.guardar(chofer);
            resp.sendRedirect(req.getContextPath() + "/choferes/listar");
        } else {
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/AltaChofer.jsp").forward(req, resp);
        }

    }
}
