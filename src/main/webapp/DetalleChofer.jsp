<%@ page contentType="text/html" pageEncoding="UTF-8" %> 
<%@ page import="java.util.*" %> 
<%@ page import="com.lopez.app.rutas.models.*" %> 
<%@ page import="java.time.format.*" %> 
<% 
Chofer chofer = (Chofer) request.getAttribute("chofer"); 
String fecha = chofer.getFechaNacimiento() != null ? chofer.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : ""; 
Boolean estado = chofer.getDiponivilidad(); 
String disponible = chofer.getDiponivilidad() ? "Disponible" : "No disponible"; 
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Detalle de Chofer</title>
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
              <h3><strong> Detalle de chofer </strong></h3>
            </div>
          </div>

          <div class="card-body">
            <ul class="list-group">
                <li class="list-group-item"> <strong> Nombre: </strong> <%= chofer.getNombre() %> </li>
                <li class="list-group-item"><strong>Ap. Paterno: </strong><%= chofer.getApPaternos() %></li>
                <li class="list-group-item"><strong>Ap. Materno: </strong><%= chofer.getApMaternos() %></li>
                <li class="list-group-item"><strong>Licencia: </strong><%= chofer.getLicencias() %></li>
                <li class="list-group-item"><strong>Teléfono: </strong><%= chofer.getTelefono() %></li>
                <li class="list-group-item"><strong>Fecha Nacimiento: </strong><%= fecha %></li>
                <li class="list-group-item"><strong>Disponibilidad: </strong><%= disponible %></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
