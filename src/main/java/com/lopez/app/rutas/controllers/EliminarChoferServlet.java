package com.lopez.app.rutas.controllers;

import java.io.IOException;
import java.sql.Connection;
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
                service.eliminar(id);
                resp.sendRedirect(req.getContextPath() + "/choferes/listar");
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No se encontro el chofer");

            }

        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error es nulo se deve enviar como parametro en la url");
        }

    }
}
