package com.lopez.app.rutas.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lopez.app.rutas.models.Camion;
import com.lopez.app.rutas.models.enums.Marcas;
import com.lopez.app.rutas.models.enums.Tipos;
import com.lopez.app.rutas.services.CamionesService;
import com.lopez.app.rutas.services.IService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/camiones/alta")
public class AltaCamionesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Crear una lista de años desde el año actual + 1 hasta el año actual - 20
        cargarDatosFormulario(req);
        List<Integer> anios = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int anioActual = calendar.get(Calendar.YEAR) + 1;

        for (int i = 0; i < 22; i++) {
            anios.add(anioActual - i);
        }

        req.setAttribute("anios", anios);
        getServletContext().getRequestDispatcher("/AltaCamion.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) req.getAttribute("conn");
        IService<Camion> service = new CamionesService(con);

        String matricula = req.getParameter("matricula");
        String tipo = req.getParameter("tipoCamion");
        String modelo = req.getParameter("modeloCamion");
        String marca = req.getParameter("marcaCamion");
        String capacidad = req.getParameter("capacidad");
        String kilometros = req.getParameter("kilometros");
        String[] checkbox = req.getParameterValues("disponibilidad");

        Boolean habilitar = checkbox != null;

        Map<String, String> errores = new HashMap<>();

        if (matricula == null || matricula.isBlank()) {
            errores.put("matricula", "La matricula es obligatoria");
        }
        try {
            if (tipo == null || tipo.isBlank() || Tipos.valueOf(tipo) == null) {
                errores.put("tipoCamion", "El tipo de camion es obligatorio");
            }
        } catch (Exception e) {
            errores.put("tipoCamion", "El tipo de camion es obligatorio");
        }

        try {
            if (modelo == null || modelo.isBlank() || Integer.parseInt(modelo) == 0) {
                errores.put("modeloCamion", "El modelo es obligatorio");
            }
        } catch (Exception e) {
            errores.put("modeloCamion", "El modelo es obligatorio");
        }

        try {
            if (marca == null || marca.isBlank() || Marcas.valueOf(marca) == null) {
                errores.put("marcaCamion", "La marca es obligatoria");
            }
        } catch (Exception e) {
            errores.put("marcaCamion", "La marca es obligatoria");
        }

        if (capacidad == null || capacidad.isBlank()) {
            errores.put("capacidad", "La capacidad es obligatoria");
        }
        if (kilometros == null || kilometros.isBlank()) {
            errores.put("kilometros", "Los kilometros son obligatorios");
        }

        if (errores.isEmpty()) {
            Camion camion = new Camion();
            camion.setMatricula(matricula);
            camion.setTipoCamion(Tipos.valueOf(tipo));
            camion.setModelo(Integer.parseInt(modelo));
            camion.setMarca(Marcas.valueOf(marca));
            camion.setCapacidad(Integer.parseInt(capacidad));
            camion.setKilometros(Double.parseDouble(kilometros));
            camion.setDisponibilidad(habilitar);

            service.guardar(camion);
            resp.sendRedirect(req.getContextPath() + "/camiones/listar");
        } else {
            req.setAttribute("errores", errores);
            cargarDatosFormulario(req);
            getServletContext().getRequestDispatcher("/AltaCamion.jsp").forward(req, resp);
        }
    }

    private void cargarDatosFormulario(HttpServletRequest req) {
        List<String> tiposCamiones = new ArrayList<>();
        for (Tipos tipos : Tipos.values()) {
            tiposCamiones.add(tipos.toString());
        }

        req.setAttribute("tipos", tiposCamiones);

        List<String> marcas = new ArrayList<>();
        for (Marcas tipo : Marcas.values()) {
            marcas.add(tipo.toString());
        }

        req.setAttribute("marcas", marcas);

        List<Integer> anios = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int anioActual = calendar.get(Calendar.YEAR) + 1;

        for (int i = 0; i < 22; i++) {
            anios.add(anioActual - i);
        }

        req.setAttribute("anios", anios);
    }

}
