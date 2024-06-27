package com.lopez.app.rutas.controllers;

import java.io.IOException;
import java.sql.Connection;

import com.lopez.app.rutas.services.IRutaService;
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

        req.setAttribute("camiones", service.listarCamiones());
        req.setAttribute("choferes", service.listarChoferes());
        getServletContext().getRequestDispatcher("/AltaRutas.jsp").forward(req, resp);
    }

}
