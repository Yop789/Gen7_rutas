package com.lopez.app.rutas.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import com.lopez.app.rutas.models.Camion;
import com.lopez.app.rutas.services.CamionesService;
import com.lopez.app.rutas.services.IService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/camiones/listar")
public class ListarCamionesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        IService<Camion> services = new CamionesService(conn);

        List<Camion> camiones = services.lista();

        req.setAttribute("camiones", camiones);
        getServletContext().getRequestDispatcher("/ListaCamiones.jsp").forward(req, resp);
    }

}
