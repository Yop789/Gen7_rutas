<%@ page contentType="text/html" pageEncoding="UTF-8" %> 
<%@ page import="java.util.*" %>
<%@ page import="com.lopez.app.rutas.models.*" %> 
<%@ page import="com.lopez.app.rutas.models.enums.*" %>

<% 
Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
List<Integer> modelos = (List<Integer>) request.getAttribute("anios");
Camion camion = (Camion) request.getAttribute("camion");
Boolean estado = camion.getDisponibilidad();
String disponible = camion.getDisponibilidad() ? "checked" : "";
Integer modelo = (Integer) camion.getModelo();
%>

<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Formulario alta camiones</title>
    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
    />
    <script
      src="https://code.jquery.com/jquery-2.2.4.min.js"
      integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
      crossorigin="anonymous"
    ></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  </head>
  <body>
    <%@ include file="Header.jsp" %>

    <div class="container">
      <div class="row">
        <div class="col-12">
          <h2>Formulario alta camiones</h2>
        </div>
      </div>

      <br>
      <% if (errores != null && !errores.isEmpty()) { %>
        <ul class="alert alert-danger mx-5 px-5">
          <% for (String e : errores.values()) { %>
            <li><%= e %></li>
          <% } %>
        </ul>
      <% } %>

      <div class="row">
        <form action="<%= request.getContextPath() %>/camiones/alta" method="post">
          <div class="col-md-12">
            <div class="form-group">
              <label for="matricula">Matrícula</label>
              <input
                type="text"
                id="matricula"
                name="matricula"
                class="form-control"
                value="<%= camion.getMatricula() != null ? camion.getMatricula() : "" %>"
              />
              <% if (errores != null && errores.containsKey("matricula")) { %>
                <span class="text-danger"><%= errores.get("matricula") %></span>
              <% } %>
            </div>

            <div class="form-group">
              <label for="tipoCamion">Tipo de Camión</label>
              <select
                name="tipoCamion"
                class="form-control"
                aria-label="Default select example"
              >
                <option value="">---selecciona un tipo---</option>
                <% for (Tipos c : Tipos.values()) { %>
                  <option value="<%= c %>" <%= c.equals(camion.getTipoCamion()) ? "selected" : "" %>><%= c %></option>
                <% } %>
              </select>
              <% if (errores != null && errores.containsKey("tipoCamion")) { %>
                <span class="text-danger"><%= errores.get("tipoCamion") %></span>
              <% } %>
            </div>
            
            <div class="form-group">
              <label for="modeloCamion">Modelo de Camión</label>
              <select
                name="modeloCamion"
                class="form-control"
                aria-label="Default select example"
              >
                <option value="">---selecciona un modelo---</option>
                <% for (Integer m : modelos) { %>
                  <option value="<%= m %>" <%= m.equals(modelo) ? "selected" : "" %>><%= m %></option>
                <% } %>
              </select>
              <% if (errores != null && errores.containsKey("modeloCamion")) { %>
                <span class="text-danger"><%= errores.get("modeloCamion") %></span>
              <% } %>
            </div>

            <div class="form-group">
              <label for="marcaCamion">Marca de Camión</label>
              <select
                name="marcaCamion"
                class="form-control"
                aria-label="Default select example"
              >
                <option value="">---selecciona una marca---</option>
                <% for (Marcas m : Marcas.values()) { %>
                  <option value="<%= m %>" <%= m.equals(camion.getMarca()) ? "selected" : "" %>><%= m %></option>
                <% } %>
              </select>
              <% if (errores != null && errores.containsKey("marcaCamion")) { %>
                <span class="text-danger"><%= errores.get("marcaCamion") %></span>
              <% } %>
            </div>

            <div class="form-group">
              <label for="capacidad">Capacidad</label>
              <input
                type="number"
                id="capacidad"
                name="capacidad"
                class="form-control"
                value="<%= camion.getCapacidad() != null ? camion.getCapacidad() : "" %>"
              />
              <% if (errores != null && errores.containsKey("capacidad")) { %>
                <span class="text-danger"><%= errores.get("capacidad") %></span>
              <% } %>
            </div>

            <div class="form-group">
              <label for="kilometros">Kilómetros</label>
              <input
                type="text"
                id="kilometros"
                name="kilometros"
                class="form-control"
                value="<%= camion.getKilometros() != null ? camion.getKilometros() : "" %>"
              />
              <% if (errores != null && errores.containsKey("kilometros")) { %>
                <span class="text-danger"><%= errores.get("kilometros") %></span>
              <% } %>
            </div>

            <div class="form-group">
              <label for="disponibilidad">Disponibilidad</label>
              <input
                type="checkbox"
                name="disponibilidad"
                id="disponibilidad"
                class="form-check-input"
                value="true"
                <%= camion.getDisponibilidad() ? "checked" : "" %>
              />
            </div>

            <div class="form-group">
              <button type="submit" class="btn btn-success">Guardar</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </body>
</html>
