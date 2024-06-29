package com.lopez.app.rutas.controllers;

import java.io.IOException;
import java.sql.Connection;
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

@WebServlet("/choferes/eliminar")
public class EliminarChoferServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        Map<String, String> errors = new HashMap<>();
        IService<Chofer> service = new ChoferesService(conn);
        long id;

        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (Exception e) {
            id = 0L;
        }

        if (id > 0) {
            Optional<Chofer> optional = service.getByID(id);
            if (optional.isPresent()) {
                try {
                    service.eliminar(id);
                    resp.sendRedirect(req.getContextPath() + "/choferes/listar");
                    return;
                } catch (Exception e) {
                    errors.put("error", "Error al eliminar el chofer: el chofer la se encuentra en la tabla de rutas");
                }
            } else {
                errors.put("error", "No se encontró el chofer con ID: " + id);
            }
        } else {
            errors.put("error", "ID inválido o no proporcionado en la URL");
        }

        req.setAttribute("errores", errors);
        req.getRequestDispatcher("/choferes/listar").forward(req, resp);

    }
}
