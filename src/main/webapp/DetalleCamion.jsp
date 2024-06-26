<%@ page contentType="text/html" pageEncoding="UTF-8" %> 
<%@ page import="java.util.*" %> 
<%@ page import="com.lopez.app.rutas.models.*" %> 

<% 
Camion camion = (Camion) request.getAttribute("camion"); 
String disponible = camion.getDisponibilidad() ? "Disponible" : "No disponible"; 
%>

<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Detalle del Camión</title>
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
          <div class="card-border">
            <div class="card-header">
              <h3><strong> Detalle del camión </strong></h3>
            </div>
          </div>

          <div class="card-body">
            <ul class="list-group">
                <li class="list-group-item"><strong>Matrícula: </strong><%= camion.getMatricula() %></li>
                <li class="list-group-item"><strong>Tipo de camión: </strong><%= camion.getTipoCamion() %></li>
                <li class="list-group-item"><strong>Modelo: </strong><%= camion.getModelo() %></li>
                <li class="list-group-item"><strong>Marca: </strong><%= camion.getMarca() %></li>
                <li class="list-group-item"><strong>Capacidad: </strong><%= camion.getCapacidad() %></li>
                <li class="list-group-item"><strong>Kilometraje: </strong><%= camion.getKilometros() %></li>
                <li class="list-group-item"><strong>Disponibilidad: </strong><%= disponible %></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
