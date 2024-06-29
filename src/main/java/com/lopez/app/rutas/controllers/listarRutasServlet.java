package com.lopez.app.rutas.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import com.lopez.app.rutas.models.Ruta;
import com.lopez.app.rutas.services.IRutaService;
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
        IRutaService service = new RutaService(conn);
        List<Ruta> rutas = service.lista();

        req.setAttribute("rutas", rutas);

        getServletContext().getRequestDispatcher("/ListarRutas.jsp").forward(req,
                resp);
    }

}
