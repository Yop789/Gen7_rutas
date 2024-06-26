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
import java.util.List;

@WebServlet ("/choferes/listar")
public class ListaChoferesServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn") ;
        IService<Chofer> services = new ChoferesService(conn);
        List<Chofer> choferes= services.lista() ;
        /*for (Chofer c : chofers){
            resp.getWriter().println("<h1>"+c.getId() + "->"
            +c.getNombre() +"->"+c.getApMaternos() + "</h1>");
        }*/

        req.setAttribute("choferes", choferes);
        getServletContext().getRequestDispatcher("/ListaChoferes.jsp").forward(req,resp);
    }
}
