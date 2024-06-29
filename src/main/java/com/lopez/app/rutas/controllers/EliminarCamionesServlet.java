package com.lopez.app.rutas.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.lopez.app.rutas.models.Camion;

import com.lopez.app.rutas.services.CamionesService;

import com.lopez.app.rutas.services.IService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/camiones/eliminar")
public class EliminarCamionesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        Map<String, String> errors = new HashMap<>();
        IService<Camion> service = new CamionesService(conn);
        long id;

        try {
            id = Long.parseLong(req.getParameter("id"));

        } catch (Exception e) {
            id = 0L;
        }

        Camion Camion = new Camion();

        if (id > 0) {
            Optional<Camion> optional = service.getByID(id);
            if (optional.isPresent()) {
                try {
                    service.eliminar(id);
                    getServletContext().getRequestDispatcher("/camiones/listar").forward(req, resp);
                } catch (Exception e) {
                    errors.put("error", "Error al eliminar el camin: el camion la se encuentra en la tabla de rutas");
                }

            } else {
                errors.put("error", "No se encontró el chofer con ID: " + id);

            }

        } else {
            errors.put("error", "ID inválido o no proporcionado en la URL");
        }

        req.setAttribute("errores", errors);
        getServletContext().getRequestDispatcher("/camiones/listar").forward(req, resp);
    }

}
